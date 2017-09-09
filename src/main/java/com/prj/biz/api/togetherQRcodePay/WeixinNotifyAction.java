package com.prj.biz.api.togetherQRcodePay;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.payrecord.PayRecord;
import com.prj.biz.service.RebateCalculation.RebateCalculationService;
import com.prj.biz.service.bankwithdrawal.BankWithdrawalService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.order.OrderService;
import com.prj.biz.service.payrecord.PayRecordService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.service.transferrecord.TransferRecordService;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.pay.HttpClientConnectionManager;
import com.prj.utils.pay.PayCommonUtil;
import com.prj.utils.pay.WXRequestUtil;
import com.prj.utils.pay.WxpayConfig;

/**
 * @author: Fengc
 * @date:2017-8-23 下午2:44:05
 * @version :0.0.1
 * @dis:微信支付回调
 */

@Controller
@RequestMapping("/api")
public class WeixinNotifyAction {

	public static DefaultHttpClient httpclient;

	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager
				.getSSLInstance(httpclient);
	}

	@Autowired
	ConsumersAccountService accountService;

	@Autowired
	PayRecordService recordService;

	@Autowired
	OrderService orderService;

	@Autowired
	RebateCalculationService calculationService;

	@Autowired
	ConsumersService consumersService;

	@Autowired
	BusinessInformationService businessInformationService;

	@Autowired
	SysUserService sysUserService;

	@Autowired
	BankWithdrawalService bankWithdrawalService;

	@Autowired
	TransferRecordService transferRecordService;

	/**
	 * 微信获取OPENID
	 * 
	 * @param code
	 * @param statue
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/getWeixinOpenid" })
	public ModelAndView getWeixinOpenid(String code, String statue,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		String jsonStr = "";
		String openid = "";
		HttpPost httpost = HttpClientConnectionManager
				.getPostMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid='"
						+ WxpayConfig.APPID
						+ "'&redirect_uri=http://hilink.chengguokj.com/XA/weixin&secret='"
						+ WxpayConfig.SECRET
						+ "'&code="
						+ code
						+ "&grant_type=authorization_code");
		try {
			HttpResponse responses = httpclient.execute(httpost);
			jsonStr = EntityUtils.toString(responses.getEntity(), "UTF-8");
			openid = jsonStr.substring(jsonStr.indexOf("openid\":\"") + 9,
					jsonStr.indexOf("\",\"scope"));
			System.out.println("openid>>>>>>>>>>>>>>>" + openid);
			String access_token = jsonStr.substring(
					jsonStr.indexOf("access_token\":\"") + 15,
					jsonStr.indexOf("\",\"expires_in"));
			System.out.println("在getOpenId中获取token:--->" + access_token);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		model.put("openid", openid);
		return new ModelAndView("/openId/weixinshowopenid", model);
	}

	@RequestMapping(value = { "/wxpayNotify" }, method = { RequestMethod.POST })
	@ResponseBody
	public String notify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("=========进入微信扫码回调========");
		Map<String, String> return_data = new HashMap<String, String>();

		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		String resultXml = new String(outSteam.toByteArray(), "utf-8");
		@SuppressWarnings("unchecked")
		Map<String, String> params = PayCommonUtil.doXMLParse(resultXml);

		for (String key : params.keySet()) {
			System.out.println("key = " + key + "; value = " + params.get(key));
		}

		outSteam.close();
		inStream.close();

		String out_trade_no = params.get("out_trade_no").toString();
		if (PayCommonUtil.isTenpaySign(params)
				&& "SUCCESS".equals(params.get("return_code"))
				&& "SUCCESS".equals(params.get("result_code"))) {
			/**
			 * 校验微信掃碼回调校验订单号
			 */

			PayRecord payRecord = new PayRecord();
			payRecord.setOrderno(out_trade_no);
			List<PayRecord> payRecords = recordService.doGetList(payRecord);

			if (payRecords.size() != 0) {
				PayRecord payRecord1 = payRecords.get(0);
				String alinum = params.get("openid").toString();
				int money = Integer.valueOf(params.get("total_fee"))*10;
				payRecord1.setAccount(alinum);
				recordService.doModById(payRecord1);
				Order order = new Order();
				order.setOrderNumber(out_trade_no);
				order.setBusinessId(payRecords.get(0).getBusinessid());
				Consumers consumers = consumersService.doGetById(payRecords
						.get(0).getBusinessid());
				if (consumers != null) {
					order.setAgentId(consumers.getAgentId());
				} else {
					BusinessInformation businessInformation = businessInformationService
							.doGetById(payRecords.get(0).getBusinessid());
					if (businessInformation != null) {
						order.setAgentId(businessInformation.getHigherAgentId());
					}
				}
				order.setMoney(String.valueOf(money));
				order.setStatus("3");
				order.setTransactionMode("1");
				order.setCreatetime(UfdmDateUtil.getCurrentTime());
				/**
				 * 锁定分成
				 */
				ConsumersAccount account = new ConsumersAccount();
				account.setUserAccount(alinum);
				List<ConsumersAccount> cas = accountService.doGetList(account);

				if (cas.size() != 0) {
					System.out.println("===被锁定==");
					// 查询订单号
					order.setPurchaserId(cas.get(0).getConsumersId());
					order.setConsumerAccount(cas.get(0).getId());
					orderService.doSave(order);

					// 【已经被锁定】插入对应的订单记录表，并分成
					if ("0".equals(cas.get(0).getIsLocalState())) {
						System.out.println("===被锁定==IsLocalState()=0");
						calculationService.lockedDraw(order.getId(), alinum,
								payRecords.get(0).getBusinessid(), cas.get(0)
										.getConsumersId(), "2");
					}
					if ("1".equals(cas.get(0).getIsLocalState())) {
						System.out.println("===被锁定==IsLocalState()=1");
						calculationService.pumpingCalculation(order.getId(),
								cas.get(0).getId(), payRecords.get(0)
										.getBusinessid());
					}

				} else {
					System.out.println("===未被锁定==");
					// 【未被锁定】插入支付者账号表并插入对应记录表，分成

					orderService.doSave(order);

					calculationService.lockedDraw(order.getId(), alinum,
							payRecords.get(0).getBusinessid(), "", "2");

				}
				calculationService.transferRecord(order);
			} else {
				/**
				 * 交易失败 不处理
				 */
			}

			System.out.print("success");
			return_data.put("return_code", "SUCCESS");
			return_data.put("return_msg", "OK");
			return WXRequestUtil.GetMapToXML(return_data);
		} else {
			// 支付失败
			return_data.put("return_code", "FAIL");
			return_data.put("return_msg", "签名错误");
			return WXRequestUtil.GetMapToXML(return_data);
		}
	}
}

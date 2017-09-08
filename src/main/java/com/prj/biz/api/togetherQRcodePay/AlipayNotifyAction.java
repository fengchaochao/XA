package com.prj.biz.api.togetherQRcodePay;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.udf.UDFFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.model.Transfer;
import com.prj.biz.bean.bankwithdrawal.BankWithdrawal;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.payrecord.PayRecord;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transactionrecords.TransactionRecords;
import com.prj.biz.bean.transferrecord.TransferRecord;
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
import com.prj.utils.pay.AlipayNotify;
import com.prj.utils.ping.TransferExample;

/**
 * @author: Fengc
 * @date:2017-8-22 下午4:50:57
 * @version :0.0.1
 * @dis:
 */

@Controller
@RequestMapping("/api")
public class AlipayNotifyAction {

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
	 * 扫码回调
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = { "/alipayNotify" }, method = { RequestMethod.POST })
	public void getPayNotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		PrintWriter out = response.getWriter();

		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
			System.out.println(name + "======>>" + valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 异步通知ID
		String notify_id = request.getParameter("notify_id");

		// sign
		String sign = request.getParameter("sign");

		if (notify_id != "" && notify_id != null) {
			if (AlipayNotify.verifyResponse(notify_id).equals("true"))// 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				System.out.println("verifyResponse---校验通过");
				if (AlipayNotify.getSignVeryfy(params, sign))// 使用支付宝公钥验签
				{
					System.out.println("getSignVeryfy---校验通过");
					if (trade_status.equals("TRADE_FINISHED")|| trade_status.equals("TRADE_SUCCESS")) {
						/**
						 * 校验支付宝回调校验订单号
						 */
						PayRecord payRecord = new PayRecord();
						payRecord.setOrderno(out_trade_no);
						List<PayRecord> payRecords = recordService
								.doGetList(payRecord);

						if (payRecords.size() != 0) {
							PayRecord payRecord1 = payRecords.get(0);
							String alinum = params.get("buyer_id").toString();
							String accounts = params.get("buyer_logon_id").toString();
							String money = params.get("total_amount").toString();
							payRecord1.setAccount(accounts);
							recordService.doModById(payRecord1);
							Order order = new Order();
							order.setOrderNumber(out_trade_no);
							order.setBusinessId(payRecords.get(0).getBusinessid());
							Consumers consumers=consumersService.doGetById(payRecords.get(0).getBusinessid());
							if(consumers!=null){
								order.setAgentId(consumers.getAgentId());
							}else{
								BusinessInformation businessInformation=businessInformationService.doGetById(payRecords.get(0).getBusinessid());
								if(businessInformation!=null){
									order.setAgentId(businessInformation.getHigherAgentId());
								}
							}
							order.setMoney(money);
							order.setStatus("3");
							order.setTransactionMode("1");
							order.setCreatetime(UfdmDateUtil.getCurrentTime());
							/**
							 * 锁定分成
							 */
							ConsumersAccount account = new ConsumersAccount();
							account.setUserAccount(accounts);
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
									calculationService.lockedDraw(order.getId(), accounts, payRecords.get(0).getBusinessid(),cas.get(0).getConsumersId(), "2");
								}
								if ("1".equals(cas.get(0).getIsLocalState())) {
									System.out.println("===被锁定==IsLocalState()=1");
									calculationService.pumpingCalculation(order.getId(), cas.get(0).getId(),payRecords.get(0).getBusinessid());
								}
							} else {
								System.out.println("===未被锁定==");
								// 【未被锁定】插入支付者账号表并插入对应记录表，分成
								orderService.doSave(order);
								calculationService.lockedDraw(order.getId(),accounts, payRecords.get(0).getBusinessid(), "", "2");

							}							
							calculationService.transferRecord(order);
						} else {
							/**
							 * 交易失败 不处理
							 */
						}					

					}
					System.out.println("===return Success====");
					out.write("success");
				} else// 验证签名失败
				{
					System.out.println("===return sign fail====");
					out.write("fail");
				}
			} else// 验证是否来自支付宝的通知失败
			{
				System.out.println("===return response fail====");
				out.write("fail");
			}
		} else {
			System.out.println("===return no notify message====");
			out.write("fail");
		}
	}
}

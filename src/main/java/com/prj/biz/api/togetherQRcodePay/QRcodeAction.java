package com.prj.biz.api.togetherQRcodePay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pingplusplus.model.Charge;
import com.prj.biz.bean.bankwithdrawal.BankWithdrawal;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.payrecord.PayRecord;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.bankwithdrawal.BankWithdrawalService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.payrecord.PayRecordService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.core.bean.resp.RespBean;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.pay.Alipay;
import com.prj.utils.pay.HttpClientConnectionManager;
import com.prj.utils.pay.JsapiTicketUtil;
import com.prj.utils.pay.WxpayConfig;
import com.prj.utils.ping.ChargeExample;

/**
 * @author: Fengc
 * @date:2017-7-14 上午8:43:06
 * @version :0.0.1
 * @dis: 二码合一支付绑定Action
 */
@Controller
@RequestMapping("/api")
public class QRcodeAction {

	public static DefaultHttpClient httpclient;

	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager
				.getSSLInstance(httpclient);
	}

	@Autowired
	PayRecordService recordService;

	@Autowired
	private BusinessInformationService businessInformationService;

	@Autowired
	private ConsumersService consumersService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private BankWithdrawalService bankWithdrawalService;

	/**
	 * 进入H5支付页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goH5Pay")
	public ModelAndView goH5Pay(HttpServletRequest request,
			HttpServletResponse response, String storeId) throws Exception {

		ModelAndView mv = new ModelAndView("/QRcodePay/receiving");
		String flag = storeId.substring(storeId.indexOf("-") + 1,
				storeId.length());
		SysUser sysUser = new SysUser();

		if ("S".equals(flag)) {
			BusinessInformation businessInformation = businessInformationService
					.doGetById(storeId.substring(0, storeId.indexOf("-")));
			mv.addObject("storeName",
					"易聚客平台-" + businessInformation.getVendorName());
			sysUser.setMerchantsId(businessInformation.getId());
			List<SysUser> sysUsers = sysUserService.doGetList(sysUser);
			if (sysUsers.size() > 0) {
				BankWithdrawal bankWithdrawal=new BankWithdrawal();
				bankWithdrawal.setUserId(sysUsers.get(0).getId());
				bankWithdrawal.setAccountType("2");
				List<BankWithdrawal> bankWithdrawals=bankWithdrawalService.doGetList(bankWithdrawal);
				String msg="";
				if(bankWithdrawals.size()>0){
					msg="1";
				}
				mv.addObject("personPhoto", sysUsers.get(0).getPersonPhoto());
				mv.addObject("msg", msg);
			}
		}
		if ("X".equals(flag)) {
			Consumers consumers = consumersService.doGetById(storeId.substring(0, storeId.indexOf("-")));
			mv.addObject("storeName", "易聚客平台-" + consumers.getNickName());
			sysUser.setMerchantsId(consumers.getId());
			List<SysUser> sysUsers = sysUserService.doGetList(sysUser);
			if (sysUsers.size() > 0) {
				mv.addObject("personPhoto", sysUsers.get(0).getPersonPhoto());
			}
		}
		mv.addObject("storeId", storeId);
		return mv;

	}

	/**
	 * 微信回调
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hdWeixin")
	public ModelAndView hdWeixin(HttpServletRequest request,
			HttpServletResponse response, String code, String state)
			throws Exception {
		System.out.println("------进入微信回调-------");
		System.out.println("------code------>" + code);
		System.out.println("------state------>" + state);
		System.out.println("------进入微信回调-------");
		ModelAndView mv = new ModelAndView("/QRcodePay/receiving1");
		// 通过截取state获得店铺信息
		String flag = state.substring(state.indexOf("-") + 1, state.length());
		SysUser sysUser = new SysUser();
		if ("S".equals(flag)) {
			BusinessInformation businessInformation = businessInformationService
					.doGetById(state.substring(0, state.indexOf("-")));
			mv.addObject("storeName",
					"易聚客平台-" + businessInformation.getVendorName());
			sysUser.setMerchantsId(businessInformation.getId());
			List<SysUser> sysUsers = sysUserService.doGetList(sysUser);
			if (sysUsers.size() > 0) {
				
				BankWithdrawal bankWithdrawal=new BankWithdrawal();
				bankWithdrawal.setUserId(sysUsers.get(0).getId());
				bankWithdrawal.setAccountType("2");
				String msg="";
				List<BankWithdrawal> bankWithdrawals=bankWithdrawalService.doGetList(bankWithdrawal);
				if(bankWithdrawals.size()>0){
					msg="1";
				}
				mv.addObject("personPhoto", sysUsers.get(0).getPersonPhoto());
				mv.addObject("msg", msg);
			}
		}
		if ("X".equals(flag)) {
			Consumers consumers = consumersService.doGetById(state.substring(0,
					state.indexOf("-")));
			mv.addObject("storeName", "易聚客平台-" + consumers.getNickName());
			sysUser.setMerchantsId(consumers.getId());
			List<SysUser> sysUsers = sysUserService.doGetList(sysUser);
			if (sysUsers.size() > 0) {
				mv.addObject("personPhoto", sysUsers.get(0).getPersonPhoto());
			}
		}

		mv.addObject("storeId", state);
		mv.addObject("code", code);
		return mv;
	}

	@RequestMapping(value = "/doWeixinPay")
	public ModelAndView doWeixinPay(HttpServletRequest request,
			HttpServletResponse response, String code, String storeId,
			String amount) throws Exception {
		System.out.println("======点击微信支付按钮之后======");
		System.out.println("====code==->" + code);
		System.out.println("====storeId==->" + storeId);
		System.out.println("====amount==->" + amount);
		System.out.println("======点击微信支付按钮之后======");
		BigDecimal b = new BigDecimal(Double.parseDouble(amount) * 100);
		
		PayRecord payRecord = new PayRecord();
		/*
		 * 点击微信支付按钮
		 */
		ModelAndView mv = new ModelAndView("/QRcodePay/weixinPay");
		/**
		 * 使用jsapi集成参数
		 */
		Date date = new Date();
		String outTradeNo = "WX" + String.valueOf(date.getTime());
		
		
		/**
		 * 细化锁定初始状态
		 */
		String type = storeId.substring(storeId.length() - 1, storeId.length());
		storeId = storeId.substring(0, storeId.length() - 2);
		if (type.equals("X")) {
			payRecord.setBusinessType("0");
		} else {
			payRecord.setBusinessType("1");
		}
		payRecord.setOrderno(outTradeNo);
		payRecord.setBusinessid(storeId);
		recordService.doSave(payRecord);
		// 生成微信js授权
		mv = JsapiTicketUtil.getWxStr(request, code, storeId, b.toString(),
				outTradeNo, mv);
		System.out.println("======微信已经返回action 准备请求页面控件吊起微信支付=====");

		return mv;
	}

	/**
	 * H5支付页面输入金额之后点击提交操作
	 * 
	 * @param request
	 * @param response
	 * @param browserType
	 *            浏览器类型,判断是支付宝支付还是微信支付
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitH5Pay")
	public void submitH5Pay(HttpServletRequest request,
			HttpServletResponse response, String amount, String storeId)
			throws Exception {
		String outTradeNo = "";

		Date date = new Date();

		PayRecord payRecord = new PayRecord();

		String ua = ((HttpServletRequest) request).getHeader("user-agent")
				.toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
			outTradeNo = "WX" + String.valueOf(date.getTime());
			// 此处将订单号插入订单表，以作支付成功后异步回调校验
			// WeixinPay.doPay(request, response, code, storeId, amount);
		} else {// 如果是其他浏览器，直接跳转至支付宝
			outTradeNo = "AL" + String.valueOf(date.getTime());
			// 此处将订单号插入订单表，以作支付成功后异步回调校验
			Alipay.doPay(request, response, outTradeNo, amount, outTradeNo,
					"[橙果科技]线下扫码购物", "[橙果科技]线下扫码购物");
		}
		/**
		 * 细化锁定初始状态
		 */
		String type = storeId.substring(storeId.length() - 1, storeId.length());
		storeId = storeId.substring(0, storeId.length() - 2);
		if (type.equals("X")) {
			payRecord.setBusinessType("0");
		} else {
			payRecord.setBusinessType("1");
		}
		payRecord.setOrderno(outTradeNo);
		payRecord.setBusinessid(storeId);
		recordService.doSave(payRecord);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param amount
	 * @param storeId
	 * @param code
	 * @throws Exception
	 */
	@RequestMapping(value = "/turnWxpay")
	public void TurnWxpay(HttpServletRequest request,
			HttpServletResponse response, String code) throws Exception {
		System.out.println("code>>>>>" + code);
		String openid = null;
		if (code != null) {
			String jsonStr = "";
			String authURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid='"
					+ WxpayConfig.APPID
					+ "'&secret='"
					+ WxpayConfig.SECRET
					+ "'&code=" + code + "&grant_type=authorization_code";
			System.out.println(authURL);
			HttpPost httpost = HttpClientConnectionManager
					.getPostMethod(authURL);
			try {
				HttpResponse responses = httpclient.execute(httpost);
				jsonStr = EntityUtils.toString(responses.getEntity(), "UTF-8");
				System.out.println("jsonStr>>" + jsonStr);
				openid = jsonStr.substring(jsonStr.indexOf("openid\":\"") + 9,
						jsonStr.indexOf("\",\"scope"));
				System.out.println("openid>>>>>>>>>>>>>>>" + openid);
				String access_token = jsonStr.substring(
						jsonStr.indexOf("access_token\":\"") + 15,
						jsonStr.indexOf("\",\"expires_in"));
				System.out.println("access_token>>>>>>>>>>" + access_token);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/appPay")
	public RespBean<Charge> appPay(HttpServletRequest request,
			HttpServletResponse response, String amount, String storeId,
			String payType, String orderId) throws Exception {
		RespBean<Charge> respBean = new RespBean<Charge>();
		String S = UfdmDateUtil.getCurrentTime();
		Date date = new Date();
		Charge res = ChargeExample.getPay(String.valueOf(date.getTime()),100.0, "127.0.0.1", "alipay", "Test pay", "XX");
		respBean.setBody(res);
		return respBean;
	}
	
	/**
	 * 微信线下扫码支付成功之后 微信跳转的页面
	 * @param request
	 * @param response
	 * @param amount
	 * @param storeId
	 * @param payType
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/wxResShow")
	public ModelAndView wxResShow(HttpServletRequest request,
			HttpServletResponse response, String amount) throws Exception {
		ModelAndView mv = new ModelAndView("/QRcodePay/wxTransferRes");
		mv.addObject("amount",amount);
		mv.addObject("nowDate",UfdmDateUtil.getCurrentTime());
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/downApkQrcode")
	public ModelAndView downApkQrcode(HttpServletRequest request,
			HttpServletResponse response, String amount) throws Exception {
		ModelAndView mv = new ModelAndView("/QRcodePay/downApkQrcode");
		return mv;
	}
	
}

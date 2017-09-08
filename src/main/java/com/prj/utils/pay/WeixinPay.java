package com.prj.utils.pay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * @author: Fengc
 * @date:2017-7-14 下午2:23:33
 * @version :0.0.1
 * @dis:
 */
public class WeixinPay {
	@SuppressWarnings("deprecation")
	public static DefaultHttpClient httpclient;

	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager
				.getSSLInstance(httpclient);
	}

	@SuppressWarnings("static-access")
	public static void doPay(HttpServletRequest request,
			HttpServletResponse httpResponse, String code ,String orderId, String amount) throws ClientProtocolException, IOException {

		String openid = "";
		String jsonStr = "";
		HttpPost httpost = HttpClientConnectionManager
				.getPostMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxab6a41f141db132a"
						+ "&redirect_uri=http://hilink.chengguokj.com/XA/weixin.htm&secret=f1e6a2a58dda8068030025bfd402a66c&code="
						+ code + "&grant_type=authorization_code");
		try {
			HttpResponse responses = httpclient.execute(httpost);
			jsonStr = EntityUtils.toString(responses.getEntity(), "UTF-8");
			openid = jsonStr.substring(jsonStr.indexOf("openid\":\"") + 9,
					jsonStr.indexOf("\",\"scope"));
			System.out.println("OPENID=========="+openid);
			String access_token = jsonStr.substring(
					jsonStr.indexOf("access_token\":\"") + 15,
					jsonStr.indexOf("\",\"expires_in"));
			System.out.println("在getOpenId中获取token:--->" + access_token);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		// 获取jsapi_ticket
		String access_token = "";
		String jsapi_ticket = "";
		HttpGet httget = HttpClientConnectionManager
				.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxab6a41f141db132a&secret=f1e6a2a58dda8068030025bfd402a66c");
		HttpResponse responses = httpclient.execute(httget);
		jsonStr = EntityUtils.toString(responses.getEntity(), "UTF-8");
		access_token = jsonStr.substring(
				jsonStr.indexOf("access_token\":\"") + 15,
				jsonStr.indexOf("\",\"expires_in"));
		System.out.println("jsapi_ticket---->access_token"+access_token);
		if (access_token != null) {
			jsonStr = "";
			httget = HttpClientConnectionManager
					.getGetMethod("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
							+ access_token + "&type=jsapi");
			try {
				responses = httpclient.execute(httget);
				jsonStr = EntityUtils.toString(responses.getEntity(), "UTF-8");

				jsapi_ticket = jsonStr.substring(
						jsonStr.indexOf("jsapi_ticket\":\"") + 15,
						jsonStr.indexOf("\",\"expires_in"));

				System.out.println("jsapi_ticket---->" + jsapi_ticket);
				// Map map = doXMLParse(jsonStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
		}

		// 微信js授权需要签名
		String nonce_str = Sha1Util.getNonceStr();// 随机字符串
		String timestamp = Sha1Util.getTimeStamp();// 时间戳
		// String appid =
		// "wxff154f8b7dae804d";//APPID,谁在问我为什么报没有APPID就去死吧
		String appid = "wxab6a41f141db132a";// APPID,谁在问我为什么报没有APPID就去死吧
		/**
		 * FIXME SS
		 */
		String url = "" + "/order_pays.htm";// 发起支付的前端页面的URL地址.而且...而且必须在微信支付里面配置才行!!!
		String sign = null;
		try {
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("jsapi_ticket", jsapi_ticket);
			packageParams.put("noncestr", nonce_str);
			packageParams.put("timestamp", timestamp);
			packageParams.put("url", url);
			sign = Sha1Util.createSHA1Sign(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String check = "appId : \"" + appid + "\",timestamp : \""
				+ timestamp // 微信个傻逼..这里的timestamp是小写~~
				+ "\", nonceStr : \"" + nonce_str + "\", signature : \"" + sign
				+ "\"";

		// 微信支付需要的签名
		// 网页授权后获取传递的参数
		String orderNo = orderId;

		// appid = "wxff154f8b7dae804d";
		appid = "wxab6a41f141db132a";
		// String appsecret = "3774f2bb322bbec7ff446fac482d942b";
		String appsecret = "f1e6a2a58dda8068030025bfd402a66c";
		// String mch_id = "1461315302";//邮件里的MCHID
		String mch_id = "1461315302";// 邮件里的MCHID
		
		String partnerkey = "bnegbnegbnegbnegbnegbnegbnegbneg";// 在微信商户平台pay.weixin.com里自己生成的那个key

		System.out.println("openId---->" + openid);

		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		nonce_str = strReq;
		// 商品描述
		// String body = describe;

		// 商品描述根据情况修改
		String body = "wxgoods";
		// 附加数据
		// String attach = userId;
		// 商户订单号
		String out_trade_no = "a" + orderNo + "a";
		// int intMoney = Integer.parseInt(finalmoney);

		// 总金额以分为单位，不带小数点
		// int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = "127.0.0.1";
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = "" + "/alipay_notify.htm";

		String trade_type = "JSAPI";

		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		// packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", "1");
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<body><![CDATA[" + body + "]]></body>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+

				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ 1
				+ "</total_fee>"
				+
				// "<total_fee>"+finalmoney+"</total_fee>"+
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
				+ "<notify_url>" + notify_url + "</notify_url>"
				+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
				+ openid + "</openid>" + "</xml>";
		System.out.println(xml);
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				System.out.println("统一支付接口获取预支付订单出错");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("prepay_id:" + prepay_id);
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		String str = "timestamp:\""
				+ timestamp // 这里的也是小写~~
				+ "\",nonceStr:\"" + nonceStr2 + "\",package:\"" + packages
				+ "\",signType: \"MD5" + "\",paySign:\"" + finalsign + "\"";

		System.out.println("wxpay end..........................");
	}
}

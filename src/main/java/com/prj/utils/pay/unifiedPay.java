package com.prj.utils.pay;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSON;

import com.alibaba.druid.support.json.JSONUtils;
import com.prj.utils.JsonUtils;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.ping.PayConfiger;

/**
 * @author: Fengc
 * @date:2017-8-24 下午4:29:47
 * @version :0.0.1
 * @dis:微信统一下单
 */
public class unifiedPay {

	public static void getPrepay() {
		Double amount = 1.0;
		String timestamp = null;
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		// 商品描述
		// String body = describe;

		// 商品描述根据情况修改
		String body = "wxgoods";
		// 附加数据
		String attach = "xxx";

		// 商户订单号
		String out_trade_no = strRandom;
		// 订单生成的机器 IP
		String spbill_create_ip = "127.0.0.1";

		String trade_type = "MWEB";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", WxpayConfig.APPID);
		packageParams.put("mch_id", WxpayConfig.MCH_ID);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee",
				BigDecimal.valueOf(amount).multiply(new BigDecimal(100))
						.toString());

		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", WxpayConfig.NOTIFY_URL);

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(WxpayConfig.APPID, WxpayConfig.SECRET,
				WxpayConfig.PARTNERKEY);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>"
				+ WxpayConfig.APPID
				+ "</appid>"
				+ "<mch_id>"
				+ WxpayConfig.MCH_ID
				+ "</mch_id>"
				+ "<nonce_str>"
				+ nonce_str
				+ "</nonce_str>"
				+ "<sign><![CDATA["
				+ sign
				+ "]]></sign>"
				+ "<body><![CDATA["
				+ body
				+ "]]></body>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+
				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ (BigDecimal.valueOf(amount).multiply(new BigDecimal(100))
						.toString()) + "</total_fee>" + "<attach>" + attach
				+ "</attach>" + "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>"
				+ WxpayConfig.NOTIFY_URL + "</notify_url>" + "<trade_type>"
				+ trade_type + "</trade_type>" + "</xml>";
		System.out.println(xml);

		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
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
		timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", WxpayConfig.APPID);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		String str = "timestamp:\""
				+ timestamp // 这里的也是小写~~
				+ "\",nonceStr:\"" + nonceStr2 + "\",package:\"" + packages
				+ "\",signType: \"MD5" + "\",paySign:\"" + finalsign + "\"";

	}
}

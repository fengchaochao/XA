package com.prj.utils.pay;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;

/**
 * @author: Fengc
 * @date:2017-7-14 上午9:04:43
 * @version :0.0.1
 * @dis:
 */
public class Alipay {

	public static void doPay(HttpServletRequest httpRequest,
			HttpServletResponse response, String orderId, String totalPrice,String outTradeNo,String goodsName,String title) {
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL,
				AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
				AlipayConfig.FORMAT, AlipayConfig.CHARSET,
				AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
		AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

		// 封装请求支付信息
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		
		model.setOutTradeNo(outTradeNo);
		model.setSubject(title);
		model.setTotalAmount(totalPrice);
		model.setBody("STOREID:" + goodsName);
		model.setTimeoutExpress("30m");
		model.setProductCode("XX-PRO");
		alipay_request.setBizModel(model);
		// 设置异步通知地址
		alipay_request.setNotifyUrl(AlipayConfig.notify_url);

		// 设置同步地址
		// form表单生产
		String form = "";
		try {
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
			response.getWriter().write(form);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

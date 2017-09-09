package com.prj.utils.pay;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.prj.utils.MD5;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author: Fengc
 * @date:2017-8-23 下午2:51:13
 * @version :0.0.1
 * @dis:
 */
public class PayCommonUtil {
	
	/**
	 * 验证回调签名
	 * @throws Exception 
	 */
	public static boolean isTenpaySign(Map<String, String> map) throws Exception {
		String charset = "utf-8";
		String signFromAPIResponse = map.get("sign");
		// API返回的数据签名数据不存在，有可能被第三方篡改!!!
		if (signFromAPIResponse == null || signFromAPIResponse.equals("")) {
			return false;
		}

		// 过滤空 设置 TreeMap
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		for (String parameter : map.keySet()) {
			String parameterValue = map.get(parameter);
			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + WxpayConfig.PARTNERKEY);
		// 算出签名
		String resultSign = "";
		String tobesign = sb.toString();
		if (null == charset || "".equals(charset)) {
			resultSign = MD5.md5Encode(tobesign, charset).toUpperCase();
		} else {
			resultSign = MD5.md5Encode(tobesign, charset).toUpperCase();
		}
		String tenpaySign = ((String) packageParams.get("sign")).toUpperCase();
		boolean  b= tenpaySign.equals(resultSign);
		System.out.println("====微信扫码回调签名校验->>>>"+ b);
		return b;
	}

	// xml解析
	public static Map doXMLParse(String strxml) throws JDOMException,
			IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}

	/**
	 * 循环查找子节点
	 * 
	 * @param children
	 * @return
	 */
	private static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
}

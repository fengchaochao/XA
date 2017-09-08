package com.prj.utils.pay;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class GetWxOrderno {
	public static void main(String[] args) {
		String strRandom = TenpayUtil.buildRandom(4) + "";
		String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<xml> \n" +
				"  <appid>wxff154f8b7dae804d</appid>  \n" +
				"  <mch_id>1461315302</mch_id>  \n" +
				"  <nonce_str>1420113801</nonce_str>  \n" +
				"  <sign><![CDATA[F67118C8FD6A24F1E6C6415067A2DBEA]]></sign>  \n" +
				"  <body><![CDATA[wxgoods]]></body>  \n" +
				"  <out_trade_no>'"+strRandom+"'</out_trade_no>  \n" +
				"  <total_fee>100</total_fee>  \n" +
				"  <attach>storeId: 110114)</attach>  \n" +
				"  <spbill_create_ip>127.0.0.1</spbill_create_ip>  \n" +
				"  <notify_url>http://www.wanhuocn.com/oneWorld/alipay_notify.htm</notify_url>  \n" +
				"  <trade_type>JSAPI</trade_type>  \n" +
				"  <openid>oinvx0pi_f96-aXkRellZsNtH8rA</openid> \n" +
				"</xml>";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

		getPayNo(createOrderURL, xmlStr);
	}
	public static String getPayNo(String url, String xmlParam) {
		DefaultHttpClient httpclient;
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager
				.getSSLInstance(httpclient);
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
				true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);
		String prepay_id = "";
		try {
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			Map<String, Object> dataMap = new HashMap<String, Object>();
			System.out.println(jsonStr);

			if (jsonStr.indexOf("FAIL") != -1) {
				return prepay_id;
			}
			Map map = doXMLParse(jsonStr);
			String return_code = (String) map.get("return_code");
			prepay_id = (String) map.get("prepay_id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepay_id;
	}

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
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
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
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

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}

}
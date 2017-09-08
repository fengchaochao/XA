package com.prj.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/** 
 * 描述: TODO<br>
 * @author 胡义振
 * @date 2014-11-28  
 */
public class UfdmXmlUtil
{
	/**
	 * 描述: 通过 dom4j 获取 xml文档所有元素
	 * @auther 胡义振
	 * @date 2014-11-28 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map doGetAllXmlElementByDom4j(String reqXml){
		try
		{
	 	    Document document = DocumentHelper.parseText(reqXml); 
	 	    Element root = document.getRootElement();
	 	    return readXml(root,null);
		}
		catch(Exception er){
			er.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 描述: 遍历XML文档
	 * @auther 胡义振
	 * @date 2014-11-28 
	 * @param element
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map readXml(Element element,Map map){
		try
		{
			if(map==null){
				map = new HashMap();
			}
			Iterator iter=element.elementIterator();
			while(iter.hasNext()) {
				Element ele= (Element)iter.next();
				map.put(ele.getName(), ele.getText().trim());
				if(ele.elements().size()!=0){
					readXml(ele,map);
				}
			}	
			return map;	
		}
		catch(Exception er){
			er.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {

		String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
				"<soapenv:Body>" +
				"<ELicenses xmlns=\"http://www.icinfo.com/dataservice\">" +
				"<ELicense>" +
				"<corpid xmlns=\"http://www.icinfo.com/dataservice\">3302000000069920</corpid>" +
				"<entName xmlns=\"http://www.icinfo.com/dataservice\">宁波菲尔电子有限公司</entName>" +
				"<regNo xmlns=\"http://www.icinfo.com/dataservice\">330200400063096</regNo>" +
				"<entUniCode xmlns=\"http://www.icinfo.com/dataservice\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"></entUniCode>" +
				"<legRep xmlns=\"http://www.icinfo.com/dataservice\">罗建灿</legRep>" +
				"<loc xmlns=\"http://www.icinfo.com/dataservice\">宁波市江东区中兴北路21号南余工业区</loc>" +
				"<regCap xmlns=\"http://www.icinfo.com/dataservice\">15.0000</regCap>" +
				"<regCapCur xmlns=\"http://www.icinfo.com/dataservice\">840</regCapCur>" +
				"<regOrg xmlns=\"http://www.icinfo.com/dataservice\">330200</regOrg>" +
				"<jur xmlns=\"http://www.icinfo.com/dataservice\">33020403</jur>" +
				"<estDate xmlns=\"http://www.icinfo.com/dataservice\">2006-10-20T00:00:00.000+08:00</estDate>" +
				"<appDate xmlns=\"http://www.icinfo.com/dataservice\">2011-06-08T00:00:00.000+08:00</appDate>" +
				"<state xmlns=\"http://www.icinfo.com/dataservice\">K</state>" +
				"<certCode xmlns=\"http://www.icinfo.com/dataservice\">330106650803047</certCode>" +
				"<entTypeCatg xmlns=\"http://www.icinfo.com/dataservice\">21</entTypeCatg>" +
				"</ELicense>" +
				"</ELicenses>" +
				"</soapenv:Body>" +
				"</soapenv:Envelope>";

		Map map = doGetAllXmlElementByDom4j(reqXml);
		
		System.out.println(map);
		  
	}
}

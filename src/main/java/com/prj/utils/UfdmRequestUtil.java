/** 
* @Description: TODO
* @date 2016年3月10日 
* @author 1936
*/
package com.prj.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/** 
* @Description: TODO
* @date 2016年3月10日 
* @author 1936
*/
public class UfdmRequestUtil {
private static Logger logger = LoggerFactory.getLogger(UfdmRequestUtil.class);
	
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getAllParam(HttpServletRequest request) {
		Map<String,String> tempMap = new HashMap<String,String>();
		try
		{
			Enumeration enuParam = request.getParameterNames();  
			while(enuParam.hasMoreElements()){  
				String paramName = (String) enuParam.nextElement();
				String [] arrParamValue = request.getParameterValues(paramName);
				String paramValue = UfdmStringUtil.arrayToStr(arrParamValue, ",");
				// 过滤掉空值
				if(paramValue!=null && paramValue.length()>0){
				    tempMap.put(paramName, paramValue);
				}
			}  
		}
		catch(Exception er){
			
		}
		return tempMap;
	}
	
	
	/**
	 * 描述: 通过 request 对象获取IP地址
	 * @auther 胡义振
	 * @date 2014-10-13 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request)
	{
		if(request!=null)
		{
			String ipAddress = request.getHeader("X-Forwarded-For");
		    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
		    	ipAddress = request.getHeader("Proxy-Client-IP");
		    }
		    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
		    	ipAddress = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
		    	ipAddress = request.getHeader("X-Real-IP");
		    }
		    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
		    	ipAddress = request.getRemoteAddr();
		    }
		    return ipAddress;
		}
		else
		{
			return "";
		}
	}

	/**
	 * 描述: 从 request 中获取 cookie 值
	 * @auther 胡义振
	 * @date 2014-10-13 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookieValue(HttpServletRequest request,String cookieName) {
		Cookie[] cookie = request.getCookies();
		for (int i = 0; cookie != null && i < cookie.length; i++) {
			if (cookie[i].getName().equals(cookieName))
				return cookie[i];
		}
		return null;
	}
	

	/**
	 * 描述: 从 request 中获取字符串信息
	 * @auther 胡义振
	 * @date 2014-10-13
	 * @param request
	 * @param encode
	 * @return
	 */
	public static String getStringFromStream(HttpServletRequest request,String encode) {
		String requestXML = "";
		try
		{
			InputStream inputStream = request.getInputStream();
			if(inputStream!=null){
				InputStreamReader inStreamRead = new InputStreamReader(inputStream,encode);
				BufferedReader buffRead = new BufferedReader(inStreamRead);
				String line;
				while((line = buffRead.readLine()) != null)
				{
					requestXML += line;
				}
			}
		}
		catch(Exception er){
			er.printStackTrace();
		}
		return requestXML;
	}
	
	/**
	 * 描述: POST 流提交
	 * @auther 胡义振
	 * @date 2014-10-13 
	 * @param strUrl  请求地址
	 * @param content 请求内容
	 * @param encode  编码
	 * @return
	 */
	public static String postStream(String strUrl, String content, String encode) throws Exception 
	{
		HttpURLConnection urlCon = null;
		try
		{
			URL url = new URL(strUrl);
			urlCon = (HttpURLConnection) url.openConnection();

			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("content-type", "application/json;charset=UTF-8");
			urlCon.setDoOutput(true);

			urlCon.getOutputStream().write(content.getBytes(encode));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
	        
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), encode));

			String line;
			String respXML = "";
			while ((line = in.readLine()) != null)
			{
				respXML += line;
			}
			in.close();
			respXML = respXML.replaceAll("null", "\"\"");
			return respXML;
		}
		catch (Exception e)
		{
			logger.info("\n 调用 Request Stream 出现异常 -- Request Url:" + strUrl + "  Request Param:"+content); 
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			if (urlCon != null)
			{
				urlCon.disconnect();
			}
		}
    }
	
	/**
	 * 描述: POST 流提交返回Map<String,Object>
	 * @auther 胡义振
	 * @date 2014-10-13 
	 * @param strUrl  请求地址
	 * @param content 请求内容
	 * @param encode  编码
	 * @return
	 */
	public static Map<String,Object> postStreamForMap(String strUrl, String content, String encode) throws Exception 
	{
		HttpURLConnection urlCon = null;
		try
		{
			URL url = new URL(strUrl);
			urlCon = (HttpURLConnection) url.openConnection();

			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("content-type", "application/json;charset=UTF-8");
			urlCon.setDoOutput(true);

			urlCon.getOutputStream().write(content.getBytes(encode));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
	        
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), encode));

			String line;
			String respXML = "";
			while ((line = in.readLine()) != null)
			{
				respXML += line;
			}
			in.close();
			respXML = respXML.replaceAll("null", "\"\"");
			return UfdmJsonUtil.jsontoHashMap(respXML);
		}
		catch (Exception e)
		{
			logger.info("\n 调用 Request Stream 出现异常 -- Request Url:" + strUrl + "  Request Param:"+content); 
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			if (urlCon != null)
			{
				urlCon.disconnect();
			}
		}
    }
	
	public static Map<String,Object> postParam(String strUrl, Map<String,String> requestMap,String encode)  throws Exception 
	{
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try
		{
			URL url = new URL(strUrl);
			HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
			// 设置通用的请求属性
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("accept", "*/*;charset=UTF-8");
			urlCon.setDoOutput(true);
			out = new PrintWriter(urlCon.getOutputStream());
			out.print(UfdmMapUtil.mapToUrlParam(requestMap,encode));
			out.flush();
			
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),encode));
			String line = "";
			while ((line = in.readLine()) != null)
			{
				result.append(line);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
					out = null;
				}
				if (in != null)
				{
					in.close();
					in = null;
				}
			}
			catch (Exception ex)
			{

			}
		}
		
		System.out.println("result.toString():"+result.toString());
		return UfdmJsonUtil.jsontoHashMap(result.toString());
	}
}

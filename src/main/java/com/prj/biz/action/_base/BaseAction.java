package com.prj.biz.action._base;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** 
* @Description: 基类ACTION
* @date 2016年1月11日 
* @author 1936
*/
public class BaseAction implements Serializable{

	private static final long serialVersionUID = -5192292757373985406L;

    /**
     * 描述: 获取Session值 
     * @auther 胡义振 
     */
    public static Object doGetSession(String key)
	{
    	return (Object) getSession().getAttribute(key);
	}
    
    /**
     * 描述: 设置Session值
     * @auther 胡义振
     */
	public static void doSetSession(String key,Object object)
	{
		getSession().setAttribute(key, object);
	}
	
	public static void doRemoveSession(String key)
	{
		getSession().removeAttribute(key);
	}
	
	
	// 获取 session
	private static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = doGetRequst().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	// 获取 request
	public static HttpServletRequest doGetRequst() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}
}

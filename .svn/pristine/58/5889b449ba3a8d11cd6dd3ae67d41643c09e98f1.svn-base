package com.prj.core.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/** 
* @Description: 会话监听
* @date 2016年7月29日 
* @author 1936
*/
public class UFDMSessionListener implements SessionListener {

	/**
	 * @Description: 会话创建时触发
	 * @param session
	 * @date 2016年8月1日 
	 * @author 1936
	 */
	@Override
	public void onStart(Session session) {
		System.out.println("\n 会话创建：" + session.getId());
	}

	/**
	 * @Description: 会话过期时触发
	 * @param session
	 * @date 2016年8月1日 
	 * @author 1936
	 */
	@Override
	public void onExpiration(Session session) {
		System.out.println("\n 会话过期：" + session.getId());
	}

	/**
	 * @Description: 退出/会话过期时触发
	 * @param session
	 * @date 2016年8月1日 
	 * @author 1936
	 */
	@Override
	public void onStop(Session session) {
		System.out.println("\n 会话停止：" + session.getId());
	}

}

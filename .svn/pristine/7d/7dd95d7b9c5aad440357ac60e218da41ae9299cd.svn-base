package com.prj.biz.service.sysuser;

import java.util.List;
import java.util.Map;
import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 系统用户Service接口<br>
 * @author 胡义振
 * @date 2013-08-06
 */
public interface SysUserService extends BaseService<SysUser>{
	
	/**
	 * 描述: 通过用户名、密码登陆
	 * @auther 胡义振
	 * @date 2013-8-16 
	 * @param sysUser
	 * @return
	 */
	public SysUser doGetSysUserForLogin(SysUser sysUser);
	
	/**
	 * 描述: 检测用户名是否存在
	 * @auther 胡义振
	 * @date 2014-1-9 
	 * @param loginName
	 * @return
	 */
	public boolean doCheckSysUserLoginNameExist(String loginName,String userId) throws Exception;
	
	/**
	 * 描述: 认证用户信息
	 * @auther 胡义振
	 * @date 2013-8-16 
	 * @param authenMap
	 * @return
	 */
	public String authenSysUser(Map<String, String> authenMap);

	/**
	 * 描述:菜单排序
	 * @return 
	 */
	List<PerResource> doGetHxResourceForMenuList(List<PerResource> hxResources);

}

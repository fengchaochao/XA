package com.prj.biz.service.permission;

import com.prj.biz.bean.permission.PerRoleResource;
import com.prj.biz.service._base.BaseService;

/** 
 * 描述: 系统权限-角色资源service接口<br>
 * @author 胡义振
 * @date 2013-5-13 
 */
public interface PerRoleResourceService  extends BaseService<PerRoleResource>
{
	/**
	 * 描述: 根据ID删除
	 * @auther 胡义振
	 * @param id 
	 */
	public void doRmRoleResource(String id) throws Exception;
	
	/**
	 * 描述: 根据角色ID删除
	 * @auther 胡义振
	 * @param roleId 角色ID
	 */
	public void doRmRoleResourceByRoleId(String roleId) throws Exception;	
	
	/**
	 * 描述: 根据资源ID删除
	 * @auther 胡义振
	 * @param resourceId 资源ID
	 */
	public void doRmRoleResourceByResourceId(String resourceId) throws Exception;	
}

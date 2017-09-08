package com.prj.biz.service.permission;

import java.util.List;

import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 权限系统--资源Service接口<br>
 * @author 胡义振
 */
public interface PerResourceService extends BaseService<PerResource> 
{
	/**
	 * 描述: 根据用户ID，查询用户的资源
	 * @auther 胡义振
	 * @param userId 用户ID
	 * @return 用户资源列表
	 * @throws Exception
	 */
	public List<PerResource> doGetResourceListByUserId(String userId) throws Exception;
	
	/**
	 * 描述: 根据用户ID，查询用户菜单资源
	 * @auther 胡义振
	 * @param userId 用户ID
	 * @return 用户菜单资源列表
	 * @throws Exception
	 */
	public List<PerResource> doGetResourceMenuListByUserId(String userId) throws Exception;
	
	/**
	 * 描述: 根据角色ID，查询角色的资源
	 * @auther 胡义振
	 * @param roleId 角色ID
	 * @return 角色资源列表
	 * @throws Exception
	 */
	public List<PerResource> doGetResourceListByRoleId(String roleId) throws Exception;
	
	/**
	 * 描述: 删除资源信息
	 * @auther 胡义振
	 * @param hxResourceIds 资源集
	 * @throws Exception
	 */
	public void doRmResource(String [] resourceIdList) throws Exception;
}

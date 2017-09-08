package com.prj.biz.service._impl.permission;

import org.springframework.stereotype.Service;

import com.prj.biz.bean.permission.PerRoleResource;
import com.prj.biz.dao.maindb.permission.PerRoleResourceDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.permission.PerRoleResourceService;

/**
 * 描述: 系统权限-角色资源Service实现<br>
 * @author 胡义振
 * @date 2013-5-13
 */
@Service
public class PerRoleResourceServiceImpl extends BaseServiceImpl<PerRoleResourceDao,PerRoleResource> implements PerRoleResourceService
{
	/**
	 * 描述: 根据ID删除
	 * @auther 胡义振
	 * @param id 
	 */
	@Override
	public void doRmRoleResource(String id) throws Exception
	{
		genDao.deleteRoleResource(id);
	}

	/**
	 * 描述: 根据角色ID删除
	 * @auther 胡义振
	 * @param roleId 角色ID
	 */
	public void doRmRoleResourceByRoleId(String roleId) throws Exception
	{
		genDao.deleteRoleResourceByRoleId(roleId);
	}
	
	/**
	 * 描述: 根据资源ID删除
	 * @auther 胡义振
	 * @param resourceId 资源ID
	 */
	public void doRmRoleResourceByResourceId(String resourceId) throws Exception
	{
		genDao.deleteRoleResourceByResourceId(resourceId);
	}


}

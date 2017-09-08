package com.prj.biz.service._impl.permission;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.dao.maindb.permission.PerResourceDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.permission.PerResourceService;
import com.prj.biz.service.permission.PerRoleResourceService;

/**
 * 描述: 权限系统--资源Service实现<br>
 * @author 胡义振
 */
@Service
public class PerResourceServiceImpl extends BaseServiceImpl<PerResourceDao,PerResource> implements PerResourceService
{
	@Resource
	private PerRoleResourceService hxRoleResourceService;

	
	/**
	 * 描述: 根据用户ID，查询用户的资源
	 * @auther 胡义振
	 * @param userId 用户ID
	 * @return 用户资源列表
	 * @throws Exception
	 */
	@Override
	public List<PerResource> doGetResourceListByUserId(String userId) throws Exception
	{
		return genDao.selectResourceListByUserId(userId);
	}
	
	/**
	 * 描述: 根据用户ID，查询用户菜单资源
	 * @auther 胡义振
	 * @param userId 用户ID
	 * @return 用户菜单资源列表
	 * @throws Exception
	 */                     
	@Override
	public List<PerResource> doGetResourceMenuListByUserId(String userId) throws Exception
	{
		return genDao.selectResourceMenuListByUserId(userId);
	}
	
	/**
	 * 描述: 根据角色ID，查询角色的资源
	 * @auther 胡义振
	 * @param roleId 角色ID
	 * @return 
	 * @throws Exception
	 */
	@Override
	public List<PerResource> doGetResourceListByRoleId(String roleId) throws Exception
	{
		return genDao.selectResourceListByRoleId(roleId);
	}
	
	/** 
	 * 描述: 删除用户资源
	 * @auther 胡义振
	 * @param hxResourceIds 资源集
	 * @throws Exception 
	 */
	@Override
	public void doRmResource(String[] perResourceIds) throws Exception
	{
		if(perResourceIds!=null){
			for(String perResourceId:perResourceIds){
				//删除资源信息
				genDao.deleteById(perResourceId);
				//删除角色资源中的资源信息
				hxRoleResourceService.doRmRoleResourceByResourceId(perResourceId);
			}
		}
	}

}

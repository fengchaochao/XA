package com.prj.biz.service._impl.permission;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.bean.permission.PerRole;
import com.prj.biz.bean.permission.PerRoleResource;
import com.prj.biz.dao.maindb.permission.PerRoleDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.permission.PerResourceService;
import com.prj.biz.service.permission.PerRoleResourceService;
import com.prj.biz.service.permission.PerRoleService;
import com.prj.biz.service.permission.PerUserRoleService;

/**
 * 描述: 权限系统--角色Service实现<br>
 * @author 胡义振
 */
@Service
public class PerRoleServiceImpl extends BaseServiceImpl<PerRoleDao,PerRole> implements PerRoleService
{
	//资源服务类
	@Resource
	private PerResourceService perResourceService;
	//角色资源服务类
	@Resource
	private PerRoleResourceService perRoleResourceService;
	//用户角色服务类
	@Resource
	private PerUserRoleService perUserRoleService;

	/** 
	 * 描述: 根据用户ID，查询用户角色
	 * @auther 胡义振
	 * @param userId
	 * @return 
	 */
	@Override
	public List<PerRole> doGetRoleListByUserId(String userId) throws Exception
	{
		return genDao.selectRoleListByUserId(userId);
	}
	
	/** 
	 * 描述: 获取所有系统角色（角色对象含有 userOwner 属性）
	 * @auther 胡义振
	 * @param userId
	 * @return 
	 */
	@Override
	public List<PerRole> doGetAllRoleListWithUserOwner(String userId) throws Exception
	{
		// 所有系统角色
		List<PerRole> allRoleList = doGetList(null);
		// 如果用户ID为空，直接返回
		if(userId==null || userId.equals("")){
			return allRoleList;
		}
		else{
			// 临时系统角色（含有 userOwner 属性）
			List<PerRole> tempRoleList = new ArrayList<PerRole>();
			
			// 用户所拥有的角色
			List<PerRole> userRoleList = doGetRoleListByUserId(userId);
			
			for(PerRole hxRole : allRoleList){
				if(isIncludeRole(hxRole.getId(),userRoleList)){
					hxRole.setUserOwner(true);
				}
				tempRoleList.add(hxRole);
			}
			return tempRoleList;
		}
	}

	/**
	 * 描述: 判断一个权限ID是否在权限集中
	 * @auther 胡义振
	 * @param roleId 权限ID
	 * @param perRoleList 权限集
	 * @return 
	 * @throws Exception
	 */
	private boolean isIncludeRole(String roleId,List<PerRole> perRoleList) throws Exception{
		for(PerRole perRole:perRoleList){
			if(perRole.getId().equals(roleId)){
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * 描述: 根据角色ID，查询角色对象【角色对象包含(所有)资源对象，资源对象包含 roleOwner 属性】
	 * @auther 胡义振
	 * @param roleId
	 * @return
	 * @throws Exception 
	 */
	@Override
	public PerRole doGetRoleWithRoleOwnerByRoleId(String roleId) throws Exception
	{
		PerRole hxRole = new PerRole();
		// 系统所有资源
		List<PerResource> allResourceList = perResourceService.doGetList(null);
		
		if(roleId!=null){
			hxRole = genDao.selectById(roleId);
			
			//角色所拥有的资源集
			List<PerResource> userResourceList = perResourceService.doGetResourceListByRoleId(roleId);
			
			//系统角色集中对用户角色添加包含标识
			List<PerResource> hxResourceNewList = new ArrayList<PerResource>();
			
			for(PerResource hxResource : allResourceList){
				if(isIncludeResource(hxResource.getId(),userResourceList)){
					hxResource.setRoleOwner(true);
				}
				hxResourceNewList.add(hxResource);
			}
			hxRole.setPerResourceList(hxResourceNewList);
		}
		else{
			hxRole.setPerResourceList(allResourceList);
		}
		return hxRole;
	}
	
	/**
	 * 描述: 判断一个资源ID是否在资源集中
	 * @auther 胡义振
	 * @param resourceId 资源ID
	 * @param hxResourceList 资源集
	 * @return 
	 * @throws Exception
	 */
	private boolean isIncludeResource(String resourceId,List<PerResource> hxResourceList) throws Exception{
		for(PerResource hxResource:hxResourceList){
			if(hxResource.getId().equals(resourceId)){
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * 描述: 添加角色及角色-资源
	 * @auther 胡义振
	 * @param paramRole
	 * @throws Exception 
	 */
	@Override
	public void doSaveRole(PerRole hxRole) throws Exception
	{
		// 保存角色信息
		genDao.insert(hxRole);
		// 保存角色-资源
		doSaveRoleResource(hxRole);
	}
	
	/** 
	 * 描述: 修改角色及角色-资源
	 * @auther 胡义振
	 * @param paramRole
	 * @throws Exception 
	 */
	@Override
	public void doModRole(PerRole perRole) throws Exception
	{
		// 修改角色信息
		genDao.updateById(perRole);
		// 删除角色资源
		perRoleResourceService.doRmRoleResourceByRoleId(perRole.getId());
		// 保存角色-资源
		doSaveRoleResource(perRole);
	}
	
	/**
	 * 描述: 保存角色对象中的角色-资源列表
	 * @auther 胡义振
	 * @param hxRole
	 * @throws Exception
	 */
	private void doSaveRoleResource(PerRole perRole) throws Exception{
		// 获取角色的资源 
		List<PerResource> perUserRoleList = perRole.getPerResourceList();
		// 角色-资源
		List<PerRoleResource> tempRoleResourceList = new ArrayList<PerRoleResource>();
		if(perUserRoleList!=null){
			// 把资源对象，转成角色-资源对象
			for(PerResource perResource : perUserRoleList){
				PerRoleResource tempRoleResource = new PerRoleResource();
				tempRoleResource.setRoleId(perRole.getId());
				tempRoleResource.setResourceId(perResource.getId());
				tempRoleResourceList.add(tempRoleResource);
			}
			perRoleResourceService.doSaveBatch(tempRoleResourceList);
		}
	}
	
	/** 
	 * 描述: 删除角色（同时删除"角色-资源"、"用户-角色"中的角色）
	 * @auther 胡义振
	 * @param roleId 角色ID集
	 * @throws Exception 
	 */
	@Override
	public void doRmRole(String roleId) throws Exception 
	{
		//删除角色信息
		genDao.deleteById(roleId);
		//删除角色资源中的角色
		perRoleResourceService.doRmRoleResourceByRoleId(roleId);
		//删除用户角色中的角色
		perUserRoleService.doRmUserRoleByRoleId(roleId);
	}
	
	/** 
	 * 描述: 删除角色（同时删除"角色-资源"、"用户-角色"中的角色）
	 * @auther 胡义振
	 * @param hxRoleIds 角色ID集 
	 * @throws Exception 
	 */
	@Override
	public void doRmRole(String[] hxRoleIds) throws Exception
	{
		if(hxRoleIds!=null){
			for(String roleId:hxRoleIds){
				//删除角色信息
				genDao.deleteById(roleId);
				//删除角色资源中的角色
				perRoleResourceService.doRmRoleResourceByRoleId(roleId);
				//删除用户角色中的角色
				perUserRoleService.doRmUserRoleByRoleId(roleId);
			}
		}
	}

	

}

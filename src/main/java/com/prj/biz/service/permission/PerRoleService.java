package com.prj.biz.service.permission;

import java.util.List;

import com.prj.biz.bean.permission.PerRole;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 权限系统--角色Service接口<br>
 * @author 胡义振
 */
public interface PerRoleService extends BaseService<PerRole>
{
	/**
	 * 描述: 根据用户ID，查询用户的角色
	 * @auther 胡义振
	 * @param userId 用户ID
	 * @return 用户角色集 
	 */
	public List<PerRole> doGetRoleListByUserId(String userId) throws Exception;
	
    /**
     * 描述: 获取所有系统角色（角色对象含有 userOwner 属性）
     * @auther 胡义振
     * @param paramUserId 用户ID
     * @return
     */
    public List<PerRole> doGetAllRoleListWithUserOwner(String userId) throws Exception;
    
	/**
	 * 描述: 根据角色ID，查询角色对象【角色对象包含(所有)资源对象，资源对象包含 roleOwner 属性】
	 * @auther 胡义振
	 * @param roleId 角色ID
	 * @return 
	 * @throws Exception
	 */
	public PerRole doGetRoleWithRoleOwnerByRoleId(String roleId) throws Exception;

	/**
	 * 描述: 添加角色及角色-资源
	 * @auther 胡义振
	 * @param paramRole 角色对象
	 * @throws Exception
	 */
	public void doSaveRole(PerRole perRole) throws Exception;
	
	/**
	 * 描述: 修改角色及角色-资源
	 * @auther 胡义振
	 * @param paramRole 角色对象
	 * @throws Exception
	 */
	public void doModRole(PerRole perRole) throws Exception;

	/**
	 * 描述: 删除角色（同时删除"角色-资源"、"用户-角色"中的角色）
	 * @auther 胡义振
	 * @param roleId 角色ID集
	 * @throws Exception
	 */
	public void doRmRole(String roleId) throws Exception;
	
	/**
	 * 描述: 删除角色（同时删除"角色-资源"、"用户-角色"中的角色）
	 * @auther 胡义振
	 * @param hxRoleIds 角色ID集 
	 * @throws Exception
	 */
	public void doRmRole(String [] roleIds) throws Exception;
}

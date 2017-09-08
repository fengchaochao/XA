package com.prj.biz.service.permission;

import com.prj.biz.bean.permission.PerUserRole;
import com.prj.biz.service._base.BaseService;

/** 
 * 描述: 系统权限-用户角色service接口<br>
 * @author 胡义振
 */
public interface PerUserRoleService extends BaseService<PerUserRole>
{
	/**
	 * 描述: 根据ID删除
	 * @auther 胡义振
	 * @param id
	 */
     public void doRmUserRole(String id) throws Exception;
     
  	/**
  	 * 描述: 根据用户ID删除
  	 * @auther 胡义振
  	 * @param userId 用户ID
  	 */
     public void doRmUserRoleByUserId(String userId) throws Exception;
       
       
	/**
	 * 描述: 根据角色ID删除
	 * @auther 胡义振
	 * @date 2014-10-11 
	 * @param roleId 角色ID
	 */
     public void doRmUserRoleByRoleId(String roleId) throws Exception;
      
 	/**
 	 * 描述: 根据用户ID与角色ID删除
 	 * @auther 胡义振
 	 * @param perUserRole 要删除的用户角色对象
 	 * @throws Exception
 	 */
     public void doRmUserRoleByUserIdAndRoleId(PerUserRole perUserRole) throws Exception;
      
     /**
      * 描述: 根据用户ID,修改用户的角色
      * @auther 胡义振
      * @param userId 用户ID
      * @param userRoleIdList 用户分配的角色
      * @throws Exception
      */
     public void doModUserRole(String userId,String [] userRoleIdList) throws Exception;
     

}

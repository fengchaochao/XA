package com.prj.biz.dao.maindb.permission;

import com.prj.biz.bean.permission.PerUserRole;
import com.prj.biz.dao._base.BaseDao;

/** 
 * 描述: 权限系统--用户角色DAO接口<br>
 * @author 胡义振
 * @date 2013-5-13 
 */
public interface PerUserRoleDao extends BaseDao<PerUserRole>
{
	/**
	 * 描述: 根据ID删除
	 * @auther 胡义振
	 * @date 2013-6-14 
	 * @param id
	 */
     public void deleteUserRole(String id) throws Exception;
     
 	/**
 	 * 描述: 根据用户ID删除
 	 * @auther 胡义振
 	 * @date 2013-6-14 
 	 * @param userId 用户ID
 	 */
     public void deleteUserRoleByUserId(String userId) throws Exception;
      
      
   	/**
   	 * 描述: 根据角色ID删除
   	 * @auther 胡义振
   	 * @date 2014-10-11 
   	 * @param roleId 角色ID
   	 */
     public void deleteUserRoleByRoleId(String roleId) throws Exception;
        
 	/**
 	 * 描述: 根据用户ID和角色ID删除
 	 * @auther 胡义振
 	 * @date 2013-6-14 
 	 * @param perUserRole 用户角色对象
 	 */
     public void deleteUserRoleByUserIdAndRoleId(PerUserRole perUserRole) throws Exception;
}

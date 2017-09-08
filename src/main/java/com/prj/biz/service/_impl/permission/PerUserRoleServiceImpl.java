package com.prj.biz.service._impl.permission;

import org.springframework.stereotype.Service;

import com.prj.biz.bean.permission.PerUserRole;
import com.prj.biz.dao.maindb.permission.PerUserRoleDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.permission.PerUserRoleService;

/**
 * 描述: 系统权限-用户角色Service实现<br>
 * @author 胡义振
 */
@Service
public class PerUserRoleServiceImpl extends BaseServiceImpl<PerUserRoleDao,PerUserRole> implements PerUserRoleService
{

	/**
	 * 描述: 根据ID删除
	 * @auther 胡义振
	 * @param id
	 */
     public void doRmUserRole(String id) throws Exception
     {
    	 genDao.deleteUserRole(id);
     }
     
  	/**
  	 * 描述: 根据用户ID删除
  	 * @auther 胡义振
  	 * @param userId 用户ID
  	 */
     public void doRmUserRoleByUserId(String userId) throws Exception
     {
    	 genDao.deleteUserRoleByUserId(userId);
     }
       
       
	/**
	 * 描述: 根据角色ID删除
	 * @auther 胡义振
	 * @param roleId 角色ID
	 */
     public void doRmUserRoleByRoleId(String roleId) throws Exception
     {
    	 genDao.deleteUserRoleByRoleId(roleId);
     }
     
     
	/** 
	 * 描述: 根据用户ID和角色ID删除用户角色信息
	 * @auther 胡义振
	 * @param perUserRole
	 * @throws Exception 
	 */
	@Override
	public void doRmUserRoleByUserIdAndRoleId(PerUserRole perUserRole) throws Exception
	{
		genDao.deleteUserRoleByUserIdAndRoleId(perUserRole);
	}
	
	/** 
	 * 描述:  根据用户ID,添加用户-角色
	 * @auther 胡义振
      * @param userId 用户ID
      * @param perUserRoleIdList 用户分配的角色 
	 * @throws Exception 
	 */
	@Override
	public void doModUserRole(String userId,String [] perUserRoleIdList) throws Exception
	{
		doRmUserRoleByUserId(userId);
		// 添加用户角色
		if(perUserRoleIdList!=null)
		{
			PerUserRole perUserRole = new PerUserRole();
			for(String perUserRoleId : perUserRoleIdList){
				perUserRole = new PerUserRole();
				perUserRole.setRoleId(perUserRoleId);
				perUserRole.setUserId(userId);
				genDao.insert(perUserRole);
			}
		}
	}


}

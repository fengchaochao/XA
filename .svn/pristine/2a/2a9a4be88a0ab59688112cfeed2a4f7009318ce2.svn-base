package com.prj.biz.dao.maindb.permission;

import java.util.List;

import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.dao._base.BaseDao;

/**
 * 描述: 权限系统--资源DAO接口<br>
 * @author 胡义振
 */
public interface PerResourceDao extends BaseDao<PerResource>
{
    /**
     * 描述: 根据用户ID，查询用户的资源
     * @auther 胡义振
     * @param paramUserId 用户ID
     * @return 用户资源列表
     * @throws Exception 
     */
    public List<PerResource> selectResourceListByUserId(String paramUserId) throws Exception;
    
    /**
     * 描述: 根据用户ID，查询用户菜单资源
     * @auther 胡义振
     * @date 2013-5-23 
     * @param paramUserId 用户ID
     * @return 用户菜单资源列表
     * @throws Exception 
     */
    public List<PerResource> selectResourceMenuListByUserId(String paramUserId) throws Exception;
    
    /**
     * 描述: 根据角色ID，查询角色的资源
     * @auther 胡义振
     * @date 2013-5-23 
     * @param paramRoleId 角色ID
     * @return 角色资源列表
     * @throws Exception 
     */
    public List<PerResource> selectResourceListByRoleId(String paramRoleId) throws Exception;
    
}

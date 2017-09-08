package com.prj.biz.bean.permission;

import java.io.Serializable;

/** 
 * 描述: 权限系统--角色资源<br>
 * @author 胡义振
 * @date 2013-5-13 
 */
public class PerRoleResource implements Serializable
{
	//序列ID
	private static final long	serialVersionUID	= 2271653318286319846L;
	//ID
	private String id;
	//角色ID
	private String roleId;
	//角色名称
	private String roleName;
	//资源ID
	private String resourceId;
	//资源URL
	private String resourceUrl;
	//资源名称
	private String resourceName;
	// 用户ID
	private String userId;
    
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getRoleId()
	{
		return roleId;
	}
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}
	
	public String getResourceId()
	{
		return resourceId;
	}
	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}
	
	public String getRoleName()
	{
		return roleName;
	}
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	
	public String getResourceUrl()
	{
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl)
	{
		this.resourceUrl = resourceUrl;
	}
	
	public String getResourceName()
	{
		return resourceName;
	}
	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "PerRoleResource [id=" + id + ", roleId=" + roleId + ", roleName=" + roleName + ", resourceId="
				+ resourceId + ", resourceUrl=" + resourceUrl + ", resourceName=" + resourceName + ", userId=" + userId
				+ "]";
	}
}

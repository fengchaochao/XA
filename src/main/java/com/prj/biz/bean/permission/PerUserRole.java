package com.prj.biz.bean.permission;

import java.io.Serializable;

/** 
 * 描述: 权限系统--用户角色<br>
 * @author 胡义振
 */
public class PerUserRole implements Serializable
{
	//序列ID
	private static final long	serialVersionUID	= -4606751362195711916L;
	//ID
	private String id;
	//用户ID
	private String userId;
	//角色ID
	private String roleId;
    
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}

	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getRoleId()
	{
		return roleId;
	}
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "PerUserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
	}

}

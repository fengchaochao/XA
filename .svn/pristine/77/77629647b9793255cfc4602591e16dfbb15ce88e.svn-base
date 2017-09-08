package com.prj.biz.bean.permission;

import java.io.Serializable;
import java.util.List;

/** 
 * 描述: 权限系统--角色<br>
 * @author 胡义振
 */
public class PerRole implements Serializable
{
	//序列ID
	private static final long	serialVersionUID	= -70964048215848006L;
	//角色ID
	private String id;
	//角色名称
	private String roleName;
	//角色类型
	private String roleType;
	//用户数
	private String userNum;
	//角色介绍
	private String roleIntroduce;
	//用户是否拥有该角色(true 表示拥有)
	private boolean userOwner;
	// 角色ID
	private String perResourceIds;
	//角色资源
	private List<PerResource> perResourceList;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getRoleName()
	{
		return roleName;
	}
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	
	public String getRoleIntroduce()
	{
		return roleIntroduce;
	}
	public void setRoleIntroduce(String roleIntroduce)
	{
		this.roleIntroduce = roleIntroduce;
	}

	
	public List<PerResource> getPerResourceList() {
		return perResourceList;
	}
	public void setPerResourceList(List<PerResource> perResourceList) {
		this.perResourceList = perResourceList;
	}
	public boolean isUserOwner()
	{
		return userOwner;
	}
	public void setUserOwner(boolean userOwner)
	{
		this.userOwner = userOwner;
	}

	public String getUserNum()
	{
		return userNum;
	}
	public void setUserNum(String userNum)
	{
		this.userNum = userNum;
	}
	
	public String getRoleType()
	{
		return roleType;
	}
	public void setRoleType(String roleType)
	{
		this.roleType = roleType;
	}
	
	public String getPerResourceIds() {
		return perResourceIds;
	}
	public void setPerResourceIds(String perResourceIds) {
		this.perResourceIds = perResourceIds;
	}
	
	@Override
	public String toString() {
		return "PerRole [id=" + id + ", roleName=" + roleName + ", roleType=" + roleType + ", userNum=" + userNum
				+ ", roleIntroduce=" + roleIntroduce + ", userOwner=" + userOwner + ", perResourceIds=" + perResourceIds
				+ ", perResourceList=" + perResourceList + "]";
	}

}

package com.prj.biz.bean.permission;

import java.io.Serializable;

/** 
 * 描述: 权限系统--资源<br>
 * @author 胡义振
 */
public class PerResource implements Serializable
{
	//序列ID
	private static final long	serialVersionUID	= -8909685393616929602L;
	//资源ID
    private String id;
    //上级ID
    private String parentId;
    //资源名称
    private String resName;
    //资源地址
    private String resUrl;
    //资源介绍
    private String resIntroduce;
    //排序顺序
    private String resOrderNum;
    //资源是否属于某一角色
    private boolean roleOwner;
    //菜单标识( 菜单 1 非菜单 0 )
    private String menuFlag;
    //菜单样式
    private String menuStyle;

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}

	public String getParentId()
	{
		return parentId;
	}
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	
	public String getResName()
	{
		return resName;
	}
	public void setResName(String resName)
	{
		this.resName = resName;
	}
	public String getResUrl()
	{
		return resUrl;
	}
	public void setResUrl(String resUrl)
	{
		this.resUrl = resUrl;
	}
	public String getResIntroduce()
	{
		return resIntroduce;
	}
	public void setResIntroduce(String resIntroduce)
	{
		this.resIntroduce = resIntroduce;
	}

	public String getResOrderNum()
	{
		return resOrderNum;
	}
	public void setResOrderNum(String resOrderNum)
	{
		this.resOrderNum = resOrderNum;
	}
	
	public boolean isRoleOwner()
	{
		return roleOwner;
	}
	public void setRoleOwner(boolean roleOwner)
	{
		this.roleOwner = roleOwner;
	}

	public String getMenuStyle()
	{
		return menuStyle;
	}
	public void setMenuStyle(String menuStyle)
	{
		this.menuStyle = menuStyle;
	}
	
	public String getMenuFlag()
	{
		return menuFlag;
	}
	public void setMenuFlag(String menuFlag)
	{
		this.menuFlag = menuFlag;
	}
	
	@Override
	public String toString() {
		return "PerResource [id=" + id + ", parentId=" + parentId + ", resName=" + resName + ", resUrl=" + resUrl
				+ ", resIntroduce=" + resIntroduce + ", resOrderNum=" + resOrderNum + ", roleOwner=" + roleOwner
				+ ", menuFlag=" + menuFlag + ", menuStyle=" + menuStyle + "]";
	}

}

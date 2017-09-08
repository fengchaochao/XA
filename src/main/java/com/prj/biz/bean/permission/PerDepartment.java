package com.prj.biz.bean.permission;

import java.io.Serializable;

/** 
 * 描述: 权限系统--部门<br>
 * @author 王静娟
 */
public class PerDepartment implements Serializable
{
	//序列ID
	private static final long serialVersionUID = 6392403446235617330L;
	//部门ID
    private String id;
    //上级部门ID
    private String parentId;
    //部门名称
    private String departmentName;
 	//排序顺序
    private String depOrderNum;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepOrderNum() {
		return depOrderNum;
	}
	public void setDepOrderNum(String depOrderNum) {
		this.depOrderNum = depOrderNum;
	}
	@Override
	public String toString() {
		return "PerDepartment [id=" + id + ", parentId=" + parentId + ", departmentName=" + departmentName
				+ ", depOrderNum=" + depOrderNum + "]";
	}
	
}

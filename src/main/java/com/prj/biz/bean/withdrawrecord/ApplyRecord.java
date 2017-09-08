package com.prj.biz.bean.withdrawrecord;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 提现记录表 实体类<br>
 * @author Liang
 * @date 2017-08-07
 * 
 */
public class ApplyRecord extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;
    
    private String id;
    //申请时间
    private String applyDate;
    //申请编号
    private String applyNo;
    //申请人编号
    private String applyerNumber;
    //申请人角色
    private String applyRole;
    //申请人 姓名
    private String applyName;
    //提现金额
    private float price;
    //银行卡号
    private String bankNum;
    //持卡人姓名
    private String bankName;
    //申请状态
    private String  applistate;
    
    
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getApplyerNumber() {
		return applyerNumber;
	}
	public void setApplyerNumber(String applyerNumber) {
		this.applyerNumber = applyerNumber;
	}
	public String getApplyRole() {
		return applyRole;
	}
	public void setApplyRole(String applyRole) {
		this.applyRole = applyRole;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getApplistate() {
		return applistate;
	}
	public void setApplistate(String applistate) {
		this.applistate = applistate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}

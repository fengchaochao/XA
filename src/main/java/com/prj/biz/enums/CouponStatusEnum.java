package com.prj.biz.enums;

/** 
* @Description: 优惠卷状态枚举类
* @date 2016年4月15日 
* @author 1936
*/
public enum CouponStatusEnum {

	COUPONSTATUS_CREATE("1","已创建待审核"),
	COUPONSTATUS_AUDITED("2","已审核"),
	COUPONSTATUS_USED("3","已使用"),
	COUPONSTATUS_REVOKE("4","作废");
	
	private String code;
	private String desc;
	
	CouponStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}

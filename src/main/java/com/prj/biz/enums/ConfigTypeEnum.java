package com.prj.biz.enums;

/** 
* @Description: 渠道服务协议类型
* @date 2016年4月19日 
* @author dfsoft
*/
public enum ConfigTypeEnum {

	CONFIGTYPE_TRYOUT("1","试用"),
	CONFIGTYPE_BUY("2","购买");
	
	private String code;
	private String desc;
	
	ConfigTypeEnum(String code,String desc){
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

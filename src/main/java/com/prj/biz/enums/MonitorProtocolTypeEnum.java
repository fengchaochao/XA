package com.prj.biz.enums;

/** 
* @Description: 监控协议类型枚举类
* @date 2016年4月15日 
* @author 1936
*/
public enum MonitorProtocolTypeEnum {
	
	PROTOCOL_TYPE_HTTP("1","HTTP"),
	PROTOCOL_TYPE_SOCKET("2","SOCKET"),
	PROTOCOL_TYPE_DB("3","DB");
	
	private String code;
	private String desc;
	
	MonitorProtocolTypeEnum(String code,String desc){
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

package com.prj.core.constant;

/**
 * 配置项
 * @author 1936
 *
 */
public class ConfigProperties {
	
	// 文件服务器
	public static String IMAGE_SERVER;
	// 文件大小限制
	public static String UPFILE_MAX_SIZE;
	// 文件后缀名
	public static String UPFILE_SUFFIX;

	public static String getIMAGE_SERVER() {
		return IMAGE_SERVER;
	}

	public void setIMAGE_SERVER(String iMAGE_SERVER) {
		IMAGE_SERVER = iMAGE_SERVER;
	}

	public static String getUPFILE_MAX_SIZE() {
		return UPFILE_MAX_SIZE;
	}

	public void setUPFILE_MAX_SIZE(String uPFILE_MAX_SIZE) {
		UPFILE_MAX_SIZE = uPFILE_MAX_SIZE;
	}

	public static String getUPFILE_SUFFIX() {
		return UPFILE_SUFFIX;
	}

	public void setUPFILE_SUFFIX(String uPFILE_SUFFIX) {
		UPFILE_SUFFIX = uPFILE_SUFFIX;
	}
	

}

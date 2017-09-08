package com.prj.core.property;

import java.util.Map;

/** 
* @Description: 动态数据源配置存储
* @date 2016年8月19日 
* @author 1936
*/
public class DynamicDataSourceConfigHolder {
	
	 /**
	  * 定义本地变量，加入存在多个Spring Context连接多个不同的数据源时,可以共用此类。
	  */
	 private static final ThreadLocal<Map<String,String>> dynamicDataSourceConfigHolder = new ThreadLocal<Map<String,String>>();
	 
	 public static void setDynamicConfig(Map<String,String> dynamicDataSourceConfig) {
	     dynamicDataSourceConfigHolder.set(dynamicDataSourceConfig);
	 }
	 
     public static Map<String,String> getDynamicDataSourceConfig() {
         return (dynamicDataSourceConfigHolder.get());
     }
     
     public static void clear() {
         dynamicDataSourceConfigHolder.remove();
     }
}

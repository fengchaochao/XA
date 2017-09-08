package com.prj.utils;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class UfdmPropertiesUtil {

	public static Properties property = null;
	public static Properties springProperty = null;

	/**
	 * 描述: 获取配置文件值
	 * @auther 胡义振
	 * @date 2015-11-6
	 * @param key
	 * @return
	 */
	public static String getProperty(String propertyFileName,String key){
        if (property == null) {
        	try
        	{
                property = getLoadProperties(propertyFileName);
        	}
        	catch(Exception er){
        		 System.out.println("没有找到 hxcodebuild.properties 配置文件");
        	}
        }

        if(property.get(key)==null){
        	return "";
        }
        else{
        	return property.get(key).toString().trim();
        }
	}
	
    public static Properties getLoadProperties(String propertyFileName) {
        Properties prop = new Properties();
        if (propertyFileName == null) {
            return null;
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertyFileName);
        Enumeration<?> enumeration = resourceBundle.getKeys();
        while (enumeration.hasMoreElements()) {
            Object key = enumeration.nextElement();
            prop.put(key, resourceBundle.getObject((String)key));
        }
        return prop;

    }
}

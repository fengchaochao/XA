package com.prj.core.property;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/** 
* @Description: TODO
* @date 2016年8月18日 
* @author 1936
*/
public class UFDMPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	

	
	 protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException
	 {
		 
		 System.out.println("\n props:"+props);
		 
		 super.processProperties(beanFactoryToProcess, props);
		 
	 }
	 

}

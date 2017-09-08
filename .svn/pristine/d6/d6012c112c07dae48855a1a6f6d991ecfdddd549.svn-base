package com.prj.core.shiro;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.prj.utils.UfdmPropertiesUtil;

/** 
* @Description: TODO
* @date 2016年7月29日 
* @author 1936
*/
public class JdbcTemplateUtils {
	
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        if(jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {

        DruidDataSource ds = new DruidDataSource();
        String configFile = "entry/dev/dbconfig";
        ds.setDriverClassName(UfdmPropertiesUtil.getProperty(configFile, "maindb.driver"));
        ds.setUrl(UfdmPropertiesUtil.getProperty(configFile, "maindb.url"));
        ds.setUsername(UfdmPropertiesUtil.getProperty(configFile, "maindb.username"));
        ds.setPassword(UfdmPropertiesUtil.getProperty(configFile, "maindb.password"));

        return new JdbcTemplate(ds);
    }
    

}

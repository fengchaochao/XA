package com.prj.core.shiro;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.service.permission.PerResourceService;


/** 
 * 描述: 加载系统权限<br>
 * @author 胡义振
 * @date 2013-6-5 
 */
public class ShiroChainDefinition  implements FactoryBean<Ini.Section>
{
	//通过配置文件配置的权限
	private String filterChainDefinitions;
	//资源服务类
	@Resource
	private PerResourceService tmplResourceService;
	
    //默认premission字符串
    public static final String PREMISSION_STRING="perms[{0}]";//"perms[\"{0}\"]";

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 描述: 加载系统的权限资源
     * @auther 胡义振
     * @return
     * @throws BeansException
     * @throws Exception
     */
    public Section getObject() throws BeansException,Exception {

        Ini ini = new Ini();
        ini.load(filterChainDefinitions);
        //初始化 section
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        /*
         * 清空 section 
         * 先这里把 section 清空，等业务系统配置的权限加载完，在加载默认的权限
         * 如果初始化就加载默认权限的话，业务系统配置的权限将失效。
         * 比如配置（/**=anon），那么业务系统加载的将失效
         */
        section.clear();
        
        //这里加载业务系统中配置的权限
        List<PerResource> hxResourceList = tmplResourceService.doGetList(null);
        for (Iterator<PerResource> it = hxResourceList.iterator(); it.hasNext();) {
        	PerResource resource = it.next();
        	if(resource.getResUrl()!=null && resource.getResUrl().indexOf("/")!=-1){
        	    section.put(resource.getResUrl(), MessageFormat.format(PREMISSION_STRING,resource.getResUrl())); 
        	}
        }

        //这里在开始加载默认权限
        ini.load(filterChainDefinitions);
        Ini.Section defaultSection = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        if(defaultSection!=null){
        	for(Iterator<?> it = defaultSection.keySet().iterator();it.hasNext();){
        		Object key = it.next();
        		if(key!=null && defaultSection.get(key)!=null){
        		    section.put(key.toString(), defaultSection.get(key));
        		}
        	}
        }
        //显示系统加载的权限(开发调试用)
        
        logger.info("loading hx-permissions ......");
        for(Iterator<?> it = section.keySet().iterator();it.hasNext();){
        	Object key = it.next();
        	logger.info(key+"="+section.get(key));
        }
        logger.info("load completed \n");
        
        return section;
    }
    
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }
	public String getFilterChainDefinitions()
	{
		return filterChainDefinitions;
	}
	
    public Class<?> getObjectType() {
        return this.getClass();
    }

    public boolean isSingleton() {
        return false;
    }


}

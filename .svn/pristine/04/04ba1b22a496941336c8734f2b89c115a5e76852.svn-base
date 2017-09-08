package com.prj.core.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.prj.biz.bean.permission.PerRoleResource;
import com.prj.biz.service.permission.PerRoleResourceService;

/** 
 * 描述: 自定义的 Shiro Realm <br>
 * @author 胡义振
 */
public class UFDMRealm extends AuthorizingRealm 
{
	//权限资源服务类
	@Resource
	private PerRoleResourceService perRoleResourceService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public String getName() {
        return "UFDMRealm";
    }
    
	/** 
	 * 描述: 用户登录
	 * @auther 胡义振
	 * @param token
	 * @return
	 * @throws AuthenticationException 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {  
	    try
		{
	    	// 账号
	    	String principal = (String)authcToken.getPrincipal();
	    	// 密码
	        String credential = new String((char[])authcToken.getCredentials());
	    	return new SimpleAuthenticationInfo(principal,credential,getName());
		}
	    catch (Exception e)
	    {
	    	throw new AuthenticationException("登录出现未知错误");
	    }
    }
	
	/** 
	 * 描述: 用户资源控制
	 * @auther 胡义振
	 * @date 2013-6-3 
	 * @param principals
	 * @return 
	 */
	@Override
    protected  AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{  
		try
		{
			//获取登录信息
			String principal = (String) principals.getPrimaryPrincipal();
			String userId = principal.split("#")[1];
		    //操作权限
		    Set<String> permissions = new HashSet<String>();
		    
		    Set<String> roles = new HashSet<String>();

			PerRoleResource tmplRoleResource = new PerRoleResource();
			tmplRoleResource.setUserId(userId);
			List<PerRoleResource>  hxRoleResourceList = perRoleResourceService.doGetList(tmplRoleResource);
			
			if(hxRoleResourceList!=null){
			    for(PerRoleResource hxRoleRes:hxRoleResourceList){
			    	if(hxRoleRes!=null && hxRoleRes.getResourceUrl()!=null && hxRoleRes.getResourceUrl().indexOf("/")!=-1){
				    	permissions.add(hxRoleRes.getResourceUrl());
				    	roles.add(hxRoleRes.getRoleName());
			    	}
			    }
			}
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		    info.setStringPermissions(permissions);
		    info.setRoles(roles);
		    return info;
		}
		catch (Exception paramExp)
		{
			paramExp.printStackTrace();
			logger.info("load user permissions exception: "+paramExp);
			return null;
		}

    }  

}

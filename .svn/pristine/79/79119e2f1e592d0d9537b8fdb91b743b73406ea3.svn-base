package tutorial.shiro;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/** 
* @Description: TODO
* @date 2016年7月21日 
* @author 1936
*/
public class MyRealmPermission extends AuthorizingRealm {
	
	@Override
	public String getName() {
		return "myRealmPermission";  
	}
	
	@Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
		
		System.out.println("\n 授权验证:"+principals);
		
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        // 添加ROLE
        authorizationInfo.addRole("role1");
        // 添加ObjectPermission对象（指定Permission的实现类）
        authorizationInfo.addObjectPermission(new MyPermission("user:add"));
        // 添加 Str (由 PermissionResolver 实现类处理 )
        authorizationInfo.addStringPermission("user:*");
        authorizationInfo.addStringPermission("test123");
        
        return authorizationInfo;  
    }

	
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
		String username = (String)token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        if("bruce".equals(username) && "888888".equals(password)) {  
            return new SimpleAuthenticationInfo(username, password, getName()); 
        } 
        else{
        	throw new AccountException("用户名、密码错误！"); 
        }
    }  
}

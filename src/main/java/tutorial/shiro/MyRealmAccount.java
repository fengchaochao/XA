package tutorial.shiro;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/** 
* @Description: 账号验证
* @date 2016年7月21日 
* @author 1936
*/
public class MyRealmAccount implements Realm {

	@Override
	public String getName() {
		return "myRealmAccount";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();  //得到用户名  
        String password = new String((char[])token.getCredentials()); //得到密码  
        
        //System.out.println("\n 登录账号规则验证  账号:"+username + ",密码:" + password);
        
        if(username!=null && !username.equals("") && password!=null && password.length()>=6) {  
            return new SimpleAuthenticationInfo(username, password, getName()); 
        } 
        else{
        	throw new AccountException("用户账号、密码不能为空，密码不能至少6位"); 
        }

	}

}

package tutorial.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/** 
* @Description: 权限处理
* @date 2016年7月21日 
* @author 1936
*/
public class MyPermissionResolver implements PermissionResolver {
		
	@Override  
    public Permission resolvePermission(String strPermission) {
		
		//System.out.println("\n 要处理的权限:"+strPermission);
		
		if(strPermission.indexOf(":")>0){
			//System.out.println("\n 通过 WildcardPermission 进行处理");
			return new WildcardPermission(strPermission);
		}
		//System.out.println("\n 通过 MyPermission 进行处理");
		return new MyPermission(strPermission);
    }  

}

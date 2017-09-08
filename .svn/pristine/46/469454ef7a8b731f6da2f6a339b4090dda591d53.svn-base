package tutorial.shiro;

import org.apache.shiro.authz.Permission;

/** 
* @Description: 子定义权限
* @date 2016年7月21日 
* @author 1936
*/
public class MyPermission implements Permission {

	private String mstrPermission;  
    
    public MyPermission(String permissionString) {  
    	mstrPermission = permissionString;
    }  
  
    @Override  
    public boolean implies(Permission p) {
        if(!(p instanceof MyPermission)) {  
            return false;  
        }  
        MyPermission other = (MyPermission) p;  
        
        if(other.mstrPermission.equals("test123")){
        	return true;
        }
        return false;  
    }  
}

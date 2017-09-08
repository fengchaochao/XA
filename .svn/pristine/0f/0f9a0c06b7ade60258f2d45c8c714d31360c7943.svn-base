import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import com.prj.biz.service.sysuser.SysUserService;

/** 
* @Description: TODO
* @date 2016年5月3日 
* @author 1936
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:entry/spring-servlet.xml")  
public class TestJunit {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Test 
	public void testSayHello() throws Exception {
		
		System.out.println(sysUserService.doGetById("0AC552C107AB11E694421C872C64BAC2"));
	}
}

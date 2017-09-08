import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.prj.biz.service.sysuser.SysUserService;


@ContextConfiguration(locations="classpath:entry/spring-servlet.xml") 
public class TestNG extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private SysUserService sysUserService; 
	
	
	@Test  
	public void testSayHello() throws Exception {
		System.out.println("结果："+sysUserService.doGetById("0AC552C107AB11E694421C872C64BAC2"));
	}
}
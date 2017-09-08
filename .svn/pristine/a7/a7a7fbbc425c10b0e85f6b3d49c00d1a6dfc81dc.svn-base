package com.prj.biz.api.easemob.server.example.test;

import io.swagger.client.model.NewPassword;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


import com.prj.biz.api.easemob.server.example.api.impl.EasemobIMUsers;

import java.util.Random;

import junit.framework.Assert;

/**
 * Created by easemob on 2017/3/21.
 */
public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
    private static EasemobIMUsers easemobIMUsers = new EasemobIMUsers();

    @Test
    public static void createUser(String userName,String password) {
        RegisterUsers users = new RegisterUsers();
        io.swagger.client.model.User payload = new io.swagger.client.model.User().username(userName).password(password);
        users.add(payload);
        Object result = easemobIMUsers.createNewIMUserSingle(users);
        logger.info(result.toString());
        Assert.assertNotNull(result);
    }

    
    @Test
    public static int getUserByName(String userName) {
    	int num = 0;
        Object result = easemobIMUsers.getIMUserByUserName(userName);
        if(result != null && !result.equals("null")){
        	num = 1;
        }
        System.out.println(num);
        return num;
    }

    @Test
    public static void gerUsers(Long limit,String cursor) {
        Object result = easemobIMUsers.getIMUsersBatch(limit, cursor);
        logger.info(result.toString());
    }

    @Test
    public static void changePassword(String userName,String newPass) {
        NewPassword psd = new NewPassword().newpassword(newPass);
        Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(userName, psd);
        logger.info(result.toString());
    }

    @Test
    public static void getFriend(String userName) {
        Object result = easemobIMUsers.getFriends(userName);
        logger.info(result.toString());
    }
    
    public static void main(String[] args) {
		//测试注册(成功)
    	//createUser("15829204801","123456");
    	// 根据用户登陆账号或者账号信息(成功)
    	getUserByName("18629426338");
    	// 根据游标分页好友数据(成功)
    	//gerUsers(Long.parseLong("10"), "0");
    	// 根据登陆账号修改密码(成功)
    	//changePassword("18629426339","234567");
    	// 根据登陆名获取该人的好友信息(成功)
    	//getFriend("18629426339");
	}
}

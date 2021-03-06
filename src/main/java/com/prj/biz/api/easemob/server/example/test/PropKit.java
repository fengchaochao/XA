package com.prj.biz.api.easemob.server.example.test;


import com.prj.biz.api.easemob.server.example.api.impl.EasemobIMUsers;

import io.swagger.client.model.NewPassword;
import io.swagger.client.model.RegisterUsers;

public class PropKit {

	//huanxin_client_secret
	//huanxin_ client_id
	
	
	
	/**
	 * 注册后申请环信账号
	 */
	public static void registerHunxin(String phone,String password){
		RegisterUsers user  = new RegisterUsers();
		//创建环信账号
		io.swagger.client.model.User payload = new io.swagger.client.model.User().username(phone).password(password);
		user.add(payload);
		EasemobIMUsers ease = new EasemobIMUsers();
		//注册环信账号
		Object result = ease.createNewIMUserSingle(user);
		System.out.println(result);
	}
	
	/**
	 * 登陆
	 * @param phone
	 * @param password
	 */
	public static void denglu(String phone,String password){
		
	}
	
	
	/**
	 * 获取用户
	 * @param phone
	 * @param password
	 */
	public static void selectAll(){
		EasemobIMUsers ease = new EasemobIMUsers();
		Object result = ease.getIMUsersBatch(Long.parseLong("10"),"0");
		System.out.println(result);
	}
	
	/**
	 * 添加好友
	 * @param userName 本人登录名
	 * @param friendName 好友登录名
	 */
	public static void addFriend(String userName, String friendName){
		EasemobIMUsers ease = new EasemobIMUsers();
		Object result = ease.addFriendSingle(userName, friendName);
		System.out.println(result);
	}
	
	/**
	 * 修改密码
	 * @param userName
	 * @param newPass
	 */
	public static void changePassword(String userName,String newPass) {
		EasemobIMUsers ease = new EasemobIMUsers();
        NewPassword psd = new NewPassword().newpassword(newPass);
        Object result = ease.modifyIMUserPasswordWithAdminToken(userName, psd);
        System.out.println(result);
    }
	
	/**
	 * 强制环信用户下线
	 * @param userName
	 */
	public static void disconnectIMUser(String userName){
		EasemobIMUsers ease = new EasemobIMUsers();
		Object result = ease.disconnectIMUser(userName);
		System.out.println(result);
	}
	
	/**
	 * 检测该用户是否已存在  0为不存在，1为已存在
	 * @param userName
	 * @return
	 */
	public static int getUserByName(String userName) {
    	EasemobIMUsers ease = new EasemobIMUsers();
    	int num = 0;
        Object result = ease.getIMUserByUserName(userName);
        if(result != null && !result.equals("null")){
        	num = 1;
        }
        System.out.println(num);
        return num;
    }
	
	public static void main(String[] args) {
		//registerHunxin("17858627414", "111111");
		//selectAll();
		//addFriend("123","18629426339");
		//disconnectIMUser("18066633913");
		//getUserByName("17858627414");
		changePassword("13486171969", "123456");
	}
}

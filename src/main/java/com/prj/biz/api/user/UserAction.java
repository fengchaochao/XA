package com.prj.biz.api.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prj.biz.action.upfile.FileUploadController;
import com.prj.biz.api.easemob.server.example.test.PropKit;
import com.prj.biz.bean.about.About;
import com.prj.biz.bean.advertising.Advertising;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.message.Message;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.enums.RespMessEnum;
import com.prj.biz.service.about.AboutService;
import com.prj.biz.service.advertising.AdvertisingService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.message.MessageService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.core.bean.exp.UfdmException;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.UfdmMd5Util;
import com.prj.utils.excel.IDUtils;

/**
 * @author: Fengc
 * @date:2017-8-8 上午8:32:12
 * @version :0.0.1
 * @dis:Api用户信息
 */
@Controller
@RequestMapping("/api")
public class UserAction {

	@Autowired
	SysUserService userService;
	
	@Autowired
	private BusinessInformationService businessInformationService;
	
	@Autowired
	private ConsumersService consumersService;
	@Autowired
	private AboutService aboutService;
	@Autowired
	private AdvertisingService advertisingService;
	@Autowired
	private MessageService messageService;

	/**
	 * App用户注册
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppRegister")
	@ResponseBody
	public RespBean<String> AppRegister(String telephone, String password)
			throws Exception {
		RespBean<String> respBean = new RespBean<String>();
		try {
			SysUser sysUser = new SysUser();
			sysUser.setLoginName(telephone);
			// 登录
			SysUser loginSysUser = userService.doGetSysUserForLogin(sysUser);

			// 判断登录是否为空
			if (loginSysUser == null || loginSysUser.getId() == null|| "".equals(loginSysUser.getId())) {
				//先生成对应的消费者信息
				Consumers consumers=new Consumers();
				consumers.setPhone(telephone);
				consumers.setIsXfconsumers("0");
				consumers.setMerchantsState("0");
				consumers.setCreateDate(UfdmDateUtil.getCurrentTime());
				int i = (int) (Math.random() * 900) + 100;
				String code = "HLX" + UfdmDateUtil.getCurrentDate1()+ UfdmDateUtil.getCurrentSimpleTime1() + i;
				consumers.setCustomerNumber(code);
				//注册新账号时默认生成一个昵称
				String name = "HL" + telephone.substring(telephone.length() - 8,telephone.length());
				consumers.setNickName(name);	
				consumersService.doSave(consumers);
				
				sysUser.setLoginPass(UfdmMd5Util.MD5Encode(password));
				sysUser.setUserStatus("0");
				sysUser.setUserState("4");
				sysUser.setMobile(telephone);
				sysUser.setTelephone(telephone);
				sysUser.setRealName(name);
				sysUser.setMerchantsId(consumers.getId());
				userService.doSave(sysUser);
				
				//注册环信账号
				PropKit.registerHunxin(telephone, password);
				

				respBean.setBody("注册成功！");
			} else {
				respBean.setBody("该用户已存在，注册失败！");
			}

		} catch (Exception e) {
			respBean.setBody("系统异常！");
		}

		return respBean;
	}


	/**
	 * App判断手机号是否已存在
	 * 
	 * @param telephone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppCheckPhone")
	@ResponseBody
	public RespBean<String> AppCheckPhone(String telephone) throws Exception {
		RespBean<String> respBean = new RespBean<String>();
		try {
			SysUser sysUser = new SysUser();
			sysUser.setTelephone(telephone);
			// 登录
			//SysUser loginSysUser = userService.doGetSysUserForLogin(sysUser);
			List<SysUser> sysUserList = userService.doGetList(sysUser);
			// 判断登录是否为空
			if (sysUserList.size() >0 ) {
				respBean.setBody("该手机号已存在");
			} else {
				respBean.setBody("该手机号不存在");
			}
			
			//环信检测该手机号在环信中是否注册过
			int num = PropKit.getUserByName(telephone);
			if(num == 1){
				respBean.setBody("该手机号已存在");
			}

		} catch (Exception e) {
			respBean.setBody("系统异常");
		}

		return respBean;

	}

	/**
	 * App用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppLogin")
	@ResponseBody
	public RespBean<Map<String, Object>> AppLogin(String telephone, String password,String deviceNumber) throws Exception {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser sysUser = new SysUser();
		// 密码MD5摘要
		sysUser.setLoginPass(UfdmMd5Util.MD5Encode(password));
		sysUser.setLoginName(telephone);
		//sysUser.setTelephone(telephone);
		// 登录ng
		SysUser loginSysUser = userService.doGetSysUserForLogin(sysUser);

		// 判断登录是否成功
		if (loginSysUser == null || loginSysUser.getId() == null|| "".equals(loginSysUser.getId())) {
			throw new UfdmException(RespMessEnum.RESP_CODE_0001002.getRespCode());
		} else {
				//判断只有商家，消费商，消费者可以登录App
				if("0".equals(loginSysUser.getUserState())||"1".equals(loginSysUser.getUserState())){
					throw new UfdmException(RespMessEnum.RESP_CODE_0001002.getRespCode());
				}
				//判断是否被停用
				if (loginSysUser != null &&"1".equals(loginSysUser.getUserStatus()))	{			
					throw new UfdmException(RespMessEnum.RESP_CODE_0001007.getRespCode());
				}
				//判断拿到商家或者消费商对应的信息
				if("2".equals(loginSysUser.getUserState())){
					BusinessInformation businessInformation=businessInformationService.doGetById(loginSysUser.getMerchantsId());
					loginSysUser.setPersonPhoto(businessInformation.getStorePhotos());
					map.put("businessInformation", businessInformation);
				}
				//判断拿到商家或者消费商对应的信息
				if("3".equals(loginSysUser.getUserState())){
					Consumers businessInformation=consumersService.doGetById(loginSysUser.getMerchantsId());
					map.put("businessInformation", businessInformation);
				}
				//登陆成功后，修改手机设备号存进数据库
				if(deviceNumber != null && !deviceNumber.equals("")){
					SysUser user = new SysUser();
					String id = loginSysUser.getId();
					user.setId(id);
					user.setDeviceNumber(deviceNumber);
					userService.doModById(user);
				}
				map.put("data", loginSysUser);
		}
		respBean.setBody(map);
		return respBean;

	}
	
	/**
	 * App商家信息和会员信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppUserInfo")
	@ResponseBody
	public RespBean<Map<String, Object>> AppUserInfo(String userId) throws Exception {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Consumers consumer = new Consumers();
		//获取当前登录人的信息
		SysUser sysUser = userService.doGetById(userId);
		if(sysUser != null && !sysUser.equals("")){
			//根据当前登录人的类型来获取当前登录人的详细信息
			// 用户类型0-总部 1-代理商 2-商家  3-消费商 4-消费者 5-消费者账户
			if(sysUser.getUserState().equals("2")){
				//根据id获取商家详细信息
				BusinessInformation businessInformation = businessInformationService.selectByIdInfo(sysUser.getMerchantsId());
				businessInformation.setPersonPhoto(businessInformation.getStorePhotos());
				if(businessInformation != null){
					map.put("businessInformation", businessInformation);
				}
			}else if(sysUser.getUserState().equals("3") || sysUser.getUserState().equals("4")){
				consumer.setId(sysUser.getMerchantsId());
				Consumers consumers = consumersService.selectByIdInfo(consumer);
				consumers.setPersonPhoto(sysUser.getPersonPhoto());
				if(consumers != null){
					map.put("businessInformation", consumers);
				}
			}
		}
		respBean.setBody(map);
		return respBean;
	}
	
	
	/**
	 * App会员修改昵称和个性签名（消费商、消费者）
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppUpdateConsumer")
	@ResponseBody
	public RespBean<Consumers> AppUpdateConsumer(Consumers consumers) throws Exception {
		RespBean<Consumers> respBean = new RespBean<Consumers>();
		consumersService.doModById(consumers);
		if(!StringUtils.isEmpty(consumers.getNickName())){
			SysUser sysUser1 = new SysUser();
			sysUser1.setMerchantsId(consumers.getId());
			List<SysUser> sysUserList = userService.doGetList(sysUser1);
			if (sysUserList.size() >0) {
				sysUserList.get(0).setRealName(consumers.getNickName());
				userService.doModById(sysUserList.get(0));
			}
		}
		respBean.setBody(consumers);
		return respBean;
	}
	
	/**
	 * 
	 * App修改用户头像
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppUpdatePerSon")
	@ResponseBody
	public RespBean<SysUser> AppUpdatePerSon(SysUser sysUser,@RequestParam("upufdmfile")MultipartFile  upufdmfile) throws Exception {
		RespBean<SysUser> respBean = new RespBean<SysUser>();
		FileUploadController fileUploadController = new FileUploadController();
        MultipartFile file = upufdmfile;  
        //上传图片  
        RespBean<Map<String,String>> respMap = fileUploadController.handleFileUpload(file);
        String idPhoto = respMap.getBody().get("upfileFilePath");
		        
		SysUser sysUser2=userService.doGetById(sysUser.getId());
		sysUser2.setPersonPhoto(idPhoto);
		userService.doModById(sysUser2);
		
		respBean.setBody(sysUser);
		return respBean;
	}
	/**
	 * App修改商家店铺介绍
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppUpdateBusiness")
	@ResponseBody
	public RespBean<BusinessInformation> AppUpdateBusiness(BusinessInformation businessInformation) throws Exception {
		RespBean<BusinessInformation> respBean = new RespBean<BusinessInformation>();
		businessInformationService.doModById(businessInformation);
		respBean.setBody(businessInformation);
		return respBean;
	}
	
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/AppAboutUs")
	@ResponseBody
	public RespBean<RespPagination<About>> AppAboutUs(About about) throws Exception{
		RespBean<RespPagination<About>> respBean = new RespBean<RespPagination<About>>();
		RespPagination<About> respPagination = new RespPagination<About>();
		Integer total = aboutService.doGetTotal(about);
		List<About> aboutsList =  aboutService.doGetList(about);;
		respPagination.setTotal(total);
		respPagination.setRows(aboutsList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	
	/**
	 * 描述: 推送消息(广告)
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/AppAdvertising")
	@ResponseBody
	public RespBean<RespPagination<Advertising>> AppAdvertising(Advertising advertising,String sellerId) throws Exception{
		RespBean<RespPagination<Advertising>> respBean = new RespBean<RespPagination<Advertising>>();
		RespPagination<Advertising> respPagination = new RespPagination<Advertising>();
		Integer total = 0;
		List<Advertising> advertisingList = new ArrayList<Advertising>();
		//获取消费者或者消费商的区域id
		Consumers consumers = consumersService.doGetById(sellerId);
		if(consumers != null ){
			String areaid = consumers.getAreaId();
			advertising.setReleaseRange(areaid);
			advertising.setCheckState("4");
			advertising.setUserId("");
			total = advertisingService.doGetTotal(advertising);
			advertisingList =  advertisingService.doGetList(advertising);
		}
		respPagination.setTotal(total);
		respPagination.setRows(advertisingList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	
	/**
	 * App申请成为消费商
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppUpdateConsumers")
	@ResponseBody
	public RespBean<Consumers> AppUpdateConsumers(Consumers consumers,@RequestParam("upufdmfile")MultipartFile[]  upufdmfile) throws Exception {
		RespBean<Consumers> respBean = new RespBean<Consumers>();
		FileUploadController fileUploadController = new FileUploadController();
		String idPhoto = "";
		
		try {
			//判断upufdmfile数组不能为空并且长度大于0  
			if(upufdmfile!=null&&upufdmfile.length>0){  
			    //循环获取upufdmfile数组中得文件  
			    for(int i = 0;i<upufdmfile.length;i++){  
			        MultipartFile file = upufdmfile[i];  
			        //上传图片  
			        RespBean<Map<String,String>> respMap = fileUploadController.handleFileUpload(file);
			        if(StringUtils.isEmpty(idPhoto)){
			        	idPhoto += respMap.getBody().get("upfileFilePath");
			        }else{
			        	idPhoto += ","+respMap.getBody().get("upfileFilePath");
			        }
			        
			    }  
			}
			
			consumers.setApplyUpgrade("0");
			consumers.setIdPhoto(idPhoto);
			consumersService.doModById(consumers);
			respBean.setBody(consumers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return respBean;
	}
	
	/**
	 * 描述: 推送消息（系统消息）
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/AppMessage")
	@ResponseBody
	public RespBean<RespPagination<Message>> AppMessage(Message message) throws Exception{
		RespBean<RespPagination<Message>> respBean = new RespBean<RespPagination<Message>>();
		RespPagination<Message> respPagination = new RespPagination<Message>();
		
		List<Message> messageList = messageService.selectListByCode(message);
		message.setLimit(0);
		message.setOffset(0);
		List<Message> messageList1 = messageService.selectListByCode(message);
		Integer total = messageList1.size();
		
		respPagination.setTotal(total);
		respPagination.setRows(messageList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 消费者成为消费商的详情数据
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/AppConsumersInfo")
	@ResponseBody
	public RespBean<Map<String, Object>> AppConsumersInfo(String sellerId) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Consumers consumers = consumersService.doGetById(sellerId);
		if(consumers == null){
			Consumers consumer = new Consumers();
			map.put("consumers", consumer);
		}else {
			map.put("consumers", consumers);
		}
		
		respBean.setBody(map);
		return respBean;
	}
	

}

package com.prj.biz.api.memberCenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Transfer;
import com.prj.biz.api.easemob.server.example.test.PropKit;
import com.prj.biz.bean.bankwithdrawal.BankWithdrawal;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.goodsclassification.GoodsClassification;
import com.prj.biz.bean.goodsevaluate.GoodsEvaluate;
import com.prj.biz.bean.leavingmessage.LeavingMessage;
import com.prj.biz.bean.order.MyEarnings;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.redenveloperecord.RedEnvelopeRecord;
import com.prj.biz.bean.sharerecord.ShareRecord;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transactionrecords.TransactionRecords;
import com.prj.biz.bean.withdrawrecord.WithdrawRecord;
import com.prj.biz.service.bankwithdrawal.BankWithdrawalService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.goodsclassification.GoodsClassificationService;
import com.prj.biz.service.goodsevaluate.GoodsEvaluateService;
import com.prj.biz.service.leavingmessage.LeavingMessageService;
import com.prj.biz.service.order.OrderService;
import com.prj.biz.service.redenveloperecord.RedEnvelopeRecordService;
import com.prj.biz.service.sharerecord.ShareRecordService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.service.transactionrecords.TransactionRecordsService;
import com.prj.biz.service.withdrawrecord.WithdrawRecordService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.utils.SmsUtils;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.UfdmMd5Util;
import com.prj.utils.UfdmRandomlyGenerated;
import com.prj.utils.ping.TransferExample;

/**
 * @author: Fengc
 * @date:2017-7-18 下午2:48:33
 * @version :0.0.1
 * @dis:App会员中心API_ACTION,
 */
@Controller
@RequestMapping("/api")
public class MemberCenterAction {

	@Autowired
	private BusinessInformationService businessInformationService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	
	@Autowired
	private GoodsEvaluateService goodsEvaluateService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TransactionRecordsService transactionRecordsService;
	
	@Autowired
	private LeavingMessageService leavingMessageService;
	
	@Autowired
	private ShareRecordService shareRecordService;

	@Autowired
	private ConsumersService consumersService;
	
	@Autowired
	private BankWithdrawalService bankWithdrawalService;
	
	@Autowired
	private RedEnvelopeRecordService envelopeRecordService;
	
	@Autowired
	private WithdrawRecordService withdrawRecordService;
	/**
	 * 在线客服->
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 *            用户ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doEnLeavingMessageList")
	@ResponseBody
	public  RespBean<RespPagination<LeavingMessage>> doEnLeavingMessageList(String sellerId,String userType) {
		RespBean<RespPagination<LeavingMessage>> respBean = new RespBean<RespPagination<LeavingMessage>>();
		RespPagination<LeavingMessage> respPagination = new RespPagination<LeavingMessage>();
		try {
			//今日锁定的消费者
			LeavingMessage leavingMessage = new LeavingMessage();
			leavingMessage.setUserId(sellerId);
			leavingMessage.setOrder("asc");
			leavingMessage.setOrderName("leavingMessage_date");
			List<LeavingMessage> messages = leavingMessageService.doGetAllList(leavingMessage);
			Integer total = leavingMessageService.doGetTotal(leavingMessage);
			respPagination.setTotal(total);
			respPagination.setRows(messages);
			
		} catch (Exception e) {
		}
		
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 在线客服 -发送留言
	 * @param request
	 * @param response
	 * @param leavingMessage 客服留言对象
	 * @return
	 */
	@RequestMapping(value = "/doEnSaveLeavingMessage",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public RespBean<LeavingMessage> doEnSaveLeavingMessage(LeavingMessage leavingMessage) {
		RespBean<LeavingMessage> respBean = new RespBean<LeavingMessage>();
		try {
			leavingMessage.setStatus("1");
			leavingMessage.setLeavingmessageDate(UfdmDateUtil.getCurrentTime());
			leavingMessageService.doSave(leavingMessage);
			respBean.setBody(leavingMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respBean;
	}
	/**
	 * 修改密码/注册-发送验证码
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/doEnSendCode")
	@ResponseBody
	public RespBean<String> doEnSendCode(String phone,String userId) {
		RespBean<String> respBean = new RespBean<String>();
		try {
			if(StringUtils.isEmpty(userId)){
				//随机生成验证码
				String code=UfdmRandomlyGenerated.createRandomVcode(6);
				//发送短信
				SmsUtils smsUtils =new SmsUtils();
				SendSmsResponse response = smsUtils.sendSms(phone,"SMS_90925065","{\"code\":\""+code+"\"}");
				/*Subject sub = SecurityUtils.getSubject();
				sub.getSession().setAttribute(SysConstants.SESSION_V_CODE, code);*/
				if("OK".equals(response.getMessage())){
					respBean.setBody(code);
				}else{
					respBean.setBody("发送失败");
				}
			}else{
				SysUser sysUser=sysUserService.doGetById(userId);
				if(sysUser==null){
					respBean.setBody("手机号不存在");
				}else{
					//随机生成验证码
					String code=UfdmRandomlyGenerated.createRandomVcode(6);
					//发送短信
					SmsUtils smsUtils =new SmsUtils();
					SendSmsResponse response = smsUtils.sendSms(phone,"SMS_89650060","{\"code\":\""+code+"\"}");
					/*Subject sub = SecurityUtils.getSubject();
					sub.getSession().setAttribute(SysConstants.SESSION_V_CODE, code);*/
					if("OK".equals(response.getMessage())){
						respBean.setBody(code);
					}else{
						respBean.setBody("发送失败");
					}
				}
			}
		} catch (InvalidSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respBean;
	}
	/**
	 * 描述:修改密码
	 * @auther Liang
	 * @date 2017-08-21
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doEnUpdatePassword")
	@ResponseBody
	public RespBean<String> doEnUpdatePassword(String userId,String code,String loginPass) throws Exception
	{
		RespBean<String> resp=new RespBean<String>();
		SysUser sysUser=sysUserService.doGetById(userId);
		if(sysUser!=null){
			sysUser.setLoginPass(UfdmMd5Util.MD5Encode(loginPass));
			sysUserService.doModById(sysUser);
			//修改环信密码
			PropKit.changePassword(sysUser.getLoginName(), loginPass);
			resp.setBody("成功修改密码");
	    }
		return resp;
	 }
	/**
	 * 描述:忘记密码验证手机号并发送验证码
	 * @auther Liang
	 * @date 2017-08-21
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("doEnForgetSendCode")
	@ResponseBody
	public RespBean<String> doEnForgetSendCode(String phone) throws Exception
	{
		RespBean<String> respBean = new RespBean<String>();
		String code="";
		SysUser sysUser=new SysUser();
		sysUser.setTelephone(phone);
		List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
		if(sysUsers.size()>0){
			code=UfdmRandomlyGenerated.createRandomVcode(6);
			//发送短信
			SmsUtils smsUtils =new SmsUtils();
			SendSmsResponse response = smsUtils.sendSms(phone,"SMS_84700105","{\"code\":\""+code+"\"}");
			if("OK".equals(response.getMessage())){
				respBean.setBody(code);
			}else{
				respBean.setBody("发送失败"); 
			}
		}else{
			respBean.setBody("该账号不存在！"); 
		}
		return respBean;
	}
	/**
	 * 描述:找回密码
	 * @auther Liang
	 * @date 2017-08-21
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doEnFindPassword")
	@ResponseBody
	public RespBean<String> doEnFindPassword(String phone,String loginPass) throws Exception
	{
		RespBean<String> resp=new RespBean<String>();
		SysUser sysUser=new SysUser();
		sysUser.setTelephone(phone);
		List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
		if(sysUsers.size()>0){
			sysUsers.get(0).setLoginPass(UfdmMd5Util.MD5Encode(loginPass));
			sysUserService.doModById(sysUsers.get(0));
			//修改环信密码
			PropKit.changePassword(phone, loginPass);
			resp.setBody("成功找回密码");
		}else{
			resp.setBody("手机号不存在");
		}
		return resp;
	}
	/**
	 * 会员中心-推广盈利-今日及本月推广盈利
	 * @param request
	 * @param response
	 * @param shareRecord 分享记录表
	 * @return
	 */
	@RequestMapping(value = "/doEnShareRecord")
	@ResponseBody
	public RespBean<Map<String, Object>> doEnShareRecord(ShareRecord shareRecord) {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//今日推广佣金
			float todayPrice=0;
			shareRecord.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<ShareRecord> shareRecords=shareRecordService.doGetList(shareRecord);
			for (int i = 0; i < shareRecords.size(); i++) {
				if(shareRecords.get(i)!=null){
					todayPrice+=Float.parseFloat(shareRecords.get(i).getShareFee());
				}
			}
			//本月推广佣金
			shareRecord.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<ShareRecord> shareRecordList=shareRecordService.doGetList(shareRecord);
			map.put("todayPrice", todayPrice);
			map.put("shareRecordList", shareRecordList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respBean.setBody(map);
		return respBean;
	}
	/**
	 * 会员中心-历史总的推广盈利
	 * @param request
	 * @param response
	 * @param shareRecord 分享记录表
	 * @return
	 */
	@RequestMapping(value = "/doEnHistoryShareRecord")
	@ResponseBody
	public RespBean<RespPagination<ShareRecord>> doEnHistoryShareRecord(ShareRecord shareRecord) {
		RespBean<RespPagination<ShareRecord>> respBean = new RespBean<RespPagination<ShareRecord>>();
		RespPagination<ShareRecord> respPagination = new RespPagination<ShareRecord>();
		try {
			if(StringUtils.isEmpty(shareRecord.getStartTime())&&StringUtils.isEmpty(shareRecord.getEndTime())){
				shareRecord.setCreateDate("1");
			}
			List<ShareRecord> shareRecordList=shareRecordService.doGetList(shareRecord);
			respPagination.setRows(shareRecordList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 会员中心-我的订单
	 * @param request
	 * @param response
	 * @param order 订单表
	 * @return
	 */
	@RequestMapping(value = "/doEnMyOrder")
	@ResponseBody
	public RespBean<RespPagination<Order>> doEnMyOrder(Order order) {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		try {
			List<Order> orderList=orderService.doGetListApi(order);
			for (Order order2 : orderList) {
				//配送地址
				String address=order2.getProvince()+"省"+order2.getCity()+order2.getArea()+order2.getDistributionAddress();
				order2.setDistributionAddress(address);
				//购买的商品记录
				TransactionRecords transactionRecords=new TransactionRecords();
				transactionRecords.setOrderNo(order2.getId());
				transactionRecords.setIsGoodsTotal("0");
				List<TransactionRecords> transactionRecordsList=transactionRecordsService.doGetList(transactionRecords);
				for (TransactionRecords transactionRecords2 : transactionRecordsList) {
					transactionRecords2.setGoodsName(transactionRecords2.getGoods().getCommodityName());
					transactionRecords2.setGoodsId(transactionRecords2.getGoods().getId());
					transactionRecords2.setGoodsUtilName(transactionRecords2.getGoodsSpecifications().getSpecificationsName());
					transactionRecords2.setGoodsPrice(String.valueOf(transactionRecords2.getGoodsSpecifications().getPrice()));
					GoodsClassification goodsClassification=goodsClassificationService.doGetById(transactionRecords2.getGoods().getCommodityTypeId());
					transactionRecords2.setGoodsType(goodsClassification.getCategoryName());
					//查看是否已评价
					GoodsEvaluate goodsEvaluate=new GoodsEvaluate();
					goodsEvaluate.setGoodsUnitId(transactionRecords2.getGoodsUnitId());
					List<GoodsEvaluate> goodsEvaluates=goodsEvaluateService.doGetList(goodsEvaluate);
					if(goodsEvaluates.size()>0){
						transactionRecords2.setIsEnveate(goodsEvaluates.get(0).getStatus());
					}else{
						transactionRecords2.setIsEnveate("0");
					}
				}
				order2.setTransactionRecords(transactionRecordsList);
				Consumers consumers=consumersService.doGetById(order2.getPurchaserId());
				if(consumers!=null){
					order2.setConsumersName(consumers.getNickName());
					SysUser sysUser=new SysUser();
					sysUser.setMerchantsId(consumers.getId());
					List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
					if(sysUsers.size()>0){
						order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
					}
				}else{
					BusinessInformation businessInformation=businessInformationService.doGetById(order2.getPurchaserId());
					order2.setConsumersName(businessInformation.getVendorName());
					SysUser sysUser=new SysUser();
					sysUser.setMerchantsId(businessInformation.getId());
					List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
					if(sysUsers.size()>0){
						order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
					}
				}
			}
			order.setLimit(0);
			order.setOffset(0);
			List<Order> orderList1=orderService.doGetListApi(order);
			respPagination.setTotal(orderList1.size());
			respPagination.setRows(orderList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 会员中心-订单详情
	 * @param request
	 * @param response
	 * @param orderId 订单表id
	 * @return
	 */
	@RequestMapping(value = "/doEnMyOrderView")
	@ResponseBody
	public RespBean<Order> doEnMyOrderView(String orderId) {
		RespBean<Order> respBean = new RespBean<Order>();
	    try {
			Order order=orderService.doGetById(orderId);
			//配送地址
			String address=order.getProvince()+"省"+order.getCity()+order.getArea()+order.getDistributionAddress();
			order.setDistributionAddress(address);
			//购买的商品记录
			TransactionRecords transactionRecords=new TransactionRecords();
			transactionRecords.setOrderNo(order.getId());
			transactionRecords.setIsGoodsTotal("0");
			List<TransactionRecords> transactionRecordsList=transactionRecordsService.doGetList(transactionRecords);
			for (TransactionRecords transactionRecords2 : transactionRecordsList) {
				transactionRecords2.setGoodsId(transactionRecords2.getGoods().getId());
				transactionRecords2.setGoodsName(transactionRecords2.getGoods().getCommodityName());
				transactionRecords2.setGoodsUtilName(transactionRecords2.getGoodsSpecifications().getSpecificationsName());
				transactionRecords2.setGoodsPrice(String.valueOf(transactionRecords2.getGoodsSpecifications().getPrice()));
				transactionRecords2.setGoodsPhoto(transactionRecords2.getGoods().getCommodityImages());
				GoodsClassification goodsClassification=goodsClassificationService.doGetById(transactionRecords2.getGoods().getGoodsClassification().getId());
				transactionRecords2.setGoodsType(goodsClassification.getCategoryName());
				//查看是否已评价
				GoodsEvaluate goodsEvaluate=new GoodsEvaluate();
				goodsEvaluate.setGoodsUnitId(transactionRecords2.getGoodsUnitId());
				List<GoodsEvaluate> goodsEvaluates=goodsEvaluateService.doGetList(goodsEvaluate);
				if(goodsEvaluates.size()>0){
					transactionRecords2.setIsEnveate(goodsEvaluates.get(0).getStatus());
				}else{
					transactionRecords2.setIsEnveate("0");
				}
				
			}
			order.setTransactionRecords(transactionRecordsList);
			Consumers consumers=consumersService.doGetById(order.getPurchaserId());
			if(consumers!=null){
				order.setConsumersName(consumers.getNickName());
				SysUser sysUser=new SysUser();
				sysUser.setMerchantsId(consumers.getId());
				List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
				if(sysUsers.size()>0){
					order.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
				}
			}else{
				BusinessInformation businessInformation=businessInformationService.doGetById(order.getPurchaserId());
				order.setConsumersName(businessInformation.getVendorName());
				SysUser sysUser=new SysUser();
				sysUser.setMerchantsId(businessInformation.getId());
				List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
				if(sysUsers.size()>0){
					order.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
				}
			}	
			respBean.setBody(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respBean;
	}
	/**
	 * 我的订单-订单详情-评价详情
	 * @param request
	 * @param response
	 * @param goodsEvaluate 
	 * @return
	 */
	@RequestMapping(value = "/doEnGetByGoodsEvaluate",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public RespBean<GoodsEvaluate> doEnGetByGoodsEvaluate(GoodsEvaluate goodsEvaluate) {
		RespBean<GoodsEvaluate> respBean = new RespBean<GoodsEvaluate>();
		try {
			List<GoodsEvaluate> goodsEvaluates=goodsEvaluateService.doGetList(goodsEvaluate);
			if(goodsEvaluates.size()>0){
				goodsEvaluate=goodsEvaluates.get(0);
			}
			respBean.setBody(goodsEvaluate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respBean;
	}
	/**
	 * 描述: 账户管理-我的账户
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doMyAccountList")
	@ResponseBody
	public RespBean<Map<String, Object>> doMyAccountList(ConsumersAccount consumersAccount,String userId) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		BankWithdrawal bankWithdrawal=new BankWithdrawal();
		bankWithdrawal.setUserId(userId);
		List<BankWithdrawal> bankWithdrawals=bankWithdrawalService.doGetList(bankWithdrawal);
		String payAcount="";
		String weixingAcount="";
		String bankNumber="";
		for (BankWithdrawal bankWithdrawal2 : bankWithdrawals) {
			//1-微信用户 2-支付宝用户  3-银行卡
			if("1".equals(bankWithdrawal2.getAccountType())){
				weixingAcount=bankWithdrawal2.getBrankNumber();
			}
			if("2".equals(bankWithdrawal2.getAccountType())){
				payAcount=bankWithdrawal2.getBrankNumber();	
			}
			if("3".equals(bankWithdrawal2.getAccountType())){
				bankNumber=bankWithdrawal2.getBrankNumber();
			}
		}
		SysUser sysUser=sysUserService.doGetById(userId);
		map.put("payAcount", payAcount);
		map.put("weixingAcount", weixingAcount);
		map.put("bankNumber", bankNumber);
		map.put("bankWithdrawals", bankWithdrawals);
		map.put("accountBalance", sysUser.getAccountBalance());
		respBean.setBody(map);
		return respBean;
	}
	/**
	 * 描述: 账户管理-测试微信或者支付宝账号
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/doTestAccount")
	@ResponseBody
	public RespBean<String> doTestAccount(BankWithdrawal bankWithdrawal) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		Transfer transfer=new Transfer();
		try {
			//微信
			if("1".equals(bankWithdrawal.getAccountType())){
				transfer=TransferExample.getTranfer("wx_pub", "b2c", 1.0, "测试账户", bankWithdrawal.getBrankName(), bankWithdrawal.getBrankNumber(),"CS"+orderNo);
			}
			//支付宝
			if("2".equals(bankWithdrawal.getAccountType())){
				transfer=TransferExample.getTranfer("alipay", "b2c", 1.0, "测试账户", bankWithdrawal.getBrankName(),bankWithdrawal.getBrankNumber(),"CS"+orderNo);
			}
		 } catch (AuthenticationException | InvalidRequestException
					| APIConnectionException | APIException | ChannelException
					| RateLimitException e) {
				if(e.getMessage().indexOf("EXCEED_LIMIT_SM_MIN_AMOUNT")>0){
					respBean.setBody("1");
				}else{
					respBean.setBody("0");
				}
		}
		return respBean;
	}
	/**
	 * 描述: 进入提现页面
	 * @auther Liang
	 * @date 2017-08-07
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnWithdrawRecord")
	@ResponseBody
	public RespBean<Map<String, Object>> doEnWithdrawRecord(String userId) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		float price=0;
		SysUser sysUsers = sysUserService.doGetById(userId);
		price=Float.parseFloat(sysUsers.getAccountBalance());
		BankWithdrawal bankWithdrawal = new BankWithdrawal();
		bankWithdrawal.setUserId(userId);
		List<BankWithdrawal> bankWithdrawalList=bankWithdrawalService.doGetList(bankWithdrawal);
		float applyPrice=0;
		if(bankWithdrawalList.size()>0){
			for (BankWithdrawal bankWithdrawal2 : bankWithdrawalList) {
				WithdrawRecord withdrawRecord=new WithdrawRecord();
				withdrawRecord.setBankId(bankWithdrawal2.getId());
				withdrawRecord.setApplyState("0");
				List<WithdrawRecord> withdrawRecordList=withdrawRecordService.doGetList(withdrawRecord);
				for (WithdrawRecord withdrawRecord2 : withdrawRecordList) {
					applyPrice+=withdrawRecord2.getWithdrawPrice();
					
				}
			}
		}
		
	    price=price-applyPrice;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("price", price);
		respBean.setBody(model);
		return respBean;
	}
	/**
	 * 描述: 账户管理-确认提现
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSureApplication")
	@ResponseBody
	public RespBean<WithdrawRecord> doSureApplication(WithdrawRecord withdrawRecord) throws Exception{
		RespBean<WithdrawRecord> respBean = new RespBean<WithdrawRecord>();
		int i = (int) (Math.random() * 900) + 100;
		String apply = "HLTX" + UfdmDateUtil.getCurrentDate1()+ UfdmDateUtil.getCurrentSimpleTime1() + i;
		withdrawRecord.setApplyState("0");
		withdrawRecord.setApplyNumber(apply);
		withdrawRecordService.doSave(withdrawRecord);
		respBean.setBody(withdrawRecord);
		return respBean;
	}
	/**
	 * 描述: 账户管理-当月收入
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("/doMonthEarnings")
	@ResponseBody
	public RespBean<MyEarnings> doMonthEarnings(String userId,String userState,String sellerId) throws Exception{
		RespBean<MyEarnings> respBean = new RespBean<MyEarnings>();
		MyEarnings myEarnings=new MyEarnings();
		//分享
		ShareRecord shareRecord=new ShareRecord();
		shareRecord.setCreateDate(UfdmDateUtil.getCurrentDate2());
		shareRecord.setUserId(userId);
		List<ShareRecord> shareRecordList=shareRecordService.doGetList(shareRecord);
		float shareGoods=0;
		for (ShareRecord shareRecord2 : shareRecordList) {
			shareGoods+=Float.parseFloat(shareRecord2.getShareFee());
		}
		myEarnings.setShareGoods(String.valueOf(shareGoods));
		
		//红包
		RedEnvelopeRecord redEnvelopeRecord=new RedEnvelopeRecord();
		redEnvelopeRecord.setReceiveDate(UfdmDateUtil.getCurrentDate2());
		redEnvelopeRecord.setReceiver(userId);
		List<RedEnvelopeRecord> redEnvelopeRecordList = envelopeRecordService.doGetList(redEnvelopeRecord);
		float redEnvelope=0;
		for (RedEnvelopeRecord redEnvelopeRecord2 : redEnvelopeRecordList) {
			redEnvelope+=Float.parseFloat(redEnvelopeRecord2.getPrice());
		}
		myEarnings.setRedEnvelope(String.valueOf(redEnvelope));
		float remmedRabet=0;
		float localRabet=0;
		if("2".endsWith(userState)||"3".equals(userState)){
			//本月推广佣金
			Order order1=new Order();
			order1.setRemmBussines(sellerId);
			order1.setCreatetime(UfdmDateUtil.getCurrentDate2());
			List<Order> orders1=orderService.doGetMonthListApi(order1);
			if(orders1.size()>0){
				for (Order order2 : orders1) {
					remmedRabet+=Float.parseFloat(order2.getRemmBonus());
				}
			}
			myEarnings.setRemmedRabet(String.valueOf(remmedRabet));
			Order order=new Order();
			order.setLocalBussiness(sellerId);
			order.setCreatetime(UfdmDateUtil.getCurrentDate2());
			List<Order> orders=orderService.doGetMonthListApi(order);
			if(orders.size()>0){
				for (Order order2 : orders) {
					localRabet+=Float.parseFloat(order2.getLocalBonus());
				}
			}
			myEarnings.setLocalRabet(String.valueOf(localRabet));
			
		}
		myEarnings.setTotalEarnings(String.valueOf(shareGoods+localRabet+remmedRabet+redEnvelope));
		respBean.setBody(myEarnings);
		return respBean;
	}
	/**
	 * 描述: 账户管理-历史总的收入
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("/doHistoryEarnings")
	@ResponseBody
	public RespBean<RespPagination<MyEarnings>> doHistoryEarnings(String userId,String userState,String sellerId,String date) throws Exception{
		RespBean<RespPagination<MyEarnings>> respBean = new RespBean<RespPagination<MyEarnings>>();
		RespPagination<MyEarnings> respPagination = new RespPagination<MyEarnings>();
		List<MyEarnings> myEarningList=new ArrayList<MyEarnings>();
		MyEarnings myEarnings=new MyEarnings();
		//分享
		ShareRecord shareRecord=new ShareRecord();
		if(!StringUtils.isEmpty(date)){
			shareRecord.setCreateDate(date);
		}else{
			shareRecord.setCreateDate("1");
		}
		
		shareRecord.setUserId(userId);
		List<ShareRecord> shareRecordList=shareRecordService.doGetList(shareRecord);
		float shareGoods=0;
		for (ShareRecord shareRecord2 : shareRecordList) {
			shareGoods+=Float.parseFloat(shareRecord2.getShareFee());
		}
		myEarnings.setShareGoods(String.valueOf(shareGoods));
		
		//红包
		RedEnvelopeRecord redEnvelopeRecord=new RedEnvelopeRecord();
		if(!StringUtils.isEmpty(date)){
			redEnvelopeRecord.setReceiveDate(date);
		}else{
			redEnvelopeRecord.setReceiveDate("1");
		}
		
		redEnvelopeRecord.setReceiver(userId);
		List<RedEnvelopeRecord> redEnvelopeRecordList = envelopeRecordService.doGetList(redEnvelopeRecord);
		float redEnvelope=0;
		for (RedEnvelopeRecord redEnvelopeRecord2 : redEnvelopeRecordList) {
			redEnvelope+=Float.parseFloat(redEnvelopeRecord2.getPrice());
		}
		myEarnings.setRedEnvelope(String.valueOf(redEnvelope));
		float remmedRabet=0;
		float localRabet=0;
		if("2".endsWith(userState)||"3".equals(userState)){
			//本月推广佣金
			Order order1=new Order();
			order1.setRemmBussines(sellerId);
			if(!StringUtils.isEmpty(date)){
				order1.setCreatetime(date);
			}else{
				order1.setCreatetime("1");
			}
			List<Order> orders1=orderService.doGetMonthListApi(order1);
			if(orders1.size()>0){
				for (Order order2 : orders1) {
					remmedRabet+=Float.parseFloat(order2.getRemmBonus());
				}
			}
			myEarnings.setRemmedRabet(String.valueOf(remmedRabet));
			//本月锁定
			
			Order order=new Order();
			order.setLocalBussiness(sellerId);
			if(!StringUtils.isEmpty(date)){
				order.setCreatetime(date);
			}else{
				order.setCreatetime("1");
			}
			List<Order> orders=orderService.doGetMonthListApi(order);
			if(orders.size()>0){
				for (Order order2 : orders) {
					localRabet+=Float.parseFloat(order2.getLocalBonus());
				}
			}
			myEarnings.setLocalRabet(String.valueOf(localRabet));
		}
		myEarnings.setTotalEarnings(String.valueOf(shareGoods+localRabet+remmedRabet+redEnvelope));
		respPagination.setRows(myEarningList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 评价商品
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doCommodityEvaluation")
	@ResponseBody
	public RespBean<GoodsEvaluate> doCommodityEvaluation(GoodsEvaluate goodsEvaluate) throws Exception{
		RespBean<GoodsEvaluate> respBean = new RespBean<GoodsEvaluate>();
		goodsEvaluate.setEvaluateDate(UfdmDateUtil.getCurrentTime());
		goodsEvaluate.setStatus("1");
		goodsEvaluateService.doSave(goodsEvaluate);
		respBean.setBody(goodsEvaluate);
		return respBean;
	}
	/**
	 * 描述: 我的订单-确认收货功能
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnConfirmReceipt")
	@ResponseBody
	public RespBean<Order> doEnConfirmReceipt(String orderId) throws Exception{
		RespBean<Order> respBean = new RespBean<Order>();
		Order order=orderService.doGetById(orderId);
		order.setStatus("3");
		orderService.doModById(order);
		respBean.setBody(order);
		return respBean;
	}
 }

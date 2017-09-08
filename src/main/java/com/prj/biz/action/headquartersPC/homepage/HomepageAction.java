package com.prj.biz.action.headquartersPC.homepage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.agent.AgentService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.order.OrderService;
import com.prj.core.constant.SysConstants;
import com.prj.utils.UfdmDateUtil;

/**
 * 描述: 系统用户管理控制类<br>
 * 
 * @author 胡义振
 */
@Controller
@RequestMapping("/homepage")
public class HomepageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private AgentService agentService;
	
	@Resource
	private BusinessInformationService businessInformationService;
	
	@Resource
	private ConsumersAccountService consumersAccountService;
	
	@Resource
	private ConsumersService consumersService;
	
	@Resource
	private OrderService orderService;

	/**
	 * @Description: 跳转到首页信息页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @date 2017年7月6日
	 * @author 1936
	 */
	@RequestMapping("/doEnHomepageList")
	public ModelAndView doEnHomepageList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("/headquarters/homepage/homepageList");
	}
	/**
	 * 描述: 今日及昨日新增会员
	 * 
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doReadTodayMemberList")
	@ResponseBody
	public Map<String, Object> doReadTodayMemberList() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		/**
		 * 今日及昨日新增会员
		 * 
		 */
		int agentNum=0;
		//商家数量
		int businessNum=0;
		//消费商数量
		int xfBusinessNum=0;
		//消费者数量
		int consumersNum=0;
		//消费者账户
		int consumersAcountNum=0;
		
		int agentNum1=0;
		//商家数量
		int businessNum1=0;
		//消费商数量
		int xfBusinessNum1=0;
		//消费者数量
		int consumersNum1=0;
		//消费者账户
		int consumersAcountNum1=0;
		
		//昨日新增 扇形图X,Y轴
		//今日新增 扇形图X,Y轴
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		Agent agent=new Agent();
		BusinessInformation businessInformation=new BusinessInformation();
		businessInformation.setEffectState("0");
		
		Consumers consumers=new Consumers();
		ConsumersAccount consumersAccount=new ConsumersAccount();
		//总部登录
		if("0".equals(sysUsers.getUserState())){
			//代理商
			agent.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<Agent> agents=agentService.doGetList(agent);
			agentNum=agents.size();
			
			agent.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<Agent> agents1=agentService.doGetList(agent);
			agentNum1=agents1.size();
			
			Map<String, Object> szx = new HashMap<String, Object>();
			szx.put("value", agentNum1);
			szx.put("name", "代理商");
			Map<String, Object> sjx = new HashMap<String, Object>();
			sjx.put("value", agentNum);
			sjx.put("name", "代理商");
			
			//商家
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			
			businessInformation.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<BusinessInformation> businessInformations1=businessInformationService.doGetList(businessInformation);
			businessNum1=businessInformations1.size();
			Map<String, Object> szx1 = new HashMap<String, Object>();
			szx1.put("value",businessNum1);
			szx1.put("name", "商家");
			Map<String, Object> sjx1 = new HashMap<String, Object>();
			sjx1.put("value",businessNum);
			sjx1.put("name", "商家");
			
			//消费商
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			
			consumers.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<Consumers> consumers4=consumersService.doGetList(consumers);
			xfBusinessNum1=consumers4.size();
			
			Map<String, Object> szx2 = new HashMap<String, Object>();
			szx2.put("value",xfBusinessNum1);
			szx2.put("name", "消费商");
			Map<String, Object> sjx2 = new HashMap<String, Object>();
			sjx2.put("value",xfBusinessNum);
			sjx2.put("name", "消费商");
			//消费者
			consumers.setIsXfconsumers("0");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<Consumers> consumers3=consumersService.doGetList(consumers);
			consumersNum=consumers3.size();
			
			consumers.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<Consumers> consumers5=consumersService.doGetList(consumers);
			consumersNum1=consumers5.size();
			Map<String, Object> szx3 = new HashMap<String, Object>();
			szx3.put("value", consumersNum1);
			szx3.put("name", "消费者");
			Map<String, Object> sjx3 = new HashMap<String, Object>();
			sjx3.put("value", consumersNum);
			sjx3.put("name", "消费者");
			
			//消费者账户
			consumersAccount.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
			
			consumersAccount.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<ConsumersAccount> consumersAccounts1=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum1=consumersAccounts1.size();
			
			Map<String, Object> szx4 = new HashMap<String, Object>();
			szx4.put("value", consumersAcountNum1);
			szx4.put("name", "消费者账户");
			Map<String, Object> sjx4 = new HashMap<String, Object>();
			sjx4.put("value", consumersAcountNum);
			sjx4.put("name", "消费者账户");
			
			List<Map<String, Object>> sz=Arrays.asList(szx,szx1,szx2,szx3,szx4);
			List<Map<String, Object>> sj=Arrays.asList(sjx,sjx1,sjx2,sjx3,sjx4);
			map.put("sz", sz);
			map.put("sj", sj);
		}
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setHigherAgentId(sysUsers.getMerchantsId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			
			businessInformation.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<BusinessInformation> businessInformations1=businessInformationService.doGetList(businessInformation);
			businessNum1=businessInformations1.size();
			
			Map<String, Object> szx1 = new HashMap<String, Object>();
			szx1.put("value",businessNum1);
			szx1.put("name", "商家");
			Map<String, Object> sjx1 = new HashMap<String, Object>();
			sjx1.put("value",businessNum);
			sjx1.put("name", "商家");
			//消费商
			consumers.setAgentId(sysUsers.getMerchantsId());
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			
			consumers.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<Consumers> consumers4=consumersService.doGetList(consumers);
			xfBusinessNum1=consumers4.size();
			
			Map<String, Object> szx2 = new HashMap<String, Object>();
			szx2.put("value",xfBusinessNum1);
			szx2.put("name", "消费商");
			Map<String, Object> sjx2 = new HashMap<String, Object>();
			sjx2.put("value",xfBusinessNum);
			sjx2.put("name", "消费商");
			
			List<Map<String, Object>> sz=Arrays.asList(szx1,szx2);
			List<Map<String, Object>> sj=Arrays.asList(sjx1,sjx2);
			map.put("sz", sz);
			map.put("sj", sj);
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setFounder(sysUsers.getId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			
			businessInformation.setCreateDate(UfdmDateUtil.getBefoDateByDays());
			List<BusinessInformation> businessInformations1=businessInformationService.doGetList(businessInformation);
			businessNum1=businessInformations1.size();
			
			Map<String, Object> szx1 = new HashMap<String, Object>();
			szx1.put("value",businessNum1);
			szx1.put("name", "商家");
			Map<String, Object> sjx1 = new HashMap<String, Object>();
			sjx1.put("value",businessNum);
			sjx1.put("name", "商家");
			//锁定的消费者账号
			consumersAccount.setBusinessInformationId(sysUsers.getMerchantsId());
			consumersAccount.setLocalDate(UfdmDateUtil.getCurrentDate());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
			
			consumersAccount.setLocalDate(UfdmDateUtil.getBefoDateByDays());
			List<ConsumersAccount> consumersAccounts1=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum1=consumersAccounts1.size();
			
			Map<String, Object> szx4 = new HashMap<String, Object>();
			szx4.put("value", consumersAcountNum1);
			szx4.put("name", "消费者账户");
			Map<String, Object> sjx4 = new HashMap<String, Object>();
			sjx4.put("value", consumersAcountNum);
			sjx4.put("name", "消费者账户");
			
			List<Map<String, Object>> sz=Arrays.asList(szx1,szx4);
			List<Map<String, Object>> sj=Arrays.asList(sjx1,sjx4);
			map.put("sz", sz);
			map.put("sj", sj);
		}
		//今日新增会员总数
		int memberGurrent=agentNum+businessNum+xfBusinessNum+consumersNum+consumersAcountNum;
		//昨日新增
		int memberGurrentF=agentNum1+businessNum1+xfBusinessNum1+consumersNum1+consumersAcountNum1;
		//柱形图 y
		List ay=new ArrayList();
		ay.add(memberGurrentF);
		ay.add(memberGurrent);
		
		map.put("ay", ay);
		return map;
		
	}
	/**
	 * 描述: 本月及上月新增会员
	 * 
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doReadMonthMemberList")
	@ResponseBody
	public Map<String, Object> doReadMonthMemberList() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		/**
		 * 本月及上月新增会员
		 * 
		 */
		int agentNum=0;
		//商家数量
		int businessNum=0;
		//消费商数量
		int xfBusinessNum=0;
		//消费者数量
		int consumersNum=0;
		//消费者账户
		int consumersAcountNum=0;
		
		int agentNum1=0;
		//商家数量
		int businessNum1=0;
		//消费商数量
		int xfBusinessNum1=0;
		//消费者数量
		int consumersNum1=0;
		//消费者账户
		int consumersAcountNum1=0;
		
		//昨日新增 扇形图X,Y轴
		//今日新增 扇形图X,Y轴
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		Agent agent=new Agent();
		BusinessInformation businessInformation=new BusinessInformation();
		businessInformation.setEffectState("0");
		Consumers consumers=new Consumers();
		ConsumersAccount consumersAccount=new ConsumersAccount();
		//总部登录
		if("0".equals(sysUsers.getUserState())){
			//代理商
			agent.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<Agent> agents=agentService.doGetList(agent);
			agentNum=agents.size();
			
			agent.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<Agent> agents1=agentService.doGetList(agent);
			agentNum1=agents1.size();
			
			Map<String, Object> szx = new HashMap<String, Object>();
			szx.put("value", agentNum1);
			szx.put("name", "代理商");
			Map<String, Object> sjx = new HashMap<String, Object>();
			sjx.put("value", agentNum);
			sjx.put("name", "代理商");
			
			//商家
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			
			businessInformation.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<BusinessInformation> businessInformations1=businessInformationService.doGetList(businessInformation);
			businessNum1=businessInformations1.size();
			Map<String, Object> szx1 = new HashMap<String, Object>();
			szx1.put("value",businessNum1);
			szx1.put("name", "商家");
			Map<String, Object> sjx1 = new HashMap<String, Object>();
			sjx1.put("value",businessNum);
			sjx1.put("name", "商家");
			
			//消费商
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			
			consumers.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<Consumers> consumers4=consumersService.doGetList(consumers);
			xfBusinessNum1=consumers4.size();
			
			Map<String, Object> szx2 = new HashMap<String, Object>();
			szx2.put("value",xfBusinessNum1);
			szx2.put("name", "消费商");
			Map<String, Object> sjx2 = new HashMap<String, Object>();
			sjx2.put("value",xfBusinessNum);
			sjx2.put("name", "消费商");
			//消费者
			consumers.setIsXfconsumers("0");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<Consumers> consumers3=consumersService.doGetList(consumers);
			consumersNum=consumers3.size();
			
			consumers.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<Consumers> consumers5=consumersService.doGetList(consumers);
			consumersNum1=consumers5.size();
			Map<String, Object> szx3 = new HashMap<String, Object>();
			szx3.put("value", consumersNum1);
			szx3.put("name", "消费者");
			Map<String, Object> sjx3 = new HashMap<String, Object>();
			sjx3.put("value", consumersNum);
			sjx3.put("name", "消费者");
			
			//消费者账户
			consumersAccount.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
			
			consumersAccount.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<ConsumersAccount> consumersAccounts1=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum1=consumersAccounts1.size();
			
			Map<String, Object> szx4 = new HashMap<String, Object>();
			szx4.put("value", consumersAcountNum1);
			szx4.put("name", "消费者账户");
			Map<String, Object> sjx4 = new HashMap<String, Object>();
			sjx4.put("value", consumersAcountNum);
			sjx4.put("name", "消费者账户");
			
			List<Map<String, Object>> sz=Arrays.asList(szx,szx1,szx2,szx3,szx4);
			List<Map<String, Object>> sj=Arrays.asList(sjx,sjx1,sjx2,sjx3,sjx4);
			map.put("sz", sz);
			map.put("sj", sj);
		}
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setHigherAgentId(sysUsers.getMerchantsId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			
			businessInformation.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<BusinessInformation> businessInformations1=businessInformationService.doGetList(businessInformation);
			businessNum1=businessInformations1.size();
			
			Map<String, Object> szx1 = new HashMap<String, Object>();
			szx1.put("value",businessNum1);
			szx1.put("name", "商家");
			Map<String, Object> sjx1 = new HashMap<String, Object>();
			sjx1.put("value",businessNum);
			sjx1.put("name", "商家");
			//消费商
			consumers.setAgentId(sysUsers.getMerchantsId());
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			
			consumers.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<Consumers> consumers4=consumersService.doGetList(consumers);
			xfBusinessNum1=consumers4.size();
			
			Map<String, Object> szx2 = new HashMap<String, Object>();
			szx2.put("value",xfBusinessNum1);
			szx2.put("name", "消费商");
			Map<String, Object> sjx2 = new HashMap<String, Object>();
			sjx2.put("value",xfBusinessNum);
			sjx2.put("name", "消费商");
			
			List<Map<String, Object>> sz=Arrays.asList(szx1,szx2);
			List<Map<String, Object>> sj=Arrays.asList(sjx1,sjx2);
			map.put("sz", sz);
			map.put("sj", sj);
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setFounder(sysUsers.getId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			
			businessInformation.setCreateDate(UfdmDateUtil.getBefoDateByMonth());
			List<BusinessInformation> businessInformations1=businessInformationService.doGetList(businessInformation);
			businessNum1=businessInformations1.size();
			
			Map<String, Object> szx1 = new HashMap<String, Object>();
			szx1.put("value",businessNum1);
			szx1.put("name", "商家");
			Map<String, Object> sjx1 = new HashMap<String, Object>();
			sjx1.put("value",businessNum);
			sjx1.put("name", "商家");
			//锁定的消费者账号
			consumersAccount.setBusinessInformationId(sysUsers.getMerchantsId());
			consumersAccount.setLocalDate(UfdmDateUtil.getCurrentDate2());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
			
			consumersAccount.setLocalDate(UfdmDateUtil.getBefoDateByMonth());
			List<ConsumersAccount> consumersAccounts1=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum1=consumersAccounts1.size();
			
			Map<String, Object> szx4 = new HashMap<String, Object>();
			szx4.put("value", consumersAcountNum1);
			szx4.put("name", "消费者账户");
			Map<String, Object> sjx4 = new HashMap<String, Object>();
			sjx4.put("value", consumersAcountNum);
			sjx4.put("name", "消费者账户");
			
			List<Map<String, Object>> sz=Arrays.asList(szx1,szx4);
			List<Map<String, Object>> sj=Arrays.asList(sjx1,sjx4);
			map.put("sz", sz);
			map.put("sj", sj);
		}
		//本月新增会员总数
		int memberGurrent=agentNum+businessNum+xfBusinessNum+consumersNum+consumersAcountNum;
		//上月新增
		int memberGurrentF=agentNum1+businessNum1+xfBusinessNum1+consumersNum1+consumersAcountNum1;
		//柱形图 y
		List ay=new ArrayList();
		ay.add(memberGurrentF);
		ay.add(memberGurrent);
		
		map.put("ay", ay);
		return map;
		
	}
	/**
	 * 描述: 销售额统计
	 * 
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/doReadSalesList")
	@ResponseBody
	public Map<String, Object> doReadSalesList() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		Order order=new Order();
		Order yeOrder=new Order();
		Order order1=new Order();
		Order lastOrder=new Order();
		
		order.setCreatetime(UfdmDateUtil.getCurrentDate());
		yeOrder.setCreatetime(UfdmDateUtil.getBefoDateByDays());
		
		order1.setCreatetime(UfdmDateUtil.getCurrentDate2());
		lastOrder.setCreatetime(UfdmDateUtil.getBefoDateByMonth());
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			order.setAgentId(sysUsers.getMerchantsId());
			yeOrder.setAgentId(sysUsers.getMerchantsId());
			order1.setAgentId(sysUsers.getMerchantsId());
			lastOrder.setAgentId(sysUsers.getMerchantsId());
			
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			order.setBusinessId(sysUsers.getMerchantsId());
			yeOrder.setBusinessId(sysUsers.getMerchantsId());
			order1.setBusinessId(sysUsers.getMerchantsId());
			lastOrder.setBusinessId(sysUsers.getMerchantsId());
		}
		//昨天及今天销售额
		int yesterdaySale=0;
		int todaySale=0;
		//本月和上月销售额
		int lastMonthSales=0;
		int monthSales=0;
		
		
		//昨天及今天订单数
		int yesterdayOrder=0;
		int todayOrder=0;
		//本月和上月订单数
		int lastMonthOrder=0;
		int monthOrder=0;
				
		List<Order> orders=orderService.doGetSalesTotal(order);
		if(orders.size()>0){
			for (int i = 0; i < orders.size(); i++) {
				todaySale+=Float.parseFloat(orders.get(i).getMoneyNum());
				todayOrder+=Integer.parseInt(orders.get(i).getOrderNum());
			}
		}
		List<Order> orders1=orderService.doGetSalesTotal(yeOrder);
		if(orders1.size()>0){
			for (int i = 0; i < orders1.size(); i++) {
				yesterdaySale+=Float.parseFloat(orders1.get(i).getMoneyNum());
				yesterdayOrder+=Integer.parseInt(orders1.get(i).getOrderNum());
			}
		}
		List<Order> orders2=orderService.doGetSalesTotal(order1);
		if(orders2.size()>0){
			for (int i = 0; i < orders2.size(); i++) {
				monthSales+=Float.parseFloat(orders2.get(i).getMoneyNum());
				monthOrder+=Integer.parseInt(orders2.get(i).getOrderNum());
			}
		}
		List<Order> orders3=orderService.doGetSalesTotal(lastOrder);
		if(orders3.size()>0){
			for (int i = 0; i < orders3.size(); i++) {
				lastMonthSales+=Float.parseFloat(orders3.get(i).getMoneyNum());
				lastMonthOrder+=Integer.parseInt(orders3.get(i).getOrderNum());
			}
		}
		List todaySales=new ArrayList();
		todaySales.add(yesterdaySale);
		todaySales.add(todaySale);
		
		List monthSale=new ArrayList();
		monthSale.add(lastMonthSales);
		monthSale.add(monthSales);
		
		List todayOrderNum=new ArrayList();
		todayOrderNum.add(yesterdayOrder);
		todayOrderNum.add(todayOrder);
		
		List monthOrderNum=new ArrayList();
		monthOrderNum.add(lastMonthOrder);
		monthOrderNum.add(monthOrder);
		
		map.put("todaySales", todaySales);
		map.put("monthSale", monthSale);
		map.put("todayOrderNum", todayOrderNum);
		map.put("monthOrderNum", monthOrderNum);
		return map;
		
	}

}

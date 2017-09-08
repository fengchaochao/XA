/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Jiang
 * @date 2017-07-24
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.memberStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.codeareas.codeAreas;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.memberstatistics.MemberStatistics;
import com.prj.biz.bean.memberstatistics.MonthMemberStatistics;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.agent.AgentService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.memberstatistics.MemberStatisticsService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.UfdmDateUtil;



/** 
 * 描述: 日增会员统计 Action 类<br>
 * @author Liang
 * @date 2017-07-24
 */
@RestController
@RequestMapping("/headquarters/memberStatistics")
public class memberStatisticsAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private AgentService agentService;
	
	@Resource
	private BusinessInformationService businessInformationService;
	
	@Resource
	private ConsumersAccountService consumersAccountService;
	
	@Resource
	private ConsumersService consumersService;
	
	@Resource
	private MemberStatisticsService memberStatisticsService;
	
	@Resource
	private codeAreasService codeAreasService;

	/***************日新增会员************************/
	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnMemberStatisticsList")
    public ModelAndView doEnMemberStatisticsListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		//代理商数量
		int agentNum=0;
		//商家数量
		int businessNum=0;
		//消费商数量
		int xfBusinessNum=0;
		//消费者数量
		int consumersNum=0;
		//消费者账户
		int consumersAcountNum=0;
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
			//商家
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			//消费商
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			//消费者
			consumers.setIsXfconsumers("0");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<Consumers> consumers3=consumersService.doGetList(consumers);
			consumersNum=consumers3.size();
			//消费者账户
			consumersAccount.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
			
			
			
		}
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setHigherAgentId(sysUsers.getMerchantsId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			//消费商
			consumers.setAgentId(sysUsers.getMerchantsId());
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			
			
			
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setFounder(sysUsers.getId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			//锁定的消费者账号
			consumersAccount.setBusinessInformationId(sysUsers.getMerchantsId());
			consumersAccount.setLocalDate(UfdmDateUtil.getCurrentDate());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
		}
		model.put("sysUsers", sysUsers);
		model.put("agentNum", agentNum);
		model.put("businessNum", businessNum);
		model.put("xfBusinessNum", xfBusinessNum);
		model.put("consumersNum", consumersNum);
		model.put("consumersAcountNum", consumersAcountNum);
		return new ModelAndView("/memberStatistics/growingMemberStatistics",model);
	}
	/**
	 * 描述: 分页查询信息
	 * 
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadMemberStatisticsList")
	@ResponseBody
	public RespBean<RespPagination<MemberStatistics>> doReadMemberStatisticsList(
			MemberStatistics memberStatistics) throws Exception {
		RespBean<RespPagination<MemberStatistics>> respBean = new RespBean<RespPagination<MemberStatistics>>();
		RespPagination<MemberStatistics> respPagination = new RespPagination<MemberStatistics>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		Integer total=0;
		List<MemberStatistics> memberStatisticsList=new ArrayList<MemberStatistics>();
		//总部登录
		if("0".equals(sysUsers.getUserState())){
			memberStatistics.setCreateDate(UfdmDateUtil.getCurrentDate());
			memberStatisticsList=memberStatisticsService.doGetList(memberStatistics);
			total=memberStatisticsService.doGetTotal(memberStatistics);
		}
		//代理商
		if("1".equals(sysUsers.getUserState())){
			memberStatistics.setCreateDate(UfdmDateUtil.getCurrentDate());
			memberStatistics.setHigherAgentId(sysUsers.getMerchantsId());
			memberStatisticsList=memberStatisticsService.doGetList(memberStatistics);
			total=memberStatisticsService.doGetTotal(memberStatistics);
		}
		
		//商家
		if("2".equals(sysUsers.getUserState())){
			memberStatistics.setCreateDate(UfdmDateUtil.getCurrentDate());
			memberStatistics.setFounder(sysUsers.getMerchantsId());
			memberStatistics.setUsertype("1");
			List<MemberStatistics> memberStatisticsList1=memberStatisticsService.doGetList(memberStatistics);
			for (int i = 0; i < memberStatisticsList1.size(); i++) {
				if(memberStatisticsList1.get(i)!=null){
					memberStatisticsList.add(memberStatisticsList1.get(i));
				}
				
			}
			memberStatistics.setCreateDate("");
			memberStatistics.setLocalDate(UfdmDateUtil.getCurrentDate());
			memberStatistics.setUsertype("4");
			memberStatistics.setFounder(sysUsers.getMerchantsId());
			List<MemberStatistics> memberStatisticsList2=memberStatisticsService.doGetList(memberStatistics);
			for (int i = 0; i < memberStatisticsList2.size(); i++) {
				if(memberStatisticsList2.get(i)!=null){
					memberStatisticsList.add(memberStatisticsList2.get(i));
				}
				
			}
			total=memberStatisticsList.size();
		}
		if(memberStatisticsList.size()>0){
			for (int j = 0; j < memberStatisticsList.size(); j++) {
				String address=memberStatisticsList.get(j).getProvinces()+"省"+memberStatisticsList.get(j).getCity()+memberStatisticsList.get(j).getArea()+memberStatisticsList.get(j).getAddress();
				memberStatisticsList.get(j).setAddress(address);
			}
		}
		respPagination.setTotal(total);
		respPagination.setRows(memberStatisticsList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 进入代理商详情页面
	 * 
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnAgentEdit")
	public ModelAndView doEnAgentEdit(String agentId) throws Exception {
		Agent agent = agentService.doGetById(agentId);
		Map<String, Object> model = new HashMap<String, Object>();
		codeAreas codeAreas = new codeAreas();
		codeAreas.setAgentId(agentId);
		List<codeAreas> codeAreasList = codeAreasService.doGetList(codeAreas);
		String business = agent.getBusinessLicensePhoto();
		String[] businessLicensePhoto = null;
		if (business != null && !"".equals(business)) {
			businessLicensePhoto = agent.getBusinessLicensePhoto().split(",");
		}

		String[] idNoPhoto = agent.getIdNoPhoto().split(",");
		//发展商家
		BusinessInformation businessInformation=new BusinessInformation();
		businessInformation.setHigherAgentId(agentId);
		List<BusinessInformation> businessInformationList=businessInformationService.doGetList(businessInformation);
		//发展的消费商
		Consumers consumers=new Consumers();
		consumers.setAgentId(agentId);
		List<Consumers> consumersList=consumersService.doGetList(consumers);
		
		model.put("agent", agent);
		model.put("codeAreasList", codeAreasList);
		model.put("businessLicensePhoto", businessLicensePhoto);
		model.put("countryList", codeAreasService.selectCountyList());
		model.put("provinceList", codeAreasService.selectProvinceList());
		model.put("cityList", codeAreasService.selectCityList());
		model.put("areaList", codeAreasService.selectRegionList());
		model.put("businessInformationList",businessInformationList);
		model.put("consumersList",consumersList);
		model.put("businessNumber",businessInformationList.size());
		model.put("consumersNumber",consumersList.size());
		model.put("idNoPhoto", idNoPhoto);
		return new ModelAndView("/memberStatistics/agentEdit", model);
	}
	/**************************月新增会员****************************************/
	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnMonthMemberStatisticsList")
    public ModelAndView doEnMonthMemberStatisticsListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		//代理商数量
		int agentNum=0;
		//商家数量
		int businessNum=0;
		//消费商数量
		int xfBusinessNum=0;
		//消费者数量
		int consumersNum=0;
		//消费者账户
		int consumersAcountNum=0;
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
			//商家
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			//消费商
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			//消费者
			consumers.setIsXfconsumers("0");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<Consumers> consumers3=consumersService.doGetList(consumers);
			consumersNum=consumers3.size();
			//消费者账户
			consumersAccount.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
			
			
			
		}
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setHigherAgentId(sysUsers.getMerchantsId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			//消费商
			consumers.setAgentId(sysUsers.getMerchantsId());
			consumers.setIsXfconsumers("1");
			consumers.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<Consumers> consumers2=consumersService.doGetList(consumers);
			xfBusinessNum=consumers2.size();
			
			
			
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			//商家
			businessInformation.setFounder(sysUsers.getId());
			businessInformation.setCreateDate(UfdmDateUtil.getCurrentDate2());
			List<BusinessInformation> businessInformations=businessInformationService.doGetList(businessInformation);
			businessNum=businessInformations.size();
			//锁定的消费者账号
			consumersAccount.setBusinessInformationId(sysUsers.getMerchantsId());
			consumersAccount.setLocalDate(UfdmDateUtil.getCurrentDate2());
			List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
			consumersAcountNum=consumersAccounts.size();
		}
		String month=UfdmDateUtil.getCurrentDate3();
		model.put("sysUsers", sysUsers);
		model.put("agentNum", agentNum);
		model.put("businessNum", businessNum);
		model.put("xfBusinessNum", xfBusinessNum);
		model.put("consumersNum", consumersNum);
		model.put("consumersAcountNum", consumersAcountNum);
		model.put("month", Integer.parseInt(month));
		return new ModelAndView("/memberStatistics/monthMemberStatistics",model);
	}
	/**
	 * 描述: 分页查询信息
	 * 
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadMonthMemberStatisticsList")
	@ResponseBody
	public RespBean<RespPagination<MonthMemberStatistics>> doReadMonthMemberStatisticsList() throws Exception {
		RespBean<RespPagination<MonthMemberStatistics>> respBean = new RespBean<RespPagination<MonthMemberStatistics>>();
		RespPagination<MonthMemberStatistics> respPagination = new RespPagination<MonthMemberStatistics>();
		
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		
		List<MonthMemberStatistics> memberStatisticsList=new ArrayList<MonthMemberStatistics>();
		String month=UfdmDateUtil.getCurrentDate3();
		
		MemberStatistics memberStatistics =new MemberStatistics();
		//总部登录
		if("0".equals(sysUsers.getUserState())){
			for (int i = 0; i < (Integer.parseInt(month)-1); i++) {
				MonthMemberStatistics monthMemberStatistics=new MonthMemberStatistics();
				monthMemberStatistics.setMonth((i+1)+"月");
				//代理商
				memberStatistics.setUsertype("0");
				memberStatistics.setMonth(String.valueOf((i+1)));
				int agentNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setAgentNumber(agentNum);
				//商家
				memberStatistics.setUsertype("1");
				memberStatistics.setMonth(String.valueOf((i+1)));
				int businessNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setBusinessNumber(businessNum);
				//消费者
				memberStatistics.setUsertype("2");
				memberStatistics.setMonth(String.valueOf((i+1)));
				int consumersNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setConsumerNumber(consumersNum);
				//消费商
				memberStatistics.setUsertype("3");
				memberStatistics.setMonth(String.valueOf((i+1)));
				int xFConsumersNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setxFConsumerNumber(xFConsumersNum);
				//消费者账户
				memberStatistics.setUsertype("4");
				memberStatistics.setMonth(String.valueOf((i+1)));
				int accountNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setAccountNumber(accountNum);
				memberStatisticsList.add(monthMemberStatistics);
				
			}
		}
		//代理商
		if("1".equals(sysUsers.getUserState())){
			for (int i = 0; i < (Integer.parseInt(month)-1); i++) {
				MonthMemberStatistics monthMemberStatistics=new MonthMemberStatistics();
				monthMemberStatistics.setMonth((i+1)+"月");
				//商家
				memberStatistics.setUsertype("1");
				memberStatistics.setMonth(String.valueOf((i+1)));
				memberStatistics.setHigherAgentId(sysUsers.getMerchantsId());
				int businessNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setBusinessNumber(businessNum);
				//消费商
				memberStatistics.setUsertype("3");
				memberStatistics.setMonth(String.valueOf((i+1)));
				memberStatistics.setHigherAgentId(sysUsers.getMerchantsId());
				int xFConsumersNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setxFConsumerNumber(xFConsumersNum);
				memberStatisticsList.add(monthMemberStatistics);
			}
		}
		//商家
		if("2".equals(sysUsers.getUserState())){
			for (int i = 0; i < (Integer.parseInt(month)-1); i++) {
				MonthMemberStatistics monthMemberStatistics=new MonthMemberStatistics();
				monthMemberStatistics.setMonth((i+1)+"月");
				//商家
				memberStatistics.setUsertype("1");
				memberStatistics.setMonth(String.valueOf((i+1)));
				memberStatistics.setFounder(sysUsers.getId());
				int businessNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setBusinessNumber(businessNum);
				//消费者账户
				memberStatistics.setUsertype("4");
				memberStatistics.setFounder(sysUsers.getMerchantsId());
				memberStatistics.setLocalMonth(String.valueOf((i+1)));
				int accountNum=memberStatisticsService.doGetTotal(memberStatistics);
				monthMemberStatistics.setAccountNumber(accountNum);
				memberStatisticsList.add(monthMemberStatistics);
			}
		}
		respPagination.setTotal(memberStatisticsList.size());
		respPagination.setRows(memberStatisticsList);
		respBean.setBody(respPagination);
		return respBean;
	}

	
}

/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-10
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.agent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.codeareas.codeAreas;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.service.agent.AgentService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.SmsUtils;
import com.prj.utils.UfdmRandomlyGenerated;

/**
 * 描述: 公众号申请代理商客户信息 Action 类<br>
 * 
 * @author Liang
 * @date 2017-07-10
 */
@RestController
@RequestMapping("/headquarters/applicationAgent")
public class ApplicationAgentAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource
	private AgentService agentService;
	@Resource
	private codeAreasService codeAreasService;
	@Resource
	private BusinessInformationService businessInformationService;
	@Resource
	private ConsumersService consumersService;

	/**
	 * 描述: 进入列表显示页面
	 * 
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doEnAgentList")
	public ModelAndView doEnAgentListAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("/headquarters/applicationAgent/agentList");
	}

	/**
	 * 描述: 分页查询信息
	 * 
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadAgentList")
	@ResponseBody
	public RespBean<RespPagination<Agent>> doReadAgentList(Agent agent)
			throws Exception {
		RespBean<RespPagination<Agent>> respBean = new RespBean<RespPagination<Agent>>();
		RespPagination<Agent> respPagination = new RespPagination<Agent>();
		agent.setApplicationState("0");
		Integer total = agentService.doGetTotal(agent);
		List<Agent> agentList = agentService.doGetList(agent);
		if(agentList.size()>0){
			for (int i = 0; i < agentList.size(); i++) {
				String address=agentList.get(i).getProvinces()+"省"+agentList.get(i).getCity()+agentList.get(i).getArea()+agentList.get(i).getAddress();
				agentList.get(i).setAddress(address);
				//代理区域
				codeAreas codeAreas=new codeAreas();
				codeAreas.setAgentId(agentList.get(i).getId());
				List<codeAreas> areas=codeAreasService.doGetList(codeAreas);
				String agentArea="";
				if(areas.size()>0){
					for (int j = 0; j < areas.size(); j++) {
						if(StringUtils.isEmpty(agentArea)){
							
							agentArea+=areas.get(j).getAreaName();
						}else{
							agentArea+="、"+areas.get(j).getAreaName();
						}
					}
					
				}
				agentList.get(i).setAgentArea(agentArea);
			}
		}
		
		respPagination.setTotal(total);
		respPagination.setRows(agentList);
		respBean.setBody(respPagination);
		return respBean;
	}



	/**
	 * 描述: 进入编辑页面
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
		return new ModelAndView("/headquarters/applicationAgent/agentEdit", model);
	}

	/**
	 * 描述: 发送短信
	 * 
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access" })
	@RequestMapping("/doEditAgent")
	public RespBean<String> doEditAgent(Agent agent) throws Exception {
		RespBean<String> respBean = new RespBean<String>();
		Agent agent1 = agentService.doGetById(agent.getId());
		//agent1.setDiamondCard(agent.getDiamondCard());
		//随机生成验证码
		String code=UfdmRandomlyGenerated.createRandomVcode(6);
		//发送短信
		SmsUtils smsUtils =new SmsUtils();
		SendSmsResponse response = smsUtils.sendSms(agent1.getPhone(),"SMS_86700123","{\"code\":\"'"+code+"'\",\"diamondCard\":\"'"+agent.getDiamondCard()+"'\"}");
		Subject sub = SecurityUtils.getSubject();
		sub.getSession().setAttribute(SysConstants.SESSION_V_CODE, code);
		if("OK".equals(response.getMessage())){
			respBean.setBody("发送成功");
		}else{
			respBean.setBody("发送失败");
		}
		return respBean;
	}

}

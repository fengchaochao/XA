/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-12
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.businessinformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.businessclassification.BusinessClassification;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.codeareas.codeAreas;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.agent.AgentService;
import com.prj.biz.service.businessclassification.BusinessClassificationService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.SmsUtils;
import com.prj.utils.UfdmRandomlyGenerated;

/**
 * 描述: 公众号申请商家信息 Action 类<br>
 * 
 * @author Liang
 * @date 2017-07-12
 */
@RestController
@RequestMapping("/headquarters/applicationBusinessInformation")
public class ApplicationBusinessInformationAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource
	private BusinessInformationService businessInformationService;

	@Resource
	private BusinessClassificationService businessClassificationService;

	@Resource
	private codeAreasService codeAreasService;

	@Resource
	private AgentService agentService;

	/**
	 * 描述: 进入列表显示页面
	 * 
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doEnBusinessInformationList")
	public ModelAndView doEnBusinessInformationListAction(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("/headquarters/applicationBusinessinformation/businessInformationList",model);
	}

	/**
	 * 描述: 分页查询信息
	 * 
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadBusinessInformationList")
	@ResponseBody
	public RespBean<RespPagination<BusinessInformation>> doReadBusinessInformationList(
			BusinessInformation businessInformation) throws Exception {
		RespBean<RespPagination<BusinessInformation>> respBean = new RespBean<RespPagination<BusinessInformation>>();
		RespPagination<BusinessInformation> respPagination = new RespPagination<BusinessInformation>();
		businessInformation.setApplicationState("0");
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		List<BusinessInformation> businessInformationList2 =new ArrayList<BusinessInformation>();
		if("1".equals(sysUsers.getUserState())){
			Agent agent=agentService.doGetById(sysUsers.getMerchantsId());
			if("0".equals(agent.getResellerLevel())){
				codeAreas areas=new codeAreas();
				areas.setAgentId(agent.getId());
				List<codeAreas> codeAreas=codeAreasService.doGetList(areas);
				if(codeAreas.size()>0){
					for (int i = 0; i < codeAreas.size(); i++) {
						if(codeAreas.get(i)!=null){
							businessInformation.setProvincesId(codeAreas.get(i).getAreaId());
							List<BusinessInformation> businessInformationList = businessInformationService.selectAllList(businessInformation);
							if(businessInformationList.size()>0){
								for (int j = 0; j < businessInformationList.size(); j++) {
									String address=businessInformationList.get(j).getProvinces()+"省"+businessInformationList.get(j).getCity()+businessInformationList.get(j).getArea()+businessInformationList.get(j).getAddress();
									businessInformationList.get(j).setAddress(address);
									businessInformationList2.add(businessInformationList.get(j));
									
								}
							}
						}
					}
				}
				
			}
			if("1".equals(agent.getResellerLevel())){
				codeAreas areas=new codeAreas();
				areas.setAgentId(agent.getId());
				List<codeAreas> codeAreas=codeAreasService.doGetList(areas);
				if(codeAreas.size()>0){
					for (int i = 0; i < codeAreas.size(); i++) {
						if(codeAreas.get(i)!=null){
							businessInformation.setCityId(codeAreas.get(i).getAreaId());
							List<BusinessInformation> businessInformationList = businessInformationService.selectAllList(businessInformation);
							if(businessInformationList.size()>0){
								for (int j = 0; j < businessInformationList.size(); j++) {
									String address=businessInformationList.get(j).getProvinces()+"省"+businessInformationList.get(j).getCity()+businessInformationList.get(j).getArea()+businessInformationList.get(j).getAddress();
									businessInformationList.get(j).setAddress(address);
									businessInformationList2.add(businessInformationList.get(j));
									
								}
							}
						}
						
						
					}
				}
				
			}
			if("2".equals(agent.getResellerLevel())){
				codeAreas areas=new codeAreas();
				areas.setAgentId(agent.getId());
				List<codeAreas> codeAreas=codeAreasService.doGetList(areas);
				if(codeAreas.size()>0){
					for (int i = 0; i < codeAreas.size(); i++) {
						if(codeAreas.get(i)!=null){
							businessInformation.setAreaId(codeAreas.get(i).getAreaId());
							List<BusinessInformation> businessInformationList = businessInformationService.selectAllList(businessInformation);
							if(businessInformationList.size()>0){
								for (int j = 0; j < businessInformationList.size(); j++) {
									String address=businessInformationList.get(j).getProvinces()+"省"+businessInformationList.get(j).getCity()+businessInformationList.get(j).getArea()+businessInformationList.get(j).getAddress();
									businessInformationList.get(j).setAddress(address);
									businessInformationList2.add(businessInformationList.get(j));
									
								}
							}
						}
						
					}
				}
			}
		}
		Integer total = businessInformationList2.size();
		respPagination.setTotal(total);
		respPagination.setRows(businessInformationList2);
		respBean.setBody(respPagination);
		return respBean;
	}


	/**
	 * 描述: 进入编辑页面
	 * 
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnBusinessInformationEdit")
	public ModelAndView doEnBusinessInformationEdit(
			String businessInformationId) throws Exception {
		BusinessInformation businessInformation = businessInformationService
				.doGetById(businessInformationId);
		Map<String, Object> model = new HashMap<String, Object>();
		List<BusinessClassification> businessClassificationList = businessClassificationService.doGetList(null);
		String[] businessLicensePhoto = businessInformation.getBusinessIcensePhoto().split(",");

		String[] idNoPhoto = businessInformation.getIdPhoto().split(",");
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		Agent agent=agentService.doGetById(sysUsers.getMerchantsId());
		model.put("businessClassificationList", businessClassificationList);
		model.put("businessInformation", businessInformation);
		model.put("idNoPhoto", idNoPhoto);
		model.put("businessLicensePhoto", businessLicensePhoto);
		model.put("agent", agent);
		return new ModelAndView("/headquarters/applicationBusinessinformation/businessInformationEdit",model);
	}

	/**
	 * 描述: 编辑保存
	 * 
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/doEditBusinessInformation")
	public RespBean<String> doEditBusinessInformation(
			BusinessInformation businessInformation) throws Exception {
		RespBean<String> respBean = new RespBean<String>();
		BusinessInformation businessInformation1 = businessInformationService.doGetById(businessInformation.getId());
		/*businessInformation1.setDiamondCard(businessInformation.getDiamondCard());
		
		businessInformationService.doModById(businessInformation1);*/
		//随机生成验证码
		String code=UfdmRandomlyGenerated.createRandomVcode(6);
		//发送短信
		SmsUtils smsUtils =new SmsUtils();
		SendSmsResponse response = smsUtils.sendSms(businessInformation1.getPhone(),"SMS_86650112","{\"code\":\"'"+code+"'\",\"diamondCard\":\"'"+businessInformation.getDiamondCard()+"'\"}");
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

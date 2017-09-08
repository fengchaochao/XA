/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-18
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.consumers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.codeareas.codeAreas;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.agent.AgentService;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.JPushClientUtil;
import com.prj.utils.ping.PayConfiger;
import com.prj.utils.qrCodeUtli.MatrixToImageWriter;



/** 
 * 描述: 申请消费商列表 Action 类<br>
 * @author Liang
 * @date 2017-07-18
 */
@RestController
@RequestMapping("/headquarters/applicationConsumers")
public class ApplicationConsumersAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private ConsumersService consumersService;
	
	@Resource
	private ConsumersAccountService consumersAccountService;
	
	@Resource
	private AgentService agentService;
	
	@Resource
	private codeAreasService codeAreasService;
	
	@Resource
	private SysUserService sysUserService;
	
	JPushClientUtil jPushClientUtil;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnConsumersList")
    public ModelAndView doEnConsumersListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/applicationConsumers/consumersList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadConsumersList")
	@ResponseBody
	public RespBean<RespPagination<Consumers>> doReadConsumersList(Consumers consumers) throws Exception{
		RespBean<RespPagination<Consumers>> respBean = new RespBean<RespPagination<Consumers>>();
		RespPagination<Consumers> respPagination = new RespPagination<Consumers>();
		consumers.setIsXfconsumers("0");
		consumers.setApplyUpgrade("0");
		Integer total = consumersService.doGetTotal(consumers);
		List<Consumers> consumersList = new ArrayList<Consumers>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		//代理商可以查看代理区域所在的商家
		if("1".equals(sysUsers.getUserState())){
			Agent agent=agentService.doGetById(sysUsers.getMerchantsId());
			if("0".equals(agent.getResellerLevel())){
				codeAreas areas=new codeAreas();
				areas.setAgentId(agent.getId());
				List<codeAreas> codeAreas=codeAreasService.doGetList(areas);
				if(codeAreas.size()>0){
					for (int i = 0; i < codeAreas.size(); i++) {
						if(codeAreas.get(i)!=null){
							consumers.setProvincesId(codeAreas.get(i).getAreaId());
							List<Consumers> consumersList1 = consumersService.doGetList(consumers);
							if(consumersList1.size()>0){
								for (int j = 0; j < consumersList1.size(); j++) {
									if(!StringUtils.isEmpty(consumersList.get(j).getProvinces())&&!StringUtils.isEmpty(consumersList.get(j).getCity())&&!StringUtils.isEmpty(consumersList.get(j).getArea())&&!StringUtils.isEmpty(consumersList.get(j).getAddress())){
										String address=consumersList1.get(j).getProvinces()+"省"+consumersList1.get(j).getCity()+consumersList1.get(j).getArea()+consumersList1.get(j).getAddress();
										consumersList.get(j).setAddress(address);
									}
									
									
									consumersList.add(consumersList1.get(j));
									
								}
							}
							total = consumersList1.size();
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
							consumers.setCityId(codeAreas.get(i).getAreaId());
							List<Consumers> consumersList1 = consumersService.doGetList(consumers);
							if(consumersList1.size()>0){
								for (int j = 0; j < consumersList1.size(); j++) {
									String address=consumersList1.get(j).getProvinces()+"省"+consumersList1.get(j).getCity()+consumersList1.get(j).getArea()+consumersList1.get(j).getAddress();
									consumersList1.get(j).setAddress(address);
									consumersList.add(consumersList1.get(j));
									
								}
							}
							total = consumersList1.size();
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
							consumers.setAreaId(codeAreas.get(i).getAreaId());
							List<Consumers> consumersList1 = consumersService.doGetList(consumers);
							if(consumersList1.size()>0){
								for (int j = 0; j < consumersList1.size(); j++) {
									String address=consumersList1.get(j).getProvinces()+"省"+consumersList1.get(j).getCity()+consumersList1.get(j).getArea()+consumersList1.get(j).getAddress();
									consumersList1.get(j).setAddress(address);
									consumersList.add(consumersList1.get(j));
									
								}
								total = consumersList1.size();
							}
						}
						
					}
				}
			}
		}
		respPagination.setTotal(total);
		respPagination.setRows(consumersList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入查看页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnConsumersEdit")
	public ModelAndView doEnConsumersEdit(String consumersId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Consumers consumers = consumersService.doGetById(consumersId);
		//消费者账号
		ConsumersAccount consumersAccount=new ConsumersAccount();
		consumersAccount.setConsumersId(consumersId);
		List<ConsumersAccount> consumersAccountList=consumersAccountService.doGetList(consumersAccount);
		String weixinAccount="";
		String payAccount="";
		if(consumersAccountList.size()==1){
			if("1".equals(consumersAccountList.get(0).getUserType())){
				weixinAccount=consumersAccountList.get(0).getUserAccount();
			}
			if("2".equals(consumersAccountList.get(0).getUserType())){
				payAccount=consumersAccountList.get(0).getUserAccount();
			}
		}
		if(consumersAccountList.size()==2){
			for (int i = 0; i < consumersAccountList.size(); i++) {
				if("1".equals(consumersAccountList.get(i).getUserType())){
					weixinAccount=consumersAccountList.get(i).getUserAccount();
				}
				if("2".equals(consumersAccountList.get(i).getUserType())){
					payAccount=consumersAccountList.get(i).getUserAccount();
				}
			}
			
		}
		model.put("weixinAccount", weixinAccount);
		model.put("payAccount", payAccount);
		model.put("consumers", consumers);
		model.put("consumersAccountList", consumersAccountList);
		return new ModelAndView("/headquarters/applicationConsumers/consumersEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/doEditConsumers")
	public RespBean<Consumers> doEditConsumers(Consumers consumers) throws Exception{
		RespBean<Consumers> respBean = new RespBean<Consumers>();
		Consumers consumers1=consumersService.doGetById(consumers.getId());
		consumers1.setCertificatePhotos(consumers.getCertificatePhotos());
		consumers1.setApplyUpgrade("1");
		consumers1.setIsXfconsumers("1");
		/**
		 * 生成店铺二维码
		 */
		String imgs = "";
		String qrCodePAth ="";
		// 项目路径
		String projectPath = doGetRequst().getSession().getServletContext().getRealPath("/");
		  //linux下
		if ("/".equals(File.separator)) {
			imgs = projectPath + "/img/login/"+ consumers1.getId() + ".png";
			qrCodePAth = projectPath + "/qrcode/"+consumers1.getId() +".jpg";
			imgs = imgs.replace("\\", "/");
			qrCodePAth = qrCodePAth.replace("\\", "/");
		}
		MatrixToImageWriter.createQRcode(PayConfiger.project_path+ "/api/goH5Pay?storeId="+ consumers1.getId() + "-X",	qrCodePAth, "", "", "");
		consumers1.setQrCode(PayConfiger.project_path+ "/qrcode/"+consumers1.getId() +".jpg");
		consumersService.doModById(consumers1);
		SysUser sysUser= new SysUser();
		sysUser.setMerchantsId(consumers.getId());
		List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
		if(sysUsers.size()>0){
			sysUsers.get(0).setUserState("3");
			sysUserService.doModById(sysUsers.get(0));
			//获取用户手机设备号，发送推送消息至手机
			String deviceNumber = sysUsers.get(0).getDeviceNumber();
			if(deviceNumber != null && !deviceNumber.equals("")){
				//标题、内容、设备号
				jPushClientUtil.pushMessage("升级消费商", "尊敬的用户你已成功升级为消费商", deviceNumber);
			}
		}
		respBean.setBody(consumers1);
		return respBean;
	}
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditConsumersState")
	public RespBean<Consumers> doEditConsumersState(Consumers consumers) throws Exception{
		RespBean<Consumers> respBean = new RespBean<Consumers>();
		Consumers consumers1=consumersService.doGetById(consumers.getId());
		consumers1.setIsXfconsumers("1");
		consumersService.doModById(consumers1);
		respBean.setBody(consumers1);
		return respBean;
	}
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelConsumers")
	@ResponseBody
	public RespBean<String> doDelConsumersAction(String consumersId) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		Consumers consumers = consumersService.doGetById(consumersId);
		ConsumersAccount consumersAccount=new ConsumersAccount();
		consumersAccount.setConsumersId(consumersId);
		List<ConsumersAccount> consumersAccountList=consumersAccountService.doGetList(consumersAccount);
		if(consumers!=null){
			if ("0".equals(consumers.getMerchantsState())) {
				consumers.setMerchantsState("1");
				if(consumersAccountList.size()>0){
					for (int i = 0; i < consumersAccountList.size(); i++) {
						consumersAccountList.get(i).setAccountState("1");
						consumersAccountService.doModById(consumersAccountList.get(i));
					}
				}
				respBean.setBody("停用成功！");
			}else{
				consumers.setMerchantsState("0");
				if(consumersAccountList.size()>0){
					for (int i = 0; i < consumersAccountList.size(); i++) {
						consumersAccountList.get(i).setAccountState("0");
						consumersAccountService.doModById(consumersAccountList.get(i));
					}
				}
				respBean.setBody("启用成功！");
				
			}
			consumersService.doModById(consumers);	
		}
		return respBean;
	}
}

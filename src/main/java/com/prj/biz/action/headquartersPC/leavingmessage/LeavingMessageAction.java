/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-27
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.leavingmessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.leavingmessage.LeavingMessage;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.agent.AgentService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.leavingmessage.LeavingMessageService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.utils.JPushClientUtil;
import com.prj.utils.UfdmDateUtil;



/** 
 * 描述: 客服系统 Action 类<br>
 * @author Liang
 * @date 2017-07-27
 */
@RestController
@RequestMapping("/headquarters/leavingMessage")
public class LeavingMessageAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private LeavingMessageService leavingMessageService;
	@Resource
	private BusinessInformationService businessInformationService;
	@Resource
	private ConsumersService consumersService;
	@Resource
	private AgentService agentService;
	@Resource
	private SysUserService sysUserService;
	JPushClientUtil jPushClientUtil;

	/**
	 * 描述: 进入列表显示页面(新留言处理页面)
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnLeavingMessageList")
    public ModelAndView doEnLeavingMessageListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/leavingmessage/leavingMessageList");
	}
	
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadLeavingMessageList")
	@ResponseBody
	public RespBean<RespPagination<LeavingMessage>> doReadLeavingMessageList(LeavingMessage leavingMessage) throws Exception{
		RespBean<RespPagination<LeavingMessage>> respBean = new RespBean<RespPagination<LeavingMessage>>();
		RespPagination<LeavingMessage> respPagination = new RespPagination<LeavingMessage>();
		Integer total = leavingMessageService.doGetTotal(leavingMessage);
		List<LeavingMessage> leavingMessageList = leavingMessageService.doGetList(leavingMessage);
		for (LeavingMessage lm : leavingMessageList) {
			String name = "";
			//通过userID分别在四种状态表中查询出对应的数据，保存名称进name
			//1:商家
			BusinessInformation information = businessInformationService.doGetById(lm.getUserId());
			if(information != null){
				name = information.getVendorName();
			}
			//2:消费者or3:消费商
			Consumers consumers = consumersService.doGetById(lm.getUserId()); 
			if(consumers != null){
				name = consumers.getNickName();
			}
			//4:代理商
			Agent agent = agentService.doGetById(lm.getUserId());
			if(agent != null ){
				name = agent.getAgentName();
			}
			lm.setUserName(name);
		}
		respPagination.setTotal(total);
		respPagination.setRows(leavingMessageList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	
	/**
	 * 描述: 进入列表显示页面(留言记录)
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnLeavingMessageRecordList")
    public ModelAndView doEnLeavingMessageRecordList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/leavingmessage/leavingMessageRecordList");
	}
	
	
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadLeavingMessageRecordList")
	@ResponseBody
	public RespBean<RespPagination<LeavingMessage>> doReadLeavingMessageRecordList(LeavingMessage leavingMessage) throws Exception{
		RespBean<RespPagination<LeavingMessage>> respBean = new RespBean<RespPagination<LeavingMessage>>();
		RespPagination<LeavingMessage> respPagination = new RespPagination<LeavingMessage>();
		Integer total = leavingMessageService.doGetTotal(leavingMessage);
		List<LeavingMessage> leavingMessageList = leavingMessageService.doGetList(leavingMessage);
		for (LeavingMessage lm : leavingMessageList) {
			String name = "";
			//通过userID分别在四种状态表中查询出对应的数据，保存名称进name
			//1:商家
			BusinessInformation information = businessInformationService.doGetById(lm.getUserId());
			if(information != null){
				name = information.getVendorName();
			}
			//2:消费者or3:消费商
			Consumers consumers = consumersService.doGetById(lm.getUserId()); 
			if(consumers != null){
				name = consumers.getNickName();
			}
			//4:代理商
			Agent agent = agentService.doGetById(lm.getUserId());
			if(agent != null ){
				name = agent.getCompanyName();
			}
			lm.setUserName(name);
		}
		respPagination.setTotal(total);
		respPagination.setRows(leavingMessageList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 查看留言记录
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnLeavingMessageSelRecor")
	public ModelAndView doEnLeavingMessageSelRecor(LeavingMessage leavingMessage) throws Exception{
		List<LeavingMessage> leavingMessagList = leavingMessageService.doGetList(leavingMessage);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("leavingMessagList", leavingMessagList);
		return new ModelAndView("/headquarters/leavingmessage/leavingMessageSelRecor",model);
	}
	
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnLeavingMessageEdit")
	public ModelAndView doEnLeavingMessageEdit(String leavingMessageId) throws Exception{
		LeavingMessage leavingMessage = leavingMessageService.doGetById(leavingMessageId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("leavingMessage", leavingMessage);
		return new ModelAndView("/headquarters/leavingmessage/leavingMessageEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/doEditLeavingMessage")
	public RespBean<LeavingMessage> doEditLeavingMessage(LeavingMessage leavingMessage) throws Exception{
		RespBean<LeavingMessage> respBean = new RespBean<LeavingMessage>();
		SysUser sysUser = new SysUser();
		leavingMessage.setReplyDate(UfdmDateUtil.getCurrentTime());
		//客服回复留言后要给app提问者推送一条消息
		LeavingMessage lm = leavingMessageService.doGetById(leavingMessage.getId());
		if(lm != null){
			sysUser.setMerchantsId(lm.getUserId());
			List<SysUser> sysUserList = sysUserService.doGetList(sysUser);
			//获取用户手机设备号，发送推送消息至手机
			String deviceNumber = sysUserList.get(0).getDeviceNumber();
			if(deviceNumber != null && !deviceNumber.equals("")){
				//标题、内容、设备号
				jPushClientUtil.pushMessage("客服回复", leavingMessage.getReplyContent(), deviceNumber);
			}
		}
		leavingMessageService.doModById(leavingMessage);
		respBean.setBody(leavingMessage);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelLeavingMessage")
	@ResponseBody
	public RespBean<String> doDelLeavingMessageAction(String leavingMessageIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		leavingMessageService.doRmByIds(leavingMessageIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Jiang
 * @date 2017-07-25
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.message.Message;
import com.prj.biz.bean.message.UserMessageEntiy;
import com.prj.biz.bean.messagedetail.MessageDetail;
import com.prj.biz.service.message.MessageService;
import com.prj.biz.service.messagedetail.MessageDetailService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;



/** 
 * 描述: 消息 Action 类<br>
 * @author Jiang
 * @date 2017-07-25
 */
@RestController
@RequestMapping("/headquarters/message")
public class MessageAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private MessageService messageService;
	
	@Resource
	private MessageDetailService messageDetailService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-25
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnMessageList")
    public ModelAndView doEnMessageListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/message/messageList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Jiang
	 * @date 2017-07-25
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadMessageList")
	@ResponseBody
	public RespBean<RespPagination<Message>> doReadMessageList(Message message) throws Exception{
		RespBean<RespPagination<Message>> respBean = new RespBean<RespPagination<Message>>();
		RespPagination<Message> respPagination = new RespPagination<Message>();
		message.setOrderName("message_date");
		message.setOrder("desc");
		Integer total = messageService.doGetTotal(message);
		List<Message> messageList = messageService.doGetList(message);
		respPagination.setTotal(total);
		respPagination.setRows(messageList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Jiang
	 * @date 2017-07-25
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnMessageEdit")
	public ModelAndView doEnMessageEdit(String messageId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("messageId", messageId);
		return new ModelAndView("/headquarters/message/messageEdit",model);
	}
	
	/**
	 * 描述: 查询推送明细
	 * @auther Jiang
	 * @date 2017-07-25
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetMessageDetailList")
	@ResponseBody
	public RespBean<RespPagination<UserMessageEntiy>> doGetMessageDetailList(String messageId) throws Exception{
		RespBean<RespPagination<UserMessageEntiy>> respBean = new RespBean<RespPagination<UserMessageEntiy>>();
		RespPagination<UserMessageEntiy> respPagination = new RespPagination<UserMessageEntiy>();
		MessageDetail messageDetail = new MessageDetail();
		messageDetail.setMessageCode(messageId);
		List<MessageDetail> messageDetailList =	messageDetailService.doGetList(messageDetail);
		if(messageDetailList.size() > 0){
			List<UserMessageEntiy> userMessageEntiy = messageDetailService.doGetMessageDetailList(messageDetailList);
			if(userMessageEntiy.size() > 0){
				for (int i = 0; i < userMessageEntiy.size(); i++) {
					if(userMessageEntiy.get(i).getUserType().equals("1")){
						userMessageEntiy.get(i).setUserType("消费商");
					}
					if(userMessageEntiy.get(i).getUserType().equals("0")){
						userMessageEntiy.get(i).setUserType("消费者");
					}
					if(userMessageEntiy.get(i).getUserType().equals("2")){
						userMessageEntiy.get(i).setUserType("商家");
					}
					if(userMessageEntiy.get(i).getUserState().equals("0")){
						userMessageEntiy.get(i).setUserState("正常");
					}else{
						userMessageEntiy.get(i).setUserState("停用");
					}
					//拼接地址
					String address=userMessageEntiy.get(i).getUserPrivices()+"省"+userMessageEntiy.get(i).getUserCity()+userMessageEntiy.get(i).getUserArea()+userMessageEntiy.get(i).getUserAddress();
					userMessageEntiy.get(i).setUserAddress(address);
				}
				respPagination.setTotal(userMessageEntiy.size());
				respPagination.setRows(userMessageEntiy);
				respBean.setBody(respPagination);
			}
		}
		return respBean;
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Jiang
	 * @date 2017-07-25
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditMessage")
	public RespBean<Message> doEditMessage(Message message) throws Exception{
		RespBean<Message> respBean = new RespBean<Message>();
		messageService.doModById(message);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Jiang
	 * @date 2017-07-25
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelMessage")
	@ResponseBody
	public RespBean<String> doDelMessageAction(String messageIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		messageService.doRmByIds(messageIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

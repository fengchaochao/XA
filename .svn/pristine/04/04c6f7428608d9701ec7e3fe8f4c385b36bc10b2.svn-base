/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-27
 * @version 1.0
 */
package com.prj.biz.action.messageobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.messageobject.MessageObject;
import com.prj.biz.service.messageobject.MessageObjectService;
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



/** 
 * 描述: 消息发送对象查询视图 Action 类<br>
 * @author Liang
 * @date 2017-07-27
 */
@RestController
@RequestMapping("/messageObject")
public class MessageObjectAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private MessageObjectService messageObjectService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnMessageObjectList")
    public ModelAndView doEnMessageObjectListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/messageobject/messageObjectList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadMessageObjectList")
	@ResponseBody
	public RespBean<RespPagination<MessageObject>> doReadMessageObjectList(MessageObject messageObject) throws Exception{
		RespBean<RespPagination<MessageObject>> respBean = new RespBean<RespPagination<MessageObject>>();
		RespPagination<MessageObject> respPagination = new RespPagination<MessageObject>();
		Integer total = messageObjectService.doGetTotal(messageObject);
		List<MessageObject> messageObjectList = messageObjectService.doGetList(messageObject);
		respPagination.setTotal(total);
		respPagination.setRows(messageObjectList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnMessageObjectEdit")
	public ModelAndView doEnMessageObjectEdit(String messageObjectId) throws Exception{
		MessageObject messageObject = messageObjectService.doGetById(messageObjectId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("messageObject", messageObject);
		return new ModelAndView("/messageobject/messageObjectEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditMessageObject")
	public RespBean<MessageObject> doEditMessageObject(MessageObject messageObject) throws Exception{
		RespBean<MessageObject> respBean = new RespBean<MessageObject>();
		messageObjectService.doModById(messageObject);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelMessageObject")
	@ResponseBody
	public RespBean<String> doDelMessageObjectAction(String messageObjectIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		messageObjectService.doRmByIds(messageObjectIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

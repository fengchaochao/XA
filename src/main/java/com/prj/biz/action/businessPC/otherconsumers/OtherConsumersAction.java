/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-31
 * @version 1.0
 */
package com.prj.biz.action.businessPC.otherconsumers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.otherconsumers.OtherConsumers;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.otherconsumers.OtherConsumersService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;



/** 
 * 描述: 其他消费者 Action 类<br>
 * @author Liang
 * @date 2017-07-31
 */
@RestController
@RequestMapping("/business/otherConsumers")
public class OtherConsumersAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private OtherConsumersService otherConsumersService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-31
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnOtherConsumersList")
    public ModelAndView doEnOtherConsumersListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/otherconsumers/otherConsumersList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-31
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadOtherConsumersList")
	@ResponseBody
	public RespBean<RespPagination<OtherConsumers>> doReadOtherConsumersList(OtherConsumers otherConsumers) throws Exception{
		RespBean<RespPagination<OtherConsumers>> respBean = new RespBean<RespPagination<OtherConsumers>>();
		RespPagination<OtherConsumers> respPagination = new RespPagination<OtherConsumers>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		List<OtherConsumers> otherConsumersList =new ArrayList<OtherConsumers>();
		Integer total =0;
		if("2".equals(sysUsers.getUserState())){
			otherConsumers.setBusinessId(sysUsers.getId());
			otherConsumersList = otherConsumersService.doGetList(otherConsumers);
			total =otherConsumersService.doGetTotal(otherConsumers);
		}
		respPagination.setTotal(total);
		respPagination.setRows(otherConsumersList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-31
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnOtherConsumersEdit")
	public ModelAndView doEnOtherConsumersEdit(String otherConsumersId) throws Exception{
		OtherConsumers otherConsumers = otherConsumersService.doGetById(otherConsumersId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("otherConsumers", otherConsumers);
		return new ModelAndView("/otherconsumers/otherConsumersEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-31
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditOtherConsumers")
	public RespBean<OtherConsumers> doEditOtherConsumers(OtherConsumers otherConsumers) throws Exception{
		RespBean<OtherConsumers> respBean = new RespBean<OtherConsumers>();
		otherConsumersService.doModById(otherConsumers);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-31
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelOtherConsumers")
	@ResponseBody
	public RespBean<String> doDelOtherConsumersAction(String otherConsumersIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		otherConsumersService.doRmByIds(otherConsumersIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

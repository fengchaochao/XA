/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-11
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.agentwithdrawalfee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.agentwithdrawalfee.AgentWithdrawalFee;
import com.prj.biz.bean.distribution.Distribution;
import com.prj.biz.service.agentwithdrawalfee.AgentWithdrawalFeeService;
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
 * 描述: 代理商提现手续费 Action 类<br>
 * @author Liang
 * @date 2017-07-11
 */
@RestController
@RequestMapping("/headquarters/agentWithdrawalFee")
public class AgentWithdrawalFeeAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private AgentWithdrawalFeeService agentWithdrawalFeeService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnAgentWithdrawalFeeList")
    public ModelAndView doEnAgentWithdrawalFeeListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		List<AgentWithdrawalFee> agentWithdrawalFeeList=agentWithdrawalFeeService.doGetList(null);
		if(agentWithdrawalFeeList.size()>0){
			model.put("agentWithdrawalFee", agentWithdrawalFeeList.get(0));
		}
		return new ModelAndView("/headquarters/agentwithdrawalfee/agentWithdrawalFeeEdit",model);
	}
	
	/**
	 * 描述: 保存
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSaveAgentWithdrawalFee")
	public RespBean<AgentWithdrawalFee> doSaveAgentWithdrawalFee(AgentWithdrawalFee agentWithdrawalFee) throws Exception{
		RespBean<AgentWithdrawalFee> respBean = new RespBean<AgentWithdrawalFee>();
		agentWithdrawalFeeService.doSave(agentWithdrawalFee);
		return respBean;
	}
	/**
	 * 描述: 编辑
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditAgentWithdrawalFee")
	public RespBean<AgentWithdrawalFee> doEditAgentWithdrawalFee(AgentWithdrawalFee agentWithdrawalFee) throws Exception{
		RespBean<AgentWithdrawalFee> respBean = new RespBean<AgentWithdrawalFee>();
		agentWithdrawalFeeService.doModById(agentWithdrawalFee);
		return respBean;
	}
	
}

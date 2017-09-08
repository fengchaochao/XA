/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-18
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.consumersaccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
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
 * 描述: 消费者账户表 Action 类<br>
 * @author Liang
 * @date 2017-07-18
 */
@RestController
@RequestMapping("/headquarters/consumersAccount")
public class ConsumersAccountAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private ConsumersAccountService consumersAccountService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnConsumersAccountList")
    public ModelAndView doEnConsumersAccountListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/consumersaccount/consumersAccountList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadConsumersAccountList")
	@ResponseBody
	public RespBean<RespPagination<ConsumersAccount>> doReadConsumersAccountList(ConsumersAccount consumersAccount) throws Exception{
		RespBean<RespPagination<ConsumersAccount>> respBean = new RespBean<RespPagination<ConsumersAccount>>();
		RespPagination<ConsumersAccount> respPagination = new RespPagination<ConsumersAccount>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		//商家查看锁定的
		if("2".equals(sysUsers.getUserState())){
			consumersAccount.setBusinessInformationId(sysUsers.getMerchantsId());
		}
		List<ConsumersAccount> consumersAccountList = consumersAccountService.doGetList(consumersAccount);
		Integer total = consumersAccountService.doGetTotal(consumersAccount);
		respPagination.setTotal(total);
		respPagination.setRows(consumersAccountList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnConsumersAccountEdit")
	public ModelAndView doEnConsumersAccountEdit(String consumersAccountId) throws Exception{
		ConsumersAccount consumersAccount = consumersAccountService.doGetById(consumersAccountId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("consumersAccount", consumersAccount);
		return new ModelAndView("/headquarters/consumersaccount/consumersAccountEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditConsumersAccount")
	public RespBean<ConsumersAccount> doEditConsumersAccount(ConsumersAccount consumersAccount) throws Exception{
		RespBean<ConsumersAccount> respBean = new RespBean<ConsumersAccount>();
		consumersAccountService.doModById(consumersAccount);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelConsumersAccount")
	@ResponseBody
	public RespBean<String> doDelConsumersAccountAction(String consumersAccountIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		consumersAccountService.doRmByIds(consumersAccountIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	/**
	 * 描述: 消费商进入锁定用户列表页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnLocalUserList")
    public ModelAndView doEnLocalUserList(HttpServletRequest request,HttpServletResponse response,String businessInformationId) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("businessInformationId", businessInformationId);
		return new ModelAndView("/headquarters/consumersaccount/localUserList",model);
	}
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadLocalUserList")
	@ResponseBody
	public RespBean<RespPagination<ConsumersAccount>> doReadLocalUserList(ConsumersAccount consumersAccount) throws Exception{
		RespBean<RespPagination<ConsumersAccount>> respBean = new RespBean<RespPagination<ConsumersAccount>>();
		RespPagination<ConsumersAccount> respPagination = new RespPagination<ConsumersAccount>();
		Integer total = consumersAccountService.doGetTotal(consumersAccount);
		List<ConsumersAccount> consumersAccountList = consumersAccountService.doEnConsumerslocalUser(consumersAccount);
		respPagination.setTotal(total);
		respPagination.setRows(consumersAccountList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商家进入锁定用户列表页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnBusinessLocalUserList")
    public ModelAndView doEnBusinessLocalUserList(HttpServletRequest request,HttpServletResponse response,String businessInformationId) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("businessInformationId", businessInformationId);
		return new ModelAndView("/headquarters/consumersaccount/businessLocalUserList",model);
	}
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadBusinessLocalUserList")
	@ResponseBody
	public RespBean<RespPagination<ConsumersAccount>> doReadBusinessLocalUserList(ConsumersAccount consumersAccount) throws Exception{
		RespBean<RespPagination<ConsumersAccount>> respBean = new RespBean<RespPagination<ConsumersAccount>>();
		RespPagination<ConsumersAccount> respPagination = new RespPagination<ConsumersAccount>();
		Integer total = consumersAccountService.doGetTotal(consumersAccount);
		List<ConsumersAccount> consumersAccountList = consumersAccountService.doEnBusinesslocalUser(consumersAccount);
		respPagination.setTotal(total);
		respPagination.setRows(consumersAccountList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
}

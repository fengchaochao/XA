/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-08-07
 * @version 1.0
 */
package com.prj.biz.action.bankwithdrawal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.bankwithdrawal.BankWithdrawal;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.bankwithdrawal.BankWithdrawalService;
import com.prj.biz.service.order.OrderService;
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
 * 描述: 银行卡信息 及待提金额 Action 类<br>
 * @author Liang
 * @date 2017-08-07
 */
@RestController
@RequestMapping("/bankWithdrawal")
public class BankWithdrawalAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private BankWithdrawalService bankWithdrawalService;
	
	@Resource
	private OrderService orderService;


	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-08-07
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnBankWithdrawalList")
    public ModelAndView doEnBankWithdrawalListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("/bankwithdrawal/bankWithdrawalList",model);
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-07
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadBankWithdrawalList")
	@ResponseBody
	public RespBean<RespPagination<BankWithdrawal>> doReadBankWithdrawalList(BankWithdrawal bankWithdrawal) throws Exception{
		RespBean<RespPagination<BankWithdrawal>> respBean = new RespBean<RespPagination<BankWithdrawal>>();
		RespPagination<BankWithdrawal> respPagination = new RespPagination<BankWithdrawal>();
		Integer total = bankWithdrawalService.doGetTotal(bankWithdrawal);
		List<BankWithdrawal> bankWithdrawalList = bankWithdrawalService.doGetList(bankWithdrawal);
		respPagination.setTotal(total);
		respPagination.setRows(bankWithdrawalList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-08-07
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnBankWithdrawalEdit")
	public ModelAndView doEnBankWithdrawalEdit(String bankWithdrawalId) throws Exception{
		BankWithdrawal bankWithdrawal = bankWithdrawalService.doGetById(bankWithdrawalId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("bankWithdrawal", bankWithdrawal);
		return new ModelAndView("/bankwithdrawal/bankWithdrawalEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-08-07
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditBankWithdrawal")
	public RespBean<BankWithdrawal> doEditBankWithdrawal(BankWithdrawal bankWithdrawal) throws Exception{
		RespBean<BankWithdrawal> respBean = new RespBean<BankWithdrawal>();
		bankWithdrawalService.doModById(bankWithdrawal);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-08-07
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelBankWithdrawal")
	@ResponseBody
	public RespBean<String> doDelBankWithdrawalAction(String bankWithdrawalIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		bankWithdrawalService.doRmByIds(bankWithdrawalIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

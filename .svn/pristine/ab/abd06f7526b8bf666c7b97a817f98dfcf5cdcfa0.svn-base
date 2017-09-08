/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Jiang
 * @date 2017-07-24
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.salesStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.order.OrderService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;



/** 
 * 描述: 销售统计 Action 类<br>
 * @author Liang
 * @date 2017-07-24
 */
@RestController
@RequestMapping("/headquarters/salesStatistics")
public class salesStatisticsAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private OrderService orderService;

	/***************销售额统计************************/
	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnSalesStatisticsList")
    public ModelAndView doEnSalesStatisticsListAction(HttpServletRequest request,HttpServletResponse response,String flag) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("flag", flag);
		return new ModelAndView("/salesStatistics/salesStatistics",model);
	}
	/**
	 * 描述: 分页查询信息
	 *  按是否抽成统计
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadSalesStatisticsList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadSalesStatisticsList(Order order) throws Exception {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			order.setAgentId(sysUsers.getMerchantsId());
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			order.setBusinessId(sysUsers.getMerchantsId());
		}
		List<Order> orders=orderService.doGetSalesTotal(order);
		respPagination.setTotal(orders.size());
		respPagination.setRows(orders);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 分页查询信息
	 * 详细的订单信息
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadSalesOrderList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadSalesOrderList(Order order) throws Exception {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			order.setAgentId(sysUsers.getMerchantsId());
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			order.setBusinessId(sysUsers.getMerchantsId());
		}
		
		List<Order> orders=orderService.doGetList(order);
		int total=orderService.doGetTotal(order);
		respPagination.setTotal(total);
		respPagination.setRows(orders);
		respBean.setBody(respPagination);
		return respBean;
	}

	
}

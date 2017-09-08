/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Jiang
 * @date 2017-07-24
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.remmStatistics;

import java.util.ArrayList;
import java.util.List;

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
 * 描述: 推荐统计（商家） Action 类<br>
 * @author Liang
 * @date 2017-07-24
 */
@RestController
@RequestMapping("/headquarters/remmStatistics")
public class remmStatisticsAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private OrderService orderService;

	/***************推荐统计************************/
	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnRemmStatisticsList")
    public ModelAndView doEnRemmStatisticsListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		return new ModelAndView("/remmStatistics/remmStatistics");
	}
	/**
	 * 描述: 查询信息
	 *  推荐统计
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadRemmStatisticsList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadRemmStatisticsList(Order order) throws Exception {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		List<Order> orders=new ArrayList<Order>();
		//只有商家可以查看
		if("2".equals(sysUsers.getUserState())){
			order.setRemmBussines(sysUsers.getId());
			orders=orderService.doGetRemmTotal(order);
		}
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
	@RequestMapping("/doReadRemmOrderList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadRemmOrderList(Order order) throws Exception {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		int total=0;
		List<Order> orders=new ArrayList<Order>();
		
		//只有商家可以查看
		if("2".equals(sysUsers.getUserState())){
			order.setRemmBussines(sysUsers.getId());
			orders=orderService.doGetList(order);
			total=orderService.doGetTotal(order);
		}
		respPagination.setTotal(total);
		respPagination.setRows(orders);
		respBean.setBody(respPagination);
		return respBean;
	}

	
}

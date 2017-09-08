/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Jiang
 * @date 2017-07-24
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.commissionStatistics;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.order.Order;
import com.prj.biz.service.order.OrderService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;



/** 
 * 描述: 抽成统计(平台) Action 类<br>
 * @author Liang
 * @date 2017-07-24
 */
@RestController
@RequestMapping("/headquarters/commissionStatistics")
public class commissionStatisticsAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	
	@Resource
	private OrderService orderService;

	/***************抽成统计************************/
	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnCommissionStatisticsList")
    public ModelAndView doEnCommissionStatisticsList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		return new ModelAndView("/commissionStatistics/commissionStatistics");
	}
	/**
	 * 描述: 分页查询信息
	 *  
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadCommissionStatisticsList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadCommissionStatisticsList(Order order) throws Exception {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		order.setIsBonus("1");
		List<Order> orders=orderService.doGetList(order);
		respPagination.setTotal(orders.size());
		respPagination.setRows(orders);
		respBean.setBody(respPagination);
		return respBean;
	}

	
}

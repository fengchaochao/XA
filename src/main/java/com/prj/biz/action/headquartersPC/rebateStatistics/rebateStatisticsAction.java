/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Jiang
 * @date 2017-07-24
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.rebateStatistics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
 * 描述: 返利统计 Action 类<br>
 * @author Liang
 * @date 2017-07-24
 */
@RestController
@RequestMapping("/headquarters/rebateStatistics")
public class rebateStatisticsAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private OrderService orderService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnRebateStatisticsList")
    public ModelAndView doEnRebateStatisticsListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		ModelAndView  modelAndView=null;
		//总部登录跳转
		if("0".equals(sysUsers.getUserState())){
		     modelAndView= new ModelAndView("/rebateStatistics/headquartersRebateStatistics");
		}
		//代理商登录跳转
		if("1".equals(sysUsers.getUserState())){
			modelAndView=new ModelAndView("/rebateStatistics/agentRebateStatistics");
		}
		//商家登录跳转
		if("2".equals(sysUsers.getUserState())){
			modelAndView=new ModelAndView("/rebateStatistics/bussinessRebateStatistics");
		}
		return modelAndView;
	}
	/**
	 * 描述: 查询信息
	 *  
	 * @auther Liang
	 * @date 2017-07-12
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadHeadquartersRebateStatisticsList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadHeadquartersRebateStatisticsList(Order order) throws Exception {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		List<Order> orders=new ArrayList<Order>();
		//总部登录
		if("0".equals(sysUsers.getUserState())){
			orders=orderService.doGetRemmTotal(order);
		}
		//代理商登录
		if("1".equals(sysUsers.getUserState())){
			order.setHigherAgent(sysUsers.getMerchantsId());
			orders=orderService.doGetRemmTotal(order);
		}
		//商家登录
		if("2".equals(sysUsers.getUserState())){
			order.setLocalBussiness(sysUsers.getMerchantsId());
			orders=orderService.doGetRemmTotal(order);
		}
		if(orders.size()>0){
			for (int i = 0; i < orders.size(); i++) {
				if(StringUtils.isEmpty(orders.get(i).getBusinessType())){
					orders.get(i).setBusinessType("消费商");
				}
				
			}
			
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
	@RequestMapping("/doReadHeadquartersRebateOrderList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadHeadquartersRebateOrderList(Order order) throws Exception {
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		
		//代理商登录 作为上级代理商获得的返利
		if("1".equals(sysUsers.getUserState())){
			order.setHigherAgent(sysUsers.getMerchantsId());
		}
		//商家登录 作为锁定者获得返利
		if("2".equals(sysUsers.getUserState())){
			order.setLocalBussiness(sysUsers.getMerchantsId());
		}
		//总部登录 平台获利
		int total=orderService.doGetTotal(order);
		List<Order> orders=orderService.doGetList(order);
		if(orders.size()>0){
			for (int i = 0; i < orders.size(); i++) {
				if(StringUtils.isEmpty(orders.get(i).getBusinessType())){
					orders.get(i).setBusinessType("消费商");
				}
				
			}
			
		}
		respPagination.setTotal(total);
		respPagination.setRows(orders);
		respBean.setBody(respPagination);
		return respBean;
	}
	
}

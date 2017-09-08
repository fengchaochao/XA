package com.prj.biz.service.order;

import java.util.List;

import com.prj.biz.bean.order.Order;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 订单表 Service 接口<br>
 * @author Liang
 * @date 2017-08-01
 */
public interface OrderService extends BaseService<Order>{
	
	/**
	 * 销售统计
	 * @param order
	 * @return
	 */
	public  List<Order> doGetSalesTotal(Order order);
	/**
	 * 推荐统计（商家）
	 * @param order
	 * @return
	 */
	public  List<Order> doGetRemmTotal(Order order);
	/**
	 * App端订单管理
	 * @param order
	 * @return
	 */
	public  List<Order> doGetListApi(Order order);
	/**
	 * App端月订单信息
	 * @param order
	 * @return
	 */
	public  List<Order> doGetMonthListApi(Order order);
	/**
	 * App端月订单返利统计信息
	 * @param order
	 * @return
	 */
	public  List<Order> doGetMonthTotalApi(Order order);
}

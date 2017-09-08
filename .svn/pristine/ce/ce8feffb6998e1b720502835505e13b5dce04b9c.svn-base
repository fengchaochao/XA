package com.prj.biz.dao.maindb.order;

import java.util.List;

import com.prj.biz.bean.order.Order;
import com.prj.biz.dao._base.BaseDao;

/**
 * 
 * 描述: 订单表 Dao 接口<br>
 * @author Liang
 * @date 2017-08-01
 */
public interface OrderDao extends BaseDao<Order>

{
	/**
	 * 销售统计
	 * @param order
	 * @return
	 */
	public  List<Order> selectSalesTotal(Order order);
	/**
	 * 推荐统计（商家）
	 * @param order
	 * @return
	 */
	public  List<Order> selectRemmTotal(Order order);
 
	/**
	 * App端订单管理
	 * @param order
	 * @return
	 */
	public  List<Order> selectListApi(Order order);
	/**
	 * App端月订单信息
	 * @param order
	 * @return
	 */
	public  List<Order> selectMonthListApi(Order order);
	/**
	 * App端月订单返利统计信息
	 * @param order
	 * @return
	 */
	public  List<Order> selectMonthTotalApi(Order order);
	
}
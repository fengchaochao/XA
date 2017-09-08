package com.prj.biz.service._impl.order;

import java.util.List;

import com.prj.biz.bean.order.Order;
import com.prj.biz.dao.maindb.order.OrderDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.order.OrderService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 订单表 Service 实现<br>
 * @author Liang
 * @date 2017-08-01
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderDao,Order> implements OrderService
{

	@Override
	public List<Order> doGetSalesTotal(Order order) {
		// TODO Auto-generated method stub
		return genDao.selectSalesTotal(order);
	}

	@Override
	public List<Order> doGetRemmTotal(Order order) {
		// TODO Auto-generated method stub
		return genDao.selectRemmTotal(order);
	}

	@Override
	public List<Order> doGetListApi(Order order) {
		// TODO Auto-generated method stub
		return genDao.selectListApi(order);
	}

	@Override
	public List<Order> doGetMonthListApi(Order order) {
		// TODO Auto-generated method stub
		return genDao.selectMonthListApi(order);
	}

	@Override
	public List<Order> doGetMonthTotalApi(Order order) {
		// TODO Auto-generated method stub
		return genDao.selectMonthTotalApi(order);
	}
	

}

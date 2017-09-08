package com.prj.biz.service.RebateCalculation;

import com.prj.biz.bean.order.Order;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 线下支付记录 Service 接口<br>
 * @author Liang
 * @date 2017-08-23
 */
public interface RebateCalculationService extends BaseService<Object>{
	


	/**
	 * 引流消费者消费抽成(当该消费者被商家A锁定的时候，去商家B购买商品)
	 * 
	 * @param orderId
	 *            订单id
	 * @param consumersAccountId
	 *            消费者账号ID
	 * @param bussinessId
	 *            商品对应的商家ID
	 */
	public void pumpingCalculation(String orderId, String consumersAccountId,
			String bussinessId) throws Exception;

	/**
	 * 分配锁定抽成(当该消费者没有被商家锁定的时候)
	 * 
	 * @param orderId
	 *            订单ID
	 * @param payAccount
	 *            支付账号
	 * @param bussinessId
	 *            订单商品对应的店铺ID
	 * @param consumersId
	 *            消费者ID及当前用户对应的merchantsId
	 * @param accountType
	 *            账号类型1-微信用户 2-支付宝用户
	 * @throws Exception
	 */
	public void lockedDraw(String orderId, String payAccount,
			String bussinessId, String consumersId, String accountType)
			throws Exception;

	/**
	 * 支付成功之后 更新库存
	 * 
	 * @param orderId
	 *            订单ID
	 * @param
	 * @throws Exception
	 */
	public void updateInvetry(String orderId) throws Exception ;
	public void transferRecord(Order order) throws Exception;
}

/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-08-17
 * @version 1.0
 */
package com.prj.biz.api.shoppingcart;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pingplusplus.model.Charge;
import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.goodsspecifications.GoodsSpecifications;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.shippingaddress.ShippingAddress;
import com.prj.biz.bean.shoppingcart.ShoppingCart;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transactionrecords.TransactionRecords;
import com.prj.biz.service.RebateCalculation.RebateCalculationService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.goodsspecifications.GoodsSpecificationsService;
import com.prj.biz.service.order.OrderService;
import com.prj.biz.service.shippingaddress.ShippingAddressService;
import com.prj.biz.service.shoppingcart.ShoppingCartService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.service.transactionrecords.TransactionRecordsService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.utils.ping.ChargeExample;
import com.prj.utils.ping.PayConfiger;



/** 
 * 描述: APP购物车 Action 类<br>
 * @author Liang
 * @date 2017-08-17
 */
@RestController
@RequestMapping("/api")
public class ShoppingCartAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private ConsumersAccountService consumersAccountService;
	
	@Autowired
	private TransactionRecordsService transactionRecordsService;
	
	@Autowired
	private GoodsSpecificationsService goodsSpecificationsService;
	
	@Autowired
	private RebateCalculationService calculationService;
	
	@Autowired
	private BusinessInformationService businessInformationService;
	
	@Autowired
	private ConsumersService consumersService;

	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadShoppingCartList")
	@ResponseBody
	public RespBean<RespPagination<ShoppingCart>> doReadShoppingCartList(ShoppingCart shoppingCart) throws Exception{
		RespBean<RespPagination<ShoppingCart>> respBean = new RespBean<RespPagination<ShoppingCart>>();
		RespPagination<ShoppingCart> respPagination = new RespPagination<ShoppingCart>();
		Integer total = shoppingCartService.doGetTotal(shoppingCart);
		List<ShoppingCart> shoppingCartList = shoppingCartService.doGetList(shoppingCart);
		for (ShoppingCart sc : shoppingCartList) {
			GoodsSpecifications specifications = goodsSpecificationsService.doGetById(sc.getSpecificationsId());
			if(specifications != null){
				int gg = specifications.getInventory();
				if(gg > Integer.parseInt(sc.getNumber())){
					
				}else {
					//库存不足
					sc.setStatus("5");
				}
			}
		}
		respPagination.setTotal(total);
		respPagination.setRows(shoppingCartList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 新增购物车商品
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doAddShoppingCart")
	public RespBean<ShoppingCart> doAddShoppingCart(ShoppingCart shoppingCart) throws Exception{
		RespBean<ShoppingCart> respBean = new RespBean<ShoppingCart>();
		ShoppingCart cart = new ShoppingCart();
		cart.setBusinessId(shoppingCart.getBusinessId());
		cart.setGoodsId(shoppingCart.getGoodsId());
		cart.setPurchaserId(shoppingCart.getPurchaserId());
		cart.setSpecificationsId(shoppingCart.getSpecificationsId());
		List<ShoppingCart> list = shoppingCartService.doGetList(cart);
		if(list.size() > 0){
			Integer num = Integer.parseInt(list.get(0).getNumber());
			ShoppingCart cart2 = new ShoppingCart();
			cart2.setId(list.get(0).getId());
			cart2.setNumber((num + 1) + "");
			shoppingCartService.doModById(cart2);
		}else {
			shoppingCartService.doSave(shoppingCart);
			respBean.setBody(shoppingCart);
		}
		return respBean;
	}
	
	
	/**
	 * 描述: 修改商品数量
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditShoppingCart")
	public RespBean<String> doEditShoppingCart(ShoppingCart shoppingCart) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		ShoppingCart sc = shoppingCartService.doGetById(shoppingCart.getId());
		//判断库存
		GoodsSpecifications specifications = goodsSpecificationsService.doGetById(sc.getSpecificationsId());
		if(specifications != null){
			int gg = specifications.getInventory();
			if(gg >= Integer.parseInt(shoppingCart.getNumber())){
				shoppingCartService.doModById(shoppingCart);
				respBean.setBody("1");
			}else {
				//库存不足加入不了购物车
				respBean.setBody("0");
			}
		}
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelShoppingCart")
	@ResponseBody
	public RespBean<String> doDelShoppingCartAction(String id) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		shoppingCartService.doRmByIds(id);
		respBean.setBody("删除成功");
		return respBean;
	}
	
	/**
	 * 描述: 下单
	 * @auther Liang
	 * @date 2017-08-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/AppOrderUp")
	@ResponseBody
	public RespBean<Map<String, Object>> AppOrderUp(String ids,String userId,String orderId) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isEmpty(orderId)){
			String[] id = ids.split(",");
			double moneyCount = 0.00;
			String businessId="";
			//循坏获取该购物车id下的商品价钱
			for (int i = 0; i < id.length; i++) {
				ShoppingCart shoppingCart = shoppingCartService.doGetById(id[i]);
				if(shoppingCart != null){
					//获取商品购买数量
					String number = shoppingCart.getNumber();
					//获取商品价钱
					String price = shoppingCart.getPrice();
					if(price != null && !price.equals("")){
						//商品总额
						moneyCount += Integer.parseInt(number) * Double.parseDouble(price);
					}
					//查询需要购买的商品对应的所有商家ID
					if(StringUtils.isEmpty(businessId)){
						businessId=shoppingCart.getBusinessId();
					}else{
						String[] businessIds = businessId.split(",");
						String msg="0";
						for (int j = 0; j < businessIds.length; j++) {
							if(businessIds[j].equals(shoppingCart.getBusinessId())){
								msg="1";
							}
						}
						if("0".equals(msg)){
							businessId=businessId+","+shoppingCart.getBusinessId();
						}
					}
				}
			}
			map.put("moneyCount", moneyCount + "");
			map.put("ids", ids);
			map.put("businessId", businessId);
		}else{
			Order order=orderService.doGetById(orderId);
			map.put("moneyCount", order.getMoney());
		}
		//获取支付方式
		SysUser sysUser=sysUserService.doGetById(userId);
		ConsumersAccount consumersAccount=new ConsumersAccount();
		consumersAccount.setConsumersId(sysUser.getMerchantsId());
		List<ConsumersAccount> consumersAccounts=consumersAccountService.doGetList(consumersAccount);
		String payAcount="";
		String weixingAcount="";
		String accountBalance=sysUser.getAccountBalance();
		//1-微信用户 2-支付宝用户 
		for (ConsumersAccount consumersAccount2 : consumersAccounts) {
			if("1".equals(consumersAccount2.getUserType())){
				weixingAcount=consumersAccount2.getUserAccount();
			}
			if("2".equals(consumersAccount2.getUserType())){
				payAcount=consumersAccount2.getUserAccount();	
			}
		}
		//获取默认的收货地址
		ShippingAddress address = new ShippingAddress();
		address.setUserId(userId);
		address.setOrder("asc");
		address.setOrderName("is_default");
		List<ShippingAddress> shippingAddressList = shippingAddressService.doGetList(address);
		map.put("shippingAddressList",shippingAddressList);
		map.put("payAcount", payAcount);
		map.put("weixingAcount", weixingAcount);
		map.put("accountBalance", accountBalance);
		respBean.setBody(map);
		return respBean;
	}
	/**
	 * 描述: 确认支付
	 * @auther Liang
	 * @date 2017-08-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/surePay")
	@ResponseBody
	public RespBean<Map<String, Object>> surePay(String ids,String payType,String distributionAddressId,String businessId,String userId,String orderId) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isEmpty(orderId)){
			//生成订单编号用
			Date date = new Date();
			double moneyCount = 0.00;
			//拼接订单编号
			String orderCode="";
			//循坏获取该购物车id下的商品价钱
			String[] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				ShoppingCart shoppingCart = shoppingCartService.doGetById(id[i]);
				if(shoppingCart != null){
					//获取商品购买数量
					String number = shoppingCart.getNumber();
					//获取商品价钱
					String price = shoppingCart.getPrice();
					if(price != null && !price.equals("")){
						//商品总额
						moneyCount += Integer.parseInt(number) * Double.parseDouble(price);
					}
				}
			}
			String[] businessIds = businessId.split(",");
			for (int i = 0; i < businessIds.length; i++) {
				//生成订单号
				String orderNo = "";
				if("2".equals(payType)){
					orderNo = "AL" + String.valueOf(date.getTime())+(int) (Math.random() * 900) + 100;
				}
				//调用微信
				if("1".equals(payType)){
					orderNo = "WX" + String.valueOf(date.getTime())+(int) (Math.random() * 900) + 100;
				}
				if("3".equals(payType)){
					orderNo = "ZH" + String.valueOf(date.getTime())+(int) (Math.random() * 900) + 100;
				}
				Order order=new Order();
				order.setOrderNumber(orderNo);
				order.setDistributionAddressId(distributionAddressId);
				order.setStatus("4");
				order.setTransactionMode("2");
				order.setBusinessId(businessIds[i]);
				
				Consumers consumers=consumersService.doGetById(businessIds[i]);
				if(consumers!=null){
					order.setAgentId(consumers.getAgentId());
				}else{
					BusinessInformation businessInformation=businessInformationService.doGetById(businessIds[i]);
					if(businessInformation!=null){
						order.setAgentId(businessInformation.getHigherAgentId());
					}
				}
				//总价格
				float priceNum=0;
				//总数量
				int Num=0;
				String[] ides = ids.split(",");
				for (int j = 0; j < ides.length; j++) {
					ShoppingCart shoppingCart = shoppingCartService.doGetById(ides[j]);
					if(shoppingCart.getBusinessId().equals(businessIds[i])){
						//获取商品购买数量
						String number = shoppingCart.getNumber();
						//获取商品价钱
						String price = shoppingCart.getPrice();
						if(price != null && !price.equals("")){
							//商品总额
							priceNum += Double.parseDouble(number) * Double.parseDouble(price);
						}
						Num+=Integer.parseInt(number);
					}
				}
				order.setMoney(String.valueOf(priceNum));
				order.setOrderNum(String.valueOf(Num));
				//获取购买者的ID
				SysUser sysUser=sysUserService.doGetById(userId);
				if(sysUser!=null){
					order.setPurchaserId(sysUser.getMerchantsId());
				}
				orderService.doSave(order);
				//拼接订单编号
				if(StringUtils.isEmpty(orderCode)){
					orderCode=orderNo;
				}else{
					orderCode=orderCode+","+orderNo;
				}
				//生成订单交易记录
				for (int j = 0; j < ides.length; j++) {
					ShoppingCart shoppingCart = shoppingCartService.doGetById(ides[j]);
					if(shoppingCart.getBusinessId().equals(businessIds[i])){
						//获取商品购买数量
						String number = shoppingCart.getNumber();
						//获取商品价钱
						String price = shoppingCart.getPrice();
						
						TransactionRecords transactionRecords=new TransactionRecords();
						transactionRecords.setOrderNo(order.getId());
						transactionRecords.setTransactionNum(number);
						transactionRecords.setTotalPrice(String.valueOf(Double.parseDouble(number) * Double.parseDouble(price)));
						transactionRecords.setGoodsUnitId(shoppingCart.getSpecificationsId());
						transactionRecords.setBusinessId(shoppingCart.getBusinessId());
						if(sysUser!=null){
							transactionRecords.setCunsumersId(sysUser.getMerchantsId());
						}
						transactionRecordsService.doSave(transactionRecords);
					}
				}
				calculationService.updateInvetry(order.getId());
				//删除购物车
				for (int x = 0; x < id.length; x++) {
					if(!StringUtils.isEmpty(id[x])){
						shoppingCartService.doRmById(id[x]);
					}
				}
			}
			
			Charge res =new Charge();
			
			//支付宝支付
			if("2".equals(payType)){
				res = ChargeExample.getPay(orderCode, (moneyCount*100), "127.0.0.1", PayConfiger.payType_alipay, "Test pay","夜鱼-在线消费");
			}
			//微信支付
			if("1".equals(payType)){
				res = ChargeExample.getPay(orderCode, (moneyCount*100), "127.0.0.1", PayConfiger.payType_wxpay, "Test pay","夜鱼-在线消费");
			}
			SysUser sysUser=sysUserService.doGetById(userId);
			//余额支付
			String msg="";
			if("3".equals(payType)){
				sysUser.setAccountBalance(String.valueOf(Float.parseFloat(sysUser.getAccountBalance())-moneyCount));
				sysUserService.doModById(sysUser);
				msg="余额支付成功";
			}
			map.put("msg", msg);
			map.put("res", res);
		}else{
			Order order=orderService.doGetById(orderId);
			Charge res =new Charge();
			//支付宝支付
			if("2".equals(payType)){
				res = ChargeExample.getPay(order.getOrderNumber(), (Double.parseDouble(order.getMoney())*100), "127.0.0.1", PayConfiger.payType_alipay, "Test pay","夜鱼-在线消费");
			}
			//微信支付
			if("1".equals(payType)){
				res = ChargeExample.getPay(order.getOrderNumber(), (Double.parseDouble(order.getMoney())*100), "127.0.0.1", PayConfiger.payType_wxpay, "Test pay","夜鱼-在线消费");
			}
			SysUser sysUser=sysUserService.doGetById(userId);
			//余额支付
			String msg="";
			if("3".equals(payType)){
				sysUser.setAccountBalance(String.valueOf(Float.parseFloat(sysUser.getAccountBalance())-Float.parseFloat(order.getMoney())));
				sysUserService.doModById(sysUser);
				msg="余额支付成功";
			}
			map.put("msg", msg);
			map.put("res", res);
		}
		respBean.setBody(map);
		return respBean;
	}
	
	/**
	 * 描述: 订单页
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doOrderList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doOrderList(Order order) throws Exception{
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		Integer total = orderService.doGetTotal(order);
		List<Order> orderList = orderService.doGetList(order);
		respPagination.setTotal(total);
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
}

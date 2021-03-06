package com.prj.biz.action.headquartersPC.businessStatistics;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tutorial.redis.RedisUtil;

import com.alibaba.druid.filter.Filter;
import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.order.OrderDown;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transactionrecords.TransactionRecords;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.order.OrderService;
import com.prj.biz.service.transactionrecords.TransactionRecordsService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.excel.ReportToExcel;

/**
 * 订单统计
 * @author xjt
 *
 */
@RestController
@RequestMapping("/headquarters/orderStatistics")
public class OrderStatisticsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Resource
	private OrderService orderService;
	@Resource
	private codeAreasService codeAreasService;
	@Resource
	private ConsumersService consumersService;
	@Resource
	private BusinessInformationService businessInformationService;
	@Resource
	private TransactionRecordsService transactionRecordsService;
	@Resource
	private ConsumersAccountService consumersAccountService;

	/**
	 * 描述: 订单统计页(总部)
	 * @auther xjt
	 * @date 2017-08-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doOrderStatisticsList")
    public ModelAndView doOrderStatisticsList(HttpServletRequest request,HttpServletResponse response,String flag) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		/**
		 * 是否显示商家实收额
		 */
		//0-不显示 1-显示
		String isShowAmount = RedisUtil.getJedis().get("isShowAmount");
		if(StringUtils.isEmpty(isShowAmount)){
			RedisUtil.getJedis().set("isShowAmount", "0");
		}
		model.put("countryList", codeAreasService.selectCountyList());
		model.put("provinceList", codeAreasService.selectProvinceList());
		model.put("cityList", codeAreasService.selectCityList());
		model.put("areaList", codeAreasService.selectRegionList());
		model.put("type", sysUsers.getUserState());
		model.put("isShowAmount", isShowAmount);
		model.put("flag", flag);
		return new ModelAndView("/headquarters/businessStatistics/orderStatisticsList",model);
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadOrderList")
	@ResponseBody
	public RespBean<RespPagination<Order>> doReadOrderList(Order order) throws Exception{
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		TransactionRecords records = new TransactionRecords();
		int num = 0;
		double money = 0.00;
		double actualMoney = 0.00;
		//总部查看全部数据，代理商查看该代理商下的数据，商家查看扫码支付的数据
		// 用户类型0-总部 1-代理商 2-商家  3-消费商 4-消费者 5-消费者账户
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		
		if(sysUsers.getUserState().equals("1")){//代理商
			order.setAgentId(sysUsers.getId());
		}else if(sysUsers.getUserState().equals("2")){
			
			order.setBusinessId(sysUsers.getId());
		}
		Integer total = orderService.doGetTotal(order);
		List<Order> orderList = orderService.doGetList(order);
		for (Order or : orderList) {
			//通过businessId字段在商家表和消费商表查找数据，拿到编号和昵称
			BusinessInformation businessInformation = businessInformationService.doGetById(or.getBusinessId());
			if(businessInformation != null){
				or.setStoreName(businessInformation.getVendorName());
				or.setStoreNumber(businessInformation.getMerchantNumber());
			}else {
				Consumers consumersxfs = consumersService.doGetById(or.getBusinessId());
				if(consumersxfs != null){
					or.setStoreName(consumersxfs.getNickName());
					or.setStoreNumber(consumersxfs.getCustomerNumber());
				}
			}
			
			//需要再这里循环查询是否是消费者id，如果是查询出对应的消费者昵称，不是则显示原本数据
			Consumers consumers = consumersService.doGetById(or.getPurchaserId());
			if(consumers != null){
				or.setConsumerAccount(consumers.getNickName());
			}else{
				//根据ConsumerAccount字段去账号表差对应的账号信息
				ConsumersAccount consumersAccount = consumersAccountService.doGetById(or.getConsumerAccount());
				if(consumersAccount != null){
					or.setConsumerAccount(consumersAccount.getUserAccount());
				}else {
					BusinessInformation bi = businessInformationService.doGetById(or.getPurchaserId());
					if(bi != null){
						or.setConsumerAccount(bi.getVendorName());
					}
				}
			}
			//获取该订单下的交易记录
			records.setOrderNo(or.getId());
			List<TransactionRecords> transactionRecordList = transactionRecordsService.doGetList(records);
			if(transactionRecordList.size() > 0 && !"[null]".equals(transactionRecordList.toString())){
				for (int i = 0; i < transactionRecordList.size(); i++) {
					String transactionNum = transactionRecordList.get(i).getTransactionNum();
					num += Integer.parseInt(transactionNum);
				}
			}
			
			money += Double.parseDouble(or.getMoney()) ;
			double bonus = 0.00;
			if(or.getBonus() != null && or.getBonus().equals("")){
				bonus = Double.parseDouble(or.getBonus());
			}
			actualMoney += Double.parseDouble(or.getMoney()) - bonus;
		}
		for (Order order2 : orderList) {
			order2.setSalesCount(num + "");
			order2.setOrderCount(orderList.size() + "");
			order2.setSalesMoney(money + "");
			order2.setActualMoney(actualMoney + "");
		}
		
		respPagination.setTotal(total);
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	
	/**
	 * 描述: 配送
	 * @auther Liang
	 * @date 2017-08-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditOrder")
	public RespBean<Order> doEditOrder(Order order) throws Exception{
		RespBean<Order> respBean = new RespBean<Order>();
		orderService.doModById(order);
		respBean.setBody(order);
		return respBean;
	}
	
	
	/**
	 * 描述: 导出
	 * @auther xingjiangtao
	 * @date 2017-8-8
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcel")
	@ResponseBody
	public void downExcelExpenditure(PrintWriter writer,HttpServletResponse response, HttpServletRequest request)
			throws Exception { 
		
		ReportToExcel<Order> ex = new ReportToExcel<Order>();
		
		List<Filter> filter = new ArrayList<Filter>();
		List dataset = new ArrayList();
		String result = "";
		String fileName = "test.xls";
		String getIp =  "118.178.226.170";//IDUtils.getIp();
		
		try {
			Order order = new Order();
			SysUser sysUser = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
			if(sysUser.getUserState().equals("1")){//代理商
				order.setAgentId(sysUser.getId());
			}else if(sysUser.getUserState().equals("2")){
				
				order.setBusinessId(sysUser.getId());
			}
			List<Order> orderList = orderService.doGetList(order);
			for (int i = 0; i < orderList.size(); i++) {
				String storeName = "";
				String storeNumber = "";
				String consumerAccount = "";
				String transactionMode = "";
				String status = "";
				String isBonus = "";
				//通过businessId字段在商家表和消费商表查找数据，拿到编号和昵称
				BusinessInformation businessInformation = businessInformationService.doGetById(orderList.get(i).getBusinessId());
				if(businessInformation != null){
					storeName = businessInformation.getVendorName();
					storeNumber = businessInformation.getMerchantNumber();
				}else {
					Consumers consumers = consumersService.doGetById(orderList.get(i).getBusinessId());
					if(consumers != null){
						storeName = consumers.getNickName();
						storeNumber = consumers.getCustomerNumber();
					}
				}
				
				//需要再这里循环查询是否是消费者id，如果是查询出对应的消费者昵称，不是则显示原本数据
				Consumers consumers = consumersService.doGetById(orderList.get(i).getConsumerAccount());
				if(consumers != null){
					consumerAccount = consumers.getNickName();
				}
				
				// 交易方式 1：扫码支付 2：在线支付 
				if(orderList.get(i).getTransactionMode().equals("1")){
					transactionMode = "扫码支付";
				}else if(orderList.get(i).getTransactionMode().equals("2")){
					transactionMode = "在线支付";
				}
				// 订单状态 1待配送、2待收货、3已完成、4:待付款 (扫码支付的订单状态只有：已完成    在线支付的订单状态有：待配送、待收货、已完成)
				if(orderList.get(i).getStatus().equals("1")){
					status = "待配送";
				}else if(orderList.get(i).getStatus().equals("2")){
					status = "待收货";
				}else if(orderList.get(i).getStatus().equals("3")){
					status = "已完成";
				}else if(orderList.get(i).getStatus().equals("4")){
					status = "待付款";
				}
				 // 是否抽成 1:是 0：否 
			    if(orderList.get(i).getIsBonus().equals("1")){
			    	isBonus = "是";
				}else if(orderList.get(i).getIsBonus().equals("0")){
					isBonus = "否";
				}
			    
				dataset.add(new OrderDown(orderList.get(i).getCreatetime(),orderList.get(i).getOrderNumber(),
						storeNumber,storeName,consumerAccount,orderList.get(i).getMoney(),isBonus,
						orderList.get(i).getBonus(),transactionMode,status));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String name = "订单统计";
			String[] headers = {"订单时间","订单号","商家编号","商家名称","消费者账户","交易金额","是否抽成","抽成金额","交易方式","订单状态"};
			ex.exportExcel("", headers, dataset, path, name, new FileOutputStream(fileName));
			result = "{\"result\":\"success\",\"filename\":\"" + getIp + ":8080/XA/uploadfiledata/"+ name + ".xls\"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "{\"result\":\"error\"}";
		}
		writer.write(result);
		writer.flush();
		writer.close();
	}
	

}

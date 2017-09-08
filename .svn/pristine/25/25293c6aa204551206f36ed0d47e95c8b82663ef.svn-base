/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-08-15
 * @version 1.0
 */
package com.prj.biz.api.shippingaddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.shippingaddress.ShippingAddress;
import com.prj.biz.service.shippingaddress.ShippingAddressService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;



/** 
 * 描述: 收货地址 Action 类<br>
 * @author Liang
 * @date 2017-08-15
 */
@RestController
@RequestMapping("/api")
public class ShippingAddressAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private ShippingAddressService shippingAddressService;

	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-15
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadShippingAddressList")
	@ResponseBody
	public RespBean<RespPagination<ShippingAddress>> doReadShippingAddressList(ShippingAddress shippingAddress) throws Exception{
		RespBean<RespPagination<ShippingAddress>> respBean = new RespBean<RespPagination<ShippingAddress>>();
		RespPagination<ShippingAddress> respPagination = new RespPagination<ShippingAddress>();
		shippingAddress.setOrder("asc");
		shippingAddress.setOrderName("is_default");
		Integer total = shippingAddressService.doGetTotal(shippingAddress);
		List<ShippingAddress> shippingAddressList = shippingAddressService.doGetList(shippingAddress);
		respPagination.setTotal(total);
		respPagination.setRows(shippingAddressList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-08-15
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnShippingAddressById")
	public RespBean<Map<String, Object>> doEnShippingAddressById(String id) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		ShippingAddress shippingAddress = shippingAddressService.doGetById(id);
		map.put("shippingAddress", shippingAddress);
	    respBean.setBody(map);
		return respBean;
	}
	
	/**
	 * 描述: 修改
	 * @auther Liang
	 * @date 2017-08-15
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditShippingAddress")
	public RespBean<ShippingAddress> doEditShippingAddress(ShippingAddress shippingAddress) throws Exception{
		RespBean<ShippingAddress> respBean = new RespBean<ShippingAddress>();
		//  在设置默认字段不为空的情况下，先把所有该用户下的地址都设置为不是默认，完成后再把要设置为默认的设置为默认
		if(shippingAddress.getIsDefault() != null || !shippingAddress.getIsDefault().equals("")){
			ShippingAddress address = new ShippingAddress();
			address.setUserId(shippingAddress.getUserId());
			List<ShippingAddress> addressesList = shippingAddressService.doGetList(address); 
			for (int i = 0; i < addressesList.size(); i++) {
				ShippingAddress sa = new ShippingAddress();
				sa.setId(addressesList.get(i).getId());
				sa.setIsDefault("1");
				shippingAddressService.doModById(sa);
			}
			shippingAddressService.doModById(shippingAddress);
		}else {
			shippingAddressService.doModById(shippingAddress);
		}
		respBean.setBody(shippingAddress);
		return respBean;
	}
	
	/**
	 * 描述: 新增
	 * @auther Liang
	 * @date 2017-08-15
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doAddShippingAddress")
	public RespBean<ShippingAddress> doAddShippingAddress(ShippingAddress shippingAddress) throws Exception{
		RespBean<ShippingAddress> respBean = new RespBean<ShippingAddress>();
		//  在设置默认字段不为空的情况下，先把所有该用户下的地址都设置为不是默认，完成后再把要设置为默认的设置为默认
		if(shippingAddress.getIsDefault() != null && !"".equals(shippingAddress.getIsDefault())){
			ShippingAddress address = new ShippingAddress();
			address.setUserId(shippingAddress.getUserId());
			List<ShippingAddress> addressesList = shippingAddressService.doGetList(address); 
			for (int i = 0; i < addressesList.size(); i++) {
				ShippingAddress sa = new ShippingAddress();
				sa.setId(addressesList.get(i).getId());
				sa.setIsDefault("1");
				shippingAddressService.doModById(sa);
			}
			shippingAddressService.doSave(shippingAddress);
		}else {
			shippingAddressService.doSave(shippingAddress);
		}
		respBean.setBody(shippingAddress);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-08-15
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelShippingAddress")
	@ResponseBody
	public RespBean<String> doDelShippingAddressAction(String id) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		shippingAddressService.doRmByIds(id);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

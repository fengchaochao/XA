package com.prj.biz.api.shareGoods;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tutorial.redis.RedisUtil;

import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.codeareas.codeAreas;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.goods.Goods;
import com.prj.biz.bean.goodsevaluate.GoodsEvaluate;
import com.prj.biz.bean.goodsspecifications.GoodsSpecifications;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.sharerecord.ShareRecord;
import com.prj.biz.bean.shippingaddress.ShippingAddress;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transactionrecords.TransactionRecords;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.goods.GoodsService;
import com.prj.biz.service.goodsevaluate.GoodsEvaluateService;
import com.prj.biz.service.goodsspecifications.GoodsSpecificationsService;
import com.prj.biz.service.order.OrderService;
import com.prj.biz.service.sharerecord.ShareRecordService;
import com.prj.biz.service.shippingaddress.ShippingAddressService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.service.transactionrecords.TransactionRecordsService;
import com.prj.core.bean.resp.RespBean;
import com.prj.utils.ObjectUtil;
import com.prj.utils.Tool;
import com.prj.utils.pay.Alipay;
import com.prj.utils.pay.WeixinPay;

/**
 * @author: Liang
 * @date:2017-8-21
 * @version :0.0.1
 * @dis:App商品分享页面,
 */
@Controller
@RequestMapping("/shareGoods")
public class ShareGoodsAction {

	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsSpecificationsService goodsSpecificationsService;
	
	@Autowired
	private GoodsEvaluateService goodsEvaluateService;
	
	@Autowired
	private codeAreasService codeAreasService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private TransactionRecordsService transactionRecordsService;
	
	@Autowired
	private ShareRecordService shareRecordService;
	@Resource
	private BusinessInformationService businessInformationService;
	@Resource
	private ConsumersService consumersService;
	
	/**
	 * 描述: 进入商品详情页面
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsView")
	@ResponseBody
	public ModelAndView doEnGoodsView(String goodsId,String shareUserId) throws Exception{
		Goods goods = goodsService.doGetById(goodsId);
		Map<String, Object> model = new HashMap<String, Object>();
		//商品规格
		GoodsSpecifications goodsSpecifications=new GoodsSpecifications();
		goodsSpecifications.setGoodsId(goodsId);
		List<GoodsSpecifications> goodsSpecificationsList=goodsSpecificationsService.doGetList(goodsSpecifications);
		//商品评价
		GoodsEvaluate goodsEvaluate=new GoodsEvaluate();
		goodsEvaluate.setGoodsId(goodsId);
		goodsEvaluate.setOrder("desc");
		goodsEvaluate.setOrderName("evaluate_date");
		List<GoodsEvaluate> goodsEvaluateList = goodsEvaluateService.doGetList(goodsEvaluate);
		for (GoodsEvaluate goodsEvaluate2 : goodsEvaluateList) {
			if(goodsEvaluate2 != null){
				BusinessInformation businessInformation = businessInformationService.doGetById(goodsEvaluate2.getConsumerId());
				if(businessInformation != null ){
					goodsEvaluate2.setConsumersName(businessInformation.getVendorName());
				}else {
					Consumers consumers = consumersService.doGetById(goodsEvaluate2.getConsumerId());
					if(consumers != null){
						goodsEvaluate2.setConsumersName(consumers.getNickName());
					}
				}
				//比较时间
				String evaluateDate=Tool.formatDateTime(goodsEvaluate2.getEvaluateDate());
				goodsEvaluate2.setEvaluateDate(evaluateDate);
				
			}
		}
		String[] commodityImages={};
		if(!StringUtils.isEmpty(goods.getCommodityImages())){
			commodityImages=goods.getCommodityImages().split(",");
		}
		String[] goodsPhotos={};
		if(!StringUtils.isEmpty(goods.getGoodsPhotos())){
			goodsPhotos=goods.getGoodsPhotos().split(",");
		}
		
		
		model.put("goodsSpecificationsList", goodsSpecificationsList);
		model.put("goods", goods);
		model.put("commodityImages", commodityImages);
		model.put("goodsPhotos", goodsPhotos);
		model.put("goodsEvaluateList", goodsEvaluateList);
		model.put("shareUserId", shareUserId);
		return new ModelAndView("/h5/shareGoods/shareOne", model);
	}
	
	/**
	 * 描述: 商品评价
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsSelById")
	@ResponseBody
	public ModelAndView doEnGoodsSelById(String goodsId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		GoodsEvaluate goodsEvaluate=new GoodsEvaluate();
		goodsEvaluate.setGoodsId(goodsId);
		goodsEvaluate.setOrder("desc");
		goodsEvaluate.setOrderName("evaluate_date");
		List<GoodsEvaluate> goodsEvaluateList = goodsEvaluateService.doGetList(goodsEvaluate);
		for (GoodsEvaluate goodsEvaluate2 : goodsEvaluateList) {
			if(goodsEvaluate2!=null){
				BusinessInformation businessInformation = businessInformationService.doGetById(goodsEvaluate2.getConsumerId());
				if(businessInformation != null ){
					goodsEvaluate2.setConsumersName(businessInformation.getVendorName());
				}else {
					Consumers consumers = consumersService.doGetById(goodsEvaluate2.getConsumerId());
					if(consumers != null){
						goodsEvaluate2.setConsumersName(consumers.getNickName());
					}
				}
				
				String evaluateDate=Tool.formatDateTime(goodsEvaluate2.getEvaluateDate());
				goodsEvaluate2.setEvaluateDate(evaluateDate);
			}
		}
		
		model.put("goodsEvaluateList", goodsEvaluateList);
		return new ModelAndView("/h5/shareGoods/commodityEvaluation", model);
	}
	/**
	 * 描述: 跳转到立即购买页面
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/doEnBuyNow")
	@ResponseBody
	public ModelAndView doEnBuyNow(String goodsUnitId,String  goodsId,String shareUserId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Goods goods = goodsService.doGetById(goodsId);
		GoodsSpecifications goodsSpecifications=goodsSpecificationsService.doGetById(goodsUnitId);
		String[] commodityImages={};
		if(!StringUtils.isEmpty(goods.getCommodityImages())){
			commodityImages=goods.getCommodityImages().split(",");
		}
		
		String key = "countCodeAreas";  
		String key1 = "codeAreas";  
	    String key2 = "cityCodeAreas";  
	    String key3 = "areaCodeAreas"; 
	    List<codeAreas> countCodeAreas=new ArrayList<codeAreas>();
	    List<codeAreas> codeAreas=new ArrayList<codeAreas>();
		List<codeAreas> cityCodeAreas=new ArrayList<codeAreas>();
		List<codeAreas> areaCodeAreas=new ArrayList<codeAreas>();
		
	    byte[] a=RedisUtil.getJedis().get(key.getBytes());
	    if(a==null){
	    	countCodeAreas=codeAreasService.selectCountyList();
	    }else{
	    	countCodeAreas = ( List<codeAreas>) ObjectUtil.bytesToObject((RedisUtil.getJedis().get(key.getBytes())));
	    }
	    byte[] b=RedisUtil.getJedis().get(key1.getBytes());
		
	    if(b==null){
		   codeAreas=codeAreasService.selectProvinceList();
	    }else{
	    	codeAreas = ( List<codeAreas>) ObjectUtil.bytesToObject((RedisUtil.getJedis().get(key1.getBytes())));
	    }
	    
	    byte[] c=RedisUtil.getJedis().get(key2.getBytes());
		
	    if(c==null){
	    	cityCodeAreas=codeAreasService.selectCityList();
	    }else{
	    	cityCodeAreas = ( List<codeAreas>) ObjectUtil.bytesToObject((RedisUtil.getJedis().get(key2.getBytes())));
	    }
	    
	    byte[] d=RedisUtil.getJedis().get(key3.getBytes());
		
	    if(d==null){
	    	areaCodeAreas=codeAreasService.selectRegionList();
	    }else{
	    	areaCodeAreas = ( List<codeAreas>) ObjectUtil.bytesToObject((RedisUtil.getJedis().get(key3.getBytes())));
	    }
	    
		model.put("countryList", countCodeAreas);
		model.put("provinceList", codeAreas);
		model.put("cityList", cityCodeAreas);
		model.put("areaList", areaCodeAreas);
		
		model.put("goods", goods);
		model.put("goodsSpecifications", goodsSpecifications);
		model.put("commodityImages", commodityImages);
		model.put("shareUserId", shareUserId);
		return new ModelAndView("/h5/shareGoods/confirmOrder", model);
	}
	/**
	 * 描述: 确认支付
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnSurePay")
	@ResponseBody
	public RespBean<String> doEnSurePay(HttpServletRequest request,
			HttpServletResponse response, ShippingAddress shippingAddress,String accountType,String num,String price,String goodsUnitId,String goodsId,String shareUserId) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		/**
		 * 生成收货地址
		 */
		shippingAddress.setIsDefault("1");
		shippingAddressService.doSave(shippingAddress);
		//获取商品对应的商家ID
		Goods goods=goodsService.doGetById(goodsId);
		SysUser sysUser=sysUserService.doGetById(goods.getPublisher());
		/**
		 * 生成订单信息
		 */
		//生成订单编号
		
		Date date = new Date();
		String orderNo = "";
		if("1".equals(accountType)){
			orderNo = "AL" + String.valueOf(date.getTime())+(int) (Math.random() * 900) + 100;
		};
		//调用微信
		if("2".equals(accountType)){
			orderNo = "WX" + String.valueOf(date.getTime())+(int) (Math.random() * 900) + 100;
		};
		
		Order order=new Order();
		order.setMoney(price);
		order.setOrderNum(num);
		order.setOrderNumber(orderNo);
		order.setDistributionAddressId(shippingAddress.getId());
		order.setStatus("4");
		order.setTransactionMode("2");
		order.setBusinessId(sysUser.getMerchantsId());
		orderService.doSave(order);
		/**
		 * 生成交易记录
		 */
		TransactionRecords transactionRecords=new TransactionRecords();
		transactionRecords.setOrderNo(order.getId());
		transactionRecords.setTransactionNum(num);
		transactionRecords.setTotalPrice(price);
		transactionRecords.setGoodsUnitId(goodsUnitId);
		transactionRecords.setBusinessId(sysUser.getMerchantsId());
		transactionRecordsService.doSave(transactionRecords);
		//调用支付宝支付
		if("1".equals(accountType)){
			Alipay.doPay(request, response, sysUser.getMerchantsId(), price, order.getOrderNumber(),goods.getCommodityName(),"[橙果科技]商品在线购买支付");
		}
		//调用微信
		if("2".equals(accountType)){
			WeixinPay.doPay(request, response, "1", sysUser.getMerchantsId(), price);
		}
		//生成分享佣金记录
		if(!"0".equals(goods.getPromotionFee())){
			SysUser shareSysUser=sysUserService.doGetById(shareUserId);
			shareSysUser.setAccountBalance(String.valueOf(Float.parseFloat(shareSysUser.getAccountBalance())+Float.parseFloat(goods.getPromotionFee())));
			sysUserService.doModById(sysUser);
			//生成分享记录
			ShareRecord shareRecord=new ShareRecord();
			shareRecord.setGoodsId(goodsId);
			shareRecord.setUserId(shareUserId);
			shareRecord.setShareFee(goods.getPromotionFee());
			shareRecordService.doSave(shareRecord);
		}
		return respBean;
	}
	
}

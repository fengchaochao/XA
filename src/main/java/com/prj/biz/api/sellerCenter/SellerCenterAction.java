package com.prj.biz.api.sellerCenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tutorial.redis.RedisUtil;

import com.prj.biz.action.upfile.FileUploadController;
import com.prj.biz.bean.advertising.Advertising;
import com.prj.biz.bean.businessclassification.BusinessClassification;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.codeareas.codeAreas;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.goods.Goods;
import com.prj.biz.bean.goodsclassification.GoodsClassification;
import com.prj.biz.bean.goodsevaluate.GoodsEvaluate;
import com.prj.biz.bean.goodsspecifications.GoodsSpecifications;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.order.OrderToday;
import com.prj.biz.bean.shoppingcart.ShoppingCart;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transactionrecords.TransactionRecords;
import com.prj.biz.service.advertising.AdvertisingService;
import com.prj.biz.service.businessclassification.BusinessClassificationService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.goods.GoodsService;
import com.prj.biz.service.goodsclassification.GoodsClassificationService;
import com.prj.biz.service.goodsevaluate.GoodsEvaluateService;
import com.prj.biz.service.goodsspecifications.GoodsSpecificationsService;
import com.prj.biz.service.order.OrderService;
import com.prj.biz.service.shoppingcart.ShoppingCartService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.service.transactionrecords.TransactionRecordsService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.utils.ObjectUtil;
import com.prj.utils.UfdmDateUtil;

/**
 * @author: Fengc
 * @date:2017-7-18 下午2:48:33
 * @version :0.0.1
 * @dis:App商家中心API_ACTION,
 */
@Controller
@RequestMapping("/api")
public class SellerCenterAction {

	@Autowired
	private BusinessInformationService businessInformationService;

	@Autowired
	private ConsumersAccountService consumersAccountService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private BusinessClassificationService businessClassificationService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	
	@Autowired
	private GoodsSpecificationsService goodsSpecificationsService;
	
	@Autowired
	private GoodsEvaluateService goodsEvaluateService;
	
	@Autowired
	private AdvertisingService advertisingService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TransactionRecordsService transactionRecordsService;
	
	@Autowired
	private codeAreasService codeAreasService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ConsumersService consumersService;

	/**
	 * 客户管理->今日锁定的消费者
	 * 
	 * @param request
	 * @param response
	 * @param sellerId
	 *            商家ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/todayLockedCustomerList")
	@ResponseBody
	public  RespBean<RespPagination<ConsumersAccount>> todayLockedCustomerList(String sellerId,String limit,String offset) {
		RespBean<RespPagination<ConsumersAccount>> respBean = new RespBean<RespPagination<ConsumersAccount>>();
		RespPagination<ConsumersAccount> respPagination = new RespPagination<ConsumersAccount>();
		
		try {
			//今日锁定的消费者
			ConsumersAccount consumersAccount = new ConsumersAccount();
			consumersAccount.setBusinessInformationId(sellerId);
			consumersAccount.setLimit(Integer.parseInt(limit));
			consumersAccount.setOffset(Integer.parseInt(offset));
			consumersAccount.setLocalDate(UfdmDateUtil.getCurrentDate());
			List<ConsumersAccount> todayConsumersList = consumersAccountService.doGetList(consumersAccount);
			if(todayConsumersList.size()>0){
				for (ConsumersAccount consumersAccount2 : todayConsumersList) {
					Consumers consumers=consumersService.doGetById(consumersAccount2.getConsumersId());
					if(consumers!=null){
						if("0".equals(consumers.getIsXfconsumers())){
							consumersAccount2.setComsumersType("1");
						}
						if("1".equals(consumers.getIsXfconsumers())){
							consumersAccount2.setComsumersType("2");
						}
					}else{
						BusinessInformation information1 =businessInformationService.doGetById(consumersAccount2.getConsumersId());
						if(information1!=null){
							consumersAccount2.setComsumersType("3");
						}else{
							consumersAccount2.setComsumersType("4");
						}
					}
				}
			}
			Integer total = consumersAccountService.doGetTotal(consumersAccount);
			respPagination.setTotal(total);
			respPagination.setRows(todayConsumersList);
			
		} catch (Exception e) {
		}
		
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 客户管理->历史锁定的消费者
	 * 
	 * @param request
	 * @param response
	 * @param sellerId
	 *            商家ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/historyLockedCustomerList")
	@ResponseBody
	public RespBean<RespPagination<ConsumersAccount>> historyLockedCustomerList(HttpServletRequest request,
			HttpServletResponse response, String sellerId,String limit,String offset) {
		RespBean<RespPagination<ConsumersAccount>> respBean = new RespBean<RespPagination<ConsumersAccount>>();
		RespPagination<ConsumersAccount> respPagination = new RespPagination<ConsumersAccount>();
		
		try {
			//历史锁定的消费者
			ConsumersAccount consumersAccount1 = new ConsumersAccount();
			consumersAccount1.setBusinessInformationId(sellerId);
			consumersAccount1.setLimit(Integer.parseInt(limit));
			consumersAccount1.setOffset(Integer.parseInt(offset));
			consumersAccount1.setHistoryLocalDate(UfdmDateUtil.getCurrentDate());
			List<ConsumersAccount> historyConsumersList = consumersAccountService.doGetList(consumersAccount1);
			if(historyConsumersList.size()>0){
				for (ConsumersAccount consumersAccount2 : historyConsumersList) {
					Consumers consumers=consumersService.doGetById(consumersAccount2.getConsumersId());
					if(consumers!=null){
						if("0".equals(consumers.getIsXfconsumers())){
							consumersAccount2.setComsumersType("1");
						}
						if("1".equals(consumers.getIsXfconsumers())){
							consumersAccount2.setComsumersType("2");
						}
					}else{
						BusinessInformation information1 =businessInformationService.doGetById(consumersAccount2.getConsumersId());
						if(information1!=null){
							consumersAccount2.setComsumersType("3");
						}else{
							consumersAccount2.setComsumersType("4");
						}
						
					}
				}
			}
			Integer total = consumersAccountService.doGetTotal(consumersAccount1);
			respPagination.setTotal(total);
			respPagination.setRows(historyConsumersList);
			
			
		} catch (Exception e) {
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 推荐的商家-今日推荐
	 * @param request
	 * @param response
	 * @param sellerId 商家Id
	 * @return
	 */
	@RequestMapping(value = "/todayRecommendBusinessList")
	@ResponseBody
	public RespBean<RespPagination<BusinessInformation>> todayRecommendBusinessList(HttpServletRequest request,
			HttpServletResponse response, String userId,String limit,String offset) {
		
		RespBean<RespPagination<BusinessInformation>> respBean = new RespBean<RespPagination<BusinessInformation>>();
		RespPagination<BusinessInformation> respPagination = new RespPagination<BusinessInformation>();
		
		try {
			
			BusinessInformation information = new BusinessInformation();
			information.setFounder(userId);
			information.setEffectState("0");
			information.setCreateDate(UfdmDateUtil.getCurrentDate());
			information.setLimit(Integer.parseInt(limit));
			information.setOffset(Integer.parseInt(offset));
			List<BusinessInformation> todayInformations = businessInformationService.doGetList(information);
			
			Integer total = businessInformationService.doGetTotal(information);
			respPagination.setTotal(total);
			respPagination.setRows(todayInformations);
			
		} catch (Exception e) {
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 推荐的商家-历史推荐
	 * @param request
	 * @param response
	 * @param sellerId 商家Id
	 * @return
	 */
	@RequestMapping(value = "/historyRecommendBusinessList")
	@ResponseBody
	public RespBean<RespPagination<BusinessInformation>> historyRecommendBusinessList(HttpServletRequest request,
			HttpServletResponse response, String userId,String limit,String offset) {
		
		RespBean<RespPagination<BusinessInformation>> respBean = new RespBean<RespPagination<BusinessInformation>>();
		RespPagination<BusinessInformation> respPagination = new RespPagination<BusinessInformation>();
		
		try {
			BusinessInformation information1 = new BusinessInformation();
			information1.setFounder(userId);
			information1.setEffectState("0");
			information1.setRemmDate(UfdmDateUtil.getCurrentDate());
			information1.setLimit(Integer.parseInt(limit));
			information1.setOffset(Integer.parseInt(offset));
			List<BusinessInformation> historyInformations = businessInformationService.doGetList(information1);
			
			Integer total = businessInformationService.doGetTotal(information1);
			respPagination.setTotal(total);
			respPagination.setRows(historyInformations);
			
		} catch (Exception e) {
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 推荐的商家新增
	 * @param request
	 * @param response
	 * @param businessInformation 商家对象
	 * @return
	 */
	@RequestMapping(value = "/RecommendBusiness")
	@ResponseBody
	public RespBean<String> RecommendBusiness(HttpServletRequest request,
			HttpServletResponse response, BusinessInformation businessInformation) {
		RespBean<String> respBean = new RespBean<String>();
		SysUser sysUser1 = new SysUser();
		sysUser1.setLoginName(businessInformation.getMerchantPhone());
		try {
			List<SysUser> sysUserList = sysUserService.doGetList(sysUser1);
			if (sysUserList.size() < 1) {
				businessInformation.setEffectState("1");
				if("2".equals(businessInformation.getSource())){
					businessInformation.setSource("1");
				}
				if("3".equals(businessInformation.getSource())){
					businessInformation.setSource("2");
				}
				businessInformationService.doSave(businessInformation);
				respBean.setBody("保存成功！");
			}else{
				respBean.setBody("该手机号对应的用户已经存在！");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			respBean.setBody("保存失败！");
		}
		return respBean;
	}
	/**
	 * 省市区
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/areaList")
	@ResponseBody
	public RespBean<Map<String, Object>> areaList(HttpServletRequest request,
			HttpServletResponse response) {
		
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String key1 = "codeAreas";  
	    String key2 = "cityCodeAreas";  
	    String key3 = "areaCodeAreas"; 
	    List<codeAreas> codeAreas=codeAreasService.selectProvinceList();
		List<codeAreas> cityCodeAreas=codeAreasService.selectCityList();
		List<codeAreas> areaCodeAreas=codeAreasService.selectRegionList();
		
		try {
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
			map.put("codeAreas", codeAreas);
			map.put("cityCodeAreas", cityCodeAreas);
			map.put("areaCodeAreas", areaCodeAreas);
		} catch (Exception e) {
		}
		respBean.setBody(map);
		return respBean;
	}
	/**
	 * 店铺分类
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/storeTypeList")
	@ResponseBody
	public RespBean<Map<String, Object>> storeTypeList(HttpServletRequest request,
			HttpServletResponse response) {
		
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<BusinessClassification> businessClassifications=businessClassificationService.doGetList(null);
			map.put("businessClassifications", businessClassifications);
		} catch (Exception e) {
		}
		respBean.setBody(map);
		return respBean;
	}
	/**
	 * 商品管理-已上架
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/goodsingList")
	@ResponseBody
	public RespBean<RespPagination<Goods>> goodsingList(HttpServletRequest request,HttpServletResponse response,String userId,String limit,String offset) {
		
		RespBean<RespPagination<Goods>> respBean = new RespBean<RespPagination<Goods>>();
		RespPagination<Goods> respPagination = new RespPagination<Goods>();
		Goods goods=new Goods();
		goods.setPublisher(userId);
		goods.setGoodsState("3");
		goods.setLimit(Integer.parseInt(limit));
		goods.setOffset(Integer.parseInt(offset));
		try {
			List<Goods> goodsList = goodsService.doGetList(goods);
			Integer total = goodsService.doGetTotal(goods);
			respPagination.setTotal(total);
			respPagination.setRows(goodsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 商品管理-未上架
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/goodsList")
	@ResponseBody
	public RespBean<RespPagination<Goods>> goodsList(HttpServletRequest request,HttpServletResponse response,
			String userId,String limit,String offset) {
		
		RespBean<RespPagination<Goods>> respBean = new RespBean<RespPagination<Goods>>();
		RespPagination<Goods> respPagination = new RespPagination<Goods>();
		Goods goods=new Goods();
		goods.setPublisher(userId);
		goods.setGoodsState("0");
		goods.setGoodState("4");
		goods.setLimit(Integer.parseInt(limit));
		goods.setOffset(Integer.parseInt(offset));
		try {
			List<Goods> goodsList = goodsService.doGetList(goods);
			Integer total = goodsService.doGetTotal(goods);
			respPagination.setTotal(total);
			respPagination.setRows(goodsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商品下架
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doShelvesGoods")
	@ResponseBody
	public RespBean<Goods> doShelvesGoodsAction(String goodsId) throws Exception{
		RespBean<Goods> respBean = new RespBean<Goods>();
		Goods goods = goodsService.doGetById(goodsId);
		goods.setGoodsState("4");
		goodsService.doModById(goods);
		respBean.setBody(goods);
		return respBean;
	}
	/**
	 * 描述: 提交审核
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doCheckGoods")
	@ResponseBody
	public RespBean<Goods> doCheckGoodsAction(String goodsId) throws Exception{
		RespBean<Goods> respBean = new RespBean<Goods>();
		Goods goods = goodsService.doGetById(goodsId);
		goods.setGoodsState("1");
		goodsService.doModById(goods);
		respBean.setBody(goods);
		return respBean;
	}
	/**
	 * 描述: 商品删除
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelGoods")
	@ResponseBody
	public RespBean<String> doDelGoodsAction(String goodsId) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		GoodsSpecifications goodsSpecifications=new GoodsSpecifications();
		goodsSpecifications.setGoodsId(goodsId);
		List<GoodsSpecifications> goodsSpecifications2=goodsSpecificationsService.doGetList(goodsSpecifications);
		for (int i = 0; i < goodsSpecifications2.size(); i++) {
			goodsSpecificationsService.doRmById(goodsSpecifications2.get(i).getId());
		}
		goodsService.doRmById(goodsId);
		respBean.setBody("删除成功");
		return respBean;
	}
	/**
	 * 商品分类
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/goodsTypeList")
	@ResponseBody
	public RespBean<RespPagination<GoodsClassification>> goodsTypeList(HttpServletRequest request,HttpServletResponse response) {
		
		RespBean<RespPagination<GoodsClassification>> respBean = new RespBean<RespPagination<GoodsClassification>>();
		RespPagination<GoodsClassification> respPagination = new RespPagination<GoodsClassification>();
		
		try {
			List<GoodsClassification> goodsClassifications=goodsClassificationService.doGetList(null);
			Integer total = goodsClassificationService.doGetTotal(null);
			respPagination.setTotal(total);
			respPagination.setRows(goodsClassifications);
		} catch (Exception e) {
		}
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 商品新增
	 * @param request
	 * @param response
	 * @param businessInformation 商家对象
	 * @return
	 */
	@RequestMapping(value = "/saveGoods")
	@ResponseBody
	
	public RespBean<Goods> saveGoods(HttpServletRequest request,
			HttpServletResponse response, Goods goods,String specifications,
			@RequestParam("upufdmfile")MultipartFile[]  upufdmfile,@RequestParam("files")MultipartFile[] files) {
		RespBean<Goods> respBean = new RespBean<Goods>();
		FileUploadController fileUploadController=new FileUploadController();
		String goodsPhotos="";
		String commodityImages="";
		
		try {
			//商品图片
			//判断upufdmfile数组不能为空并且长度大于0  
	        if(upufdmfile!=null&&upufdmfile.length>0){  
	            //循环获取upufdmfile数组中得文件  
	            for(int i = 0;i<upufdmfile.length;i++){  
	                MultipartFile file = upufdmfile[i];  
	                //上传图片  
	                RespBean<Map<String,String>> respMap =fileUploadController.handleFileUpload(file);
	                if(StringUtils.isEmpty(goodsPhotos)){
	                	goodsPhotos+=respMap.getBody().get("upfileFilePath");
	                }else{
	                	goodsPhotos+=","+respMap.getBody().get("upfileFilePath");
	                }
	                
	            }  
	        } 
	        //商品轮播图
			//判断files数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取files数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //上传图片  
	                RespBean<Map<String,String>> respMap =fileUploadController.handleFileUpload(file);
	                if(StringUtils.isEmpty(commodityImages)){
	                	commodityImages+=respMap.getBody().get("upfileFilePath");
	                }else{
	                	commodityImages+=","+respMap.getBody().get("upfileFilePath");
	                }
	                
	            }  
	        }
	        goods.setCommodityImages(commodityImages);
	        goods.setGoodsPhotos(goodsPhotos);
			goods.setGoodsState("0");
			int a = (int) (Math.random() * 900) + 100;
			String code = "GOODS" + UfdmDateUtil.getCurrentDate1()
					+ UfdmDateUtil.getCurrentSimpleTime1() + a;
			goods.setCommodityNumber(code);
			goodsService.doSave(goods);
			
			String[] specificationsList=specifications.split(",");
			for (int i = 0; i < specificationsList.length; i++) {
				if(specificationsList[i]!=null&&specificationsList[i]!=""){
					String[] specification=specificationsList[i].split("@");
					GoodsSpecifications goodsSpecifications=new GoodsSpecifications();
					goodsSpecifications.setSpecificationsName(specification[0]);
					goodsSpecifications.setInventory(Integer.parseInt(specification[1]));
					goodsSpecifications.setPrice(Double.parseDouble(specification[2]));
					goodsSpecifications.setGoodsId(goods.getId());
					goodsSpecificationsService.doSave(goodsSpecifications);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respBean.setBody(goods);
		return respBean;
	}
	/**
	 * 描述: 进入商品详情页面
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsView")
	@ResponseBody
	public RespBean<Map<String, Object>> doEnGoodsView(String goodsId,String sellerId) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Goods goods = goodsService.doGetById(goodsId);
		SysUser sysUser=sysUserService.doGetById(goods.getPublisher());
		if(sysUser!=null){
			if("2".equals(sysUser.getUserState())){
				BusinessInformation businessInformation=businessInformationService.doGetById(sysUser.getMerchantsId());
				goods.setBusinessId(businessInformation.getId());
			}
			if("3".equals(sysUser.getUserState())){
				Consumers consumers=consumersService.doGetById(sysUser.getMerchantsId());
				goods.setBusinessId(consumers.getId());
			}
		}
		//
		ShoppingCart shoppingCart=new ShoppingCart();
		shoppingCart.setGoodsId(goodsId);
		shoppingCart.setPurchaserId(sellerId);
		Integer total = shoppingCartService.doGetTotal(shoppingCart);
		goods.setShippingNum(total);
		Map<String, Object> model = new HashMap<String, Object>();
		//商品规格
		GoodsSpecifications goodsSpecifications=new GoodsSpecifications();
		goodsSpecifications.setGoodsId(goodsId);
		List<GoodsSpecifications> goodsSpecificationsList=goodsSpecificationsService.doGetList(goodsSpecifications);
		
		model.put("goodsSpecificationsList", goodsSpecificationsList);
		model.put("goods", goods);
		respBean.setBody(model);
		return respBean;
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
	public RespBean<RespPagination<GoodsEvaluate>> doEnGoodsSelById(String goodsId,String limit,String offset) throws Exception{
		RespBean<RespPagination<GoodsEvaluate>> respBean = new RespBean<RespPagination<GoodsEvaluate>>();
		RespPagination<GoodsEvaluate> respPagination = new RespPagination<GoodsEvaluate>();
		GoodsEvaluate goodsEvaluate=new GoodsEvaluate();
		goodsEvaluate.setGoodsId(goodsId);
		goodsEvaluate.setLimit(Integer.parseInt(limit));
		goodsEvaluate.setOffset(Integer.parseInt(offset));
		goodsEvaluate.setOrder("desc");
		goodsEvaluate.setOrderName("evaluate_date");
		Integer total = goodsEvaluateService.doGetTotal(goodsEvaluate);
		List<GoodsEvaluate> goodsEvaluateList = goodsEvaluateService.doGetList(goodsEvaluate);
		if(goodsEvaluateList.size()>0){
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
				}
			}
		}
		
		respPagination.setTotal(total);
		respPagination.setRows(goodsEvaluateList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 回复评价
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditGoodsEvaluate")
	@ResponseBody
	public RespBean<GoodsEvaluate> doEditGoodsEvaluate(GoodsEvaluate goodsEvaluate) throws Exception{
		RespBean<GoodsEvaluate> respBean = new RespBean<GoodsEvaluate>();
		GoodsEvaluate goodsEvaluate1=goodsEvaluateService.doGetById(goodsEvaluate.getId());
		if(goodsEvaluate1!=null){
			goodsEvaluate1.setReplyContent(new String(goodsEvaluate.getReplyContent().getBytes("iso-8859-1"),"utf-8"));
			goodsEvaluate1.setStatus("2");
			goodsEvaluate1.setReplyDate(UfdmDateUtil.getCurrentTime());
			goodsEvaluateService.doModById(goodsEvaluate1);
			respBean.setBody(goodsEvaluate1);
		}
		return respBean;
	}
	/**
	 * 描述: 待审核广告列表
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadAdvertisingSHList")
	@ResponseBody
	public RespBean<RespPagination<Advertising>> doReadAdvertisingSHList(String sellerId,String limit,String offset) throws Exception{
		RespBean<RespPagination<Advertising>> respBean = new RespBean<RespPagination<Advertising>>();
		RespPagination<Advertising> respPagination = new RespPagination<Advertising>();
		Advertising advertising=new Advertising();
		advertising.setCheckState("1");//待审核数据
		advertising.setUserId(sellerId);
		advertising.setLimit(Integer.parseInt(limit));
		advertising.setOffset(Integer.parseInt(offset));
		Integer total = advertisingService.doGetTotal(advertising);
		List<Advertising> advertisingList = advertisingService.doGetList(advertising);
		respPagination.setTotal(total);
		respPagination.setRows(advertisingList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 审核通过待发布列表
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadAdvertisingList")
	@ResponseBody
	public RespBean<RespPagination<Advertising>> doReadAdvertisingList(String sellerId,String limit,String offset) throws Exception{
		RespBean<RespPagination<Advertising>> respBean = new RespBean<RespPagination<Advertising>>();
		RespPagination<Advertising> respPagination = new RespPagination<Advertising>();
		Advertising advertising=new Advertising();
		advertising.setCheckState("3");
		advertising.setUserId(sellerId);
		advertising.setLimit(Integer.parseInt(limit));
		advertising.setOffset(Integer.parseInt(offset));
		Integer total = advertisingService.doGetTotal(advertising);
		List<Advertising> advertisingList = advertisingService.doGetList(advertising);
		respPagination.setTotal(total);
		respPagination.setRows(advertisingList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 发布广告
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doAdvertisingActionRelease")
	@ResponseBody
	public RespBean<Advertising> doAdvertisingActionRelease(Advertising advertising) throws Exception{
		RespBean<Advertising> respBean = new RespBean<Advertising>();
		advertising.setCheckState("4");
		advertisingService.doModById(advertising);
		respBean.setBody(advertising);
		return respBean;
	}
	/**
	 * 描述: 删除广告
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelAdvertising")
	@ResponseBody
	public RespBean<String> doDelAdvertisingAction(String advertisingId) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		advertisingService.doRmById(advertisingId);
		respBean.setBody("删除成功");
		return respBean;
	}
	/**
	 * 描述: 新增广告
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doSaveAdvertising",method=RequestMethod.POST,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public RespBean<Advertising> doSaveAdvertising(Advertising advertising) throws Exception{
		RespBean<Advertising> respBean = new RespBean<Advertising>();
		//商家信息
		BusinessInformation businessInformation=businessInformationService.doGetById(advertising.getUserId());
		if(businessInformation!=null){
			advertising.setReleaseRange(businessInformation.getAreaId());
		}
		
		advertising.setCheckState("1");
		advertisingService.doSave(advertising);
		respBean.setBody(advertising);
		
		return respBean;
	}
	
	/**
	 * 描述: 订单管理-待配送订单
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnOrdeManagementing")
	@ResponseBody
	public RespBean<RespPagination<Order>> doEnOrdeManagementing(String sellerId,String limit,String offset) throws Exception{
		
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		Order order=new Order();
		order.setBusinessId(sellerId);
		order.setStatus("1");
		order.setTransactionMode("2");
		order.setLimit(Integer.parseInt(limit));
		order.setOffset(Integer.parseInt(offset));
		List<Order> orderList=orderService.doGetListApi(order);
		for (Order order2 : orderList) {
			if(!StringUtils.isEmpty(order2.getDistributionAddressId())){
				//配送地址
				String address=order2.getProvince()+"省"+order2.getCity()+order2.getArea()+order2.getDistributionAddress();
				order2.setDistributionAddress(address);
			}
			
			//购买的商品记录
			TransactionRecords transactionRecords=new TransactionRecords();
			transactionRecords.setOrderNo(order2.getId());
			transactionRecords.setIsGoodsTotal("0");
			List<TransactionRecords> transactionRecordsList=transactionRecordsService.doGetList(transactionRecords);
			if(transactionRecordsList.size()>0){
				for (TransactionRecords transactionRecords2 : transactionRecordsList) {
					if(transactionRecords2!=null){
						transactionRecords2.setGoodsName(transactionRecords2.getGoods().getCommodityName());
						transactionRecords2.setGoodsUtilName(transactionRecords2.getGoodsSpecifications().getSpecificationsName());
						transactionRecords2.setGoodsPrice(String.valueOf(transactionRecords2.getGoodsSpecifications().getPrice()));
					}
				}
			}
			
			order2.setTransactionRecords(transactionRecordsList);
			Consumers consumers=consumersService.doGetById(order2.getPurchaserId());
			if(consumers!=null){
				order2.setConsumersName(consumers.getNickName());
				SysUser sysUser=new SysUser();
				sysUser.setMerchantsId(consumers.getId());
				List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
				if(sysUsers.size()>0){
					order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
				}
			}else{
				BusinessInformation businessInformation=businessInformationService.doGetById(order2.getPurchaserId());
				if(businessInformation!=null){
					order2.setConsumersName(businessInformation.getVendorName());
					SysUser sysUser=new SysUser();
					sysUser.setMerchantsId(businessInformation.getId());
					List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
					if(sysUsers.size()>0){
						order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
					}
				}
			}
		}
		order.setLimit(0);
		order.setOffset(0);
		List<Order> orderList1=orderService.doGetListApi(order);
		respPagination.setTotal(orderList1.size());
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 订单管理-完成订单
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnOrdeManagement")
	@ResponseBody
	public RespBean<RespPagination<Order>> doEnOrdeManagement(String sellerId,String limit,String offset) throws Exception{
		
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		Order order=new Order();
		order.setBusinessId(sellerId);
		order.setStatus("2");
		order.setOrderState("3");
		order.setTransactionMode("2");
		order.setLimit(Integer.parseInt(limit));
		order.setOffset(Integer.parseInt(offset));
		List<Order> orderList=orderService.doGetListApi(order);
		for (Order order2 : orderList) {
			if(!StringUtils.isEmpty(order2.getDistributionAddressId())){
				//配送地址
				String address=order2.getProvince()+"省"+order2.getCity()+order2.getArea()+order2.getDistributionAddress();
				order2.setDistributionAddress(address);
			}
			//购买的商品记录
			TransactionRecords transactionRecords=new TransactionRecords();
			transactionRecords.setOrderNo(order2.getId());
			transactionRecords.setIsGoodsTotal("0");
			List<TransactionRecords> transactionRecordsList=transactionRecordsService.doGetList(transactionRecords);
			if(transactionRecordsList.size()>0){
				for (TransactionRecords transactionRecords2 : transactionRecordsList) {
					transactionRecords2.setGoodsName(transactionRecords2.getGoods().getCommodityName());
					transactionRecords2.setGoodsUtilName(transactionRecords2.getGoodsSpecifications().getSpecificationsName());
					transactionRecords2.setGoodsPrice(String.valueOf(transactionRecords2.getGoodsSpecifications().getPrice()));
				}
			}
			order2.setTransactionRecords(transactionRecordsList);
			Consumers consumers=consumersService.doGetById(order2.getPurchaserId());
			if(consumers!=null){
				order2.setConsumersName(consumers.getNickName());
				SysUser sysUser=new SysUser();
				sysUser.setMerchantsId(consumers.getId());
				List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
				if(sysUsers.size()>0){
					order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
				}
			}else{
				BusinessInformation businessInformation=businessInformationService.doGetById(order2.getPurchaserId());
				if(businessInformation!=null){
					order2.setConsumersName(businessInformation.getVendorName());
					SysUser sysUser=new SysUser();
					sysUser.setMerchantsId(businessInformation.getId());
					List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
					if(sysUsers.size()>0){
						order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
					}
				}
				
			}
		}
		order.setLimit(0);
		order.setOffset(0);
		List<Order> orderList1=orderService.doGetListApi(order);
		respPagination.setTotal(orderList1.size());
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商家中心-订单管理-配送功能
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnDistribution")
	@ResponseBody
	public RespBean<Order> doEnDistribution(String orderId) throws Exception{
		RespBean<Order> respBean = new RespBean<Order>();
		Order order=orderService.doGetById(orderId);
		order.setStatus("2");
		order.setDeliveryTime(UfdmDateUtil.getCurrentTime());
		orderService.doModById(order);
		respBean.setBody(order);
		return respBean;
	}
	/**
	 * 描述: 商家中心-盈利统计-消费者返利
	 * (当月的按天显示)
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnTodayConsumerRebate")
	@ResponseBody
	public RespBean<RespPagination<OrderToday>> doEnTodayConsumerRebate(String sellerId) throws Exception{
		RespBean<RespPagination<OrderToday>> respBean = new RespBean<RespPagination<OrderToday>>();
		RespPagination<OrderToday> respPagination = new RespPagination<OrderToday>();
		List<OrderToday>  orderList=new ArrayList<OrderToday>();
		Order order=new Order();
		order.setLocalBussiness(sellerId);
		order.setCreatetime(UfdmDateUtil.getCurrentDate2());
		List<Order> orders=orderService.doGetMonthListApi(order);
		if(orders.size()>0){
			for (Order order2 : orders) {
				order.setCreatetime(order2.getCreatetime());
				List<Order> orders2=orderService.doGetListApi(order);
				OrderToday orderToday=new OrderToday();
				orderToday.setDate(order2.getCreatetime());
				orderToday.setOrderList(orders2);
				orderList.add(orderToday);
			}
		}
		respPagination.setTotal(orderList.size());
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商家中心-盈利统计-消费者返利
	 * (其他的月份按月统计显示)
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnMonthConsumerRebate")
	@ResponseBody
	public RespBean<RespPagination<OrderToday>> doEnMonthConsumerRebate(String sellerId) throws Exception{
		RespBean<RespPagination<OrderToday>> respBean = new RespBean<RespPagination<OrderToday>>();
		RespPagination<OrderToday> respPagination = new RespPagination<OrderToday>();
		List<OrderToday>  orderList=new ArrayList<OrderToday>();
		String month=UfdmDateUtil.getCurrentDate3();
		Order order=new Order();
		order.setLocalBussiness(sellerId);
		for (int i = (Integer.parseInt(month)-1); i >0; i--) {
			OrderToday orderToday=new OrderToday();
			orderToday.setDate((i+1)+"月");
			order.setMonth(String.valueOf((i+1)));
			List<Order> orders=orderService.doGetMonthTotalApi(order);
			if(orders.get(0)!=null){
				orderToday.setBonusNum(orders.get(0).getLocalBonusNum());
			}else{
				orderToday.setBonusNum("0");
			}
			orderList.add(orderToday);
			
		}
		respPagination.setTotal(orderList.size());
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商家中心-盈利统计-商家返利
	 * (当月的按天显示)
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnTodayBussinessRebate")
	@ResponseBody
	public RespBean<RespPagination<OrderToday>> doEnTodayBussinessRebate(String sellerId) throws Exception{
		RespBean<RespPagination<OrderToday>> respBean = new RespBean<RespPagination<OrderToday>>();
		RespPagination<OrderToday> respPagination = new RespPagination<OrderToday>();
		List<OrderToday>  orderList=new ArrayList<OrderToday>();
		Order order=new Order();
		order.setRemmBussines(sellerId);
		order.setCreatetime(UfdmDateUtil.getCurrentDate2());
		List<Order> orders=orderService.doGetMonthListApi(order);
		
		if(orders.size()>0){
			for (Order order2 : orders) {
				
				order.setCreatetime(order2.getCreatetime());
				List<Order> orders2=orderService.doGetListApi(order);
				OrderToday orderToday=new OrderToday();
				orderToday.setDate(order2.getCreatetime());
				orderToday.setOrderList(orders2);
				orderList.add(orderToday);
			}
		}
		respPagination.setTotal(orderList.size());
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商家中心-盈利统计-商家返利
	 * (其他的月份按月统计显示)
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnMonthBusinessRebate")
	@ResponseBody
	public RespBean<RespPagination<OrderToday>> doEnMonthBusinessRebate(String sellerId) throws Exception{
		RespBean<RespPagination<OrderToday>> respBean = new RespBean<RespPagination<OrderToday>>();
		RespPagination<OrderToday> respPagination = new RespPagination<OrderToday>();
		List<OrderToday>  orderList=new ArrayList<OrderToday>();
		String month=UfdmDateUtil.getCurrentDate3();
		Order order=new Order();
		order.setLocalBussiness(sellerId);
		for (int i = (Integer.parseInt(month)-1); i >0; i--) {
			OrderToday orderToday=new OrderToday();
			orderToday.setDate((i+1)+"月");
			order.setMonth(String.valueOf((i+1)));
			List<Order> orders=orderService.doGetMonthTotalApi(order);
			if(orders.get(0)!=null){
				orderToday.setBonusNum(orders.get(0).getRemmBonusNum());
			}else{
				orderToday.setBonusNum("0");
			}
			
			orderList.add(orderToday);
			
		}
		respPagination.setTotal(orderList.size());
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商家中心-营业统计-月订单量统计
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/doEnMonthOrderTotal")
	@ResponseBody
	public RespBean<OrderToday> doEnMonthOrderTotal(String orderDate,String sellerId) throws Exception{
		RespBean<OrderToday> respBean = new RespBean<OrderToday>();
		DecimalFormat decimalFormat = new DecimalFormat(".00");
		OrderToday orderToday=new OrderToday();
		Order order=new Order();
		TransactionRecords transactionRecords=new TransactionRecords();
		transactionRecords.setBusinessId(sellerId);
		if(StringUtils.isEmpty(orderDate)){
			order.setCreatetime(UfdmDateUtil.getCurrentDate2());
			transactionRecords.setCreateDate(UfdmDateUtil.getCurrentDate2());
			orderToday.setDate(UfdmDateUtil.getCurrentDate2());
		}else{
			 order.setCreatetime(orderDate);
			 transactionRecords.setCreateDate(orderDate);
			 orderToday.setDate(orderDate);
		}
		order.setBusinessId(sellerId);
		List<Order> orders=orderService.doGetSalesTotal(order);
		//月销售额
		float monthSales=0;
		//月订单量
		int monthOrder=0;
		int monthNum=0;
		//订单被抽掉的总额
		float a=0;
		if(orders.size()>0){
			for (int i = 0; i < orders.size(); i++) {
				monthSales+=Float.parseFloat(orders.get(i).getMoneyNum());
				monthOrder+=Integer.parseInt(orders.get(i).getOrderNum());
				a+=Float.parseFloat(orders.get(i).getBonusNum());
				Order order2=orderService.doGetById(orders.get(i).getId());
				if("1".equals(order2.getTransactionMode())){
					monthNum+=0;
				}
			}
		}
		//月销售量
		List<TransactionRecords> transactionRecords2=transactionRecordsService.doGetList(transactionRecords);
		if(transactionRecords2.size()>0&&!"[null]".equals(transactionRecords2.toString())){
			for (int i = 0; i < transactionRecords2.size(); i++) {
				monthNum+=Integer.parseInt(transactionRecords2.get(i).getTransactionNum());
			}
		}
		orderToday.setSalesNum(String.valueOf(monthSales));
		orderToday.setOrderNum(String.valueOf(monthOrder));
		orderToday.setGoodsNum(String.valueOf(monthNum));
		orderToday.setPaidAmount(String.valueOf(monthSales-a));
		respBean.setBody(orderToday);
		return respBean;
	}
	/**
	 * 描述: 商家中心-营业统计-当月每天的统计
	 * 
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnDayOrderTotal")
	@ResponseBody
	public RespBean<RespPagination<OrderToday>> doEnDayOrderTotal(String orderDate,String sellerId) throws Exception{
		RespBean<RespPagination<OrderToday>> respBean = new RespBean<RespPagination<OrderToday>>();
		RespPagination<OrderToday> respPagination = new RespPagination<OrderToday>();
		
		List<OrderToday>  orderTodayList=new ArrayList<OrderToday>();
		Order order=new Order();
		order.setBusinessId(sellerId);
		TransactionRecords transactionRecords=new TransactionRecords();
		transactionRecords.setBusinessId(sellerId);
		if(StringUtils.isEmpty(orderDate)){
			order.setCreatetime(UfdmDateUtil.getCurrentDate2());
			transactionRecords.setCreateDate(UfdmDateUtil.getCurrentDate2());
		}else{
			 order.setCreatetime(orderDate);
			 transactionRecords.setCreateDate(orderDate);
		}
		List<Order> orderList=orderService.doGetMonthListApi(order);
		if(orderList.size()>0){
			for (int i = 0; i < orderList.size(); i++) {
				OrderToday orderToday=new OrderToday();
				orderToday.setDate(orderList.get(i).getCreatetime());
				order.setCreatetime(orderList.get(i).getCreatetime());
				transactionRecords.setCreateDate(orderList.get(i).getCreatetime());
				List<Order> orders=orderService.doGetSalesTotal(order);
				//月销售额
				float monthSales=0;
				//月订单量
				int monthOrder=0;
				int monthNum=0;
				//订单被抽掉的总额
				float a=0;
				if(orders.size()>0){
					for (int j = 0; j < orders.size(); j++) {
						monthSales+=Float.parseFloat(orders.get(j).getMoneyNum());
						monthOrder+=Integer.parseInt(orders.get(j).getOrderNum());
						a+=Float.parseFloat(orders.get(j).getBonusNum());
						Order order2=orderService.doGetById(orders.get(i).getId());
						if("1".equals(order2.getTransactionMode())){
							monthNum+=0;
						}
					}
				}
				orderToday.setSalesNum(String.valueOf(monthSales));
				orderToday.setOrderNum(String.valueOf(monthOrder));
				//月销售量
				List<TransactionRecords> transactionRecords2=transactionRecordsService.doGetList(transactionRecords);
				if(transactionRecords2.size()>0&&!"[null]".equals(transactionRecords2.toString())){
					for (int h = 0; h < transactionRecords2.size(); h++) {
						monthNum+=Integer.parseInt(transactionRecords2.get(h).getTransactionNum());
					}
				}
				orderToday.setGoodsNum(String.valueOf(monthNum));
				orderToday.setPaidAmount(String.valueOf(monthSales-a));
				orderTodayList.add(orderToday);
			}
		}
		respPagination.setTotal(orderTodayList.size());
		respPagination.setRows(orderTodayList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商家中心-营业统计-更多
	 * 
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnMoreTotal")
	@ResponseBody
	public RespBean<RespPagination<Order>> doEnMoreTotal(String orderDate,String sellerId,String limit,String offset) throws Exception{
		RespBean<RespPagination<Order>> respBean = new RespBean<RespPagination<Order>>();
		RespPagination<Order> respPagination = new RespPagination<Order>();
		
		Order order=new Order();
		order.setCreatetime(orderDate);
		order.setBusinessId(sellerId);
		order.setOrder("desc");
		order.setOrderName("createtime");
		order.setLimit(Integer.parseInt(limit));
		order.setOffset(Integer.parseInt(offset));
		List<Order> orderList=orderService.doGetListApi(order);
		List<TransactionRecords> transactionRecordsList=new ArrayList<TransactionRecords>();
		for (Order order2 : orderList) {
			if(!StringUtils.isEmpty(order2.getProvince())&&!StringUtils.isEmpty(order2.getCity())&&!StringUtils.isEmpty(order2.getArea())&&!StringUtils.isEmpty(order2.getDistributionAddress())){
				String address=order2.getProvince()+"省"+order2.getCity()+order2.getArea()+order2.getDistributionAddress();
				order2.setDistributionAddress(address);
			}
			
			//购买的商品记录
			TransactionRecords transactionRecords=new TransactionRecords();
			transactionRecords.setOrderNo(order2.getId());
			transactionRecordsList=transactionRecordsService.doGetList(transactionRecords);
			if(transactionRecordsList.size()>0&&!"[null]".equals(transactionRecordsList.toString())){
				for (TransactionRecords transactionRecords2 : transactionRecordsList) {
					transactionRecords2.setGoodsName(transactionRecords2.getGoods().getCommodityName());
					transactionRecords2.setGoodsUtilName(transactionRecords2.getGoodsSpecifications().getSpecificationsName());
					transactionRecords2.setGoodsPrice(String.valueOf(transactionRecords2.getGoodsSpecifications().getPrice()));
				}
			}else{
				transactionRecordsList=new ArrayList<TransactionRecords>();
			}
			order2.setTransactionRecords(transactionRecordsList);
			Consumers consumers=consumersService.doGetById(order2.getPurchaserId());
			if(consumers!=null){
				order2.setConsumersName(consumers.getNickName());
				SysUser sysUser=new SysUser();
				sysUser.setMerchantsId(consumers.getId());
				List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
				if(sysUsers.size()>0){
					order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
				}
			}else{
				BusinessInformation businessInformation=businessInformationService.doGetById(order2.getPurchaserId());
				if(businessInformation!=null){
					order2.setConsumersName(businessInformation.getVendorName());
					SysUser sysUser=new SysUser();
					sysUser.setMerchantsId(businessInformation.getId());
					List<SysUser> sysUsers=sysUserService.doGetList(sysUser);
					if(sysUsers.size()>0){
						order2.setPersonPhoto(sysUsers.get(0).getPersonPhoto());
					}
				}
			}
		}
		
		order.setLimit(0);
		order.setOffset(0);
		List<Order> orderList1=orderService.doGetListApi(order);
		respPagination.setTotal(orderList1.size());
		respPagination.setRows(orderList);
		respBean.setBody(respPagination);
		return respBean;
	}
}

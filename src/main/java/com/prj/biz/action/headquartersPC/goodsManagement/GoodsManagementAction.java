/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-13
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.goodsManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.goods.Goods;
import com.prj.biz.bean.goodsclassification.GoodsClassification;
import com.prj.biz.bean.goodsspecifications.GoodsSpecifications;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transactionrecords.TransactionRecords;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.service.goods.GoodsService;
import com.prj.biz.service.goodsclassification.GoodsClassificationService;
import com.prj.biz.service.goodsspecifications.GoodsSpecificationsService;
import com.prj.biz.service.transactionrecords.TransactionRecordsService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;



/** 
 * 描述: 商品在线管理 Action 类<br>
 * @author Liang
 * @date 2017-07-13
 */
@RestController
@RequestMapping("/headquarters/goodsManagement")
public class GoodsManagementAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private GoodsClassificationService goodsClassificationService;
	
	@Resource
	private GoodsSpecificationsService goodsSpecificationsService;
	
	@Resource
	private codeAreasService codeAreasService;
	
	@Resource
	private TransactionRecordsService transactionRecordsService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnGoodsManagementList")
    public ModelAndView doEnGoodsManagementListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		List<GoodsClassification> goodsClassificationList=goodsClassificationService.doGetList(null);
		model.put("goodsClassificationList", goodsClassificationList);
		model.put("countryList", codeAreasService.selectCountyList());
		model.put("provinceList", codeAreasService.selectProvinceList());
		model.put("cityList", codeAreasService.selectCityList());
		model.put("areaList", codeAreasService.selectRegionList());
		return new ModelAndView("/headquarters/goodsManagement/goodsList",model);
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadGoodsManagementList")
	@ResponseBody
	public RespBean<RespPagination<Goods>> doReadGoodsManagementList(Goods goods) throws Exception{
		RespBean<RespPagination<Goods>> respBean = new RespBean<RespPagination<Goods>>();
		RespPagination<Goods> respPagination = new RespPagination<Goods>();
		goods.setGoodsState("3");
		List<Goods> goodsList = goodsService.doGetList(goods);
		Integer total = goodsService.doGetTotal(goods);
		respPagination.setTotal(total);
		respPagination.setRows(goodsList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 商品交易记录
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsTransactionRecords")
	public ModelAndView doEnGoodsTransactionRecords(String goodsId) throws Exception{
		Goods goods = goodsService.doGetById(goodsId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("goods", goods);
		model.put("goodsId", goodsId);
		return new ModelAndView("/headquarters/goodsManagement/goodsTransactionRecords",model);
	}
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadGoodsTransactionRecordsList")
	@ResponseBody
	public RespBean<RespPagination<TransactionRecords>> doReadGoodsTransactionRecordsList(TransactionRecords transactionRecords,String goodsId) throws Exception{
		RespBean<RespPagination<TransactionRecords>> respBean = new RespBean<RespPagination<TransactionRecords>>();
		RespPagination<TransactionRecords> respPagination = new RespPagination<TransactionRecords>();
		GoodsSpecifications goodsSpecifications=new GoodsSpecifications();
		goodsSpecifications.setGoodsId(goodsId);
		List<GoodsSpecifications> goodsSpecifications2=goodsSpecificationsService.doGetList(goodsSpecifications);
		List<TransactionRecords> transactionRecordsList=new ArrayList<TransactionRecords>();
		
		for (GoodsSpecifications goodsSpecifications3 : goodsSpecifications2) {
			transactionRecords.setGoodsUnitId(goodsSpecifications3.getId());
			List<TransactionRecords> transactionRecordsList1=transactionRecordsService.doGetList(transactionRecords);
			if(transactionRecordsList1.size()>0){
				for (TransactionRecords transactionRecords2 : transactionRecordsList1) {
					transactionRecordsList.add(transactionRecords2);
				}
			}
			
		}
		
		respPagination.setTotal(transactionRecordsList.size());
		respPagination.setRows(transactionRecordsList);
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
}

/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-13
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.goodsspecifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.goodsspecifications.GoodsSpecifications;
import com.prj.biz.service.goodsspecifications.GoodsSpecificationsService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;



/** 
 * 描述: 商品规格表 Action 类<br>
 * @author Liang
 * @date 2017-07-13
 */
@RestController
@RequestMapping("/headquarters/goodsSpecifications")
public class GoodsSpecificationsAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private GoodsSpecificationsService goodsSpecificationsService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnGoodsSpecificationsList")
    public ModelAndView doEnGoodsSpecificationsListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/goodsspecifications/goodsSpecificationsList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadGoodsSpecificationsList")
	@ResponseBody
	public RespBean<RespPagination<GoodsSpecifications>> doReadGoodsSpecificationsList(GoodsSpecifications goodsSpecifications) throws Exception{
		RespBean<RespPagination<GoodsSpecifications>> respBean = new RespBean<RespPagination<GoodsSpecifications>>();
		RespPagination<GoodsSpecifications> respPagination = new RespPagination<GoodsSpecifications>();
		Integer total = goodsSpecificationsService.doGetTotal(goodsSpecifications);
		List<GoodsSpecifications> goodsSpecificationsList = goodsSpecificationsService.doGetList(goodsSpecifications);
		respPagination.setTotal(total);
		respPagination.setRows(goodsSpecificationsList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsSpecificationsEdit")
	public ModelAndView doEnGoodsSpecificationsEdit(String goodsSpecificationsId) throws Exception{
		GoodsSpecifications goodsSpecifications = goodsSpecificationsService.doGetById(goodsSpecificationsId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("goodsSpecifications", goodsSpecifications);
		return new ModelAndView("/goodsspecifications/goodsSpecificationsEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditGoodsSpecifications")
	public RespBean<GoodsSpecifications> doEditGoodsSpecifications(GoodsSpecifications goodsSpecifications) throws Exception{
		RespBean<GoodsSpecifications> respBean = new RespBean<GoodsSpecifications>();
		goodsSpecificationsService.doModById(goodsSpecifications);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelGoodsSpecifications")
	@ResponseBody
	public RespBean<String> doDelGoodsSpecificationsAction(String goodsSpecificationsIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		goodsSpecificationsService.doRmByIds(goodsSpecificationsIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

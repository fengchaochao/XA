/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-11
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.goodsclassification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.businessclassification.BusinessClassification;
import com.prj.biz.bean.goodsclassification.GoodsClassification;
import com.prj.biz.service.goodsclassification.GoodsClassificationService;
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
 * 描述: 商品分类 Action 类<br>
 * @author Liang
 * @date 2017-07-11
 */
@RestController
@RequestMapping("/headquarters/goodsClassification")
public class GoodsClassificationAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private GoodsClassificationService goodsClassificationService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnGoodsClassificationList")
    public ModelAndView doEnGoodsClassificationListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/goodsclassification/goodsClassificationList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadGoodsClassificationList")
	@ResponseBody
	public RespBean<RespPagination<GoodsClassification>> doReadGoodsClassificationList(GoodsClassification goodsClassification) throws Exception{
		RespBean<RespPagination<GoodsClassification>> respBean = new RespBean<RespPagination<GoodsClassification>>();
		RespPagination<GoodsClassification> respPagination = new RespPagination<GoodsClassification>();
		Integer total = goodsClassificationService.doGetTotal(goodsClassification);
		goodsClassification.setOrder("asc");
		goodsClassification.setOrderName("goods_number");
		List<GoodsClassification> goodsClassificationList = goodsClassificationService.doGetList(goodsClassification);
		respPagination.setTotal(total);
		respPagination.setRows(goodsClassificationList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsClassificationEdit")
	public ModelAndView doEnGoodsClassificationEdit(String goodsClassificationId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		GoodsClassification goodsClassification = goodsClassificationService.doGetById(goodsClassificationId);
		if(goodsClassificationId==null||"".equals(goodsClassificationId)){
			List<GoodsClassification> goodsClassificationList=goodsClassificationService.doGetList(null);
			model.put("goodsNumber", goodsClassificationList.size()+1);
		}
		
		
		model.put("goodsClassification", goodsClassification);
		model.put("goodsClassificationId", goodsClassificationId);
		return new ModelAndView("/headquarters/goodsclassification/goodsClassificationEdit",model);
	}
	/**
	 * 描述: 保存
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSaveGoodsClassification")
	public RespBean<GoodsClassification> doSaveGoodsClassification(GoodsClassification goodsClassification) throws Exception{
		RespBean<GoodsClassification> respBean = new RespBean<GoodsClassification>();
		goodsClassificationService.doSave(goodsClassification);
		respBean.setBody(goodsClassification);
		return respBean;
	}
	
	/**
	 * 描述: 编辑
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditGoodsClassification")
	public RespBean<GoodsClassification> doEditGoodsClassification(GoodsClassification goodsClassification) throws Exception{
		RespBean<GoodsClassification> respBean = new RespBean<GoodsClassification>();
		goodsClassificationService.doModById(goodsClassification);
		respBean.setBody(goodsClassification);
		return respBean;
	}
	/**
	 * 描述: 分类名称去重复
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toRepeatGoodsClassification")
	public RespBean<String> toRepeatGoodsClassification(GoodsClassification goodsClassification) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		String msg="";
		List<GoodsClassification> goodsClassificationList=goodsClassificationService.doGetList(goodsClassification);
		if(goodsClassificationList.size()>0){
			msg="1";
		}else{
			msg="0";
		}
		respBean.setBody(msg);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelGoodsClassification")
	@ResponseBody
	public RespBean<String> doDelGoodsClassificationAction(String goodsClassificationIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		goodsClassificationService.doRmByIds(goodsClassificationIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

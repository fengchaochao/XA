/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-27
 * @version 1.0
 */
package com.prj.biz.action.businessPC.goodsevaluate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.goods.Goods;
import com.prj.biz.bean.goodsclassification.GoodsClassification;
import com.prj.biz.bean.goodsevaluate.GoodsEvaluate;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.goods.GoodsService;
import com.prj.biz.service.goodsclassification.GoodsClassificationService;
import com.prj.biz.service.goodsevaluate.GoodsEvaluateService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.UfdmDateUtil;



/** 
 * 描述: 商品评价 Action 类<br>
 * @author xjt
 * @date 2017-07-27
 */
@RestController
@RequestMapping("/business/goodsEvaluate")
public class GoodsEvaluateAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	

	@Resource
	private GoodsService goodsService;
	@Resource
	private GoodsEvaluateService goodsEvaluateService;
	@Resource
	private GoodsClassificationService classificationService;
	@Resource
	private BusinessInformationService businessInformationService;
	@Resource
	private ConsumersService consumersService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnGoodsEvaluateList")
    public ModelAndView doEnGoodsEvaluateListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/goodsevaluate/goodsEvaluateList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther xjt
	 * @date 2017-07-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadGoodsEvaluateList")
	@ResponseBody
	public RespBean<RespPagination<Goods>> doReadGoodsEvaluateList(Goods goods) throws Exception{
		RespBean<RespPagination<Goods>> respBean = new RespBean<RespPagination<Goods>>();
		RespPagination<Goods> respPagination = new RespPagination<Goods>();
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		goods.setPublisher(sysUsers.getId());
		goods.setGoodsState("3");
		Integer total = goodsService.doGetTotal(goods);
		List<Goods> goodsList = goodsService.doGetList(goods);
		respPagination.setTotal(total);
		respPagination.setRows(goodsList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 回复评论页
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsSelById")
	public ModelAndView doEnGoodsSelById(String goodsId) throws Exception{
		Goods goods = goodsService.doGetById(goodsId);
		List<GoodsClassification> classificationList = classificationService.doGetList(null);
		String[] commodityImages = goods.getCommodityImages().split(",");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("goods", goods);
		model.put("commodityImages", commodityImages);
		model.put("classificationList", classificationList);
		return new ModelAndView("/goodsevaluate/goodsSelEvaluateList",model);
	}
	
	/**
	 * 描述: 查看评论页
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnSelEvaluateStatus")
	public ModelAndView doEnSelEvaluateStatus(String goodsId) throws Exception{
		Goods goods = goodsService.doGetById(goodsId);
		List<GoodsClassification> classificationList = classificationService.doGetList(null);
		String[] commodityImages = goods.getCommodityImages().split(",");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("goods", goods);
		model.put("commodityImages", commodityImages);
		model.put("classificationList", classificationList);
		return new ModelAndView("/goodsevaluate/goodsEvaluateListAll",model);
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadEvaluateList")
	@ResponseBody
	public RespBean<RespPagination<GoodsEvaluate>> doReadEvaluateList(GoodsEvaluate goodsEvaluate) throws Exception{
		RespBean<RespPagination<GoodsEvaluate>> respBean = new RespBean<RespPagination<GoodsEvaluate>>();
		RespPagination<GoodsEvaluate> respPagination = new RespPagination<GoodsEvaluate>();
		Integer total = goodsEvaluateService.doGetTotal(goodsEvaluate);
		List<GoodsEvaluate> goodsEvaluateList = goodsEvaluateService.doGetList(goodsEvaluate);
		for (GoodsEvaluate ge : goodsEvaluateList) {
			BusinessInformation businessInformation = businessInformationService.doGetById(ge.getConsumerId());
			if(businessInformation != null ){
				ge.setConsumersName(businessInformation.getVendorName());
			}else {
				Consumers consumers = consumersService.doGetById(ge.getConsumerId());
				if(consumers != null){
					ge.setConsumersName(consumers.getNickName());
				}
			}
			
		}
		respPagination.setTotal(total);
		respPagination.setRows(goodsEvaluateList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsEvaluateEdit")
	public ModelAndView doEnGoodsEvaluateEdit(String goodsEvaluateId) throws Exception{
		//GoodsEvaluate goodsEvaluate = goodsEvaluateService.doGetById(goodsEvaluateId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("goodsEvaluateId", goodsEvaluateId);
		return new ModelAndView("/goodsevaluate/goodsEvaluateEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditGoodsEvaluate")
	public RespBean<GoodsEvaluate> doEditGoodsEvaluate(GoodsEvaluate goodsEvaluate) throws Exception{
		RespBean<GoodsEvaluate> respBean = new RespBean<GoodsEvaluate>();
		goodsEvaluate.setReplyDate(UfdmDateUtil.getCurrentTime());
		goodsEvaluateService.doModById(goodsEvaluate);
		respBean.setBody(goodsEvaluate);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther xjt
	 * @date 2017-07-27
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelGoodsEvaluate")
	@ResponseBody
	public RespBean<String> doDelGoodsEvaluateAction(String goodsEvaluateIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		goodsEvaluateService.doRmByIds(goodsEvaluateIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

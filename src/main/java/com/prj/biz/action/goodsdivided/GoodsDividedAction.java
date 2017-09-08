/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-18
 * @version 1.0
 */
package com.prj.biz.action.goodsdivided;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.goodsdivided.GoodsDivided;
import com.prj.biz.service.goodsdivided.GoodsDividedService;
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
 * 描述: 商品分成记录 Action 类<br>
 * @author Liang
 * @date 2017-07-18
 */
@RestController
@RequestMapping("/goodsDivided")
public class GoodsDividedAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private GoodsDividedService goodsDividedService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnGoodsDividedList")
    public ModelAndView doEnGoodsDividedListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/goodsdivided/goodsDividedList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadGoodsDividedList")
	@ResponseBody
	public RespBean<RespPagination<GoodsDivided>> doReadGoodsDividedList(GoodsDivided goodsDivided) throws Exception{
		RespBean<RespPagination<GoodsDivided>> respBean = new RespBean<RespPagination<GoodsDivided>>();
		RespPagination<GoodsDivided> respPagination = new RespPagination<GoodsDivided>();
		Integer total = goodsDividedService.doGetTotal(goodsDivided);
		List<GoodsDivided> goodsDividedList = goodsDividedService.doGetList(goodsDivided);
		respPagination.setTotal(total);
		respPagination.setRows(goodsDividedList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnGoodsDividedEdit")
	public ModelAndView doEnGoodsDividedEdit(String goodsDividedId) throws Exception{
		GoodsDivided goodsDivided = goodsDividedService.doGetById(goodsDividedId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("goodsDivided", goodsDivided);
		return new ModelAndView("/goodsdivided/goodsDividedEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditGoodsDivided")
	public RespBean<GoodsDivided> doEditGoodsDivided(GoodsDivided goodsDivided) throws Exception{
		RespBean<GoodsDivided> respBean = new RespBean<GoodsDivided>();
		goodsDividedService.doModById(goodsDivided);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelGoodsDivided")
	@ResponseBody
	public RespBean<String> doDelGoodsDividedAction(String goodsDividedIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		goodsDividedService.doRmByIds(goodsDividedIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

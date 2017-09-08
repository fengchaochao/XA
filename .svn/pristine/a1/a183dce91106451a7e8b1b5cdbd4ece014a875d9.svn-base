/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-17
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.shufflingfigure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.businessclassification.BusinessClassification;
import com.prj.biz.bean.shufflingfigure.ShufflingFigure;
import com.prj.biz.service.shufflingfigure.ShufflingFigureService;
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
 * 描述: 轮播图设置 Action 类<br>
 * @author Liang
 * @date 2017-07-17
 */
@RestController
@RequestMapping("/headquarters/shufflingFigure")
public class ShufflingFigureAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private ShufflingFigureService shufflingFigureService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnShufflingFigureList")
    public ModelAndView doEnShufflingFigureListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/shufflingfigure/shufflingFigureList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadShufflingFigureList")
	@ResponseBody
	public RespBean<RespPagination<ShufflingFigure>> doReadShufflingFigureList(ShufflingFigure shufflingFigure) throws Exception{
		RespBean<RespPagination<ShufflingFigure>> respBean = new RespBean<RespPagination<ShufflingFigure>>();
		RespPagination<ShufflingFigure> respPagination = new RespPagination<ShufflingFigure>();
		shufflingFigure.setTypeName("APP轮播图");
		shufflingFigure.setOrder("asc");
		shufflingFigure.setOrderName("serial_number");
		Integer total = shufflingFigureService.doGetTotal(shufflingFigure);
		List<ShufflingFigure> shufflingFigureList = shufflingFigureService.doGetList(shufflingFigure);
		respPagination.setTotal(total);
		respPagination.setRows(shufflingFigureList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadShufflingFigureList1")
	@ResponseBody
	public RespBean<RespPagination<ShufflingFigure>> doReadShufflingFigureList1(ShufflingFigure shufflingFigure) throws Exception{
		RespBean<RespPagination<ShufflingFigure>> respBean = new RespBean<RespPagination<ShufflingFigure>>();
		RespPagination<ShufflingFigure> respPagination = new RespPagination<ShufflingFigure>();
		shufflingFigure.setTypeName("APP广告条");
		shufflingFigure.setOrder("asc");
		shufflingFigure.setOrderName("serial_number");
		Integer total = shufflingFigureService.doGetTotal(shufflingFigure);
		List<ShufflingFigure> shufflingFigureList = shufflingFigureService.doGetList(shufflingFigure);
		respPagination.setTotal(total);
		respPagination.setRows(shufflingFigureList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 进入添加页面
	 * @auther Liang
	 * @date 2017-07-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnShufflingFigureAdd")
	public ModelAndView doEnShufflingFigureAdd(String typeName) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		ShufflingFigure shufflingFigure=new ShufflingFigure();
		shufflingFigure.setTypeName(typeName);
		List<ShufflingFigure> shufflingFigureList = shufflingFigureService.doGetList(shufflingFigure);
		model.put("serialNumber", shufflingFigureList.size()+1);
		model.put("typeName", typeName);
		return new ModelAndView("/headquarters/shufflingfigure/shufflingFigureAdd",model);
	}
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnShufflingFigureEdit")
	public ModelAndView doEnShufflingFigureEdit(String shufflingFigureId) throws Exception{
		ShufflingFigure shufflingFigure = shufflingFigureService.doGetById(shufflingFigureId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("shufflingFigure", shufflingFigure);
		return new ModelAndView("/headquarters/shufflingfigure/shufflingFigureEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditShufflingFigure")
	public RespBean<ShufflingFigure> doEditShufflingFigure(ShufflingFigure shufflingFigure) throws Exception{
		RespBean<ShufflingFigure> respBean = new RespBean<ShufflingFigure>();
		if(shufflingFigure.getId()==null||"".equals(shufflingFigure.getId())){
			shufflingFigureService.doSave(shufflingFigure);
		}else{
			shufflingFigureService.doModById(shufflingFigure);
		}
		respBean.setBody(shufflingFigure);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelShufflingFigure")
	@ResponseBody
	public RespBean<String> doDelShufflingFigureAction(String shufflingFigureId) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		shufflingFigureService.doRmById(shufflingFigureId);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

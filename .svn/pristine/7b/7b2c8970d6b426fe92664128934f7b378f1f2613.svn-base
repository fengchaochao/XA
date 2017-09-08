/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-10
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.codeareas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.codeareas.codeAreas;
import com.prj.biz.service.codeareas.codeAreasService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tutorial.redis.RedisUtil;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.utils.ObjectUtil;



/** 
 * 描述: 代理区域表 Action 类<br>
 * @author Liang
 * @date 2017-07-10
 */
@RestController
@RequestMapping("/codeAreas")
public class codeAreasAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private codeAreasService codeAreasService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEncodeAreasList")
    public ModelAndView doEncodeAreasListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/codeareas/codeAreasList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadcodeAreasList")
	@ResponseBody
	public RespBean<RespPagination<codeAreas>> doReadcodeAreasList(codeAreas codeAreas) throws Exception{
		RespBean<RespPagination<codeAreas>> respBean = new RespBean<RespPagination<codeAreas>>();
		RespPagination<codeAreas> respPagination = new RespPagination<codeAreas>();
		Integer total = codeAreasService.doGetTotal(codeAreas);
		List<codeAreas> codeAreasList = codeAreasService.doGetList(codeAreas);
		respPagination.setTotal(total);
		respPagination.setRows(codeAreasList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEncodeAreasEdit")
	public ModelAndView doEncodeAreasEdit(String codeAreasId) throws Exception{
		codeAreas codeAreas = codeAreasService.doGetById(codeAreasId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("codeAreas", codeAreas);
		return new ModelAndView("/codeareas/codeAreasEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditcodeAreas")
	public RespBean<codeAreas> doEditcodeAreas(codeAreas codeAreas) throws Exception{
		RespBean<codeAreas> respBean = new RespBean<codeAreas>();
		codeAreasService.doModById(codeAreas);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelcodeAreas")
	@ResponseBody
	public RespBean<String> doDelcodeAreasAction(String codeAreasIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		codeAreasService.doRmByIds(codeAreasIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	/**
	 * 省市区的查询
	 * 
	 * @throws Exception
	 *//*
	@RequestMapping(value="/areaTask")
	@ResponseBody
	public void areaTask() throws Exception {
		
		List<codeAreas> countCodeAreas=codeAreasService.selectCountyList();
		List<codeAreas> codeAreas=codeAreasService.selectProvinceList();
		List<codeAreas> cityCodeAreas=codeAreasService.selectCityList();
		List<codeAreas> areaCodeAreas=codeAreasService.selectRegionList();
		
	    String key = "countCodeAreas";  
	    String key1 = "codeAreas";  
	    String key2 = "cityCodeAreas";  
	    String key3 = "areaCodeAreas";  
	        
		RedisUtil.getJedis().set(key.getBytes(),ObjectUtil.objectToBytes(countCodeAreas));
		RedisUtil.getJedis().set(key1.getBytes(),ObjectUtil.objectToBytes(codeAreas));
		RedisUtil.getJedis().set(key2.getBytes(),ObjectUtil.objectToBytes(cityCodeAreas));
		RedisUtil.getJedis().set(key3.getBytes(),ObjectUtil.objectToBytes(areaCodeAreas));

	}*/
	
}

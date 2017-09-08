/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-08-17
 * @version 1.0
 */
package com.prj.biz.action.historyseacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.historyseacher.HistorySeacher;
import com.prj.biz.service.historyseacher.HistorySeacherService;
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
 * 描述: APP首页历史搜索记录 Action 类<br>
 * @author Liang
 * @date 2017-08-17
 */
@RestController
@RequestMapping("/historySeacher")
public class HistorySeacherAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private HistorySeacherService historySeacherService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnHistorySeacherList")
    public ModelAndView doEnHistorySeacherListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/historyseacher/historySeacherList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadHistorySeacherList")
	@ResponseBody
	public RespBean<RespPagination<HistorySeacher>> doReadHistorySeacherList(HistorySeacher historySeacher) throws Exception{
		RespBean<RespPagination<HistorySeacher>> respBean = new RespBean<RespPagination<HistorySeacher>>();
		RespPagination<HistorySeacher> respPagination = new RespPagination<HistorySeacher>();
		Integer total = historySeacherService.doGetTotal(historySeacher);
		List<HistorySeacher> historySeacherList = historySeacherService.doGetList(historySeacher);
		respPagination.setTotal(total);
		respPagination.setRows(historySeacherList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnHistorySeacherEdit")
	public ModelAndView doEnHistorySeacherEdit(String historySeacherId) throws Exception{
		HistorySeacher historySeacher = historySeacherService.doGetById(historySeacherId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("historySeacher", historySeacher);
		return new ModelAndView("/historyseacher/historySeacherEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditHistorySeacher")
	public RespBean<HistorySeacher> doEditHistorySeacher(HistorySeacher historySeacher) throws Exception{
		RespBean<HistorySeacher> respBean = new RespBean<HistorySeacher>();
		historySeacherService.doModById(historySeacher);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-08-17
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelHistorySeacher")
	@ResponseBody
	public RespBean<String> doDelHistorySeacherAction(String historySeacherIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		historySeacherService.doRmByIds(historySeacherIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

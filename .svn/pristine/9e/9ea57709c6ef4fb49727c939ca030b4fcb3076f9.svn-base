/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-08-18
 * @version 1.0
 */
package com.prj.biz.api.historybrowsing;

import java.util.List;

import com.prj.biz.bean.historybrowsing.HistoryBrowsing;
import com.prj.biz.service.historybrowsing.HistoryBrowsingService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;



/** 
 * 描述: 我的足迹 Action 类<br>
 * @author Liang
 * @date 2017-08-18
 */
@RestController
@RequestMapping("/api")
public class HistoryBrowsingAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private HistoryBrowsingService historyBrowsingService;

	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadHistoryBrowsingList")
	@ResponseBody
	public RespBean<RespPagination<HistoryBrowsing>> doReadHistoryBrowsingList(HistoryBrowsing historyBrowsing) throws Exception{
		RespBean<RespPagination<HistoryBrowsing>> respBean = new RespBean<RespPagination<HistoryBrowsing>>();
		RespPagination<HistoryBrowsing> respPagination = new RespPagination<HistoryBrowsing>();
		Integer total = historyBrowsingService.doGetTotal(historyBrowsing);
		List<HistoryBrowsing> historyBrowsingList = historyBrowsingService.doGetList(historyBrowsing);
		respPagination.setTotal(total);
		respPagination.setRows(historyBrowsingList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-08-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doAddHistoryBrowsing")
	public RespBean<HistoryBrowsing> doAddHistoryBrowsing(HistoryBrowsing historyBrowsing) throws Exception{
		RespBean<HistoryBrowsing> respBean = new RespBean<HistoryBrowsing>();
		List<HistoryBrowsing> browsingList = historyBrowsingService.doGetList(historyBrowsing);
		//判断该商家该商品是否浏览过，如果是，则修改浏览时间，否，则新增
		if(browsingList.size() > 0){
			historyBrowsingService.doModById(browsingList.get(0));
		}else {
			historyBrowsingService.doSave(historyBrowsing);
		}
		respBean.setBody(historyBrowsing);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-08-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelHistoryBrowsing")
	@ResponseBody
	public RespBean<String> doDelHistoryBrowsingAction(String historyBrowsingIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		historyBrowsingService.doRmByIds(historyBrowsingIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

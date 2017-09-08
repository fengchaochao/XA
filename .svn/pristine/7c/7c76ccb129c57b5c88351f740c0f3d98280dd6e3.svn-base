/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-26
 * @version 1.0
 */
package com.prj.biz.action.redenveloperecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.redenveloperecord.RedEnvelopeRecord;
import com.prj.biz.service.redenveloperecord.RedEnvelopeRecordService;
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
 * 描述: 红包记录表 Action 类<br>
 * @author Liang
 * @date 2017-07-26
 */
@RestController
@RequestMapping("/redEnvelopeRecord")
public class RedEnvelopeRecordAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private RedEnvelopeRecordService redEnvelopeRecordService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnRedEnvelopeRecordList")
    public ModelAndView doEnRedEnvelopeRecordListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/redenveloperecord/redEnvelopeRecordList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadRedEnvelopeRecordList")
	@ResponseBody
	public RespBean<RespPagination<RedEnvelopeRecord>> doReadRedEnvelopeRecordList(RedEnvelopeRecord redEnvelopeRecord) throws Exception{
		RespBean<RespPagination<RedEnvelopeRecord>> respBean = new RespBean<RespPagination<RedEnvelopeRecord>>();
		RespPagination<RedEnvelopeRecord> respPagination = new RespPagination<RedEnvelopeRecord>();
		Integer total = redEnvelopeRecordService.doGetTotal(redEnvelopeRecord);
		List<RedEnvelopeRecord> redEnvelopeRecordList = redEnvelopeRecordService.doGetList(redEnvelopeRecord);
		respPagination.setTotal(total);
		respPagination.setRows(redEnvelopeRecordList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnRedEnvelopeRecordEdit")
	public ModelAndView doEnRedEnvelopeRecordEdit(String redEnvelopeRecordId) throws Exception{
		RedEnvelopeRecord redEnvelopeRecord = redEnvelopeRecordService.doGetById(redEnvelopeRecordId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("redEnvelopeRecord", redEnvelopeRecord);
		return new ModelAndView("/redenveloperecord/redEnvelopeRecordEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditRedEnvelopeRecord")
	public RespBean<RedEnvelopeRecord> doEditRedEnvelopeRecord(RedEnvelopeRecord redEnvelopeRecord) throws Exception{
		RespBean<RedEnvelopeRecord> respBean = new RespBean<RedEnvelopeRecord>();
		redEnvelopeRecordService.doModById(redEnvelopeRecord);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelRedEnvelopeRecord")
	@ResponseBody
	public RespBean<String> doDelRedEnvelopeRecordAction(String redEnvelopeRecordIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		redEnvelopeRecordService.doRmByIds(redEnvelopeRecordIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

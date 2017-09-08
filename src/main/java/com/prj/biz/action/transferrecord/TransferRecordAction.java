/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-09-01
 * @version 1.0
 */
package com.prj.biz.action.transferrecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.transferrecord.TransferRecord;
import com.prj.biz.service.transferrecord.TransferRecordService;
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
 * 描述: 转账记录表 Action 类<br>
 * @author Liang
 * @date 2017-09-01
 */
@RestController
@RequestMapping("/transferRecord")
public class TransferRecordAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private TransferRecordService transferRecordService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-09-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnTransferRecordList")
    public ModelAndView doEnTransferRecordListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/transferrecord/transferRecordList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-09-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadTransferRecordList")
	@ResponseBody
	public RespBean<RespPagination<TransferRecord>> doReadTransferRecordList(TransferRecord transferRecord) throws Exception{
		RespBean<RespPagination<TransferRecord>> respBean = new RespBean<RespPagination<TransferRecord>>();
		RespPagination<TransferRecord> respPagination = new RespPagination<TransferRecord>();
		Integer total = transferRecordService.doGetTotal(transferRecord);
		List<TransferRecord> transferRecordList = transferRecordService.doGetList(transferRecord);
		respPagination.setTotal(total);
		respPagination.setRows(transferRecordList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-09-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnTransferRecordEdit")
	public ModelAndView doEnTransferRecordEdit(String transferRecordId) throws Exception{
		TransferRecord transferRecord = transferRecordService.doGetById(transferRecordId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("transferRecord", transferRecord);
		return new ModelAndView("/transferrecord/transferRecordEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-09-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditTransferRecord")
	public RespBean<TransferRecord> doEditTransferRecord(TransferRecord transferRecord) throws Exception{
		RespBean<TransferRecord> respBean = new RespBean<TransferRecord>();
		transferRecordService.doModById(transferRecord);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-09-01
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelTransferRecord")
	@ResponseBody
	public RespBean<String> doDelTransferRecordAction(String transferRecordIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		transferRecordService.doRmByIds(transferRecordIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

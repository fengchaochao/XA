/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-08-04
 * @version 1.0
 */
package com.prj.biz.action.showamount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.showamount.ShowAmount;
import com.prj.biz.service.showamount.ShowAmountService;
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
 * 描述: 是否显示商家实收款 Action 类<br>
 * @author Liang
 * @date 2017-08-04
 */
@RestController
@RequestMapping("/showAmount")
public class ShowAmountAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private ShowAmountService showAmountService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-08-04
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnShowAmountList")
    public ModelAndView doEnShowAmountListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/showamount/showAmountList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-08-04
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadShowAmountList")
	@ResponseBody
	public RespBean<RespPagination<ShowAmount>> doReadShowAmountList(ShowAmount showAmount) throws Exception{
		RespBean<RespPagination<ShowAmount>> respBean = new RespBean<RespPagination<ShowAmount>>();
		RespPagination<ShowAmount> respPagination = new RespPagination<ShowAmount>();
		Integer total = showAmountService.doGetTotal(showAmount);
		List<ShowAmount> showAmountList = showAmountService.doGetList(showAmount);
		respPagination.setTotal(total);
		respPagination.setRows(showAmountList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-08-04
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnShowAmountEdit")
	public ModelAndView doEnShowAmountEdit(String showAmountId) throws Exception{
		ShowAmount showAmount = showAmountService.doGetById(showAmountId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("showAmount", showAmount);
		return new ModelAndView("/showamount/showAmountEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-08-04
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditShowAmount")
	public RespBean<ShowAmount> doEditShowAmount(ShowAmount showAmount) throws Exception{
		RespBean<ShowAmount> respBean = new RespBean<ShowAmount>();
		showAmountService.doModById(showAmount);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-08-04
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelShowAmount")
	@ResponseBody
	public RespBean<String> doDelShowAmountAction(String showAmountIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		showAmountService.doRmByIds(showAmountIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}

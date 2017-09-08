/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-11
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.distribution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.distribution.Distribution;
import com.prj.biz.service.distribution.DistributionService;
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
 * 描述: 抽成分配 Action 类<br>
 * @author Liang
 * @date 2017-07-11
 */
@RestController
@RequestMapping("/headquarters/distribution")
public class DistributionAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private DistributionService distributionService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnDistributionList")
    public ModelAndView doEnDistributionListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		List<Distribution> distributionList=distributionService.doGetList(null);
		if(distributionList.size()>0){
			model.put("distribution", distributionList.get(0));
		}
		return new ModelAndView("/headquarters/distribution/distributionEdit",model);
	}
	
	/**
	 * 描述: 修改
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditDistribution")
	public RespBean<Distribution> doEditDistribution(Distribution distribution) throws Exception{
		RespBean<Distribution> respBean = new RespBean<Distribution>();
		distributionService.doModById(distribution);
		respBean.setBody(distribution);
		return respBean;
	}
	/**
	 * 描述: 保存
	 * @auther Liang
	 * @date 2017-07-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSaveDistribution")
	public RespBean<Distribution> doSaveDistribution(Distribution distribution) throws Exception{
		RespBean<Distribution> respBean = new RespBean<Distribution>();
		distributionService.doSave(distribution);
		respBean.setBody(distribution);
		return respBean;
	}
	
}

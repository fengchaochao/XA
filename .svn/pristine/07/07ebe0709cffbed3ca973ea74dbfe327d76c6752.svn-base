/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-20
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.advertisementsh;

import java.util.List;

import com.prj.biz.bean.advertising.Advertising;
import com.prj.biz.service.advertising.AdvertisingService;
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
 * 描述: 广告表 Action 类<br>
 * @author Liang
 * @date 2017-07-20
 */
@RestController
@RequestMapping("headquarters/advertsh")
public class AdvertisementSHAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private AdvertisingService advertisingService;

	
	/**
	 * 描述: 广告审核页
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doSHAdvertisingList")
    public ModelAndView doSHAdvertisingList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/advertisementsh/advertisementshList");
	}
	
	/**
	 * 描述: 广告历史
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doHistoryAdvertisingList")
    public ModelAndView doHistoryAdvertisingList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/advertisementsh/advertisementhistoryList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadAdvertisingSHList")
	@ResponseBody
	public RespBean<RespPagination<Advertising>> doReadAdvertisingSHList(Advertising advertising) throws Exception{
		RespBean<RespPagination<Advertising>> respBean = new RespBean<RespPagination<Advertising>>();
		RespPagination<Advertising> respPagination = new RespPagination<Advertising>();
		advertising.setCheckState("1");//待审核数据
		Integer total = advertisingService.doGetTotal(advertising);
		List<Advertising> advertisingList = advertisingService.doGetList(advertising);
		respPagination.setTotal(total);
		respPagination.setRows(advertisingList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 分页查询信息历史
	 * @auther xjt
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadAdvertisingHistoryList")
	@ResponseBody
	public RespBean<RespPagination<Advertising>> doReadAdvertisingHistoryList(Advertising advertising) throws Exception{
		RespBean<RespPagination<Advertising>> respBean = new RespBean<RespPagination<Advertising>>();
		RespPagination<Advertising> respPagination = new RespPagination<Advertising>();
		if(advertising.getCheckState() == null || advertising.getCheckState().equals("")){
			advertising.setCheckState("5"); //已审核数据
		}
		Integer total = advertisingService.doGetTotal(advertising);
		List<Advertising> advertisingList = advertisingService.doGetList(advertising);
		respPagination.setTotal(total);
		respPagination.setRows(advertisingList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	
	
	/**
	 * 描述: 审核是否通过
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEditAdvertisingStatus")
	@ResponseBody
	public RespBean<String> doEditAdvertisingStatus(Advertising advertising) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		advertisingService.doModById(advertising);
		respBean.setBody("审核成功!");
		return respBean;
	}
	
}

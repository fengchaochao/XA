/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-20
 * @version 1.0
 */
package com.prj.biz.action.businessPC.advertising;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.advertising.Advertising;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.advertising.AdvertisingService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.JPushClientUtil;



/** 
 * 描述: 广告表 Action 类<br>
 * @author Liang
 * @date 2017-07-20
 */
@RestController
@RequestMapping("business/advertising")
public class AdvertisingAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private AdvertisingService advertisingService;
	@Resource
	private ConsumersService consumersService;
	@Resource
	private SysUserService sysUserService;
	JPushClientUtil jPushClientUtil;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnAdvertisingList")
    public ModelAndView doEnAdvertisingListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/advertising/advertisingList");
	}
	
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadAdvertisingList")
	@ResponseBody
	public RespBean<RespPagination<Advertising>> doReadAdvertisingList(Advertising advertising) throws Exception{
		RespBean<RespPagination<Advertising>> respBean = new RespBean<RespPagination<Advertising>>();
		RespPagination<Advertising> respPagination = new RespPagination<Advertising>();
		SysUser sysUser = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		advertising.setUserId(sysUser.getBusinessInformationId());
		Integer total = advertisingService.doGetTotal(advertising);
		List<Advertising> advertisingList = advertisingService.doGetList(advertising);
		respPagination.setTotal(total);
		respPagination.setRows(advertisingList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 进入编辑页面
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnAdvertisingEdit")
	public ModelAndView doEnAdvertisingEdit(String advertisingId) throws Exception{
		Advertising advertising = advertisingService.doGetById(advertisingId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("advertising", advertising);
		return new ModelAndView("/advertising/advertisingEdit",model);
	}
	
	/**
	 * 描述: 编辑保存
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditAdvertising")
	public RespBean<Advertising> doEditAdvertising(Advertising advertising) throws Exception{
		RespBean<Advertising> respBean = new RespBean<Advertising>();
		String id = advertising.getId();
		if(id == null || id.equals("")){
			SysUser sysUser = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
			advertising.setUserId(sysUser.getMerchantsId());
			advertising.setReleaseRange(sysUser.getAreaId());
			advertisingService.doSave(advertising);
		}else {
			advertising.setCheckState("1");
			advertisingService.doModById(advertising);
		}
		respBean.setBody(advertising);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelAdvertising")
	@ResponseBody
	public RespBean<String> doDelAdvertisingAction(String advertisingIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		advertisingService.doRmByIds(advertisingIds);
		respBean.setBody("删除成功");
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
		respBean.setBody("删除成功");
		return respBean;
	}
	
	/**
	 * 描述: 发布
	 * @auther Liang
	 * @date 2017-07-20
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/doAdvertisingActionRelease")
	@ResponseBody
	public RespBean<String> doAdvertisingActionRelease(Advertising advertising) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		SysUser sysUser = new SysUser();
		Consumers consumers = new Consumers();
		Advertising advert = new Advertising();
		advert.setUserId(advertising.getUserId());
		
		Advertising ad = advertisingService.doGetById(advertising.getId());
		//获取该商家所在区的消费者和消费商
		consumers.setAreaId(advertising.getReleaseRange());
		List<Consumers> consumersList = consumersService.doGetList(consumers);
		if(consumersList.size() > 0){
			for (int i = 0; i < consumersList.size(); i++) {
				sysUser.setMerchantsId(consumersList.get(i).getId());
				List<SysUser> sysUserList = sysUserService.doGetList(sysUser);
				//获取用户手机设备号，发送推送消息至手机
				String deviceNumber = sysUserList.get(0).getDeviceNumber();
				if(deviceNumber != null && !deviceNumber.equals("")){
					//标题、内容、设备号
					jPushClientUtil.pushMessage(ad.getHeadline(), ad.getContext(), deviceNumber);
				}
			}
		}
		advertisingService.doModById(advertising);
		respBean.setBody("发布成功！");
		return respBean;
	}
	
}

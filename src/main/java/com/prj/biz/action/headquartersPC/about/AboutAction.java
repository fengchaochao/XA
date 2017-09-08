/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Jiang
 * @date 2017-07-24
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.about;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.about.About;
import com.prj.biz.service.about.AboutService;
import com.prj.biz.action._base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.core.bean.resp.RespBean;
import com.prj.utils.UfdmDateUtil;



/** 
 * 描述: 关于 Action 类<br>
 * @author Jiang
 * @date 2017-07-24
 */
@RestController
@RequestMapping("/headquarters/about")
public class AboutAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private AboutService aboutService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnAboutList")
    public ModelAndView doEnAboutListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		List<About>  aboutList = aboutService.doGetList(null);
		if(aboutList.size() > 0 ){
			model.put("about", aboutList.get(0));
		}
		return new ModelAndView("/headquarters/about/aboutList",model);
	}
	
	
	/**
	 * 描述: 删除
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelAbout")
	@ResponseBody
	public RespBean<String> doDelAboutAction(String aboutIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		aboutService.doRmByIds(aboutIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
	/**
	 * 描述: 保存
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSaveAbout")
	public RespBean<About> doSaveAbout(About about) throws Exception{
		RespBean<About> respBean = new RespBean<About>();
		about.setAboutEndtime(UfdmDateUtil.getCurrentDate());
		aboutService.doSave(about);
		respBean.setBody(about);
		return respBean;
	}
	
	/**
	 * 描述: 修改
	 * @auther Jiang
	 * @date 2017-07-24
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditAbout")
	public RespBean<About> doEditAbout(About about) throws Exception{
		RespBean<About> respBean = new RespBean<About>();
		about.setAboutEndtime(UfdmDateUtil.getCurrentDate());
		aboutService.doModById(about);
		respBean.setBody(about);
		return respBean;
	}
	
}

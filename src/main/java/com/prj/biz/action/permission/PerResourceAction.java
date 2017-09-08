package com.prj.biz.action.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.service.permission.PerResourceService;
import com.prj.core.bean.resp.RespBean;

/** 
 * 描述: 系统资源管理<br>
 * @author 胡义振
 */
@Controller
@RequestMapping("resource")
public class PerResourceAction extends BaseAction
{
	private static final long	serialVersionUID	= -2760601192347633341L;
	
	@Resource
	private PerResourceService perResourceService;
	

	/**
	 * @Description: 进入资源列表页
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author 1936
	 */
	@RequestMapping(value="doEnResourceList")
	public ModelAndView doEnRoleList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, List<PerResource>> model = new HashMap<String, List<PerResource>>();
		List<PerResource> resourceList = perResourceService.doGetList(null);
		model.put("resourceList", resourceList);
		return new ModelAndView("/permission/resource/perResourceList",model);
	}
	
	
	/**
	 * 描述: 进入编辑角色
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doEnEditResource")
	@ResponseBody
	public ModelAndView doEnEditResourceAction(PerResource perResource) throws Exception 
	{
		if(perResource!=null && perResource.getId()!=null && !perResource.getId().equals("")){
			perResource =  perResourceService.doGetById(perResource.getId());
		}
		Map<String, PerResource> model = new HashMap<String, PerResource>();
		model.put("perResource", perResource);
		return new ModelAndView("/permission/resource/perResourceEdit",model);
	}
	
	/**
	 * 描述: 编辑资源
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditResource")
	@ResponseBody
	public RespBean<String> doEditResourceAction(PerResource perResource) throws Exception {
		RespBean<String> respBean = new RespBean<String>();
		if(perResource!=null && perResource.getId()!=null && !perResource.getId().equals("")){
			if(perResource.getResOrderNum()==null || perResource.getResOrderNum().equals("")){
				perResource.setResOrderNum("0");
			}
		    perResourceService.doModById(perResource);
		}
		else{
			if(perResource.getResOrderNum()==null || perResource.getResOrderNum().equals("")){
				perResource.setResOrderNum("0");
			}
			perResourceService.doSave(perResource);
			
		}
		respBean.setBody("编辑成功");
		return respBean;
	}

	/**
	 * 描述: 删除资源信息
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doDelResource")
	@ResponseBody
	public RespBean<String> doDelResourceAction(String perResourceIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		perResourceService.doRmByIds(perResourceIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
	
	
}

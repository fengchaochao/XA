package com.prj.biz.action.permission;

import java.util.ArrayList;
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
import com.prj.biz.bean.permission.PerRole;
import com.prj.biz.service.permission.PerResourceService;
import com.prj.biz.service.permission.PerRoleService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;

/** 
 * 描述: 系统角色管理<br>
 * @author 胡义振
 */
@Controller
@RequestMapping("role")
public class PerRoleAction extends BaseAction
{
	private static final long serialVersionUID = 7290179367304966919L;
	
	@Resource
	private PerRoleService perRoleService;
	
	@Resource
	private PerResourceService perResourceService;
	
	
	/**
	 * @Description: 进入角色列表页
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author 1936
	 */
	@RequestMapping(value="doEnRoleList")
	public ModelAndView doEnRoleList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return new ModelAndView("/permission/role/perRoleList");
	}
	
	/**
	 * @Description: 进入角色列表页
	 * @return
	 * @throws Exception
	 * @author 1936
	 */
	@RequestMapping(value="doReadRoleList")
	@ResponseBody
	public RespBean<RespPagination<PerRole>>  doReadRoleList(PerRole perRole) throws Exception {
		RespBean<RespPagination<PerRole>> respBean = new RespBean<RespPagination<PerRole>>();
		RespPagination<PerRole> respPagination = new RespPagination<PerRole>();
		Integer total = perRoleService.doGetTotal(perRole);
		List<PerRole> perRoleList = perRoleService.doGetList(perRole);
		respPagination.setTotal(total);
		respPagination.setRows(perRoleList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	
	/**
	 * 描述: 进入编辑角色
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doEnEditRole")
	public ModelAndView doEnEditRoleAction(String perRoleId) throws Exception 
	{
		PerRole perRole = null;
		if(perRoleId!=null && !perRoleId.equals("")){
			perRole = perRoleService.doGetRoleWithRoleOwnerByRoleId(perRoleId);
		}
		else{
			perRole = perRoleService.doGetRoleWithRoleOwnerByRoleId(null);
		}
		Map<String, PerRole> model = new HashMap<String, PerRole>();
		model.put("perRole", perRole);
		return new ModelAndView("/permission/role/perRoleEdit",model);
	}
	
	/**
	 * 描述: 编辑角色
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doEditRole")
	@ResponseBody
	public RespBean<String> doEditRoleAction(PerRole perRole) throws Exception
	{
		if(perRole.getPerResourceIds()!=null){
			String arrrResourceId [] =  perRole.getPerResourceIds().split(",");
			if(arrrResourceId!=null && arrrResourceId.length>0){
				List<PerResource> perResourceList = new ArrayList<PerResource>();
				for(String resourceId:arrrResourceId){
					PerResource perResource = new PerResource();
					perResource.setId(resourceId);
					perResourceList.add(perResource);
				}
				perRole.setPerResourceList(perResourceList);
			}
		}
		RespBean<String> respBean = new RespBean<String>();
		if(perRole.getId()==null || perRole.getId().equals("")){
			perRoleService.doSaveRole(perRole);
		}
		else{
			perRoleService.doModRole(perRole);
		}
		respBean.setBody("编辑成功");
		return respBean;
	}
	
	/**
	 * 描述: 删除角色
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doDelRole")
	@ResponseBody
	public RespBean<String> doDelRoleAction(String roleIds) throws Exception
	{
		RespBean<String> respBean = new RespBean<String>();
		if(roleIds!=null && !roleIds.equals("")){
			perRoleService.doRmByIds(roleIds);
		}
		respBean.setBody("删除成功");
		return respBean;
	}
	
	/**
	 * 描述: 进入添加角色
	 * @auther 王静娟
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doEnAddRole")
	public ModelAndView doEnAddRoleAction() throws Exception 
	{
		List<PerResource>  resourceList= perResourceService.doGetList(new PerResource());
		Map<String, List<PerResource>> model = new HashMap<String, List<PerResource>>();
		model.put("resourceList", resourceList);
		return new ModelAndView("/permission/role/perRoleAdd",model);
	}
	
	/**
	 * 描述: 添加角色
	 * @auther 王静娟
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doAddRole")
	@ResponseBody
	public RespBean<String> doAddRoleAction(PerRole perRole) throws Exception
	{
		if(perRole.getPerResourceIds()!=null){
			String arrrResourceId [] =  perRole.getPerResourceIds().split(",");
			if(arrrResourceId!=null && arrrResourceId.length>0){
				List<PerResource> perResourceList = new ArrayList<PerResource>();
				for(String resourceId:arrrResourceId){
					PerResource perResource = new PerResource();
					perResource.setId(resourceId);
					perResourceList.add(perResource);
				}
				perRole.setPerResourceList(perResourceList);
			}
		}
		RespBean<String> respBean = new RespBean<String>();
		perRoleService.doSaveRole(perRole);
		respBean.setBody("编辑成功");
		return respBean;
	}
	
}

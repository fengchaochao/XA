package com.prj.biz.action.permission;

import java.io.Serializable;
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
import com.prj.biz.bean.permission.PerDepartment;
import com.prj.biz.service.permission.PerDepartmentService;
import com.prj.core.bean.resp.RespBean;

/** 
 * 描述: 部门管理<br>
 * @author 王静娟
 */
@Controller
@RequestMapping("department")
public class PerDepartmentAction extends BaseAction implements Serializable{
	
	private static final long serialVersionUID = -9151688993359242525L;
	
	@Resource
	private PerDepartmentService perDepartmentService;
	
	/**
	 * @Description: 进入部门列表页
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author 王静娟
	 */
	@RequestMapping(value="doEnDepartmentList")
	public ModelAndView doEnDepartmentList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, List<PerDepartment>> model = new HashMap<String, List<PerDepartment>>();
		List<PerDepartment> departmentList = perDepartmentService.doGetList(null);
		model.put("departmentList", departmentList);
		return new ModelAndView("/permission/department/perDepartmentList",model);
	}
	
	/**
	 * 描述: 进入编辑部门
	 * @auther 王静娟
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doEnEditDepartment")
	@ResponseBody
	public ModelAndView doEnEditDepartmentAction(PerDepartment perDepartment) throws Exception 
	{
		if(perDepartment!=null && perDepartment.getId()!=null && !perDepartment.getId().equals("")){
			perDepartment =  perDepartmentService.doGetById(perDepartment.getId());
		}
		Map<String, PerDepartment> model = new HashMap<String, PerDepartment>();
		model.put("perDepartment", perDepartment);
		return new ModelAndView("/permission/department/perDepartmentEdit",model);
	}
	
	/**
	 * 描述: 编辑/添加部门
	 * @auther 王静娟
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditDepartment")
	@ResponseBody
	public RespBean<String> doEditDepartmentAction(PerDepartment perDepartment) throws Exception {
		RespBean<String> respBean = new RespBean<String>();
		if(perDepartment!=null && perDepartment.getId()!=null && !perDepartment.getId().equals("")){
			if(perDepartment.getDepOrderNum()==null || perDepartment.getDepOrderNum().equals("")){
				perDepartment.setDepOrderNum("0");
			}
		    perDepartmentService.doModDepartment(perDepartment);
		
		}else{
			if(perDepartment.getDepOrderNum()==null || perDepartment.getDepOrderNum().equals("")){
				perDepartment.setDepOrderNum("0");
			}
			perDepartmentService.doSave(perDepartment);
		}
		respBean.setBody("编辑成功");
		return respBean;
	}
	
	/**
	 * 描述: 删除部门信息
	 * @auther 王静娟
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="doDelDepartment")
	@ResponseBody
	public RespBean<String> doDelDepartmentAction(String perDepartmentIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		perDepartmentService.doRmByDepartments(perDepartmentIds);
		respBean.setBody("删除成功");
		return respBean;
	}
}

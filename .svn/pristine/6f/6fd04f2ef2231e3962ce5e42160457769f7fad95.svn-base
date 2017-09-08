package com.prj.biz.action.sysuser;

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
import com.prj.biz.bean.permission.PerRole;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.enums.RespMessEnum;
import com.prj.biz.service.permission.PerDepartmentService;
import com.prj.biz.service.permission.PerRoleService;
import com.prj.biz.service.permission.PerUserRoleService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.core.bean.exp.UfdmException;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.core.constant.SysConstants;
import com.prj.utils.UfdmMd5Util;

/**
 * 描述: 系统用户管理控制类<br>
 * @author 胡义振
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SysUserService		sysUserService;
	@Resource
	private PerUserRoleService	perUserRoleService;
	@Resource
	private PerRoleService      perRoleService;
	@Resource
	private PerDepartmentService perDepartmentService;


	/**
	 * @Description: 进入用户列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @date 2016年3月11日 
	 * @author 1936
	 */
	@RequestMapping("/doEnSysUserList")
	public ModelAndView doEnSysUserList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return new ModelAndView("/sysuser/sysUserList");
	}
	
	/**
	 * @Description: 查询用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @date 2016年3月11日 
	 * @author 1936
	 */
	@RequestMapping("/doReadSysUserList")
	@ResponseBody
	public RespBean<RespPagination<SysUser>> doReadSysUserList(SysUser sysUser) throws Exception{
		RespBean<RespPagination<SysUser>> respBean = new RespBean<RespPagination<SysUser>>();
		RespPagination<SysUser> respPagination = new RespPagination<SysUser>();
		Integer total = sysUserService.doGetTotal(sysUser);
		List<SysUser> sysUserList = sysUserService.doGetList(sysUser);
		respPagination.setTotal(total);
		respPagination.setRows(sysUserList);
		respBean.setBody(respPagination);
		return respBean;
	}

	/**
	 * 描述: 批量删除
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doDelSysUser")
	@ResponseBody
	public RespBean<String> doDelSysUser(String ids) throws Exception
	{
		RespBean<String> respBean = new RespBean<String>();
		sysUserService.doRmByIds(ids);
		String userIds[] = ids.split(",");
		for(String userId : userIds)
		{
			perUserRoleService.doRmUserRoleByUserId(userId);
		}
		respBean.setBody("删除成功");
		return respBean;
	}

	/**
	 * 描述: 进编辑页面
	 * @auther 胡义振
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnEditSysUser")
	public ModelAndView doEnEditSysUser(String sysUserId) throws Exception
	{
		SysUser sysUser = getSysUserById(sysUserId);
		//获取所有部门列表
		List<PerDepartment> perDepartmentList=perDepartmentService.doGetList(null);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sysUser", sysUser);
		model.put("perDepartmentList", perDepartmentList);
		return new ModelAndView("/sysuser/sysUserEdit", model);
	}
	
	/**
	 * 描述: 进添加页面
	 * @author dfsoft
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnAddSysUser")
	public ModelAndView doEnAddSysUser() throws Exception
	{
		//获取所有部门列表
		List<PerDepartment> perDepartmentList=perDepartmentService.doGetList(null);
		//获取所有角色
		List<PerRole> perRoleList = perRoleService.doGetList(null);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("perDepartmentList", perDepartmentList);
		model.put("perRoleList", perRoleList);
		return new ModelAndView("/sysuser/sysUserAdd", model);
	}
	
	private SysUser getSysUserById(String sysUserId) throws Exception{
		SysUser sysUser = null;
		if (sysUserId != null && !"".equals(sysUserId)) {
			sysUser = sysUserService.doGetById(sysUserId);
		} else {
			if (sysUser == null) {
				sysUser = new SysUser();
			}
		}
		// 设置角色
		List<PerRole> hxRoleList = perRoleService.doGetAllRoleListWithUserOwner(sysUser.getId());
		sysUser.setHxRoleList(hxRoleList);
		return sysUser;
	}
	
	@RequestMapping("doEnEditSysUserInfo")
	public ModelAndView doEnEditSysUserInfo() throws Exception{
		SysUser sessionSysUser = (SysUser) doGetSession(SysConstants.SESSION_SYS_USER);
		SysUser sysUser=getSysUserById(sessionSysUser.getId());
		String roleIds="";
		if(sysUser!=null&&sysUser.getHxRoleList()!=null){
			for(PerRole role:sysUser.getHxRoleList()){
				if(role.isUserOwner()){
					roleIds=roleIds+","+role.getId();
				}
			}
		}
		sysUser.setHxRoleIds(roleIds);
		Map<String,SysUser> model=new HashMap<String,SysUser>();
		model.put("sysUser", sysUser);
		return new ModelAndView("/userCenter/editSysUserInfo",model);
	}
	/**
	 * 描述: 编辑操作
	 * @auther 胡义振
	 * @date 2013-12-13
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditSysUser")
	@ResponseBody
	public RespBean<Boolean> doEditSysUser(SysUser sysUser) throws Exception
	{
		RespBean<Boolean> respBean = new RespBean<Boolean>();
		
		if (sysUser != null && sysUser.getId() != null && !sysUser.getId().equals("") )	{
			//检测用户名是否存在
			boolean isExist = sysUserService.doCheckSysUserLoginNameExist(sysUser.getLoginName(),sysUser.getId().toString());
			if(isExist){
				throw new UfdmException(RespMessEnum.RESP_CODE_0001003.getRespCode());
			}
			if(sysUser!=null && sysUser.getLoginPass()!=null && !sysUser.getLoginPass().equals("")){
				sysUser.setLoginPass(UfdmMd5Util.MD5Encode(sysUser.getLoginPass()));
			}
			sysUserService.doModById(sysUser);
		}
		else{
			boolean isExist = sysUserService.doCheckSysUserLoginNameExist(sysUser.getLoginName(),null);
			if(isExist){
				throw new UfdmException(RespMessEnum.RESP_CODE_0001003.getRespCode());
			}
			if(sysUser!=null && sysUser.getLoginPass()!=null && !sysUser.getLoginPass().equals("")){
				sysUser.setLoginPass(UfdmMd5Util.MD5Encode(sysUser.getLoginPass()));
			}
			sysUserService.doSave(sysUser);
		}
		
		String [] hxRoleDecIds = new String[]{};
		if(sysUser.getHxRoleIds()!=null){
			String [] hxRoleIds = sysUser.getHxRoleIds().split(",");
			hxRoleDecIds = new String[hxRoleIds.length];
			for(int i = 0;i<hxRoleIds.length;i++){
				hxRoleDecIds[i]=new String(hxRoleIds[i]);
			}
		}
		perUserRoleService.doModUserRole(sysUser.getId(), hxRoleDecIds);
		
		respBean.setBody(true);
		return respBean;
	}
	


	
	
}

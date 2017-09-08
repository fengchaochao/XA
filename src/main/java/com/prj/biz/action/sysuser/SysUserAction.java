package com.prj.biz.action.sysuser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.agent.Agent;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.permission.PerRole;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.enums.RespMessEnum;
import com.prj.biz.service.agent.AgentService;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
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
	@Resource
	private BusinessInformationService businessInformationService;
	@Resource
	private ConsumersAccountService consumersAccountService;
	@Resource
	private ConsumersService consumersService;
	@Resource
	private AgentService agentService;


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
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sysUsers", sysUsers);
		return new ModelAndView("/sysuser/sysUserList", model);
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
		//
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		List<SysUser> sysUserList = new ArrayList<SysUser>();
		Integer total =0;
		sysUser.setOrder("asc");
		sysUser.setOrderName("user_state");
		//总部可以查看所有的用户
		if("0".equals(sysUsers.getUserState())){
			sysUserList = sysUserService.doGetList(sysUser);
			total = sysUserService.doGetTotal(sysUser);
		}
		//代理商可以查看自己和管理自己的员工
		if("1".equals(sysUsers.getUserState())){
			sysUser.setCreater(sysUsers.getId());	
			sysUserList = sysUserService.doGetList(sysUser);
			sysUserList.add(sysUsers);
			total = sysUserService.doGetTotal(sysUser)+1;
		}
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
		
		String userIds[] = ids.split(",");
		for(String userId : userIds)
		{
			//删除对应的权限
			perUserRoleService.doRmUserRoleByUserId(userId);
		}
		sysUserService.doRmByIds(ids);
		respBean.setBody("删除成功");
		return respBean;
	}
	/**
	 * 描述: 停用/启用
	 * @auther Liang
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doForbiddenSysUser")
	@ResponseBody
	public RespBean<String> doForbiddenSysUser(String id) throws Exception
	{
		RespBean<String> respBean = new RespBean<String>();
		SysUser sysUser=sysUserService.doGetById(id);
		if(sysUser!=null){
			//禁用
			if ("0".equals(sysUser.getUserStatus())) {
				sysUser.setUserStatus("1");
				//代理商
				if("1".equals(sysUser.getUserState())){
					Agent agent=agentService.doGetById(sysUser.getMerchantsId());
					agent.setAgentState("1");
					agentService.doModById(agent);
				}
				//消费商/消费者
				if("3".equals(sysUser.getUserState())||"4".equals(sysUser.getUserState())){
					Consumers consumers=consumersService.doGetById(sysUser.getMerchantsId());
					consumers.setMerchantsState("1");
					consumersService.doModById(consumers);
				}
				//商家
				if("2".equals(sysUser.getUserState())){
					BusinessInformation businessInformation=businessInformationService.doGetById(sysUser.getMerchantsId());
					businessInformation.setBusinessState("1");
					businessInformationService.doModById(businessInformation);
				}
				sysUserService.doModById(sysUser);
				respBean.setBody("禁用成功");
			}
			//启用
			else {
				sysUser.setUserStatus("0");
				//代理商
				if("1".equals(sysUser.getUserState())){
					Agent agent=agentService.doGetById(sysUser.getMerchantsId());
					agent.setAgentState("0");
					agentService.doModById(agent);
				}
				//消费商/消费者
				if("3".equals(sysUser.getUserState())||"4".equals(sysUser.getUserState())){
					Consumers consumers=consumersService.doGetById(sysUser.getMerchantsId());
					consumers.setMerchantsState("0");
					consumersService.doModById(consumers);
				}
				//商家
				if("2".equals(sysUser.getUserState())){
					BusinessInformation businessInformation=businessInformationService.doGetById(sysUser.getMerchantsId());
					businessInformation.setBusinessState("0");
					businessInformationService.doModById(businessInformation);
				}
				sysUserService.doModById(sysUser);
				respBean.setBody("启用成功");
			}
		}else{
			respBean.setBody("用户信息不存在");
		}
		
		return respBean;
	}
	/**
	 * 描述: 重置密码
	 * @auther Liang
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doResetPasswords")
	@ResponseBody
	public RespBean<String> doResetPasswords(String id) throws Exception
	{
		RespBean<String> respBean = new RespBean<String>();
		SysUser sysUser=sysUserService.doGetById(id);
		if(sysUser!=null){
			sysUser.setLoginPass(UfdmMd5Util.MD5Encode("123456"));
		    sysUserService.doModById(sysUser);
		    respBean.setBody("重置成功");
		}else{
			respBean.setBody("用户信息不存在");
		}
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
		SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sysUser", sysUser);
		model.put("sysUsers", sysUsers);
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
		//获取所有角色
		List<PerRole> perRoleList = perRoleService.doGetList(null);
		Map<String, Object> model = new HashMap<String, Object>();
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
		}else{
			boolean isExist = sysUserService.doCheckSysUserLoginNameExist(sysUser.getLoginName(),null);
			if(isExist){
				throw new UfdmException(RespMessEnum.RESP_CODE_0001003.getRespCode());
			}
			if(sysUser!=null && sysUser.getLoginPass()!=null && !sysUser.getLoginPass().equals("")){
				sysUser.setLoginPass(UfdmMd5Util.MD5Encode(sysUser.getLoginPass()));
			}
			SysUser sysUsers = (SysUser) (SecurityUtils.getSubject().getSession().getAttribute(SysConstants.SESSION_SYS_USER));
			if("0".equals(sysUsers.getUserState())){
				sysUser.setCreater(sysUsers.getId());
				sysUser.setUserState("0");
			}
			if("1".equals(sysUsers.getUserState())){
				sysUser.setCreater(sysUsers.getId());
				sysUser.setUserState("1");
			}
			sysUser.setUserStatus("0");
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

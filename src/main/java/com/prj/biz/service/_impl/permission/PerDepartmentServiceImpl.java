package com.prj.biz.service._impl.permission;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.prj.biz.bean.permission.PerDepartment;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.dao.maindb.permission.PerDepartmentDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.permission.PerDepartmentService;
import com.prj.biz.service.sysuser.SysUserService;

/**
 * 描述: 权限系统--部门Service实现<br>
 * @author 王静娟
 */
@Service
public class PerDepartmentServiceImpl extends BaseServiceImpl<PerDepartmentDao,PerDepartment> implements PerDepartmentService
{
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 描述：编辑部门 并修改隶属部门的用户
	 * @param perDepartment 待修改部门对象
	 * @return
	 */
	@Override
	public int doModDepartment(PerDepartment perDepartment) throws Exception {
		//修改隶属部门的用户部门信息
		if(perDepartment.getDepartmentName()!=null){
			//获取部门用户
			SysUser user = new SysUser();
			user.setDepId(perDepartment.getId());
			List<SysUser> sysUsers = sysUserService.doGetList(user);
			//全删除当前部门下用户
			for (SysUser sysUser : sysUsers) {
				sysUser.setDepName(perDepartment.getDepartmentName());
				sysUserService.doModById(sysUser);
			}
		}
		return genDao.updateById(perDepartment);
	}

	
	/**
	 * 删除部门 并修改隶属部门的用户
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@Override
	public int doRmByDepartments(String ids) throws Exception {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			if(id!=null&&!"".equals(id)){
				SysUser user = new SysUser();
				user.setDepId(id);
				List<SysUser> sysUsers = sysUserService.doGetList(user);
				//修改用户Department为null
				for (SysUser sysUser : sysUsers) {
					sysUser.setDepName(null);
					sysUser.setDepId(null);
					sysUserService.doModById(sysUser);
				}
			}
		}
		return super.doRmByIds(ids);
	}
}

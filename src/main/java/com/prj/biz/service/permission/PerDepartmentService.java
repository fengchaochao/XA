package com.prj.biz.service.permission;

import com.prj.biz.bean.permission.PerDepartment;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 权限系统--部门Service接口<br>
 * @author 王静娟
 */
public interface PerDepartmentService extends BaseService<PerDepartment>
{
	/**
	 * 描述：编辑部门 并修改隶属部门的用户
	 * @param perDepartment 待修改部门对象
	 * @return
	 */
	int doModDepartment(PerDepartment perDepartment) throws Exception;

	
	/**
	 * 删除部门 并修改隶属部门的用户
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int doRmByDepartments(String ids)throws Exception;
	
}

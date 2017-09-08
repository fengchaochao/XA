package com.prj.biz.dao.maindb.leavingmessage;

import java.util.List;

import com.prj.biz.bean.leavingmessage.LeavingMessage;
import com.prj.biz.dao._base.BaseDao;

/**
 * 
 * 描述: 客服系统 Dao 接口<br>
 * @author Liang
 * @date 2017-07-27
 */
public interface LeavingMessageDao extends BaseDao<LeavingMessage>
{
	/**
	 * 查询所有
	 * @param leavingMessage
	 * @return
	 */
	public List<LeavingMessage>  selectAllList(LeavingMessage leavingMessage);
}
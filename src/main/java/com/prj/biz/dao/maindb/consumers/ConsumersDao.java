package com.prj.biz.dao.maindb.consumers;

import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.dao._base.BaseDao;

/**
 * 
 * 描述: 消费者 Dao 接口<br>
 * @author Liang
 * @date 2017-07-18
 */
public interface ConsumersDao extends BaseDao<Consumers>
{
	
	/**
	 * 根据id获取昵称和头像
	 * @param businessInformation
	 * @return
	 */
	public Consumers selectByIdInfo(Consumers consumers);
}
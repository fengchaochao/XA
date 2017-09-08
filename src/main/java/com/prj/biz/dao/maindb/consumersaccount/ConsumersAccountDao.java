package com.prj.biz.dao.maindb.consumersaccount;

import java.util.List;

import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.dao._base.BaseDao;

/**
 * 
 * 描述: 消费者账户表 Dao 接口<br>
 * @author Liang
 * @date 2017-07-18
 */
public interface ConsumersAccountDao extends BaseDao<ConsumersAccount>
{
	/**
	 * 消费商锁定用户列表
	 * @param consumersAccount
	 * @return
	 */
	public List<ConsumersAccount>  selectConsumerslocalUser(ConsumersAccount consumersAccount);
	/**
	 * 商家锁定用户列表
	 * @param consumersAccount
	 * @return
	 */
	public List<ConsumersAccount>  selectBusinesslocalUser(ConsumersAccount consumersAccount);

}
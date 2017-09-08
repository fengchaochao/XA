package com.prj.biz.service._impl.consumersaccount;

import java.util.List;

import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.dao.maindb.consumersaccount.ConsumersAccountDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 消费者账户表 Service 实现<br>
 * @author Liang
 * @date 2017-07-18
 */
@Service
public class ConsumersAccountServiceImpl extends BaseServiceImpl<ConsumersAccountDao,ConsumersAccount> implements ConsumersAccountService
{

	@Override
	public List<ConsumersAccount> doEnConsumerslocalUser(
			ConsumersAccount consumersAccount) {
		// TODO Auto-generated method stub
		return genDao.selectConsumerslocalUser(consumersAccount);
	}

	@Override
	public List<ConsumersAccount> doEnBusinesslocalUser(
			ConsumersAccount consumersAccount) {
		// TODO Auto-generated method stub
		return genDao.selectBusinesslocalUser(consumersAccount);
	}
	

}

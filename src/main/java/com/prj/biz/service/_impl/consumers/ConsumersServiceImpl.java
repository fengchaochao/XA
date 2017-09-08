package com.prj.biz.service._impl.consumers;

import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.dao.maindb.consumers.ConsumersDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.consumers.ConsumersService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 消费者 Service 实现<br>
 * @author Liang
 * @date 2017-07-18
 */
@Service
public class ConsumersServiceImpl extends BaseServiceImpl<ConsumersDao,Consumers> implements ConsumersService
{

	@Override
	public Consumers selectByIdInfo(Consumers consumers) {
		// TODO Auto-generated method stub
		return genDao.selectByIdInfo(consumers);
	}
	

}

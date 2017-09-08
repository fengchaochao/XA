package com.prj.biz.service._impl.leavingmessage;

import java.util.List;

import com.prj.biz.bean.leavingmessage.LeavingMessage;
import com.prj.biz.dao.maindb.leavingmessage.LeavingMessageDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.leavingmessage.LeavingMessageService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 客服系统 Service 实现<br>
 * @author Liang
 * @date 2017-07-27
 */
@Service
public class LeavingMessageServiceImpl extends BaseServiceImpl<LeavingMessageDao,LeavingMessage> implements LeavingMessageService
{

	@Override
	public List<LeavingMessage> doGetAllList(LeavingMessage leavingMessage) {
		// TODO Auto-generated method stub
		return genDao.selectAllList(leavingMessage);
	}
	

}

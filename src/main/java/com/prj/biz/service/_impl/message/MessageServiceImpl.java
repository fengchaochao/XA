package com.prj.biz.service._impl.message;

import java.util.List;

import com.prj.biz.bean.message.Message;
import com.prj.biz.dao.maindb.message.MessageDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.message.MessageService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 消息 Service 实现<br>
 * @author Jiang
 * @date 2017-07-25
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageDao,Message> implements MessageService
{

	@Override
	public List<Message> selectListByCode(Message message) {
		// TODO Auto-generated method stub
		return genDao.selectListByCode(message);
	}
	

}

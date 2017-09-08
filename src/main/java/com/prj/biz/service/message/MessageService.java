package com.prj.biz.service.message;

import java.util.List;

import com.prj.biz.bean.message.Message;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 消息 Service 接口<br>
 * @author Jiang
 * @date 2017-07-25
 */
public interface MessageService extends BaseService<Message>{
	
	public List<Message> selectListByCode(Message message);
}

package com.prj.biz.service.consumers;

import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 消费者 Service 接口<br>
 * @author Liang
 * @date 2017-07-18
 */
public interface ConsumersService extends BaseService<Consumers>{
	

	/**
	 * 根据id获取昵称和头像
	 * @param businessInformation
	 * @return
	 */
	public Consumers selectByIdInfo(Consumers consumers);
}

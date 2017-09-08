package com.prj.biz.service.withdrawrecord;

import java.util.List;

import com.prj.biz.bean.withdrawrecord.WithdrawRecord;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 提现记录表 Service 接口<br>
 * @author Liang
 * @date 2017-08-07
 */
public interface WithdrawRecordService extends BaseService<WithdrawRecord>{
	
	/**
	 * 联表查询
	 * @param withdrawRecord
	 * @return
	 */
	public List<WithdrawRecord> doGetAllList(WithdrawRecord withdrawRecord);
}

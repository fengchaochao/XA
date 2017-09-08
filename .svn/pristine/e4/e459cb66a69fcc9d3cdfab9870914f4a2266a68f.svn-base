package com.prj.biz.service._impl.withdrawrecord;

import java.util.List;

import com.prj.biz.bean.withdrawrecord.WithdrawRecord;
import com.prj.biz.dao.maindb.withdrawrecord.WithdrawRecordDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.withdrawrecord.WithdrawRecordService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 提现记录表 Service 实现<br>
 * @author Liang
 * @date 2017-08-07
 */
@Service
public class WithdrawRecordServiceImpl extends BaseServiceImpl<WithdrawRecordDao,WithdrawRecord> implements WithdrawRecordService
{

	@Override
	public List<WithdrawRecord> doGetAllList(WithdrawRecord withdrawRecord) {
		// TODO Auto-generated method stub
		return genDao.selectAllList(withdrawRecord);
	}
	

}

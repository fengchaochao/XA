package com.prj.biz.service._impl.advertising;

import java.util.List;

import com.prj.biz.bean.advertising.Advertising;
import com.prj.biz.dao.maindb.advertising.AdvertisingDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.advertising.AdvertisingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 广告表 Service 实现<br>
 * @author Liang
 * @date 2017-07-20
 */
@Service
public class AdvertisingServiceImpl extends BaseServiceImpl<AdvertisingDao,Advertising> implements AdvertisingService
{

	@Autowired
	private AdvertisingDao advertisingDao;
	@Override
	public List<Advertising> selectByUser(Advertising advertising) {
		// TODO Auto-generated method stub
		return advertisingDao.selectByUser(advertising);
	}
	

}

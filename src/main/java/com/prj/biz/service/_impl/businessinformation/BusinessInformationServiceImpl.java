package com.prj.biz.service._impl.businessinformation;

import java.util.List;

import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.dao.maindb.businessinformation.BusinessInformationDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 商家信息 Service 实现<br>
 * @author Liang
 * @date 2017-07-12
 */
@Service
public class BusinessInformationServiceImpl extends BaseServiceImpl<BusinessInformationDao,BusinessInformation> implements BusinessInformationService
{

	@Override
	public List<BusinessInformation> selectAllList(BusinessInformation businessInformation) {
		// TODO Auto-generated method stub
		return genDao.selectAllList(businessInformation);
	}

	@Override
	public List<BusinessInformation> doEnConsumersRecommendedList(
			BusinessInformation businessInformation) {
		// TODO Auto-generated method stub
		return genDao.selectConsumersRecommendedList(businessInformation);
	}

	@Override
	public List<BusinessInformation> doEnBusinessRecommendedList(
			BusinessInformation businessInformation) {
		// TODO Auto-generated method stub
		return genDao.selectBusinessRecommendedList(businessInformation);
	}

	@Override
	public List<BusinessInformation> doEnRecommendedBusinessAllList(
			BusinessInformation businessInformation) {
		// TODO Auto-generated method stub
		return genDao.selectRecommendedBusinessAllList(businessInformation);
	}

	@Override
	public Integer doEnRecommendedBusinessTotal(
			BusinessInformation businessInformation) {
		// TODO Auto-generated method stub
		return genDao.selectRecommendedBusinessTotal(businessInformation);
	}

	@Override
	public BusinessInformation selectByIdInfo(String id) {
		// TODO Auto-generated method stub
		return genDao.selectByIdInfo(id);
	}
	

}

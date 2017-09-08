package com.prj.biz.service._impl.goodsspecifications;

import java.util.List;

import com.prj.biz.bean.goodsspecifications.GoodsSpecifications;
import com.prj.biz.dao.maindb.goodsspecifications.GoodsSpecificationsDao;
import com.prj.biz.service._impl._base.BaseServiceImpl;
import com.prj.biz.service.goodsspecifications.GoodsSpecificationsService;
import org.springframework.stereotype.Service;
/**
 * 
 * 描述: 商品规格表 Service 实现<br>
 * @author Liang
 * @date 2017-07-13
 */
@Service
public class GoodsSpecificationsServiceImpl extends BaseServiceImpl<GoodsSpecificationsDao,GoodsSpecifications> implements GoodsSpecificationsService
{

	@Override
	public List<GoodsSpecifications> doGetAllList(
			GoodsSpecifications goodsSpecifications) {
		// TODO Auto-generated method stub
		return genDao.selectAllList(goodsSpecifications);
	}

	@Override
	public Integer doGetAllTotal(GoodsSpecifications goodsSpecifications) {
		// TODO Auto-generated method stub
		return genDao.selectAllTotal(goodsSpecifications);
	}
	

}

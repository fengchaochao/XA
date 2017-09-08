package com.prj.biz.service.advertising;

import java.util.List;

import com.prj.biz.bean.advertising.Advertising;
import com.prj.biz.service._base.BaseService;

/**
 * 描述: 广告表 Service 接口<br>
 * @author Liang
 * @date 2017-07-20
 */
public interface AdvertisingService extends BaseService<Advertising>{
	
	/**
	 * 查找该商家是否已发布过
	 * @param advertising
	 * @return
	 */
	public List<Advertising> selectByUser(Advertising advertising);
}

package com.prj.biz.dao.maindb.businessinformation;

import java.util.List;

import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.dao._base.BaseDao;

/**
 * 
 * 描述: 商家信息 Dao 接口<br>
 * @author Liang
 * @date 2017-07-12
 */
public interface BusinessInformationDao extends BaseDao<BusinessInformation>
{
	/**
	 * 查询列表
	 * @param businessInformation
	 * @return
	 */
	public List<BusinessInformation> selectAllList(BusinessInformation businessInformation);
	/**
	 * 消费商推荐商家列表
	 * @param businessInformation
	 * @return
	 */
	public List<BusinessInformation> selectConsumersRecommendedList(BusinessInformation businessInformation);
	/**
	 * 商家推荐商家列表
	 * @param businessInformation
	 * @return
	 */
	public List<BusinessInformation> selectBusinessRecommendedList(BusinessInformation businessInformation);
	/**
	 * 总部查看商家和消费商推荐的商家
	 * @param businessInformation
	 * @return
	 */
	public List<BusinessInformation> selectRecommendedBusinessAllList(BusinessInformation businessInformation);
	/**
	 * 统计 总部查看商家和消费商推荐的商家
	 * @param businessInformation
	 * @return
	 */
	public Integer selectRecommendedBusinessTotal(BusinessInformation businessInformation);
	
	/**
	 * 根据id获取昵称和头像
	 * @param businessInformation
	 * @return
	 */
	public BusinessInformation selectByIdInfo(String  id);

}
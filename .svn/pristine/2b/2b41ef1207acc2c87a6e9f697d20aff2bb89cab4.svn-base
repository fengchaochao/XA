package com.prj.biz.dao._base;

import java.util.List;

/** 
* @Description: 通用DAO基础类
* @date 2016年3月8日 
* @author 1936
*/
public interface BaseDao<T> {
	
	/**
	 * 描述: 通用单个对象查询
	 * @auther 胡义振
	 * @date 2016-03-08
	 * @param paramId
	 * @return
	 * @throws Exception
	 */
	public T selectById(String paramId) throws Exception;
	
	/**
	 * 描述: 通用列表查询
	 * @auther 胡义振
	 * @date 2013-5-27 
	 * @param paramQryMap 查询参数
	 * @return 查询结果集
	 * @throws Exception
	 */
	public List<T> selectList(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用查询总数
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramQryMap 查询参数
	 * @return 查询总数
	 * @throws Exception
	 */
	public Integer selectTotal(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用保存操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramObject 要保存的对象
	 * @return 保存的ID
	 * @throws Exception
	 */
	public int insert(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用保存操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramObject 要保存的对象
	 * @return 保存的ID
	 * @throws Exception
	 */
	public int insertBatch(List<T> listObject) throws Exception;
	
	/**
	 * 描述: 通用单条更新操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramObject 要修改的对象
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateById(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用批量更新操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param listObject 要修改的对象
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateBatchById(List<T> listObject) throws Exception;
	
	
	/**
	 * 描述: 通用单条删除操作
	 * @auther 胡义振
	 * @date 2016年3月8日 
	 * @param paramId 要删除的ID
	 * @return 删除条数
	 * @throws Exception
	 */
	public int deleteById(String paramId) throws Exception;
	
	/**
	 * 描述: 通用多条删除操作
	 * @auther 胡义振
	 * @date 2016年3月8日 
	 * @param paramIdList 要删除的ID(以“,”号分割)
	 * @return 删除条数
	 * @throws Exception
	 */
	public int deleteByIds(List<String> paramIdList) throws Exception;
	
}

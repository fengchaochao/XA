package com.prj.biz.service._base;

import java.util.List;

/** 
* @Description: TODO
* @date 2016年3月8日 
* @author 1936
*/
public interface BaseService<T> {
	
	/**
	 * 描述: 通用根据ID查询对象
	 * @auther 胡义振
	 * @date 2014-10-1 
	 * @param paramId
	 * @return
	 * @throws Exception
	 */
	public T doGetById(String paramId) throws Exception;
	
    /**
     * 描述: 通用查询列表
     * @auther 胡义振
     * @date 2013-5-28 
     * @param paramQryMap 查询参数
     * @return 查询结果集
     * @throws Exception
     */
	public List<T> doGetList(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用查询总数
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramQryMap 查询参数
	 * @return 查询总数
	 * @throws Exception
	 */
	public Integer doGetTotal(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用单条操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramObject 要保存的对象
	 * @return 保存的ID
	 * @throws Exception
	 */
	public int doSave(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用批量操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param listObject 要保存的对象
	 * @return 保存的ID
	 * @throws Exception
	 */
	public int doSaveBatch(List<T> listObject) throws Exception;
	/**
	 * 描述: 通用更新操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramObject 要修改的对象
	 * @return 更新条数
	 * @throws Exception
	 */
	public int doModById(T paramObject) throws Exception;
	
	/**
	 * 描述: 通用批量更新操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramObject 要修改的对象
	 * @return 更新条数
	 * @throws Exception
	 */
	public int doModBatchById(List<T> listObject) throws Exception;
	
	/**
	 * 描述: 通用删除操作
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramId 要删除的ID
	 * @return 删除条数
	 * @throws Exception
	 */
	public int doRmById(String paramId) throws Exception;

	/**
	 * 描述: 通用删除多条
	 * @auther 胡义振
	 * @date 2013-5-28 
	 * @param paramIds 要删除的ID（多条以“,”号分割）
	 * @return 删除条数
	 * @throws Exception
	 */
	public int doRmByIds(String paramIds) throws Exception;


}

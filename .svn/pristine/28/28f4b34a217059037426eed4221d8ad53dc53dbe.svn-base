package com.prj.biz.service._impl._base;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.prj.biz.dao._base.BaseDao;
import com.prj.biz.service._base.BaseService;

/** 
 * @Description: Service 基础服务类
 * @date 2016年3月8日 
 * @author 1936
 */
public class BaseServiceImpl<GENDAO extends BaseDao<T> ,T> implements BaseService<T>{
	
	   @Autowired
	   public GENDAO genDao; 

		/**
		 * 描述: 构造函数
		 * @auther 胡义振
		 * @date 2013-5-28
		 */
		public BaseServiceImpl(){

		}

		/**
		 * 描述: 通用单个对象查询
		 * @auther 胡义振
		 * @date 2014-10-1 
		 * @param paramId
		 * @return
		 * @throws Exception
		 */
		@Override
		public T doGetById(String paramId) throws Exception{
			return genDao.selectById(paramId);
		}
		
	    /**
	     * 描述: 通用列表查询
	     * @auther 胡义振
	     * @param paramQryMap 查询参数
	     * @return 查询结果集
	     * @throws Exception
	     */
		@Override
		public List<T> doGetList(T paramObject) throws Exception
		{
			return genDao.selectList(paramObject);
		}

		/**
		 * 描述: 通用查询总数
		 * @auther 胡义振
		 * @param paramQryMap 查询参数
		 * @return 查询总数
		 * @throws Exception
		 */
		@Override
		public Integer doGetTotal(T paramObject) throws Exception
		{
			return genDao.selectTotal(paramObject);
		}
		
		/**
		 * 描述: 通用保存操作
		 * @auther 胡义振
		 * @param paramObject 要保存的对象
		 * @return 保存的ID
		 * @throws Exception
		 */
		@Override
		public int doSave(T paramObject) throws Exception
		{
			return genDao.insert(paramObject);
		}


		/**
		 * 描述: 通用更新操作
		 * @auther 胡义振
		 * @param paramObject 要修改的对象
		 * @return 更新条数
		 * @throws Exception
		 */
		@Override
		public int doModById(T paramObject) throws Exception
		{
			return genDao.updateById(paramObject);
		}

		/**
		 * 描述: 通用批量更新操作
		 * @auther 胡义振
		 * @param paramObject 要修改的对象
		 * @return 更新条数
		 * @throws Exception
		 */
		public int doModBatchById(List<T> listObject) throws Exception{
			return genDao.updateBatchById(listObject);
		}
		/**
		 * 描述: 通用删除操作
		 * @auther 胡义振
		 * @param paramId 要删除的ID
		 * @return 删除条数
		 * @throws Exception
		 */
		@Override
		public int doRmById(String paramId) throws Exception
		{
			return genDao.deleteById(paramId);
		}
		
		/**
		 * 描述: 通用多条删除操作
		 * @auther 胡义振
		 * @date 2016年3月8日 
		 * @param paramIds 要删除的ID(以“,”号分割)
		 * @return 删除条数
		 * @throws Exception
		 */
		@Override
		public int doRmByIds(String paramIds) throws Exception
		{
			if (paramIds == null)  paramIds="";
			String[] arrParamId = paramIds.split(",");
			List<String> idList = new ArrayList<String>();
			for(String paramId : arrParamId){
				idList.add(paramId);
			}
			return genDao.deleteByIds(idList);
		}
		
		/**
		 * 描述: 通用批量操作
		 * @auther 胡义振
		 * @param objectList 要保存的对象
		 * @return 保存的ID
		 * @throws Exception
		 */
		public int doSaveBatch(List<T> listObject) throws Exception{
			return genDao.insertBatch(listObject);
		}

}

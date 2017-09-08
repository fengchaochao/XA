package com.prj.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** 
* @Description: Map 工具类
* @date 2015年11月13日 
* @author 1936
*/
public class UfdmMapUtil {
	
	/**
	 * @Description: 两个MAP相加
	 */
	public static Map<String,String> mapAddMap(Map<String,String> target, Map<String,String> plus) {
		
        Object[] os = plus.keySet().toArray();
        String key;
        for (int i=0; i<os.length; i++) {
            key = (String)os[i];
            if (target.containsKey(key)) 
                target.put(key, target.get(key) + plus.get(key));
            else
                target.put(key, plus.get(key));
        }
        return target;
    }
	
	/**
	 * 描述: 把Map转成URL参数
	 * @param paramMap map类型数据
	 * @param encode 编码
	 * @date 2013-8-8 
	 * @return URL参数
	 */
	public static String mapToUrlParam(Map<String,String> paramMap,String encode) throws Exception{
		String params = "";
		String tempKey;
		String tempValue;
		Set<String> paramKey = paramMap.keySet();
		Iterator<String> it = paramKey.iterator();
		while(it.hasNext()){
			tempKey = it.next();
			tempValue = paramMap.get(tempKey)!=null ? URLEncoder.encode(paramMap.get(tempKey),encode) : "";
			if(params.equals("")){
				params = tempKey + "=" + tempValue;
			}
			else{
				params = params + "&" + tempKey + "=" + tempValue;
			}
		}
		return params;
	}

	/**
	 * 描述: 把Url参数转成Map
	 * @param urlParam URL 参数
	 * @param encode 编码
	 * @auther 胡义振
	 * @date 2013-8-8 
	 * @return UrlMap
	 */
	public static Map<String,String> urlParamToMap(String urlParam) {
		Map<String,String> urlParamMap = new HashMap<String,String>();
		if(urlParam!=null)
		{
			try
			{
				String [] arrParam = urlParam.split("&");
				for(int i=0;i<arrParam.length;i++){
					String [] keyValue = arrParam[i].split("=");
					urlParamMap.put(keyValue[0], keyValue[1]);
				}
			}
			catch(Exception er){
				er.printStackTrace();
			}
		}
		return urlParamMap;
	}
	
	/**
	 * 描述: 根据分割符把Map转成String格式。如：key1=value1,key2=valu2  "," 为分割符
	 * @param paramMap map类型数据
	 * @param separator 分割符
	 * @auther 胡义振
	 * @date 2013-8-8 
	 * @return URL参数
	 */
	public static String mapToString(Map<String,String> paramMap,String separator) throws Exception{
		String params = "";
		String tempKey;
		String tempValue;
		Set<String> paramKey = paramMap.keySet();
		Iterator<String> it = paramKey.iterator();
		while(it.hasNext()){
			tempKey = it.next();
			tempValue = paramMap.get(tempKey)!=null ? paramMap.get(tempKey): "";
			if(params.equals("")){
				params = tempKey + "=" + tempValue;
			}
			else{
				params = params + separator + tempKey + "=" + tempValue;
			}
		}
		return params;
	}
	
	
	/**
	 * @Description: MAP 按键排序
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new UfdmMapComparator());
        sortMap.putAll(map);
        return sortMap;
    }
}

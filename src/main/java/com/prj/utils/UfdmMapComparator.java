package com.prj.utils;

import java.util.Comparator;

/** 
* @Description: 比较器类
* @date 2015年11月13日 
* @author 1936
*/
public class UfdmMapComparator implements Comparator<String>{
	
	@Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
	
}

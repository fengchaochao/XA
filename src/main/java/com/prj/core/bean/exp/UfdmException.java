package com.prj.core.bean.exp;

/** 
* @Description: 业务异常
* @date 2016年1月11日 
* @author 1936
*/
public class UfdmException extends Exception{
	
	private static final long serialVersionUID = 1L;  
	  
    public UfdmException() {  
    	
    }  
  
    public UfdmException(String message) { 
        super(message);  
    }  
  
    public UfdmException(Throwable cause) { 
        super(cause);  
    }  
  
    public UfdmException(String message, Throwable cause) { 
        super(message, cause);  
    }  
}

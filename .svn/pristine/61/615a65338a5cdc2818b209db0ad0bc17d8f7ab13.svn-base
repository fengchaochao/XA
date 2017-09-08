package com.prj.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.enums.RespMessEnum;
import com.prj.core.bean.exp.UfdmException;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespBeanHead;
import com.prj.utils.UfdmJsonUtil;

/** 
* @Description: 异常处理拦截器
* @date 2016年1月11日 
* @author 1936
*/
public class ExceptionInterceptor implements HandlerExceptionResolver {

	/**
	 * @Description: 异常处理
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 * @date 2016年1月11日 
	 * @author 1936
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		try
		{
			if(ex instanceof UfdmException){
				RespBean<String> respBean = new RespBean<String>();
				RespBeanHead respBeanHead = new RespBeanHead();
				RespMessEnum [] BuszExpEnums = RespMessEnum.values();
				for(RespMessEnum buszExpEnum:BuszExpEnums){
					if(buszExpEnum.getRespCode().equals(ex.getMessage())){
						respBeanHead.setRespCode(buszExpEnum.getRespCode());
						respBeanHead.setRespContent(buszExpEnum.getRespContent());
						respBean.setHead(respBeanHead);
						break;
					}
				}
				respBean.setBody("");
				//设置状态码
				response.setStatus(HttpStatus.OK.value());
				//设置ContentType
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control","no-cache, must-revalidate");
				response.getWriter().write(UfdmJsonUtil.Object2Json(respBean));
			}
			else if(ex instanceof Exception)
			{
				ex.printStackTrace();
				RespBean<String> respBean = new RespBean<String>();
				RespBeanHead respBeanHead = new RespBeanHead();
				respBeanHead.setRespCode(RespMessEnum.RESP_CODE_9999999.getRespCode());
				respBeanHead.setRespContent(RespMessEnum.RESP_CODE_9999999.getRespContent());
				respBean.setHead(respBeanHead);
				respBean.setBody("");
				//设置状态码
				response.setStatus(HttpStatus.OK.value());
				//设置ContentType
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control","no-cache, must-revalidate");
				response.getWriter().write(UfdmJsonUtil.Object2Json(respBean));
			}
		}
		catch(Exception er){
			
		}
		return new ModelAndView();
	}

}

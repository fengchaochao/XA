package com.prj.biz.api.upfileApi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.enums.RespMessEnum;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.core.bean.exp.UfdmException;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.constant.ConfigProperties;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.UfdmStringUtil;

/**
 * @author: Fengc
 * @date:2017-8-8 上午8:32:12
 * @version :0.0.1
 * @dis:Api上传图片
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/image")
public class UpfileAction extends BaseAction{

	@Autowired
	SysUserService userService;
	

	/**
	 * 上传图片
	 * 
	 * @param  
	 * @param  
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public RespBean<Map<String,String>> handleFileUpload(MultipartFile upufdmfile) throws Exception
	{
		Map<String,String> respMap = new HashMap<String,String>();
		
		RespBean<Map<String,String>> respBean = new RespBean<Map<String,String>>();
		
		
		if (!upufdmfile.isEmpty()) {

			// 文件太大
			if(upufdmfile.getSize()>getLimitFileSize()){
				throw new UfdmException(RespMessEnum.RESP_CODE_0003000.getRespCode());
			}
			
			// 文件后缀名
			String suffix = UfdmStringUtil.getSuffixName(upufdmfile.getOriginalFilename());
			// 文件名
			String oriFileName = UfdmStringUtil.getPrefixName(upufdmfile.getOriginalFilename());
			// 不支持的后缀名
			boolean isAllSuffix = UfdmStringUtil.checkIncludeString(ConfigProperties.getUPFILE_SUFFIX(),suffix);
			if(!isAllSuffix){
				throw new UfdmException(RespMessEnum.RESP_CODE_0003001.getRespCode()); 
			}
			
			// 项目路径
			String projectPath= doGetRequst().getSession().getServletContext().getRealPath("/");
			// 文件路径
			String filePath = "/uploadfiledata" + getFilePath();
			// 绝对路径
			String absolutePath = projectPath + filePath;
			File fold = new File(absolutePath);
			if(!fold.exists()){
				fold.mkdirs();
			}
			String newFileName = getFileName();
			String absoluteFile = absolutePath + "/" + newFileName + "." + suffix; 
			
			byte[] bytes = upufdmfile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(absoluteFile)));
			stream.write(bytes);
			stream.close();

			String relativePath = filePath + "/" + newFileName + "." + suffix;
			
			respMap.put("upfileFileName", oriFileName);
			respMap.put("upfileFilePath", relativePath);
			
			respBean.setBody(respMap);
		}
		return respBean;
	}
	// 获取文件限制大小
	private long getLimitFileSize(){
		long fileSize = 1048576; // 默认1M
		String limitFileSize = UfdmStringUtil.replaceAllBlank(ConfigProperties.UPFILE_MAX_SIZE);
		if(!limitFileSize.equals("")){
			// 获取最后一个字符
			String lastChar = limitFileSize.substring(limitFileSize.length()-1).toLowerCase();
			if(lastChar.equals("m")){
				fileSize = Long.parseLong(limitFileSize.substring(0, limitFileSize.length()-1)) * 1024 * 1024;
			}
			else if(lastChar.equals("k"))
			{
				fileSize = Long.parseLong(limitFileSize.substring(0, limitFileSize.length()-1)) * 1024;
			}
			else{
				fileSize = Long.parseLong(limitFileSize);
			}
		}
		return fileSize;
	}
	
	// 获取文件路径
	private String getFilePath(){
		
		// 当前日期
		String currDate = UfdmDateUtil.dateToString(new java.util.Date(),"yyyyMMdd");
		String year = currDate.substring(0, 4);
		String month = currDate.substring(4, 6);
		String day = currDate.substring(6, 8);
		
		return "/" + year + "/" + month + "/" + day;
	}
	// 获取文件名
	private String getFileName(){
		String currDate = UfdmDateUtil.dateToString(new java.util.Date(),"yyyyMMdd");
		return currDate + "_" +UUID.randomUUID().toString();
	}

}

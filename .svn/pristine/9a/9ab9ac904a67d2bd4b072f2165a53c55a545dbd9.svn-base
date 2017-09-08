package com.prj.utils.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.prj.biz.enums.RespMessEnum;
import com.prj.core.bean.exp.UfdmException;
import com.prj.core.constant.ConfigProperties;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.UfdmStringUtil;

/**
 * @author: Fengc
 * @date:2017-7-6 上午10:44:29
 * @version :0.0.1
 * @dis:
 */
public class FileUploadUtlis {

	public String fileUpload(MultipartFile upufdmfile) throws Exception {
		
		String relativePath = "";

		if (!upufdmfile.isEmpty()) {

			// 文件太大
			if (upufdmfile.getSize() > getLimitFileSize()) {
				throw new UfdmException(
						RespMessEnum.RESP_CODE_0003000.getRespCode());
			}

			// 文件后缀名
			String suffix = UfdmStringUtil.getSuffixName(upufdmfile
					.getOriginalFilename());
			// 文件名
			String oriFileName = UfdmStringUtil.getPrefixName(upufdmfile
					.getOriginalFilename());
			// 不支持的后缀名
			boolean isAllSuffix = UfdmStringUtil.checkIncludeString(
					ConfigProperties.getUPFILE_SUFFIX(), suffix);
			if (!isAllSuffix) {
				throw new UfdmException(
						RespMessEnum.RESP_CODE_0003001.getRespCode());
			}

			// 项目路径
			String projectPath = doGetRequst().getSession().getServletContext()
					.getRealPath("/");
			// 文件路径
			String filePath = "/uploadfiledata" + getFilePath();
			// 绝对路径
			String absolutePath = projectPath + filePath;
			File fold = new File(absolutePath);
			if (!fold.exists()) {
				fold.mkdirs();
			}
			String newFileName = getFileName();
			String absoluteFile = absolutePath + "/" + newFileName + "."
					+ suffix;

			byte[] bytes = upufdmfile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(absoluteFile)));
			stream.write(bytes);
			stream.close();

			relativePath = filePath + "/" + newFileName + "." + suffix;

		}
		return relativePath;
	}

	// 获取文件限制大小
	private long getLimitFileSize() {
		long fileSize = 1048576; // 默认1M
		String limitFileSize = UfdmStringUtil
				.replaceAllBlank(ConfigProperties.UPFILE_MAX_SIZE);
		if (!limitFileSize.equals("")) {
			// 获取最后一个字符
			String lastChar = limitFileSize.substring(
					limitFileSize.length() - 1).toLowerCase();
			if (lastChar.equals("m")) {
				fileSize = Long.parseLong(limitFileSize.substring(0,
						limitFileSize.length() - 1)) * 1024 * 1024;
			} else if (lastChar.equals("k")) {
				fileSize = Long.parseLong(limitFileSize.substring(0,
						limitFileSize.length() - 1)) * 1024;
			} else {
				fileSize = Long.parseLong(limitFileSize);
			}
		}
		return fileSize;
	}

	// 获取文件路径
	private String getFilePath() {

		// 当前日期
		String currDate = UfdmDateUtil.dateToString(new java.util.Date(),
				"yyyyMMdd");
		String year = currDate.substring(0, 4);
		String month = currDate.substring(4, 6);
		String day = currDate.substring(6, 8);

		return "/" + year + "/" + month + "/" + day;
	}

	// 获取文件名
	private String getFileName() {
		String currDate = UfdmDateUtil.dateToString(new java.util.Date(),
				"yyyyMMdd");
		return currDate + "_" + UUID.randomUUID().toString();
	}

	// 获取 request
	public static HttpServletRequest doGetRequst() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}
}

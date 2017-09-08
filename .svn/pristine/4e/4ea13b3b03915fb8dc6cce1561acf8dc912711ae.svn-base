package com.prj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *	解析pdf文件
 * @author Fengc
 *
 */
public class ResolvePDFUtils {
	public static void readFdf(String file) throws Exception {
		// 是否排序
		boolean sort = false;
		// pdf文件名
		String pdfFile = file;
		// 输入文本文件名称
		String textFile = null;
		// 编码方式
		String encoding = "UTF-8";
		// 开始提取页数
		int startPage = 1;
		// 结束提取页数
		int endPage = Integer.MAX_VALUE;
		// 文件输入流，生成文本文件
		Writer output = null;
		// 内存中存储的PDF Document
		PDDocument document = null;
		try {
			try {
				// 首先当作一个URL来装载文件，如果得到异常再从本地文件系统//去装载文件
				URL url = new URL(pdfFile);
				// 注意参数已不是以前版本中的URL.而是File。
				document = PDDocument.load(pdfFile);
				// 获取PDF的文件名
				String fileName = url.getFile();
				// 以原来PDF的名称来命名新产生的txt文件
				if (fileName.length() > 4) {
					File outputFile = new File(fileName.substring(0,
							fileName.length() - 4)
							+ ".txt");
					textFile = outputFile.getName();
				}
			} catch (MalformedURLException e) {
				// 如果作为URL装载得到异常则从文件系统装载
				// 注意参数已不是以前版本中的URL.而是File。
				document = PDDocument.load(pdfFile);
				if (pdfFile.length() > 4) {
					textFile = pdfFile.substring(0, pdfFile.length() - 4)
							+ ".txt";
				}
			}
			// 文件输入流，写入文件倒textFile
			output = new OutputStreamWriter(new FileOutputStream(textFile),
					encoding);
			// PDFTextStripper来提取文本
			PDFTextStripper stripper = null;
			stripper = new PDFTextStripper();
			// 设置是否排序
			stripper.setSortByPosition(sort);
			// 设置起始页
			stripper.setStartPage(startPage);
			// 设置结束页
			stripper.setEndPage(endPage);
			// 调用PDFTextStripper的writeText提取并输出文本
			stripper.writeText(document, output);
		} finally {
			if (output != null) {
				// 关闭输出流
				output.close();
			}
			if (document != null) {
				// 关闭PDF Document
				document.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			readFdf("E:\\OrderAcknowledgement.PDF");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
/*
		String filePath = "E:\\OrderAcknowledgement.txt";
		readTxtFile(filePath);*/

	}

	public static void readTxtFile(String filePath) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String flag = "0";
				StringBuffer buffer = new StringBuffer();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (flag == "1") {
						if ("Remarks:".equals(lineTxt)) {
							break;
						}
						buffer.append(lineTxt);
						buffer.append("#");
					} else if ("Quantity".equals(lineTxt)) {
						flag = "1";
						continue;
					} else {
						continue;
					}

				}
				//System.out.println(buffer.toString());
				String data = buffer.toString();
				String[] ds = data.split("#");
				for (int i = 0; i < ds.length; i++) {
					if (i % 2 == 0) {
						System.out.println(ds[i]);	
					}

				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}

}

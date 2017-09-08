package com.prj.utils.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.prj.utils.UfdmDateUtil;


/**
 * 生成excel
 * @author libiwei;
 * 2015-4-1 14:39
 */
@SuppressWarnings("deprecation")
public class ReportToExcel<T>
{
 public void exportExcel(String title,String[] headers, Collection<T> dataset, String path,String name,
       OutputStream out) {
      try {
		exportExcel("POI导出EXCEL文档",title, headers, dataset, path, name,out,"yyyy-MM-dd");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }

// public void exportExcel(String[] headers, Collection<T> dataset,
//       OutputStream out, String pattern) {
//    exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
// }
 /**
  * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
  *
  * @param title
  *            表格标题名
  * @param headers
  *            表格属性列名数组
  * @param dataset
  *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
  *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
  * @param out
  *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
  * @param pattern
  *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
 * @throws IOException 
  */
 @SuppressWarnings({ "rawtypes", "unchecked" })
 public void exportExcel(String title,String titles, String[] headers,
       Collection<T> dataset,String path, String name,OutputStream out, String pattern) throws IOException {
    // 声明一个工作薄
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 生成一个表格
    HSSFSheet sheet = workbook.createSheet(title);
    // 设置表格默认列宽度为15个字节
    sheet.setDefaultColumnWidth((short) 15);
    // 生成一个样式
    HSSFCellStyle style = workbook.createCellStyle();
    // 设置这些样式
    style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 生成一个字体
    HSSFFont font = workbook.createFont();
    font.setColor(HSSFColor.VIOLET.index);
    font.setFontHeightInPoints((short) 12);
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // 把字体应用到当前的样式
    style.setFont(font);
    // 生成并设置另一个样式
      HSSFCellStyle style2 = workbook.createCellStyle();
//    style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//    style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//    style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//    style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    // 生成另一个字体
    HSSFFont font2 = workbook.createFont();
    font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
    // 把字体应用到当前的样式
    style2.setFont(font2);
    // 声明一个画图的顶级管理器
    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
    // 定义注释的大小和位置,详见文档
    HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
    // 设置注释内容
    comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
    // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
    comment.setAuthor("myc");
    //产生表格标题行
    HSSFRow row = sheet.createRow(0);
    if(titles.equals("管道预制深度报表")||titles.equals("Pipe Fabrication Rate Report")){
        HSSFRow row1=sheet.createRow((short) 1);
    	HSSFCell cell1=null;
    	//第0列
    	cell1 = row.createCell((short) 0);  
    	cell1.setCellStyle(style);
        cell1.setCellValue(headers[0]);  
        //合并单元格
    	sheet.addMergedRegion(new Region((short)0, (short)0, (short)1, (short)0));//合并从第0行开始,从第0列开始，到第0个行，第n列
    	
    	//第1列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0, (short)1, (short)1, (short)1));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 1);  
        cell1.setCellValue(headers[1]);  
        cell1.setCellStyle(style);
        
        //第2列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)2,(short)1,(short)2));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 2);  
        cell1.setCellValue(headers[2]);  
        cell1.setCellStyle(style);
        
        //第3列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)3,(short)1,(short)3));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 3);  
        cell1.setCellValue(headers[3]);  
        cell1.setCellStyle(style);
        //第4列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)4,(short)1,(short)4));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 4);  
        cell1.setCellValue(headers[4]);  
        cell1.setCellStyle(style);
        //第5列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)5,(short)0,(short)6));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 5);  
        HSSFCell cell2 = row1.createCell((short) 5);  
        cell2.setCellValue(headers[10]);
        cell2.setCellStyle(style);
        HSSFCell cell3 = row1.createCell((short) 6);  
        cell3.setCellValue(headers[11]);
        cell3.setCellStyle(style);
        cell1.setCellValue(headers[5]);  
        cell1.setCellStyle(style);
        //第6列
        //合并单元格
//        HSSFRow row2=sheet.createRow((short) 1);
        sheet.addMergedRegion(new Region((short)0,(short)7,(short)0,(short)8));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 7);  
        HSSFCell cell5 = row1.createCell((short) 7);  
        cell5.setCellValue(headers[12]);
        cell5.setCellStyle(style);
        HSSFCell cell6 = row1.createCell((short) 8);  
        cell6.setCellValue(headers[13]);
        cell6.setCellStyle(style);
        cell1.setCellValue(headers[6]);  
        cell1.setCellStyle(style);
        //第7列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)9,(short)0,(short)10));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 9);  
        HSSFCell cell7 = row1.createCell((short) 9);  
        cell7.setCellValue(headers[14]);
        cell7.setCellStyle(style);
        HSSFCell cell8 = row1.createCell((short) 10);  
        cell8.setCellValue(headers[15]);
        cell8.setCellStyle(style);
        cell1.setCellValue(headers[7]);  
        cell1.setCellStyle(style);
        //第8列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)11,(short)0,(short)12));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 11);  
        HSSFCell cell9 = row1.createCell((short) 11);  
        cell9.setCellValue(headers[16]);
        cell9.setCellStyle(style);
        HSSFCell cell10 = row1.createCell((short) 12);  
        cell10.setCellValue(headers[17]);
        cell10.setCellStyle(style);
        cell1.setCellValue(headers[8]);  
        cell1.setCellStyle(style);
        //第9列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)13,(short)1,(short)13));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 13);  
        cell1.setCellValue(headers[9]);  
        cell1.setCellStyle(style);
    }
    if(titles.equals("管段预制状态报告")||titles.equals("SpoolFab Status Report")){
        HSSFRow row1=sheet.createRow((short) 1);
    	HSSFCell cell1=null;
    	//第0列
    	cell1 = row.createCell((short) 0);  
    	cell1.setCellStyle(style);
        cell1.setCellValue(headers[0]);  
        //合并单元格
    	sheet.addMergedRegion(new Region((short)0, (short)0, (short)1, (short)0));//合并从第0行开始,从第0列开始，到第0个行，第n列
    	
    	//第1列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0, (short)1, (short)1, (short)1));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 1);  
        cell1.setCellValue(headers[1]);  
        cell1.setCellStyle(style);
        
        
        //第2列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)2,(short)1,(short)2));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 2);  
        cell1.setCellValue(headers[2]);  
        cell1.setCellStyle(style);
        //第3列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)3,(short)0,(short)4));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 3);  
        cell1.setCellValue(headers[3]);  
        cell1.setCellStyle(style);
        HSSFCell cell4 = row1.createCell((short) 3);  
        cell4.setCellValue(headers[7]);  
        cell4.setCellStyle(style);
        HSSFCell cell5 = row1.createCell((short) 4);  
        cell5.setCellValue(headers[8]);  
        cell5.setCellStyle(style);
        //第4列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)5,(short)0,(short)7));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 5);  
        cell1.setCellValue(headers[4]);  
        cell1.setCellStyle(style);
        HSSFCell cell7 = row1.createCell((short) 5);  
        cell7.setCellValue(headers[9]);  
        cell7.setCellStyle(style);
        HSSFCell cell8 = row1.createCell((short) 6);  
        cell8.setCellValue(headers[10]); 
        cell8.setCellStyle(style);
        HSSFCell cell9 = row1.createCell((short) 7);  
        cell9.setCellValue(headers[11]);  
        cell9.setCellStyle(style);
        //第5列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)8,(short)0,(short)10));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 8);  
        cell1.setCellValue(headers[5]);  
        cell1.setCellStyle(style);
        HSSFCell cell10= row1.createCell((short) 8);  
        cell10.setCellValue(headers[12]);
        cell10.setCellStyle(style);
        HSSFCell cell11 = row1.createCell((short) 9);  
        cell11.setCellValue(headers[13]);
        cell11.setCellStyle(style);
        HSSFCell cell12 = row1.createCell((short) 10);  
        cell12.setCellValue(headers[14]);
        cell12.setCellStyle(style);
        //第6列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)11,(short)0,(short)13));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 11);  
        cell1.setCellValue(headers[6]);  
        cell1.setCellStyle(style);
        HSSFCell cell13= row1.createCell((short) 11);  
        cell13.setCellValue(headers[15]);
        cell13.setCellStyle(style);
        HSSFCell cell14 = row1.createCell((short) 12);  
        cell14.setCellValue(headers[16]);
        cell14.setCellStyle(style);
        HSSFCell cell15 = row1.createCell((short) 13);  
        cell15.setCellValue(headers[17]);
        cell15.setCellStyle(style);
    }
    if(titles.equals("焊缝预制状态报告")||titles.equals("Joint Fabrication Status Report")){
        HSSFRow row1=sheet.createRow((short) 1);
        HSSFRow row2=sheet.createRow((short) 2);
    	HSSFCell cell1=null;
    	//第0列
    	cell1 = row.createCell((short) 0);  
    	cell1.setCellStyle(style);
        cell1.setCellValue(headers[0]);  
        //合并单元格
    	sheet.addMergedRegion(new Region((short)0, (short)0, (short)2, (short)0));//合并从第0行开始,从第0列开始，到第0个行，第n列
    	
    	//第1列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0, (short)1, (short)2, (short)1));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 1);  
        cell1.setCellValue(headers[1]);  
        cell1.setCellStyle(style);
        
        //第2列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)2,(short)0,(short)7));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 2);  
        cell1.setCellValue(headers[2]);  
        cell1.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)2,(short)2,(short)2));
        HSSFCell cell2=row1.createCell((short) 2);
        cell2.setCellValue(headers[6]);  
        cell2.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)3,(short)2,(short)3));
        HSSFCell cell3=row1.createCell((short) 3);
        cell3.setCellValue(headers[7]);  
        cell3.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)4,(short)1,(short)5));
        HSSFCell cell4=row1.createCell((short) 4);
        cell4.setCellValue(headers[8]);  
        cell4.setCellStyle(style);
        HSSFCell cell5=row2.createCell((short) 4);
        cell5.setCellValue(headers[22]);  
        cell5.setCellStyle(style);
        HSSFCell cell6=row2.createCell((short) 5);
        cell6.setCellValue(headers[23]);  
        cell6.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)6,(short)1,(short)7));
        HSSFCell cell7=row1.createCell((short) 6);
        cell7.setCellValue(headers[9]);  
        cell7.setCellStyle(style);
        HSSFCell cell8=row2.createCell((short) 6);
        cell8.setCellValue(headers[24]);  
        cell8.setCellStyle(style);
        HSSFCell cell9=row2.createCell((short) 7);
        cell9.setCellValue(headers[25]);  
        cell9.setCellStyle(style);
//        //第3列
//        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)8,(short)0,(short)13));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 8);  
        cell1.setCellValue(headers[3]);  
        cell1.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)8,(short)2,(short)8));
        HSSFCell cell10=row1.createCell((short) 8);
        cell10.setCellValue(headers[10]);  
        cell10.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)9,(short)2,(short)9));
        HSSFCell cell11=row1.createCell((short) 9);
        cell11.setCellValue(headers[11]);  
        cell11.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)10,(short)1,(short)11));
        HSSFCell cell12=row1.createCell((short) 10);
        cell12.setCellValue(headers[12]);  
        cell12.setCellStyle(style);
        HSSFCell cell13=row2.createCell((short) 10);
        cell13.setCellValue(headers[26]);  
        cell13.setCellStyle(style);
        HSSFCell cell14=row2.createCell((short) 11);
        cell14.setCellValue(headers[27]);  
        cell14.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)12,(short)1,(short)13));
        HSSFCell cel15=row1.createCell((short) 12);
        cel15.setCellValue(headers[13]);  
        cel15.setCellStyle(style);
        HSSFCell cell16=row2.createCell((short) 12);
        cell16.setCellValue(headers[28]);  
        cell16.setCellStyle(style);
        HSSFCell cell17=row2.createCell((short) 13);
        cell17.setCellValue(headers[29]);  
        cell17.setCellStyle(style);
//        //第4列
//        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)14,(short)0,(short)19));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 14);  
        cell1.setCellValue(headers[4]);  
        cell1.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)14,(short)2,(short)14));
        HSSFCell cell18=row1.createCell((short) 14);
        cell18.setCellValue(headers[14]);  
        cell18.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)15,(short)2,(short)15));
        HSSFCell cell19=row1.createCell((short) 15);
        cell19.setCellValue(headers[15]);  
        cell19.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)16,(short)1,(short)17));
        HSSFCell cell20=row1.createCell((short) 16);
        cell20.setCellValue(headers[16]);  
        cell20.setCellStyle(style);
        HSSFCell cell21=row2.createCell((short) 16);
        cell21.setCellValue(headers[30]);  
        cell21.setCellStyle(style);
        HSSFCell cell22=row2.createCell((short) 17);
        cell22.setCellValue(headers[31]);  
        cell22.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)18,(short)1,(short)19));
        HSSFCell cel123=row1.createCell((short) 18);
        cel123.setCellValue(headers[17]);  
        cel123.setCellStyle(style);
        HSSFCell cell24=row2.createCell((short) 18);
        cell24.setCellValue(headers[32]);  
        cell24.setCellStyle(style);
        HSSFCell cell25=row2.createCell((short) 19);
        cell25.setCellValue(headers[33]);  
        cell25.setCellStyle(style);
//        //第5列
//        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)20,(short)0,(short)25));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 20);  
        cell1.setCellValue(headers[5]);  
        cell1.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)20,(short)2,(short)20));
        HSSFCell cell26=row1.createCell((short) 20);
        cell26.setCellValue(headers[18]);  
        cell26.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)21,(short)2,(short)21));
        HSSFCell cell27=row1.createCell((short) 21);
        cell27.setCellValue(headers[19]);  
        cell27.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)22,(short)1,(short)23));
        HSSFCell cell28=row1.createCell((short) 22);
        cell28.setCellValue(headers[20]);  
        cell28.setCellStyle(style);
        HSSFCell cell29=row2.createCell((short)22);
        cell29.setCellValue(headers[34]);  
        cell29.setCellStyle(style);
        HSSFCell cell30=row2.createCell((short) 23);
        cell30.setCellValue(headers[35]);  
        cell30.setCellStyle(style);
        sheet.addMergedRegion(new Region((short)1,(short)24,(short)1,(short)25));
        HSSFCell cel131=row1.createCell((short) 24);
        cel131.setCellValue(headers[21]);  
        cel131.setCellStyle(style);
        HSSFCell cell32=row2.createCell((short) 24);
        cell32.setCellValue(headers[36]);  
        cell32.setCellStyle(style);
        HSSFCell cell33=row2.createCell((short) 25);
        cell33.setCellValue(headers[37]);  
        cell33.setCellStyle(style);
    }if(titles.equals("管段出入库状态报告")||titles.equals("Spool In & Out Storage Status Report")){
        HSSFRow row1=sheet.createRow((short) 1);
    	HSSFCell cell1=null;
    	//第0列
    	cell1 = row.createCell((short) 0);  
    	cell1.setCellStyle(style);
        cell1.setCellValue(headers[0]);  
        //合并单元格
    	sheet.addMergedRegion(new Region((short)0, (short)0, (short)1, (short)0));//合并从第0行开始,从第0列开始，到第0个行，第n列
    	
    	//第1列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0, (short)1, (short)1, (short)1));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 1);  
        cell1.setCellValue(headers[1]);  
        cell1.setCellStyle(style);
        
        
        //第2列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)2,(short)0,(short)3));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 2);  
        cell1.setCellValue(headers[2]);  
        cell1.setCellStyle(style);
        HSSFCell cell2=row1.createCell((short) 2);
        cell2.setCellValue(headers[6]);
        cell2.setCellStyle(style);
        HSSFCell cell3=row1.createCell((short) 3);
        cell3.setCellValue(headers[7]);
        cell3.setCellStyle(style);
        //第3列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)4,(short)0,(short)7));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 4);  
        cell1.setCellValue(headers[3]);  
        cell1.setCellStyle(style);
        HSSFCell cell4 = row1.createCell((short) 4);  
        cell4.setCellValue(headers[8]);  
        cell4.setCellStyle(style);
        HSSFCell cell5 = row1.createCell((short) 5);  
        cell5.setCellValue(headers[9]);  
        cell5.setCellStyle(style);
        HSSFCell cell6 = row1.createCell((short) 6);  
        cell6.setCellValue(headers[10]);  
        cell6.setCellStyle(style);
        HSSFCell cell7 = row1.createCell((short) 7);  
        cell7.setCellValue(headers[11]);  
        cell7.setCellStyle(style);
        //第4列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0,(short)8,(short)0,(short)11));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 8);  
        cell1.setCellValue(headers[4]);  
        cell1.setCellStyle(style);
        HSSFCell cell8 = row1.createCell((short) 8);  
        cell8.setCellValue(headers[12]);  
        cell8.setCellStyle(style);
        HSSFCell cell9 = row1.createCell((short) 9);  
        cell9.setCellValue(headers[13]); 
        cell9.setCellStyle(style);
        HSSFCell cell10 = row1.createCell((short) 10);  
        cell10.setCellValue(headers[14]);  
        cell10.setCellStyle(style);
        HSSFCell cell11 = row1.createCell((short) 11);  
        cell11.setCellValue(headers[15]);  
        cell11.setCellStyle(style);
    	//第5列
        //合并单元格
        sheet.addMergedRegion(new Region((short)0, (short)12, (short)1, (short)12));//合并从第0行开始,从第0列开始，到第0个行，第n列
        cell1 = row.createCell((short) 12);  
        cell1.setCellValue(headers[5]);  
        cell1.setCellStyle(style);
    }
    System.out.println(titles);
    if(titles.equals("项目区域工作估算")||titles.equals("Project Area Work Estimation")){
        for (short i = 0; i< headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
           }
    }
   if(headers.length<16){
     for (short i = 0; i< headers.length; i++) {
       HSSFCell cell = row.createCell(i);
       cell.setCellStyle(style);
       HSSFRichTextString text = new HSSFRichTextString(headers[i]);
       cell.setCellValue(text);
      }
    }
      //遍历集合数据，产生数据行
    Iterator<T> it = dataset.iterator();
    int index = 0;
    if(titles.equals("管道预制深度报表")||titles.equals("Pipe Fabrication Rate Report")){
    	   index = 1;
    }
    if(titles.equals("管段预制状态报告")||titles.equals("SpoolFab Status Report")){
    	index = 1;
    }if(titles.equals("焊缝预制状态报告")||titles.equals("Joint Fabrication Status Report")){
    	index = 2;
    }
    if(titles.equals("管段出入库状态报告")||titles.equals("Spool In & Out Storage Status Report")){
    	index = 1;
    }
    if(titles.equals("项目区域工作估算")||titles.equals("Project Area Work Estimation")){
    	index = 0;
    }
    if(headers.length<16){
    	 index = 0;
    }
    while (it.hasNext()) {
       index++;
       row = sheet.createRow(index);
       T t = (T) it.next();//Book.class
       //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
       //T  ----Book 
       Field[] fields = t.getClass().getDeclaredFields();
       for (short i = 0; i < fields.length; i++) {
          HSSFCell cell = row.createCell(i);
          cell.setCellStyle(style2);
          Field field = fields[i];
          String fieldName = field.getName();//属性名 name   getName();
          String getMethodName = "get"   
                 + fieldName.substring(0, 1).toUpperCase()
                 + fieldName.substring(1);//getName()
          try {
          	// 获取Class实例对象

			Class tCls = t.getClass();
          	//动态方法调用
              Method getMethod = tCls.getMethod(getMethodName,
                    new Class[] {});
              Object value = getMethod.invoke(t, new Object[] {});
              //判断值的类型后进行强制类型转换
              String textValue = null;
//            if (value instanceof Integer) {
//               int intValue = (Integer) value;
//               cell.setCellValue(intValue);
//            } else if (value instanceof Float) {
//               float fValue = (Float) value;
//               textValue = new HSSFRichTextString(
//                     String.valueOf(fValue));
//               cell.setCellValue(textValue);
//            } else if (value instanceof Double) {
//               double dValue = (Double) value;
//               textValue = new HSSFRichTextString(
//                     String.valueOf(dValue));
//               cell.setCellValue(textValue);
//            } else if (value instanceof Long) {
//               long longValue = (Long) value;
//               cell.setCellValue(longValue);
//            }
              if (value instanceof Boolean) {
                 boolean bValue = (Boolean) value;
                 textValue = "男";
                 if (!bValue) {
                    textValue ="女";
                 }
              } else if (value instanceof Date) {
                 Date date = (Date) value;
                 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                  textValue = sdf.format(date);
              }  else if (value instanceof byte[]) {
                 // 有图片时，设置行高为60px;
                 row.setHeightInPoints(60);
                 // 设置图片所在列宽度为80px,注意这里单位的一个换算
                 sheet.setColumnWidth(i, (short) (35.7 * 80));
                 // sheet.autoSizeColumn(i);
                 byte[] bsValue = (byte[]) value;
                 HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                       1023, 255, (short) 6, index, (short) 6, index);
                 anchor.setAnchorType(2);
                 patriarch.createPicture(anchor, workbook.addPicture(
                       bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
              } else{
                 //其它数据类型都当作字符串简单处理
                 textValue = value.toString();
              }
              //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
              //正则表达式：
                //\d:  数字        \s:空白（空格 \t  \n）         \w:字母和数字和$    .任意字符    [a-z]  [A-Z]  [0-9]  [A-D]  [1234] [1-4]
              // 数量修饰：  +:1次以上      *：0次以上          ?:0次或1次   {m,n}    {m,}   {m}
             
             //  ^: 开始匹配                    $:结束匹配
              //常用正则表达式： 邮政编码:\d{6}    手机号：  \d{11}    座机号: \d{3,4}[-]\d{7,8}
              // 身份证号码:  \d{15}(\d{2}[0-9xX])? 
              //邮箱:  \w+@\w{2,5}(\.[a-z]{2,3}){1,2}
              if(textValue!=null){
                 Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                 Matcher matcher = p.matcher(textValue);
                 if(matcher.matches()){
                    //是数字当作double处理
                    cell.setCellValue(Double.parseDouble(textValue));
                 }else{
                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    HSSFFont font3 = workbook.createFont();
                    font3.setColor(HSSFColor.BLUE.index);
                    richString.applyFont(font3);
                    cell.setCellValue(richString);
                 }
              }
          } catch (SecurityException e) {
              e.printStackTrace();
          } catch (NoSuchMethodException e) {
              e.printStackTrace();
          } catch (IllegalArgumentException e) {
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          } catch (InvocationTargetException e) {
              e.printStackTrace();
          } finally {
              //清理资源
          }
       }
    try {
       workbook.write(out);
    } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
    }

 }
    
// 第六步，将文件存到指定位置  
File f1 = new File(path+"/uploadfiledata/");
if(!f1.exists())
{
	f1.mkdir();
}
File f2 = new File(f1,name+".xls");
if(!f2.exists())
{
	f2.createNewFile();
}
FileOutputStream fout = new FileOutputStream(f2);  
workbook.write(fout);  
fout.close();  
  }
 
}

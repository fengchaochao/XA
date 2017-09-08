package com.prj.utils.pay;


import java.io.BufferedReader;  
import java.io.ByteArrayInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStream;  
import java.net.ConnectException;  
import java.net.HttpURLConnection;  
import java.net.InetAddress;  
import java.net.URL;  
import java.security.KeyStore;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.SortedMap;  
import java.util.TreeMap;  
  
import javax.net.ssl.SSLContext;  
  
import org.apache.http.Consts;  
import org.apache.http.HttpEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.util.EntityUtils;  
import org.jdom.Document;  
import org.jdom.Element;  
import org.jdom.input.SAXBuilder;
/**
 * @author: Fengc
 * @date:2017-9-1 下午5:07:25
 * @version :0.0.1
 * @dis:
 */
public class WXRequestUtil {  
    private static String WXSign = null;  
//  //测试  
    public static void main(String[] args) {  
        //Map<String,String> res = SendPayment("苹果","20170106113325",1,0);  
    }  
      
      
    /** 
     * 获取签名 
     * @return 
     */  
    public static String getWXSign() {  
        return WXSign;  
    }  
  

      
    /** 
     * 获取时间戳 
     */  
    public static String GetTimeStamp(){  
        int t = (int)(System.currentTimeMillis()/1000);  
        return t+"";  
    }  
      
      
    /** 
     *  获取用户的ip 
     *  
     */  
     public static String GetIp() {  
        InetAddress ia=null;  
        try {  
            ia=InetAddress.getLocalHost();  
            String localip=ia.getHostAddress();  
            return localip;  
        } catch (Exception e) {  
            return null;  
        }  
    }  
       

     /** 
      *  
      * Map转xml数据 
      */  
     public static String GetMapToXML(Map<String,String> param){  
         StringBuffer sb = new StringBuffer();  
         sb.append("<xml>");  
         for (Map.Entry<String,String> entry : param.entrySet()) {   
                sb.append("<"+ entry.getKey() +">");  
                sb.append(entry.getValue());  
                sb.append("</"+ entry.getKey() +">");  
        }    
         sb.append("</xml>");  
         return sb.toString();  
     }  
      
      
   
    //xml解析    
    public static Map<String, String> doXMLParse(String strxml) throws Exception {    
          strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");    
          if(null == strxml || "".equals(strxml)) {    
              return null;    
          }    
              
          Map<String,String> m = new HashMap<String,String>();     
          InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));    
          SAXBuilder builder = new SAXBuilder();    
          Document doc = builder.build(in);    
          Element root = doc.getRootElement();    
          List list = root.getChildren();    
          Iterator it = list.iterator();    
          while(it.hasNext()) {    
              Element e = (Element) it.next();    
              String k = e.getName();    
              String v = "";    
              List children = e.getChildren();    
              if(children.isEmpty()) {    
                  v = e.getTextNormalize();    
              } else {    
                  v = getChildrenText(children);    
              }    
                  
              m.put(k, v);    
          }    
              
          //关闭流    
          in.close();     
          return m;    
    }    
      
    //xml解析    
    public static SortedMap<String, String> doXMLParseWithSorted(String strxml) throws Exception {    
          strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");    
          if(null == strxml || "".equals(strxml)) {    
              return null;    
          }    
              
          SortedMap<String,String> m = new TreeMap<String,String>();     
          InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));    
          SAXBuilder builder = new SAXBuilder();    
          Document doc = builder.build(in);    
          Element root = doc.getRootElement();    
          List list = root.getChildren();    
          Iterator it = list.iterator();    
          while(it.hasNext()) {    
              Element e = (Element) it.next();    
              String k = e.getName();    
              String v = "";    
              List children = e.getChildren();    
              if(children.isEmpty()) {    
                  v = e.getTextNormalize();    
              } else {    
                  v = getChildrenText(children);    
              }    
                  
              m.put(k, v);    
          }    
              
          //关闭流    
          in.close();     
          return m;    
    }    
        
    public static String getChildrenText(List children) {    
          StringBuffer sb = new StringBuffer();    
          if(!children.isEmpty()) {    
              Iterator it = children.iterator();    
              while(it.hasNext()) {    
                  Element e = (Element) it.next();    
                  String name = e.getName();    
                  String value = e.getTextNormalize();    
                  List list = e.getChildren();    
                  sb.append("<" + name + ">");    
                  if(!list.isEmpty()) {    
                      sb.append(getChildrenText(list));    
                  }    
                  sb.append(value);    
                  sb.append("</" + name + ">");    
              }    
          }     
          return sb.toString();    
    }  
}  
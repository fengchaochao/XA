package com.prj.utils.excel;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class IDUtils {
	private final static SimpleDateFormat sdfDayshms = new SimpleDateFormat("yyMMddhhmmss");
	
	/**
	 * 得到八位的id
	 */
	public static String get8UID(){
			String str = "";
 			for(int i = 0; i < 8; i++){
				str += (int)(Math.random()*10);
			}
		return str;
	}
	
	/**
	 * 得到四位的id
	 */
	public static String get4UID(){
			String str = "";
 			for(int i = 0; i < 4; i++){
				str += (int)(Math.random()*10);
			}
		return str;
	}
	
	/**
	 * 得到十位的id
	 */
	public static String get10UID(){
			String str = "";
 			for(int i = 0; i < 10; i++){
				str += (int)(Math.random()*10);
			}
		return str;
	}
	
	/**
	 * 得到随机九位字母
	 */
	public String get9ZM(){
		String chars = "abcdefghijklmnopqrstuvwxyz";
 		String str = "";
 		for(int i = 0; i < 9; i++){
			str += chars.charAt((int)(Math.random() * 26));
		}
		return str;
	}
	
	/**
	 * 得到随机1位字母
	 */
	public static String get1ZM(){
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String str = "";
		for(int i = 0; i < 1; i++){
			str += chars.charAt((int)(Math.random() * 26));
		}
		return str;
	}
	
	/**
	 * 得到随机4位字母或数字
	 */
	public String get4ZMSZ(){
		String chars = "abcdefghijklmnopqrstuvwxyz12345678901234567890";
 		String str = "";
 		for(int i = 0; i < 4; i++){
			str += chars.charAt((int)(Math.random() * 46));
		}
		return str;
	}
	
	/**
	 * 得到时间类型id
	 */
	public static String getTimeID(){
			String str = getDayshms()+  (int)((Math.random()*9+1)*1000);
		return str;
	}
	
	/**
	 * 得到6位的id
	 */
	public static String get6UID(){
			String str = "";
			str += (int)(Math.random()*9+1);
			for(int i = 0; i < 5; i++){
				str += (int)(Math.random()*10);
			}
		return str;
	}
	
	/**
	 * 推广记录单号
	 */
	public static String getTGJL(){
			String str = "";
			String da = getDayshms();
			int num = (int)((Math.random()*9+1)*10);
			//组合为编号
			str = "TGJL"+da+""+num;
		return str;
	}
	
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public static String get32UUID(){
		return UuidUtil.get32UUID();
	}

	/**
	 * 获取yyyyMMddhhmmss格式
	 * 
	 * @return
	 */
	public static String getDayshms(){
		return sdfDayshms.format(new Date());
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();	
		return request;
	}
	
	
	//获取项目的绝对路径
	@SuppressWarnings("deprecation")
	public String getCurrentPath(){
  		return  this.getRequest().getRealPath("/");//获取文件跟补录
	}
	
	//获取本机ip
	public static String getIp(){
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
			//System.out.println("======"+ip+"======");
 		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
	
	 /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    public static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                                System.out.println(ipaddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("获取ip地址异常");
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        System.out.println("IP:"+ip);
        return ip;
    }
	
}

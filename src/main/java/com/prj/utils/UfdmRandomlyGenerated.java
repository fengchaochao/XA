package com.prj.utils;

import java.util.Random;

public class UfdmRandomlyGenerated {
	/**
	 * 描述: 随机生成指定位数的随机数（包含字母和数字）
	 * @auther Liang
	 * @date 2017-07-10
	 * @return
	 * @throws Exception
	 */
	 public static String getStringRandom(int length) {
			String val = "";
			Random random = new Random();
			//参数length，表示生成几位随机数
			for(int i = 0; i < length; i++) {
				String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
				//输出字母还是数字
				if( "char".equalsIgnoreCase(charOrNum) ) {
					//输出是大写字母还是小写字母
					int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
					val += (char)(random.nextInt(26) + temp);
				} else if( "num".equalsIgnoreCase(charOrNum) ) {
					val += String.valueOf(random.nextInt(10));
				}
			}
			return val;
     }
	/**
	 * 
     * 随机生成4位随机验证码
     * @param num 创建验证码位数
     */
    public static String createRandomVcode(int num){
        //验证码
        String vcode = "" ;
        for (int i = 0; i < num; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
    public static void main(String[] args) {
    	System.out.println(createRandomVcode(6));
	}
}

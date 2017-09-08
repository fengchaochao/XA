package com.prj.utils.encryption;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import com.prj.utils.ping.PayConfiger;

/**
 * @author: Fengc
 * @date:2017-8-25 下午4:26:04
 * @version :0.0.1
 * @dis:支付回调加密
 */
public class ShiroEncryption {

	public static String encryption(String orderNo, String publicKey) {
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("SHA-1024");
		hashService.setPrivateSalt(new SimpleByteSource(publicKey)); // 私盐，默认无
		hashService.setGeneratePublicSalt(true);// 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// 用于生成公盐。
		hashService.setHashIterations(1); // 生成Hash值的迭代次数
		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes(orderNo))
				.setSalt(ByteSource.Util.bytes("1024")).setIterations(10)
				.build();
		String hex = hashService.computeHash(request).toHex();
		return hex;
	}

	public static boolean validate(String body, String orderNo) {
		String hex = encryption(orderNo, PayConfiger.hookspublickey);
		String hexo = body.substring(body.indexOf("cm:")+3, body.length());
		if (hex.equals(hexo)) {
			return true;
		} else {
			return false;
		}
	}
	public static void main(String[] args) {
		boolean isPass = validate("该商品已经购买谢谢cm:80df391f9ba73ab3e9fd3cd0e7f8e019", "123456");
		System.out.println(isPass);
	}
}

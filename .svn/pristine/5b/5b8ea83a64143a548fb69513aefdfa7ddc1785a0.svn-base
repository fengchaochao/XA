package tutorial.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

/** 
* @Description: shiro 秘钥系统
* @date 2016年7月28日 
* @author 1936
*/
public class Encrypt {

	public static void main(String[] args) throws Exception {
		
		String base64Str = "ufdmpass12345678";  
		String base64Encoded = Base64.encodeToString(base64Str.getBytes());  
		System.out.println("base64 加密:"+base64Encoded);
		String decodeStr = Base64.decodeToString(base64Encoded); 
		System.out.println("base64 解密:"+decodeStr);
		
		
		String hexstr = "你好";  
		String hexEncoded = Hex.encodeToString(hexstr.getBytes());  
		System.out.println("十六进制 加密:"+hexEncoded);
		String decodeHexStr = new String(Hex.decode(hexEncoded.getBytes()));  
		System.out.println("十六进制 解密:"+decodeHexStr);
		
		
		String str = "hello";  
		String salt = "123";  
		
		System.out.println("\n decode:"+new String(Base64.decode("4AvVhmFLUs0KTA3Kprsdag==")));
		
		// SHA-1,SHA-512,MD5
		SimpleHash simpleHash = new SimpleHash("SHA-512", str, salt);   
		
		System.out.println(simpleHash.toString());
		System.out.println(simpleHash.toBase64());
		System.out.println(simpleHash.toHex());
		
		AesCipherService aesCipherService = new AesCipherService();  
		aesCipherService.setKeySize(128); //设置key长度  
		//生成key  
		//Key key = aesCipherService.generateNewKey();  
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");  
		keyGenerator.init(128, new SecureRandom("ufdmpass12345678".getBytes()));
		Key key = keyGenerator.generateKey();
        
		String text = "qazxswed";  
		//加密  
		String encryptText =   aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();  
		
		encryptText = "LCJB++toANNA/hlpe3TbbvRW8u8XFcFeurk1bw1tUmrsICVVt0x8kF6AzHI3LvTDIdKrwx6dpG1YjbM+NYMke++qMmz/iWCVYUGRAsJFOVc0WhCvYbNgUKB/oPudS+dc1YTAY9WtzuCCEayzsEFd4c0grxyWVihDfsIGqYJxE+9NiIiKnb8OQH3ssRZb1IicKeCV3sOp2Jkg1Y+n8EklHxSba1tdAg1Dw1JwxgRHUJOqd0A3RcW9MMLFWi+3YHqkUgxTMBN5InMKDxDVhn4uOveqPRQLJrWdl/U+OiU/PjxCMxkm4+k1ZmdX6NxhX8BOQjtKJXNkZ+HcTxPK1O49ZxklYFtw7jBGbzHsCx4kVZ0GvkQzDm73O8mJcLI0Hit931jPI3f8WREIcorm+1Cwt4XeqD8K4tfwy+p3cvUN5EE+Olv2CJXfSIc40moRBjdJSXp5iK1l0nK+/7kSSUn3U0J95ok8sZouqyX6SYvUIS9HLx8wLI29uLsznMeSClXPiktiVwI5AkCqwOcS9GKoiYVDPlPPrLzsjJN5awjxo/c=";
		System.out.println("AES 加密："+encryptText);
		//解密  
		String decrypt =  new String(aesCipherService.decrypt(Base64.decode(encryptText), key.getEncoded()).getBytes());  
		
		System.out.println("AES 解密："+decrypt);
		
		
		byte[] key1 = Base64.decode(base64Encoded);  
        System.out.println("key1 size:" + key1.length);  
        System.out.println(Base64.encodeToString(key1));  
	}

}

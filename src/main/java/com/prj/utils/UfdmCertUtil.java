package com.prj.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import javax.crypto.Cipher;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Store;

/**
 * 描述: 证书工具类<br>
 * @author 胡义振
 * @date 2013-9-30
 */
public class UfdmCertUtil
{
	/**
	 * 描述: 把X509证书转为 map 键-值 格式
	 * @auther 胡义振
	 * @date 2013-9-30 
	 * @param base64Cert base64 编码的证书
	 * @return
	 */
	public static Map<String,String>  getMapByX509Cert(String base64Cert) {
		Map<String,String> mapCert = new HashMap<String,String>();
		try
		{
			//去掉证书开始 -----BEGIN CERTIFICATE----- 与 -----END CERTIFICATE-----
			if(base64Cert.indexOf("-----")>-1){
				String arrStr [] = base64Cert.split("-----");
				if(arrStr.length>2){
					base64Cert = arrStr[2];
				}
			}
			
		    CertificateFactory cf = CertificateFactory.getInstance("X.509");	    
		    InputStream certis = new ByteArrayInputStream(UfdmBase64Util.decode(base64Cert.trim()));
		    X509Certificate x509Cert = (X509Certificate) cf.generateCertificate(certis);
		    
		    //证书的有效日期
		    mapCert.put("certValidStartTime", UfdmDateUtil.dateToString(x509Cert.getNotBefore(),"yyyy-MM-dd HH:mm:ss"));
		    mapCert.put("certValidEndTime", UfdmDateUtil.dateToString(x509Cert.getNotAfter(),"yyyy-MM-dd HH:mm:ss")); 
		    
		    Principal subjectDN = x509Cert.getSubjectDN();
		    if(subjectDN!=null){
			    String [] arrSubjectDN = subjectDN.toString().replaceAll(" ","").split(",");
			    if(arrSubjectDN!=null){
				    for(String theme: arrSubjectDN){
				    	if(theme!=null){
						    String [] arrTheme = theme.split("=");
						    if(arrTheme.length>1){
						    	mapCert.put(arrTheme[0], arrTheme[1]);
						    }
						    else{
						    	mapCert.put(arrTheme[0], "");
						    }
				    	}
				    }
			    }
		    }		    
		}
		catch(Exception er){
			mapCert.put("err", er.toString());
		}
		return mapCert;
	}
	
	/**
	 * 描述: base64 编码公钥证书转为 X509 证书对象
	 * @auther 胡义振
	 * @date 2014-10-17 
	 * @param base64Cert
	 * @return
	 */
	public static X509Certificate getX509Cert(String base64Cert) {
		try
		{
			//去掉证书开始 -----BEGIN CERTIFICATE----- 与 -----END CERTIFICATE-----
			if(base64Cert.indexOf("-----")>-1){
				String arrStr [] = base64Cert.split("-----");
				if(arrStr.length>2){
					base64Cert = arrStr[2];
				}
			}
		    CertificateFactory cf = CertificateFactory.getInstance("X.509");	    
		    InputStream certis = new ByteArrayInputStream(UfdmBase64Util.decode(base64Cert.trim()));
		    X509Certificate x509Cert = (X509Certificate) cf.generateCertificate(certis);
		    return x509Cert;
		}
		catch(Exception er){
			er.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 描述: 私钥加密
	 * @auther 胡义振
	 * @date 2014-6-5 
	 * @param originalMess 原文
	 * @param privateKey 私钥
	 * @return 私钥加密后的base64编码
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String originalMess, PrivateKey privateKey) throws Exception {  
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
		
		// 私钥加密后的值
		byte [] bytePriKeyEncMess = cipher.doFinal(originalMess.getBytes());
		// 私钥加密后的值转成base64编码值
		String priKeyEncMess = new String(UfdmBase64Util.encode(bytePriKeyEncMess));
		return priKeyEncMess;  
    }  

	/**
	 * 描述: 公钥解密
	 * @auther 胡义振
	 * @date 2014-6-5 
	 * @param priKeyEncMess 私钥加密后的base64编码
	 * @param publicKey 公钥
	 * @return 公钥解密后的原文
	 * @throws Exception
	 */
	public static String decryptByPublicKey(String priKeyEncMess, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        // 私钥加密后的base64编码值转为byte类型
        byte [] bytePriKeyEncMess =  UfdmBase64Util.decode(priKeyEncMess);
        // 公钥解密
        String pubKeyDecMess = new String(cipher.doFinal(bytePriKeyEncMess));
        return pubKeyDecMess;  
    }  

	/**
	 * 描述: 私钥签名
	 * @auther 胡义振
	 * @date 2014-6-5 
	 * @param privateKey  私钥
	 * @param originalMess 原文
	 * @return 签名值
	 * @throws Exception
	 */
	public static String digitalSign(PrivateKey privateKey,String originalMess) throws Exception
	{
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(privateKey);
		signature.update(originalMess.getBytes());
		byte[] signedByte = signature.sign(); 
		String signedStr = new String(UfdmBase64Util.encode(signedByte));
		return signedStr;
	}
	
	
	/**
	 * 描述: 公钥验证签名信息
	 * @auther 胡义振
	 * @date 2014-6-5 
	 * @param publicKey 公钥
	 * @param originalMess 原文
	 * @param signedMess 签名值
	 * @return
	 * @throws Exception
	 */
	public static boolean verifySignature(PublicKey publicKey,String originalMess,String signedMess) throws Exception
	{
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(publicKey);
		signature.update(originalMess.getBytes());
		byte[] signedByte = UfdmBase64Util.decode(signedMess);
		if (signature.verify(signedByte)) {
			System.out.println("originalMess=" + originalMess);
			System.out.println("签名验证通过");
			return true;
		}
		else {
			System.out.println("签名验证失败");
			return false;
		}
	}
	
	/**
	 * 描述: 根据pfx证书获取 X509 证书
	 * @auther 胡义振
	 * @date 2014-6-5 
	 * @param pfxType  pfx证书类型（1：文件　2：base64 编码）
	 * @param strPfx strPfx证书（证书类型为 1时：文件证书，为文件路径；证书类型为2：base64b编码证书）
	 * @param strPassword 证书密码
	 * @return
	 */
	public static X509Certificate getX509CertByBase64Pfx(String pfxType,String strPfx,String strPfxPass){
		X509Certificate x509Cert = null;
		try
		{
			char[] arrCharPassword = ( strPfxPass!=null ? strPfxPass.toCharArray() : null);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			if(pfxType!=null && pfxType.equals("1")){
				FileInputStream fileInputStream = new FileInputStream(strPfx);
				keyStore.load(fileInputStream, arrCharPassword);
				fileInputStream.close();
			}
			else if(pfxType!=null && pfxType.equals("2")){
				InputStream inputStream = new ByteArrayInputStream(UfdmBase64Util.decode(strPfx.trim()));
				keyStore.load(inputStream, arrCharPassword);
				inputStream.close();
			}
			Enumeration<?> enumas = keyStore.aliases();
			String keyAlias = null;
			if(enumas.hasMoreElements())
			{
				keyAlias = (String) enumas.nextElement();
			}
			x509Cert = (X509Certificate) keyStore.getCertificate(keyAlias);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return x509Cert;
	}
	
	/**
	 * 描述: 根据pfx证书获取证书私钥
	 * @auther 胡义振
	 * @date 2014-6-5 
	 * @param pfxType  pfx证书类型（1：文件　2：base64 编码）
	 * @param strPfx strPfx证书（证书类型为 1时：文件证书，为文件路径；证书类型为2：base64b编码证书）
	 * @param strPassword 证书密码
	 * @return
	 */
	public static PrivateKey getPrivateKeyByBase64Pfx(String pfxType,String strPfx,String strPfxPass){
		PrivateKey privateKey = null;
		try
		{
			char[] arrCharPassword = ( strPfxPass!=null ? strPfxPass.toCharArray() : null);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			if(pfxType!=null && pfxType.equals("1")){
				FileInputStream fileInputStream = new FileInputStream(strPfx);
				keyStore.load(fileInputStream, arrCharPassword);
				fileInputStream.close();
			}
			else if(pfxType!=null && pfxType.equals("2")){
				InputStream inputStream = new ByteArrayInputStream(UfdmBase64Util.decode(strPfx.trim()));
				keyStore.load(inputStream, arrCharPassword);
				inputStream.close();
			}
			
			Enumeration<?> enumas = keyStore.aliases();
			String keyAlias = null;
			if(enumas.hasMoreElements())
			{
				keyAlias = (String) enumas.nextElement();
			}
			// 获取私钥
			privateKey = (PrivateKey) keyStore.getKey(keyAlias, arrCharPassword);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return privateKey;
	}

	/**
	 * 描述: 产生秘钥对
	 * @auther 胡义振
	 * @date 2014-6-5 
	 * @return
	 * @throws Exception
	 */
	public static KeyPair generateKeyPair() throws Exception{
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		SecureRandom secrand = new SecureRandom();
		secrand.setSeed(UfdmStringUtil.getCharAndNumr(10).getBytes());
		keygen.initialize(1024, secrand);
		KeyPair keyPair = keygen.genKeyPair();
		return keyPair;
	}
	
	/**
	 * 描述: 通过base64公钥，获取公钥证书
	 * @auther 胡义振
	 * @date 2014-8-28 
	 * @param pubKey
	 * @return
	 */
	public static PublicKey getPubKey(String base64PubKey)
	{
		PublicKey publicKey = null;
		try
		{
			byte[] keyBytes = UfdmBase64Util.decode(base64PubKey);
			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(keyBytes);
			// RSA对称加密算法
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			// 取公钥匙对象
			publicKey = keyFactory.generatePublic(bobPubKeySpec);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return publicKey;
	}

	/**
	 * 描述: 验证Pkcs7签名
	 * @auther 胡义振
	 * @date 2014-10-29 
	 * @param base64Signed 签名值（BASE64编码）
	 * @param orialText 签名原文
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean verifyPkcs7Signature(String base64Signed, String orialText)
	{
		boolean signRet = false;
		try
		{
			byte[] signedData = UfdmBase64Util.decode(base64Signed);
			
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			// 新建PKCS#7签名数据处理对象（带原文）
			CMSSignedData sd = new CMSSignedData(new CMSProcessableByteArray(new String(orialText + "\0").getBytes()), signedData);
			
			// 不带原文
			// CMSSignedData sd = new CMSSignedData(signedData);

			// 添加BouncyCastle作为安全提供
			BouncyCastleProvider bc = new BouncyCastleProvider();
			// String strAlgId = "Alg.Alias.Signature."+PKCSObjectIdentifiers.sha1WithRSAEncryption;
			// bc.put(strAlgId, "SHA1WITHRSA");
			Security.addProvider(bc);

			// 获得证书信息
			// CertStore certs = sd.getCertificatesAndCRLs("Collection", "BC");
			Store store = sd.getCertificates();

			// 获得签名者信息
			SignerInformationStore signers = sd.getSignerInfos();
			Collection c = signers.getSigners();
			Iterator it = c.iterator();

			// 当有多个签名者信息时需要全部验证
			while (it.hasNext())
			{
				SignerInformation signer = (SignerInformation) it.next();
				// 证书
				// Collection certCollection = certs.getCertificates(signer.getSID());
				Collection certCollection = store.getMatches(signer.getSID());
				Iterator certIt = certCollection.iterator();
				X509CertificateHolder cert = (X509CertificateHolder) certIt.next();

				// 验证数字签名
				if (signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert)))
				{
					signRet = true;
				}
				else
				{
					signRet = false;
				}
			}
		}
		catch (Exception e)
		{
			signRet = false;
			e.printStackTrace();
		}
		return signRet;
	}
	
	public static void main(String[] args) throws Exception 
	{
		StringBuffer pubBase64Cert = new StringBuffer();
		pubBase64Cert.append("MIIE4DCCBEmgAwIBAgIMGE87CZGn9WVxHeUnMA0GCSqGSIb3DQEBBQUAMGExDTAL");
		pubBase64Cert.append("BgNVBAYeBABDAE4xDzANBgNVBAgeBm1ZbF93ATEPMA0GA1UEBx4GZ21d3l4CMRsw");
		pubBase64Cert.append("GQYDVQQKHhJtWWxfdwFlcFtXi6SLwU4tX8MxETAPBgNVBAMeCABaAEoAQwBBMB4X");
		pubBase64Cert.append("DTEzMDEyNDAwMTIwNloXDTE1MDEyNDAwMTIwNlowggEGMQ0wCwYDVQQIHgRtWWxf");
		pubBase64Cert.append("MREwDwYDVQQKHggAWgBKAEcAUzERMA8GA1UECx4IAFoASgBHAFMxJzAlBgNVBAMe");
		pubBase64Cert.append("Hn7fi6F29GKlXpR1KG1Li9UAMQA3ADYANgA2ADcAMjEbMBkGA1UEAR4SADcANQA1");
		pubBase64Cert.append("ADkAMQA4ADQANgA5MQ0wCwYDVQQGHgQAQwBOMSkwJwYDVQQJHiAAMwAzADAAMAAw");
		pubBase64Cert.append("ADMAMAAwADAAMAAxADAAMQAzADgANDENMAsGA1UEBx4EZ21d3jEjMCEGA1UEFB4a");
		pubBase64Cert.append("ADMAMwAwADAAMAAzADAAMAAxADAAMQA3ADkxGzAZBgNVBCoeEn7fi6F29GKlXpR1");
		pubBase64Cert.append("KG1Li9UAMTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAxC9teuLYJ9fSqMMw");
		pubBase64Cert.append("cUlnLYWvshEJcqsNJplpkNtOimXw9tkOQ/s5RJ+s5rfh0+DbVYrP02Mx07E52Iec");
		pubBase64Cert.append("p0ZmHABeDwAfvb2OHUzJLABwV3vlW+IqGXNakJ1KwQYksWAusvHB5/qBmgnTExNq");
		pubBase64Cert.append("Qziet/cYdQjccQYCm/fFpqKNjwkCAwEAAaOCAfQwggHwMA8GA1UdEwEBAAQFMAMB");
		pubBase64Cert.append("AQAwDgYDVR0PAQEABAQDAgDAMBYGA1UdJQEBAAQMMAoGCCsGAQUFBwMCMCcGCmCB");
		pubBase64Cert.append("HAHVeAECzhIBAQAEFgQUaWQ9MDgxMTEwMzMwMDAwMDAwMDEwIgYDVR0jAQEABBgw");
		pubBase64Cert.append("FoAU23qUoIsvwYS9UDnQ8eOqP0ZEaQkwgZgGCCsGAQUFBwEBAQEABIGIMIGFMIGC");
		pubBase64Cert.append("BggrBgEFBQcwAoZ2bGRhcDovL2xkYXAuempjYS5jb20uY24vQ049WkpDQSxDTj1a");
		pubBase64Cert.append("SkNBLE9VPWNBQ2VydGlmaWNhdGVzLG89empjYT9jQUNlcnRpZmljYXRlP2Jhc2U/");
		pubBase64Cert.append("b2JqZWN0Q2xhc3M9Y2VydGlmaWNhdGlvbkF1dGhvcml0eTCBqgYDVR0fAQEABIGf");
		pubBase64Cert.append("MIGcMIGZoIGWoIGThoGQbGRhcDovL2xkYXAuempjYS5jb20uY24vQ049WkpDQWdy");
		pubBase64Cert.append("b3VwaWQxNjIyLENOPVpKQ0Esb3U9Q1JMRGlzdHJpYnV0ZVBvaW50cyxvPXpqY2E/");
		pubBase64Cert.append("Y2VydGlmaWNhdGVSZXZvY2F0aW9uTGlzdD9iYXNlP29iamVjdGNsYXNzPWNSTERp");
		pubBase64Cert.append("c3RyaWJ1dGlvblBvaW50MCAGA1UdDgEBAAQWBBQL4eT2gg15IJzpIqrAN9+xDnH5");
		pubBase64Cert.append("YzANBgkqhkiG9w0BAQUFAAOBgQBiNZmAmlfN+DW2X++QD7fz7UAgETxshQpsOdK+");
		pubBase64Cert.append("6P1mMo/PSgI9WtG/Bg+y4jeA54almwf51AyE+yX7xjL2aUWKns4NoYykWRLCijUP");
		pubBase64Cert.append("T0DD6RkEZhpPIpQ4xr1m+O6Vr3VWBZzy3Qr5YHWkkXqj1F1qeAF7eGkGsTqu/7w9");
		pubBase64Cert.append("JMML9w==");

//		byte [] bytePubKey = getX509Cert(pubBase64Cert.toString()).getPublicKey().getEncoded();
//		String pub = new String(Base64.encode(bytePubKey));
//		System.out.println("\n base64pub:"+pub);
//		System.out.println("\n getPubKey:"+getPubKey(pub));
//		
//		
//		String base64pfx = "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";
//		X509Certificate x509Cert = getX509CertByBase64Pfx("2",base64pfx,"111111");
		
		KeyPair keyPair = generateKeyPair();
		String jiami = encryptByPrivateKey("abc",keyPair.getPrivate());
		System.out.println("\n 私钥加密:"+jiami);
		System.out.println("\n 公钥解密:"+decryptByPublicKey(jiami,keyPair.getPublic()));
	
		

	}
}

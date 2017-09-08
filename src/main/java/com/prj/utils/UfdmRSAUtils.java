package com.prj.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/** *//**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class UfdmRSAUtils {

    /** *//**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    
    /** *//**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** *//**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";
    
    /** *//**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /** *//**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    
    /** *//**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * 
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = UfdmBase64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return UfdmBase64Util.encode(signature.sign());
    }

    /** *//**
     * <p>
     * 校验数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * 
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = UfdmBase64Util.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(UfdmBase64Util.decode(sign));
    }

    /** *//**
     * <P>
     * 私钥解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, RSAPrivateKey privateK)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** *//**
     * <p>
     * 公钥解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** *//**
     * <p>
     * 公钥加密
     * </p>
     * 
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey)
            throws Exception {
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** *//**
     * <p>
     * 私钥加密
     * </p>
     * 
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, RSAPrivateKey privateK)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** *//**
     * <p>
     * 获取私钥
     * </p>
     * 
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return UfdmBase64Util.encode(key.getEncoded());
    }

    /** *//**
     * <p>
     * 获取公钥
     * </p>
     * 
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return UfdmBase64Util.encode(key.getEncoded());
    }
    
    
//    public static void main(String[] args) throws Exception {
////        PublicKey pubKey = RsaHelper.decodePublicKeyFromXml(ConfigReader.PUBKEYXML);
////        PrivateKey priKey = RsaHelper.decodePrivateKeyFromXml(ConfigReader.PRIKEYXML);
////        String userNo = "380014841";
////        String companyCode = "1";
////        byte[] userNoBytes = userNo.getBytes();
//////        byte[] userNoEncy = RSAUtil.encrypt((RSAPublicKey)pubKey, userNoBytes);
////        byte[] userNoEncy = RSAUtils.encryptByPublicKey(userNoBytes, (RSAPublicKey)pubKey);
////        byte[] companyCodeBytes = companyCode.getBytes();
//////        byte[] decBytes1 = RSAUtils.decryptByPrivateKey(userNoEncy, (RSAPrivateKey) priKey);
//////        byte[] companyCodeEncy = RSAUtil.encrypt((RSAPublicKey)pubKey, companyCodeBytes);
////        byte[] companyCodeEncy = RSAUtils.encryptByPublicKey(companyCodeBytes,(RSAPublicKey)pubKey);
////        
//////        Map<String, String> params = new HashMap<String, String>();
//////        params.put("userNo", URLEncoder.encode(Base64Helper.encode(userNoEncy),"UTF-8"));
//////        params.put("companyCode", URLEncoder.encode(Base64Helper.encode(companyCodeEncy),"UTF-8") );
//////        
////        String un = URLEncoder.encode(Base64Helper.encode(userNoEncy),"UTF-8");
////        String cc = URLEncoder.encode(Base64Helper.encode(companyCodeEncy),"UTF-8");
//////        String url = "http://10.20.8.27:2015/App/GetUserInfoByNo?userNo="+un+"&companyCode="+cc;
//////        System.out.println(url);
//////        ResponseEntity<String> result = ClientUtils.getForString(url, null);
////        String url = "http://10.20.8.27:2015/App/GetUserInfoByNo";
////        String result = HttpRequest.sendGet(url,"userNo="+un+"&companyCode="+cc);
//////        ResponseEntity<String> result = ClientUtils.postForString(url, params);
////        System.out.println(result);
////      //用私钥解密  
////        byte[] resultBytes = Base64Helper.decode("HrA/7FiMGA44nCXPwD4xIYAi1lw1f9PHrg96vJxIRSl/ZJfFVmiiz5SBRink0Ih4W/QPi37Ku1tIM5ABMLBBo97UqqCJ7MWZiQOEnZd0kjJCCzGZxfH1cPEIep/UGIm/KcW8HXqutUBMQYMFFNSk0QMc/1mqmNnUaN9MwHFx8Wt5lAy+A4nHu6ibGTRTxyZWkvU2w7PLviZyd2joVUUIYT1gIHAgHyi317ayNyMs+/R6VqGfnHSXBPmageKCMnLgGlrz8kRM4wco2olpWe1KxWKVnS4vkmcxVlgmJiLXcsi8KCzsrw1iCBarRcTIqgcqNyUi9SbWHS2QThg8fy091ly1HpN7k/OCrtxilMJo0GaE0/iYq6v12VESuOIboqi7hcolJzU6WHFkeaOwgIUL429IKwVAzxEJMS+4/KGglMoPciSSabLCDS6eMZew5zv2GnYKB/pdVDyUimk9KepRgPTHaplferzqifZATVTwqbD2RRfM+sKHhpy7wP1BqsCAMTlVP3bvwx83UKH5AFUvg7uHdONORK87NYqEGd2+oHGLUVatH+3UFWhbKzdY4TP+D+AIdjjs+rBGo+M3LH/mlv9/eHIMqVM+DcXZ1A4aieHZ2cPeqdYXYJY9YdAbiUymrWIfskckhnw4Vc7UNtOGYyd5n/U9An/PxAUiE+Ni9uw=");
////        byte[] decBytes = RSAUtils.decryptByPrivateKey(resultBytes, (RSAPrivateKey) priKey);
////        System.out.println(new String(decBytes));
//        
//     // 获取公钥私钥
//        String householdNum = "380014841";
//        String companyCode = "1";
//        
//        PublicKey pubKey = RsaHelper.decodePublicKeyFromXml(ConfigReader.PUBKEYXML);
//        PrivateKey priKey = RsaHelper.decodePrivateKeyFromXml(ConfigReader.PRIKEYXML);
//        
//        //分别对户号及燃气公司code加密
//        byte[] userNoEncy = RSAUtils.encryptByPublicKey(householdNum.getBytes(), (RSAPublicKey)pubKey);
//        byte[] companyCodeEncy = RSAUtils.encryptByPublicKey(companyCode.getBytes(),(RSAPublicKey)pubKey);
//        //对加密后的数据做Base64编码及URL编码
//        String un = URLEncoder.encode(Base64Helper.encode(userNoEncy),"UTF-8");
//        String cc = URLEncoder.encode(Base64Helper.encode(companyCodeEncy),"UTF-8");
//        
//        String url = "http://10.20.8.27:2015/App/GetUserInfoByNo";
//        String result = HttpRequest.sendGet(url,"userNo="+un+"&companyCode="+cc);
//        System.out.println(result);
//    }

}


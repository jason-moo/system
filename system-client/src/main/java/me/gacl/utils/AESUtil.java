package me.gacl.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


/**
 * AES（Advanced Encryption Standard，对称加密的一种 ）加解密算法封装，主要包括：<br />
 * 1. 基于AES的加密算法实现方法；<br />
 * 2. 基于AES的解密算法实现方法；<br />
 * 3. 提供了基于main函数的简单测试。<br />
 *
 * @author 开发支持中心
 */

public class AESUtil {
    private static Logger logger = LoggerFactory.getLogger(AESUtil.class);


    public static void main(String[] args) throws Exception {
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
		 * 此处使用AES-128-CBC加密模式，key需要为16位。
		 */
        String cKey = "f1Cnw1CAyKt79buz";
        // 需要加密的字串
        String cSrc = "18072850850";
        System.out.println(cSrc);
        // 加密
        String enString = AESUtil.encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);
        // 解密
        String DeString = AESUtil.decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);

    }


    /**
     * 加密算法：基于明文串，密钥对明文信息进行加密
     *
     * @param sSrc 待加密的字符串。
     * @param sKey 密钥。长度必须=16个字节(长度为128位)
     * @return 加密后的经base64编码的字符串。
     * @throws GeneralSecurityException
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String sSrc, String sKey)
            throws GeneralSecurityException {
        if (sSrc == null || sSrc.trim().length() == 0 || sKey == null
                || sKey.trim().length() != 16) {

        }

        try {
            // 构造密钥。
            byte[] raw = sKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"

            IvParameterSpec iv = new IvParameterSpec(
                    "0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes());
            return CodecUtil.base64Encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密 算法：根据加密串和密钥对密文进行解密.
     *
     * @param encryptedString 待解密的加密串。
     * @param sKey            密钥。
     * @return 解密后的字符串。
     */
    public static String decrypt(String encryptedString, String sKey) {
        if (encryptedString == null || encryptedString.trim().length() == 0
                || sKey == null || sKey.trim().length() != 16) {
            return null;
        }

        try {
            byte[] raw = sKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = CodecUtil.base64Decode(encryptedString);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对传入参数进行加密
     *
     * @param param 为排序参数
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String md5(Map<String, String> param,String md5Key) throws NoSuchAlgorithmException {
        if (param == null || param.size() == 0) {
            return null;
        }
        String msg = null;

        Set<Map.Entry<String, String>> set = param.entrySet();
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(set);

        // 对传入参数排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }

        });

        // 对传入参数拼接字符串
        StringBuilder sb = new StringBuilder();
        Map.Entry<String, String> entry;
        for (int i=0, j=list.size(); i<j; i++) {
            entry = list.get(i);
            sb.append(entry.getKey()).append(entry.getValue());
        }
        // 加入MD5KEY
        sb.append(md5Key);
        msg = sb.toString();
        logger.info("拼接后的字符串：" + msg);
        return md5(msg);
    }

    public static String md5(String inputText) throws NoSuchAlgorithmException {
        return md5Encrypt(inputText, "md5");
    }

    public static String md5Encrypt(String inputText, String algorithmName) throws NoSuchAlgorithmException {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("please input source string");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        MessageDigest m = MessageDigest.getInstance(algorithmName);
        m.update(inputText.getBytes(Charset.forName("UTF-8")));
        byte s[] = m.digest();
        return hex(s);
    }

    /**
     * 返回十六进制字符串
     *
     * @param arr
     * @return
     */
    public static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

}

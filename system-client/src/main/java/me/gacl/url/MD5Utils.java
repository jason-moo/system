/**
 * Copyright (C), 2011-2016, 微贷网.
 */
package me.gacl.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密类
 *
 * Created by caosk on 2017/6/1.
 */
public class MD5Utils {
    private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    // 用来将字节转换成 16 进制表示的字符
    static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String md5Str(String inStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("md5加密字符串异常", e);
            return null;
        }
    }

    public static void main(String args[]) {
        System.out.println(md5Str(null));
        System.out.println(md5Str("e10adc3949ba59abbe56e057f20f883e" + "2"));
        System.out.println(md5Str("8e114197e1b33783a00542ad67e80516" + "7524099"));

    }

    /**
     * 对文件全文生成MD5摘要
     * @param file   要加密的文件
     * @return MD5摘要码
     */
    public static String md5File(File file) {
        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            logger.info("MD5摘要长度：" + md.getDigestLength());
            fis = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            int length = -1;
            logger.info("开始生成摘要");
            long s = System.currentTimeMillis();
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            logger.info("摘要生成成功,总用时: "
                    + (System.currentTimeMillis() - s) + "ms");
            byte[] b = md.digest();
            return byteToHexString(b);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 把byte[]数组转换成十六进制字符串表示形式
     * @param tmp    要转换的byte[]
     * @return 十六进制字符串表示形式
     */
    private static String byteToHexString(byte[] tmp) {
        String s;
        // 用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
            // 转换成 16 进制字符的转换
            byte byte0 = tmp[i]; // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        s = new String(str); // 换后的结果转换为字符串
        return s;
    }

    /**
     * MD532位加密
     * 
     * @param s
     * @return
     */
    public final static String MD5To32byte(String s) {
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("md5Str");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return (new String(str)).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }
}

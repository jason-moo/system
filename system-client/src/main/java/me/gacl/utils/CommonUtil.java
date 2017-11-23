/**
 * Copyright (C), 2011-2016, 微贷网.
 */
package me.gacl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 对象转数组
     * @param obj
     * @return
     */
    public static byte[] object2Byte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public static Object byte2Object(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * 校验字符串是否都为空
     *
     * @param params
     * @return
     */
    public static boolean hasNullStr(String... params) {
        for (String content : params) {
            if (content == null || content.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验对象是否都为null
     *
     * @param params
     * @return
     */
    public static boolean hasNullObj(Object... params) {
        for (Object o : params) {
            if (o == null)
                return true;
        }
        return false;
    }

    /**
     * 去字符串中的空格、回车、换行符、制表符
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断传进来的字符串，是否
     * 大于指定的字节，如果大于递归调用
     * 直到小于指定字节数 ，一定要指定字符编码，因为各个系统字符编码都不一样，字节数也不一样
     * @param s
     *      原始字符串
     * @param num
     *      传进来指定字节数
     * @return String 截取后的字符串

     * @throws UnsupportedEncodingException
     */
    public static String subStr(String s,int num) {
        try {
            int length = s.getBytes("UTF-8").length;
            if(length > num){
                s = s.substring(0, s.length() - 1);
                s = subStr(s,num);
            }
            return s;
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串编码错误", e);
            return "";
        }
    }

}

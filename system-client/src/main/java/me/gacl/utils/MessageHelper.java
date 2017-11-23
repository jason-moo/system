package me.gacl.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 报文帮助类
 * Created by caosk on 2017/6/1.
 */
public class MessageHelper {

    private static Logger logger = LoggerFactory.getLogger(MessageHelper.class);

    public static String getEncodeXml(String requestXml, String wzcbSecretKey){
        try {
            logger.info("加密前报文内容：" + requestXml);
            requestXml = SignUtils.encrypt(requestXml, wzcbSecretKey, SignUtils.DESede);
            logger.info("加密后报文内容：" + requestXml);
            return requestXml;
        } catch (Exception e) {
            logger.error("DES 加密失败, requestXml=" + requestXml, e);
        }
        return "";
    }

    public static String getRequestMessage(String requestXml, Map<String, String> params, String privateKey, String wzcbSecretKey, String encode){
        String sign = SignUtils.createSign(params, privateKey, encode);
        String encodeXml = getEncodeXml(requestXml, wzcbSecretKey);
        params.put("request_xml", encodeXml);
        String requestStr = SignUtils.getRequestXml(params);
        requestStr = requestStr + "&sign=" + sign;
        return requestStr;
    }

    public static Map<String, String> getRequestParams(String requestXml, Map<String, String> params, String privateKey, String wzcbSecretKey, String encode){
        String sign = SignUtils.createSign(params, privateKey, encode);
        String encodeXml = getEncodeXml(requestXml, wzcbSecretKey);
        params.put("request_xml", encodeXml);
        params.put("sign", sign);
        return params;
    }

    /**
     * 将url参数转换成map
     * @param param aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, String> getResponseParams(String param) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

}

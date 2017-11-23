
package me.gacl.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

public class CodecUtil {
    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    private static final char[] ALPHABET = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static final int[] INVERTED_ALPHABET = new int[128];
    static final String INIT_STR = "00000000000000000000000000000000000000000000000000000000000000000000";
    static final BigInteger MODULUS;

    static {
        int i;
        for(i = 0; i < 128; ++i) {
            INVERTED_ALPHABET[i] = -1;
        }

        for(i = 65; i <= 90; ++i) {
            INVERTED_ALPHABET[i] = i - 65 + 10;
        }

        for(i = 48; i <= 57; ++i) {
            INVERTED_ALPHABET[i] = i - 48;
        }

        MODULUS = new BigInteger("36");
    }

    public CodecUtil() {
    }

    public static String hexEncode(byte[] data) {
        return data != null && data.length != 0?Hex.encodeHexString(data):null;
    }

    public static byte[] hexDecode(String str) throws DecoderException {
        byte[] result = (byte[])null;
        return str != null && str.length() != 0?Hex.decodeHex(str.toCharArray()):result;
    }

    public static String base64Encode(byte[] binaryData) {
        return binaryData != null && binaryData.length != 0?Base64.encodeBase64String(binaryData):null;
    }

    public static String base64UrlEncode(byte[] binaryData) {
        return binaryData != null && binaryData.length != 0?Base64.encodeBase64URLSafeString(binaryData):null;
    }

    public static byte[] base64Decode(String base64String) {
        return base64String != null && base64String.length() != 0?Base64.decodeBase64(base64String):null;
    }

    public static String base36Encode(String hexStr) {
        if(hexStr != null && hexStr.length() != 0) {
            StringBuffer sb = new StringBuffer();
            BigInteger bi = new BigInteger(hexStr, 16);
            BigInteger d = bi;
            BigInteger m = BigInteger.ZERO;

            while(!BigInteger.ZERO.equals(d)) {
                m = d.mod(MODULUS);
                d = d.divide(MODULUS);
                sb.insert(0, ALPHABET[m.intValue()]);
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    public static String base36Encode(String hexStr, int length) {
        if(hexStr != null && hexStr.length() != 0) {
            if(length > 64) {
                throw new IllegalArgumentException("参数错误（length不能超过64）:param length = " + length);
            } else {
                String str = base36Encode(hexStr);
                if(length > str.length()) {
                    str = "00000000000000000000000000000000000000000000000000000000000000000000".concat(str);
                    str = str.substring(str.length() - length);
                } else {
                    str = str.substring(str.length() - length);
                }

                return str;
            }
        } else {
            return null;
        }
    }

    public static String base36Decode(String base36Str) {
        if(base36Str != null && base36Str.length() != 0) {
            if(!isValidBase32Str(base36Str)) {
                throw new IllegalArgumentException("base36字符串格式错误:\"" + base36Str + "\"");
            } else {
                base36Str = base36Str.toUpperCase();
                BigInteger bi = BigInteger.ZERO;
                char[] chars = base36Str.toCharArray();

                for(int i = 0; i < base36Str.length(); ++i) {
                    char c = chars[i];
                    int n = INVERTED_ALPHABET[c];
                    bi = bi.multiply(MODULUS).add(new BigInteger("" + n));
                }

                return bi.toString(16);
            }
        } else {
            return null;
        }
    }

    public static String urlEncode(String s) {
        return urlEncode(s, "UTF-8");
    }

    public static String urlEncode(String s, String enc) {
        if(s != null && s.trim().length() != 0) {
            try {
                return URLEncoder.encode(s, enc);
            } catch (UnsupportedEncodingException var3) {
                throw new IllegalArgumentException("urlEncode(" + s + "," + enc + ")，URL编码异常!", var3);
            }
        } else {
            return null;
        }
    }

    public static String urlDecode(String encodedUrl) {
        return urlDecode(encodedUrl, "UTF-8");
    }

    public static String urlDecode(String s, String enc) {
        if(s != null && s.trim().length() != 0) {
            try {
                return URLDecoder.decode(s, enc);
            } catch (UnsupportedEncodingException var3) {
                throw new IllegalArgumentException("urlEncode(" + s + "," + enc + ")，URL解码异常!", var3);
            }
        } else {
            return s;
        }
    }

    public static String htmlEscape(String html) {
        return html != null && html.length() != 0?StringEscapeUtils.escapeHtml(html):null;
    }

    public static String htmlUnescape(String escapedHtml) {
        return escapedHtml != null && escapedHtml.length() != 0?StringEscapeUtils.unescapeHtml(escapedHtml):null;
    }

    public static String xmlEscape(String xml) {
        return xml != null && xml.length() != 0?StringEscapeUtils.escapeXml(xml):null;
    }

    public static String xmlUnescape(String escapedXml) {
        return escapedXml != null && escapedXml.length() != 0?StringEscapeUtils.unescapeXml(escapedXml):null;
    }

    private static boolean isValidBase32Str(String str) {
        char[] chars = str.toCharArray();

        for(int i = 0; i < chars.length; ++i) {
            if(!isValidBase32Char(chars[i])) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidBase32Char(char c) {
        return c >= 0 && c < 128?INVERTED_ALPHABET[c] != -1:false;
    }

    public static void main(String[] args) throws DecoderException {
        System.out.println(urlDecode("%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%8C%E6%94%B6%E5%88%B0%E5%8F%96%E7%A5%A8%E7%9F%AD%E4%BF%A1%E4%BA%86%E5%90%A7%E3%80%82%E8%AF%B7%E5%88%B0%E5%BD%B1%E9%99%A2%E5%94%AE%E7%A5%A8%E5%A4%A7%E5%8E%85%E7%BD%91%E7%A5%A8%E7%BD%91%E5%8F%96%E7%A5%A8%E6%9C%BA%E5%8F%96%E7%A5%A8%E8%A7%82%E5%BD%B1%EF%BC%8C%E9%9A%8F%E5%88%B0%E9%9A%8F%E5%8F%96%E3%80%82%E5%88%AB%E5%8E%BB%E5%94%AE%E7%A5%A8%E6%9F%9C%E5%8F%B0%E6%8E%92%E9%98%9F%E5%93%A6%EF%BC%8C%E5%8F%96%E7%A5%A8%E6%9C%BA%E4%B8%8D%E5%A5%BD%E6%89%BE%E5%B0%B1%E9%97%AE%E4%B8%8B%E5%BD%B1%E9%99%A2%E5%B7%A5%E4%BD%9C%E4%BA%BA%E5%91%98%E5%90%A7%EF%BC%81%E4%BA%B2%E4%B8%8B%E3%80%82&sendTime=20130109163006&phone=13811939741&orderId=2013010916ORDER02962815"));
        System.out.println(urlDecode("zYpux5fbQtE+WBZzlPT1BQ=="));
        System.out.println(urlEncode("zYpux5fbQtE+WBZzlPT1BQ=="));
    }
}

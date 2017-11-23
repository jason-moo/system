package me.gacl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 此代码用了默认补位方式，为RSA/None/PKCS1Padding
 * Created by caosk on 2017/6/1.
 */
public class RSAUtils {
    private static Logger logger = LoggerFactory.getLogger(RSAUtils.class);
    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    /**
     * 算法名称
     */
    private static final String ALGORITHOM = "RSA";
    /**
     * 填充名词
     */
    public final static String RSA_CHIPER = "RSA/ECB/PKCS1Padding";
    private static Cipher cipher;
    /**
     * 指定key的大小
     */
    private static int KEYSIZE = 1024;

    static {
        try {
            cipher = Cipher.getInstance(RSA_CHIPER);
        } catch (NoSuchAlgorithmException e) {
            logger.error("RSA Cipher 初始化失败:没有该算法", e);
        } catch (NoSuchPaddingException e) {
            logger.error("RSA Cipher初始化失败：没有该填充方式", e);
        }
    }

    /**
     * 使用公钥对明文进行加密，返回BASE64编码的字符串
     *
     * @param publicKey
     * @param plainText
     * @return
     */
    public static String encrypt(PublicKey publicKey, String plainText, String charset) throws InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int MaxBlockSize = KEYSIZE / 8;
        byte[][] bytes = splitArray(plainText.getBytes(charset), MaxBlockSize - 11);
        String mi = "";
        int len = 0;
        for (byte[] s : bytes) {
            len += cipher.doFinal(s).length;
        }
        byte[] bbb = new byte[len];
        int i = 0;
        for (byte[] s : bytes) {
            System.arraycopy(cipher.doFinal(s), 0, bbb, i, cipher.doFinal(s).length);
            i = i + cipher.doFinal(s).length;
        }
        mi = new BASE64Encoder().encode(bbb);
        return mi;

    }

    /**
     * 使用私钥对明文密文进行解密
     *
     * @param privateKey
     * @param enStr
     * @return
     */
    public static String decrypt(PrivateKey privateKey, String enStr, String charset) throws InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String ming = "";
        byte[] b = new BASE64Decoder().decodeBuffer(enStr);
        int key_len = KEYSIZE / 8;
        byte[][] arrays = splitArray(b, key_len);
        int len = 0;
        for (byte[] arr : arrays) {
            len = len + cipher.doFinal(arr).length;
        }
        byte[] bbb = new byte[len];
        int i =0;
        for (byte[] s : arrays) {
            System.arraycopy(cipher.doFinal(s), 0, bbb, i, cipher.doFinal(s).length);
            i=i+cipher.doFinal(s).length;

        }
        ming = new String(bbb,charset);
        return ming;
    }

    /**
     * 16进制密钥（经过base64编码）
     * 字符类型明文
     */
    public static String encryptString(String publicKeyStr, String content, String charset) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        return encrypt(publicKey, content, charset);
    }

    public static String decryptString(String privateKeyStr, String content, String charset) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        return decrypt(privateKey, content, charset);
    }

    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
    //====签名

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey 商户私钥
     * @param encode     字符集编码
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String encode) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, SignatureException {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKey));
        KeyFactory keyf = KeyFactory.getInstance(ALGORITHOM);
        PrivateKey priKey = keyf.generatePrivate(priPKCS8);
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(priKey);
        signature.update(content.getBytes(encode));
        byte[] signed = signature.sign();
        return Base64Utils.encodeToString(signed);

    }

    /**
     * RSA验签名检查
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @param encode    字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey, String encode) {
        boolean bverify = false;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
            byte[] encodedKey = new byte[0];

            encodedKey = Base64Utils.decodeFromString(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));
            bverify = signature.verify(Base64Utils.decodeFromString(sign));
        } catch (NoSuchAlgorithmException e) {
            logger.error("验证签名失败：找不到对应rsa 算法", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("验证签名失败：content.getBytes失败", e);
        } catch (SignatureException e) {
            logger.error("验证签名失败", e);
        } catch (InvalidKeyException e) {
            logger.error("验证签名失败", e);
        } catch (InvalidKeySpecException e) {
            logger.error("验证签名失败", e);
        } catch (Exception e){
            logger.error("验证签名失败", e);
        }
        return bverify;
    }

    /**
     * 分块
     *
     * @param string
     * @param len
     * @return
     */
    public static String[] splitString(String string, int len) {
        int x = string.length() / len;
        int y = string.length() % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for (int i = 0; i < x + z; i++) {
            if (i == x + z - 1 && y != 0) {
                str = string.substring(i * len, i * len + y);
            } else {
                str = string.substring(i * len, i * len + len);
            }
            strings[i] = str;
        }
        return strings;
    }

    /**
     */
    public static String bcd2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');


            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    /**
     */
    public static byte[][] splitArray(byte[] data, int len) {
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(data, i * len, arr, 0, y);
            } else {
                System.arraycopy(data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }
}

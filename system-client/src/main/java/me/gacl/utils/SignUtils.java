package me.gacl.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Cipher;

/**
 * 加验签工具类
 * Created by caosk on 2017/6/5.
 */
public class SignUtils {

	private static Logger logger = LoggerFactory.getLogger(SignUtils.class);

	public final static String DESede = "DESede";

	/**
	 * 获取签名
	 * @param params
	 * @return
	 */
	public static String createSign(Map<String, String> params, String privateKey, String encode) {
		String contentStr = getRequestXml(params);
		try {
			String sign = RSAUtils.sign(contentStr, privateKey, encode);
			return sign;
		} catch (Exception e) {
			logger.error("生成签名出错, content: " + contentStr, e);
		}
		return null;
	}

	/**
	 * 生成请求参数串
	 * @param params
	 * @return
	 */
	public static String getRequestXml(Map<String, String> params){
		SortedMap<String, String> sortedMap = new TreeMap<>(params);

		StringBuilder content = new StringBuilder();
		for (String key : sortedMap.keySet()) {
			String value = params.get(key);
			if (StringUtils.isNotEmpty(value) && !"sign".equals(key) && !"key".equals(key)) {
				content.append(key + "=" + value + "&");
			}
		}
		//删除最后一个字符
		content.deleteCharAt(content.length() - 1);
		return content.toString();
	}

	/**
	 * 加签
	 *
	 * @param text
	 *            明文
	 * @param key
	 *            密钥
	 * @param algorithm
	 *            签名算法,目前KMI支持NONEwithRSA, MD2withRSA, MD5withRSA, SHA1withRSA,
	 *            SHA256withRSA, SHA384withRSA, SHA512withRSA , SHA1withDSA
	 * @return 签名
	 * @throws Exception
	 */
	public static String sign(final String text, final String key,
					   final String algorithm) {
		final byte[] textBytes = text.getBytes();
		final byte[] keyBytes = Base64Utils.decodeFromString(key);
		byte[] resultBytes = null;
		try {
			resultBytes = SignatureUtil.sign(textBytes, keyBytes, algorithm);
			return Base64Utils.encodeToString(resultBytes);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 验签
	 *
	 * @param text
	 *            明文
	 * @param signText
	 *            签名
	 * @param key
	 *            密钥
	 * @param algorithm
	 *            验签算法,目前KMI支持NONEwithRSA, MD2withRSA, MD5withRSA, SHA1withRSA,
	 *            SHA256withRSA, SHA384withRSA, SHA512withRSA , SHA1withDSA
	 * @return 验签通过返回true，不通过返回false
	 * @throws Exception
	 */
	public static boolean verify(final String text, final String signText,
						  final String key, final String algorithm) {

		try {
			return SignatureUtil.verify(text.getBytes(), Base64Utils
					.decodeFromString(signText), Base64Utils.decodeFromString(key), algorithm);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 加密
	 *
	 * @param text 明文
	 * @param key 密钥
	 * @param algorithm 算法
	 * @return 密文
	 */
	public static String encrypt(String text, String key, String algorithm) {

		byte[] bytes = text.getBytes(); // 待加/解密的数据
		byte[] keyData = Base64Utils.decodeFromString(key); // 密钥数据

		try {
			byte[] cipherBytes = SymmtricCryptoUtil.symmtricCrypto(bytes,
					keyData, algorithm, Cipher.ENCRYPT_MODE);
			return Base64Utils.encodeToString(cipherBytes);
		} catch (GeneralSecurityException e) {
			return "";
		}
	}

	/**
	 * 解密
	 *
	 * @param text 密文
	 * @param key 密钥
	 * @param algorithm 算法
	 * @return 明文
	 */
	public static String decrypt(String text, String key, String algorithm) {

		byte[] bytes = Base64Utils.decodeFromString(text); // 待加/解密的数据
		byte[] keyData = Base64Utils.decodeFromString(key); // 密钥数据

		try {
			byte[] cipherBytes = SymmtricCryptoUtil.symmtricCrypto(bytes,
					keyData, algorithm, Cipher.DECRYPT_MODE);
			return new String(cipherBytes);
		} catch (GeneralSecurityException e) {
			return "";
		}
	}

	public static void main(String[] args) {
		String data = "<?xml version='1.0' encoding='utf-8'?><wzbank><head><appid>aabfd12221412</appid><version>1.0</version><timestamp>20170207121551</timestamp><secure_code></secure_code></head><body><card_no>6282550000010108</card_no><cert_type>1</cert_type>3303030195652154512<cert_no></cert_no><mob_no>138577958445</mob_no><auth_code></auth_code><trans_type>02</trans_type></body></wzbank>";
		String key = "ddlYtvADaIXMBSo/KfhV6hOdYnIUJond";


		String en_request_xml=encrypt(data, key, "DESede");
		System.out.println(en_request_xml);

		System.out.println(decrypt(en_request_xml, key, "DESede"));


		String content = "ZJuZne/7QUP02X+iPdmMj8kvetYBAWRdgbnyWBRmnkgOh9iEkbBQ5iZHJ26D+g4mcNiJF0rHFbxvnNOp6VVC+wOiFJdY/BnsgrcH7TUTDiss40Qvi4gWNby7g+05pmwfBtIzSXDmyHwaKVxbEABbVAAYzKw9uLvOuUZwAjr3xpL0uBHT4ilSr3qOe6kmu2fHlVrk26ytgNebxaaBzlndEY+ousHfNyAM9uk/Z4DZ0dc=";

		String sign = "pt1dkk1LO5A2Xr7zD8T0RuenTNVxCTgJpeItZguJTNpTYN7aakA3pWuXzYUZLf7SGj+cXLoYXr1kA4aUlokdIbdbeljx6mm6Bv8Mo0H3kKgQ7DLuKV2+HjuFNHqhK5wtlKwX7goEmHH5bmZ0xVkMv9McP2ZuHy+GSdjYN2n6McU=";
		String pKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8Pdjmet2TJcidFPQ+nU2Gg0pY6kpat9GTtKpNdMc030OmcvNyIe0KnRmc74V68DnKk5ZJ8hASFqO8jOsqTsgaIiTqWGOdeSH6ghAZFDWf1xe4MiaAqhZ0GHUPYGJIGEZZBvoKl/uai/ZgZKNfOGFxmqfOdPFEoNQBHFvtLLMKCwIDAQAB";
		String rsa = "SHA1withRSA";
		boolean pass = verify(content, sign, pKey, rsa);
		System.out.println(pass);
	}

}

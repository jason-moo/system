package me.gacl.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class MD5 {
	
	private String salt;

	private static final String MD5_ALGORITHM = "MD5";

	private MessageDigest algorithm;
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public MD5() {
		super();
	}

	public MD5(String salt) {
		this.salt = salt;
		try {
			algorithm = MessageDigest.getInstance(MD5_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	   public String digest(String plainData) throws Exception {
	        algorithm.reset();
	        byte[] plainBytes = convertText2Bytes(plainData, false);
	        byte[] skeyHexBytes = Base64.encodeBase64(salt.getBytes());
	        algorithm.update(plainBytes);
	        algorithm.update(skeyHexBytes);
	        return convertBytes2Text(algorithm.digest(), true);
	    }
	    
	    public String digestHex(String plainData) throws Exception {
	        algorithm.reset();
	        byte[] plainBytes = convertText2Bytes(plainData, false);
	        byte[] skeyHexBytes = Base64.encodeBase64(salt.getBytes());
	        algorithm.update(plainBytes);
	        algorithm.update(skeyHexBytes);
	        byte digest[]= algorithm.digest();
	        return byteToHex(digest);
	    }
	    
	    
	    public static String byteToHex(byte  [] bt){
	    	StringBuffer buffer = new StringBuffer();  
	    	   String item = "";  
	    	   for (int i = 0; i < bt.length; i++) {  
	    	    item = Integer.toHexString(bt[i] & 0XFF);  
	    	    if(item.length()==1){
	    	    	buffer.append('0');
	    	    }
		    	buffer.append(item);  
	    	      
	    	   }  
	    	return buffer.toString();
	    	
	    }
	    
	    // 解码
	    public static byte[] convertText2Bytes(String text, boolean isSecretData) throws UnsupportedEncodingException {
	        byte[] bytes = text.getBytes("UTF-8");
	        if (isSecretData) {
	            bytes = Base64.decodeBase64(bytes);
	        }

	        return bytes;
	    }

	    // 编码返回字符串
	    public static String convertBytes2Text(byte[] bytes, boolean isSecretData) throws UnsupportedEncodingException {
	        if (isSecretData) {
	            bytes = Base64.encodeBase64(bytes);
	        }
	        return new String(bytes, "UTF-8");
	    }
	    
}

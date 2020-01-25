package com.scalesampark.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class EncriptionDecryption {
	Cipher ecipher;
	Cipher dcipher;
	
	public EncriptionDecryption(SecretKey key) throws Exception {
		ecipher = Cipher.getInstance("AES");
	    dcipher = Cipher.getInstance("AES");
	    ecipher.init(Cipher.ENCRYPT_MODE, key);
	    dcipher.init(Cipher.DECRYPT_MODE, key);
	}
	
	@SuppressWarnings("restriction")
	public String encrypt(String str) throws Exception {
	    byte[] utf8 = str.getBytes("UTF8");
	    // Encrypt
	    byte[] enc = ecipher.doFinal(utf8);
	    return new sun.misc.BASE64Encoder().encode(enc);
	  }
	
	@SuppressWarnings("restriction")
	public String decrypt(String str) throws Exception {
	    // Decode base64 to get bytes
	    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
	    byte[] utf8 = dcipher.doFinal(dec);
	    // Decode using utf-8
	    return new String(utf8, "UTF8");
	  }
}

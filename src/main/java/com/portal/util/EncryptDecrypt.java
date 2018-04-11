package com.portal.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class EncryptDecrypt {

	public static String encrypt(String password) {
		String md5 = null;
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption
			// algorithm
			mdEnc.update(password.getBytes(), 0, password.length());
			md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted
			// string
		} catch (Exception ex) {
			System.out.println(ex);
			throw new RuntimeException();
		}
		return md5;
	}

}
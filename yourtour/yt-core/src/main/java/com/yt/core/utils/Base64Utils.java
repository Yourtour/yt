package com.yt.core.utils;

import java.security.MessageDigest;
import java.util.UUID;

import com.litesuits.common.assist.Base64;

public class Base64Utils {

	private Base64Utils() {
		super();
	}

	public static String encode(byte[] value) throws Exception {
		return Base64.encodeToString(value, Base64.DEFAULT | Base64.NO_WRAP);
	}

	public static byte[] decode(String base64) throws Exception {
		return Base64
				.decode(base64.getBytes(), Base64.DEFAULT | Base64.NO_WRAP);
	}
	
	public static String getUUID(){
		String s = UUID.randomUUID().toString(); 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	
	/**
	 * 
	 * @param value
	 */
	public static String MD5(String value) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5"); 
		md5.update(value.getBytes()); 
		String t = encode(md5.digest());
		
		return t;
	}
}

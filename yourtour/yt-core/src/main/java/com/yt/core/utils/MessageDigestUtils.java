package com.yt.core.utils;

import java.security.MessageDigest;

public class MessageDigestUtils {

	private MessageDigestUtils() {
		super();
	}

	/**
	 * 使用指定的签名算法对输入的数据进行签名，并将签名结果进行BASE64编码。
	 * 
	 * @param algorithm
	 *            指定的签名算法
	 * @param value
	 *            输入的数据，将采用UTF-8进行解码
	 * @return 签名后并进行BASE64编码的字符串
	 * @throws Exception
	 *             签名过程中发生的异常
	 */
	public static String digest(String algorithm, String value)
			throws Exception {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		byte[] tar = md.digest(value.getBytes("UTF-8"));
		return Base64Utils.encode(tar);
	}
	
}

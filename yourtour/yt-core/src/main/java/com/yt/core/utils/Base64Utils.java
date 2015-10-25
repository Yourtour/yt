package com.yt.core.utils;

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
}

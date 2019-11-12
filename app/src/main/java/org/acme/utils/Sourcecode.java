package org.acme.utils;

import java.util.Base64;

public class Sourcecode {
	public static String decode(String code) {
		byte[] actualByte = Base64.getDecoder().decode(code);
		return new String(actualByte);
	}
}

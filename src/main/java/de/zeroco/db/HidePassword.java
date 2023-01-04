package de.zeroco.db;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class HidePassword {

	public static String encryptPassword(String password) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(password.getBytes());
	}
	public static String decryptPassword(byte[] password) {
		Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(password));
	}
}

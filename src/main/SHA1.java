package main;

import java.security.MessageDigest;

public class SHA1 {
	
	
	public static byte[] SHA(String x) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(x.getBytes());
		return md.digest();
	}
}

package Utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.stream.Collectors;

public class Md5andBase64Utils {
/**
 * 可以把一段文字转换为MD
 * Can convert a file to MD5
 * @param text
 * @return md5
 */
public static String encode(String text){
	try {
	MessageDigest digest = MessageDigest.getInstance("md5");
	byte[] buffer = digest.digest(text.getBytes());
	StringBuffer sb = new StringBuffer();
	for (byte b : buffer) {
		int a = b & 0xff;
		String hex = Integer.toHexString(a);

		if (hex.length() == 1) {
			hex = 0 + hex;
		}
		sb.append(hex);
	}
	return sb.toString();
} catch (NoSuchAlgorithmException e) {
	e.printStackTrace();
}
return null;
}

/***
 * 任意文件转换成Md5
 * Can convert a text to MD5
 * @param in
 * @return md5
 */

public static String encode(InputStream in) {
	try {
		MessageDigest digester = MessageDigest.getInstance("MD5");
		byte[] bytes = new byte[8192];
		int byteCount;
		while ((byteCount = in.read(bytes)) > 0) {
			digester.update(bytes, 0, byteCount);
		}
		byte[] digest = digester.digest();

		// byte -128 ---- 127
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			int a = b & 0xff;
			// Log.d(TAG, "" + a);

			String hex = Integer.toHexString(a);

			if (hex.length() == 1) {
				hex = 0 + hex;
			}

			sb.append(hex);
		}

		return sb.toString();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			in = null;
		}
	}
	return null;
}


/**
 * @Author wk
 * @Description: base64对md5密码加密
 * @Date 2020/6/13 22:19
 * @Param: [key]
 * @return: java.lang.String
 **/
public static String encryptBASE64(String key){
	byte[] bt = key.getBytes();
	String encoded = new BASE64Encoder().encodeBuffer(bt);
	return encoded;
}


/**
 * @Author wk
 * @Description: base64解密
 * @Date 2020/6/13 22:22
 * @Param: [key]
 * @return: java.lang.String
 **/
public static String decryptBASE64(String key){
	byte[] bt;
	try {
		bt = (new BASE64Decoder()).decodeBuffer(key);
		return new String(bt);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return "";
}

/**
 * @Author wk
 * @Description: 文件转为字符串
 * @Date 2020/6/13 22:53
 * @Param: [inputStream]
 * @return: java.lang.String
 **/
public static String fileencryptBASE64(InputStream inputStream){
	InputStreamReader inreader = new InputStreamReader(inputStream);
	//转为字符串
	String bureader = new BufferedReader(inreader).lines().parallel().collect(Collectors.joining(System.lineSeparator()));
	return bureader;
}



}

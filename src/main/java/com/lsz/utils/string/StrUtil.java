package com.lsz.utils.string;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Properties;

import com.sun.jmx.snmp.Timestamp;

public class StrUtil {
	public static void main(String[] args) {
		makeMD5("aaaaa");
	}

	public static String makeMD5(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	public static String md5(String name) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = getByte(name);
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] getByte(String name) {
		// �õ��ļ�����
		File file = new File(name);
		byte[] b = new byte[(int) file.length()];
		try {
			InputStream in = new FileInputStream(file);
			try {
				in.read(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return b;
	}

	public static String isNull(String str) {
		return str == null ? "" : str;
	}

	public static String getStrProp(Properties pr, String propName, String def) {
		String ret;
		if (pr == null || propName == null) {
			ret = def;
		} else {
			ret = pr.getProperty(propName);
		}
		if (ret == null)
			ret = "";
		return ret.trim();
	}

	public static int getIntProp(Properties pr, String propName, int def) {
		String retS = getStrProp(pr, propName, null);
		int ret;
		try {
			ret = Integer.parseInt(retS);
		} catch (NumberFormatException ex) {
			ret = def;
		}
		return ret;
	}

	public static boolean filterSelectSQL(String sql) {
		if (sql.indexOf("%20@") != -1)
			return false;
		if (sql.indexOf("exec") != -1)
			return false;
		if (sql.indexOf("declare") != -1)
			return false;
		if (sql.indexOf("<script") != -1)
			return false;
		if (sql.indexOf("<if") != -1)
			return false;
		if (sql.indexOf("truncate") != -1)
			return false;
		if (sql.indexOf("drop") != -1)
			return false;
		if (sql.indexOf("insert") != -1)
			return false;
		if (sql.indexOf("update") != -1)
			return false;
		if (sql.indexOf("delete") != -1)
			return false;
		if (sql.indexOf("sysfiles") != -1)
			return false;
		if (sql.indexOf("sysindexes") != -1)
			return false;
		if (sql.indexOf("sysobjects") != -1)
			return false;
		if (sql.indexOf("syscolumns") != -1)
			return false;
		if (sql.indexOf("syscomments") != -1)
			return false;
		return true;
	}

	public static boolean filterSQL(String sql) {
		if (sql.indexOf("%20@") != -1)
			return false;
		if (sql.indexOf("exec") != -1)
			return false;
		if (sql.indexOf("declare") != -1)
			return false;
		if (sql.indexOf("<script") != -1)
			return false;
		if (sql.indexOf("<if") != -1)
			return false;
		if (sql.indexOf("truncate") != -1)
			return false;
		if (sql.indexOf("drop") != -1)
			return false;
		if (sql.indexOf("sysfiles") != -1)
			return false;
		if (sql.indexOf("sysindexes") != -1)
			return false;
		if (sql.indexOf("sysobjects") != -1)
			return false;
		if (sql.indexOf("syscolumns") != -1)
			return false;
		if (sql.indexOf("syscomments") != -1)
			return false;
		return true;
	}

	public static String Conversion(String s) throws IOException {
		String s1 = new String();
		String s2 = new String();
		byte abyte0[] = s.getBytes("Unicode");
		for (int j = 2; j < abyte0.length; j += 2) {
			String s3 = Integer.toHexString(abyte0[j + 1]);
			int i = s3.length();
			if (i < 2)
				s1 = s1 + "&#x" + "0" + s3;
			else
				s1 = s1 + "&#x" + s3.substring(i - 2);
			s3 = Integer.toHexString(abyte0[j]);
			i = s3.length();
			if (i < 2)
				s1 = s1 + "0" + s3 + ";";
			else
				s1 = s1 + s3.substring(i - 2) + ";";
		}

		return s1;
	}

	public static String Unicode2GBK(String dataStr) {
		int index = 0;
		StringBuffer buffer = new StringBuffer();
		while (index < dataStr.length()) {
			if (!"\\u".equals(dataStr.substring(index, index + 2))) {
				buffer.append(dataStr.charAt(index));
				index++;
				continue;
			}
			String charStr = "";
			charStr = dataStr.substring(index + 2, index + 6);
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(letter);
			index += 6;
		}

		return buffer.toString();

	}

}

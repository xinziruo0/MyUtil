package com.lsz.utils.config;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import com.lsz.utils.string.StrUtil;


public class CenterProp {
	private static final String logFileName = "center.properties";
	public static int sessionTime = 60;
	public static String memcacheServer = null;
	public static int memcacheInitConn = 10;// 初始连接数
	public static int memcacheMaxConn = 50;// 最小连接数
	public static int memcacheMinConn = 10;// 最大连接数
	public static int memcacheMaxIdle = 30;// 最大处理时间
	public static int memcacheMaintSleep = 30;// 设置主线程的睡眠时间
	public static boolean memcacheNagle = false;// 设置TCP的参数
	public static int memcacheSocketTO = 3000;// 设置连接超时
	public static int memcacheSocketConnectTO = 0;// 设置TCP的参数，连接超时等
	public static int threadCount = 5;// 设置线程数
	public static String memcachePoolname = null;

	private static Properties prop = new Properties();
	static {
		init();
	}

	private CenterProp() {
	}

	public static void init() {
		try {
			InputStream is = new FileInputStream((new StringBuilder(String
					.valueOf(getClassPath()))).append("center.properties")
					.toString());
			prop.load(is);
			is.close();
			inItProperties();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void inItProperties() {
		sessionTime = StrUtil.getIntProp(prop, "sessionTime", 60);
		memcacheServer = StrUtil.getStrProp(prop, "memcacheServer",
				"127.0.0.1:11211");
		memcachePoolname = StrUtil.getStrProp(prop, "memcachePoolname",
				"127.0.0.1:11211");
		memcacheInitConn = StrUtil.getIntProp(prop, "memcacheInitConn", 10);
		memcacheMaxConn = StrUtil.getIntProp(prop, "memcacheMaxConn", 50);
		memcacheMinConn = StrUtil.getIntProp(prop, "memcacheMinConn", 10);
		memcacheSocketTO = StrUtil.getIntProp(prop, "memcacheSocketTO", 3000);
		memcacheSocketConnectTO = StrUtil.getIntProp(prop,
				"memcacheSocketConnectTO", 0);
		threadCount = StrUtil.getIntProp(prop,
				"threadCount", 5);
	}

	public static String getProperties(String key) {
		return prop.getProperty(key);
	}

	public static int getPropertiesInt(String key) {
		try {
			String str = prop.getProperty(key);
			if (str != null)
				return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	public static long getPropertiesLong(String key) {
		try {
			String str = prop.getProperty(key);
			if (str != null)
				return Long.parseLong(str);
		} catch (Exception e) {
			return 0L;
		}
		return 0L;
	}

	public static void setProperties(String key, String value) {
		prop.setProperty(key, value);
		save();
	}

	public static void remove(String key) {
		prop.remove(key);
		save();
	}

	public static synchronized void save() {
		try {
			FileOutputStream fos = new FileOutputStream((new StringBuilder(
					String.valueOf(getClassPath()))).append(
					"centerProp.properties").toString());
			prop.store(fos, "");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getClassPath() {
		boolean isWindows = System.getProperty("os.name").toLowerCase()
				.startsWith("windows");
		CenterProp o = new CenterProp();
		String strPackageName = "";
		if (o.getClass().getPackage() != null)
			strPackageName = o.getClass().getPackage().getName();
		String strClassName = o.getClass().getName();
		String strClassFileName;
		if (!strPackageName.equals(""))
			strClassFileName = strClassName.substring(
					strPackageName.length() + 1, strClassName.length());
		else
			strClassFileName = strClassName;
		URL url = o.getClass().getResource(
				(new StringBuilder(String.valueOf(strClassFileName))).append(
						".class").toString());
		String strURL = url.toString();
		try {
			strURL = URLDecoder.decode(strURL, "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		strURL = strURL.substring(strURL.indexOf('/') + (isWindows ? 1 : 0),
				strURL.lastIndexOf('/') - strPackageName.length());
		return strURL;
	}
}
package com.lsz.utils.charset;

import org.junit.Test;

public class TestCharTools {
	
	@Test
	public void test(){

	    //CharTools charTools = new CharTools();

	    String url;

	    url = "http://www.google.com/search?hl=zh-CN&newwindow=1&q=%E4%B8%AD%E5%9B%BD%E5%A4%A7%E7%99%BE%E7%A7%91%E5%9C%A8%E7%BA%BF%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2&btnG=%E6%90%9C%E7%B4%A2&lr=";
	    if(CharTools.isUtf8Url(url)){
	      System.out.println(CharTools.Utf8URLdecode(url));
	    }else{
	      //System.out.println(URLDecoder.decode(url));
	    }

	    url = "http://www.baidu.com/baidu?word=%D6%D0%B9%FA%B4%F3%B0%D9%BF%C6%D4%DA%CF%DF%C8%AB%CE%C4%BC%EC%CB%F7&tn=myie2dg";
	    if(CharTools.isUtf8Url(url)){
	      System.out.println(CharTools.Utf8URLdecode(url));
	    }else{
	      //System.out.println(URLDecoder.decode(url));
	    }

	}
}

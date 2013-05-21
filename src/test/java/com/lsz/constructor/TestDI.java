package com.lsz.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDI {
	private static ApplicationContext ac;
	public static void main(String[] args) {
		ac = new ClassPathXmlApplicationContext(new String[] {
				"test-context.xml"
			});
		BeDI bedi = (BeDI)ac.getBean("bedi");
	}
}

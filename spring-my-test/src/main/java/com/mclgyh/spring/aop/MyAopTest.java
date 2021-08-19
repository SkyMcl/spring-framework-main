package com.mclgyh.spring.aop;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 16:19 2021/8/13
 * @Modified By:
 **/
public class MyAopTest {
	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\eclipse-workspace\\spring-framework-main\\code");
		ApplicationContext context =  new ClassPathXmlApplicationContext("aop.xml");
		MyRun myRun = (MyRun)context.getBean("myRun");
		String s = myRun.get();
		System.out.println("------"+s);
	}
}

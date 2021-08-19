package com.mclgyh.spring.aop.annotition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 11:49 2021/8/16
 * @Modified By:
 **/
public class MyAopTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext();
		context.register(AnnotationConfiguration.class);
		context.refresh();
		MyRun myRun = (MyRun)context.getBean("myRun");
		String s = myRun.get();
		System.out.println(" get Return = " +s);

	}
}

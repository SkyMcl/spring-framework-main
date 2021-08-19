package com.mclgyh.spring.annotition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 17:22 2021/7/29
 * @Modified By:
 **/

public class MyAnnotition {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AnnotationTest.class);
		context.refresh();
		Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(CustomAnnotation.class);
		Object next = beansWithAnnotation.values().iterator().next();
		CustomAnnotation annotation = next.getClass().getAnnotation(CustomAnnotation.class);
		String value = annotation.value();
		System.out.println("value = "+value);
	}
}

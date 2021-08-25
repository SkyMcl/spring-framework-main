package com.mclgyh.spring.annotition;

<<<<<<< HEAD
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

=======
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

>>>>>>> b441c4e07c829610ea8c8b19abf7dd86ef197ad2
/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 17:22 2021/7/29
 * @Modified By:
 **/
<<<<<<< HEAD

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
=======
@Component
public class MyAnnotition {

	@Configuration
	@Component
	class MyChild{

	}

>>>>>>> b441c4e07c829610ea8c8b19abf7dd86ef197ad2
}

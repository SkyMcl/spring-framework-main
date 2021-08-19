package com.mclgyh.spring.tx;

import com.mclgyh.spring.aop.MyRun;
import com.mclgyh.spring.tx.service.UserService;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 16:38 2021/8/16
 * @Modified By:
 **/
public class MyTxTest {

	public static void main(String[] args) {

		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\eclipse-workspace\\spring-framework-main\\code");
		ApplicationContext context =  new ClassPathXmlApplicationContext("tx.xml");
		UserService myRun = (UserService)context.getBean("userService");
		String l = System.currentTimeMillis()+"";
		String temp = (System.currentTimeMillis()+"").substring(l.length()-5);
		myRun.check("test"+temp,2);

	}
}

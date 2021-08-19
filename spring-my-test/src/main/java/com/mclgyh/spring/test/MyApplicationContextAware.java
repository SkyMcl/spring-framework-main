package com.mclgyh.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 11:50 2021/8/19
 * @Modified By:
 **/
public class MyApplicationContextAware implements ApplicationContextAware {
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("---------------------------MyApplicationContextAware start-----------------------------------");
		System.out.println(applicationContext.getApplicationName()+" name ");
		System.out.println("---------------------------MyApplicationContextAware end-------------------------------------");
	}
}

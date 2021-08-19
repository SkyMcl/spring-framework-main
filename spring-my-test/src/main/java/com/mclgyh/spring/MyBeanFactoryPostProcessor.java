package com.mclgyh.spring;

import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 16:20 2021/7/28
 * @Modified By:
 **/
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		DefaultAdvisorAdapterRegistry adapterRegistry = beanFactory.getBean(DefaultAdvisorAdapterRegistry.class);
//		adapterRegistry.
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

	}
}

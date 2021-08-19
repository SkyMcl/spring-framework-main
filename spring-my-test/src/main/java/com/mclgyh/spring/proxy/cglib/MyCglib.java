package com.mclgyh.spring.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 22:35 2021/8/9
 * @Modified By:
 **/
public class MyCglib implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		Object invoke = methodProxy.invokeSuper(o,objects);
		return invoke;
	}
}

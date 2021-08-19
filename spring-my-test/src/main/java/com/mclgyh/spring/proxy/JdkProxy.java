package com.mclgyh.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 22:33 2021/8/8
 * @Modified By:
 **/
public class JdkProxy {

/**
* 实现步骤
 * JDK Proxy 需要一个接口 才可以实现动态代理
 * 1、创建classLoader
 * 2、获取当前类的所有实现的接口
 * 3、创建InvocationHandler 匿名内部类
 * 4、获取对象
 * 5、返回
* @Date
* @Param
* @return
**/

public static Book getProxyInstance(final Book book){

	ClassLoader classLoader = book.getClass().getClassLoader();
	Class<?>[] interfaces = book.getClass().getInterfaces();
	InvocationHandler h = new InvocationHandler() {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object invoke = method.invoke(book, args);
			return invoke;
		}
	};

	Object o = Proxy.newProxyInstance(classLoader, interfaces, h);

	return (Book)o;
}



}

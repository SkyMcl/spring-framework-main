package com.mclgyh.spring.proxy;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 22:46 2021/8/8
 * @Modified By:
 **/
public class Test
{

	public static void main(String[] args) {
		/**
		 *
		 * jdk 动态代理 步骤
		 * 1、获取接口
		 * 2、创建接口代理类文件
		 *  2.1、添加 toString hashCode equals 方法
		 *  2.2、添加 默认构造函数
		 *  2.3、添加 静态代码块 处理 映射的方法
		 * 3、获取指定参数的构造函数
		 *  3.1 添加 指定参数
		 * 4、通过构造函数 创建动态代理对象
		 * 5、调用动态代理对象的方法时 会通过反射 找到实现接口的子类 进行方法调用
		 **/
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
		Book proxyInstance = JdkProxy.getProxyInstance(new MyBook());
		int add = proxyInstance.add(1, 2);
		System.out.println(add+" className = "+proxyInstance.getClass());
	}
}

package com.mclgyh.spring.proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 22:23 2021/8/9
 * @Modified By:
 **/
public class CglibProxy {

	public static void main(String[] args) {
		//存放动态代理的 class文件到本地
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"d:\\code");
		//初始化类增强器Enhancer
		// 生成代理类的类文件
		// 1、生成子类
		// 2、生成无参构造函数
		// 3、生成有参构造函数
		// 4、初始化参数
		// 5、添加 hashCode equals toString 方法
		// 6、通过classLoader 将类文件加载到内存
		// 7、通过构造函数生成代理类对象

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(MyBook.class);
		//设置回调对象
		enhancer.setCallback(new MyCglib());
		MyBook o = (MyBook)enhancer.create();
		int add = o.add(1, 3);
		int cut = o.cut(1, 3);
		System.out.println("add="+add);
		System.out.println("cut="+cut);
		System.out.println("className="+o.getClass());

	}


}

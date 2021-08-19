package com.mclgyh.spring.handler;

import com.mclgyh.spring.parser.MyParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		//设置属性 对应的解析器
		registerBeanDefinitionParser("user", new MyParser());
	}
}

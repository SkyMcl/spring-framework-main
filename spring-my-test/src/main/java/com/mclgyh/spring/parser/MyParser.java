package com.mclgyh.spring.parser;

import com.mclgyh.spring.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**  */
public class MyParser  extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {

		//获取fileencoding 属性
		String name = element.getAttribute("name");
		if (StringUtils.hasLength(name)) {
			builder.addPropertyValue("name", name);
		}
		//获取order属性
		String age = element.getAttribute("age");
		if (StringUtils.hasLength(age)) {
			builder.addPropertyValue("age", Integer.valueOf(age));
		}


	}
}
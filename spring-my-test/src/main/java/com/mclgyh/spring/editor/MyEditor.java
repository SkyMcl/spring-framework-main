package com.mclgyh.spring.editor;

import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

public class MyEditor extends PropertyEditorSupport {


	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("____---------MyEditor-----------_____text="+text);

		String[] s = text.split("_");
		Address address =  new Address();
		address.setProvince(s[0]);
		address.setCity(s[1]);
		address.setDistrict(s[2]);
		setValue(address);

		System.out.println("____---------MyEditor-----------_____"+address);
	}

}

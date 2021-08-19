package com.mclgyh.spring.annotition;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 17:22 2021/7/29
 * @Modified By:
 **/
@Component
public class MyAnnotition {

	@Configuration
	@Component
	class MyChild{

	}

}

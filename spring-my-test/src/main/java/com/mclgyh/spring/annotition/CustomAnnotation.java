package com.mclgyh.spring.annotition;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 14:19 2021/8/19
 * @Modified By:
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
@Inherited

public @interface CustomAnnotation {

	String value() default "";
}

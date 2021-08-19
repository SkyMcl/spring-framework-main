package com.mclgyh.spring.aop.annotition;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 12:04 2021/8/16
 * @Modified By:
 **/
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.mclgyh.spring.aop")
public class AnnotationConfiguration {
}

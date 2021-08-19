package com.mclgyh.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 16:20 2021/8/13
 * @Modified By:
 **/

public class LogUtils {

	public void start(){
		System.out.println("This is aop start method !");
	}

	public void after(){
		System.out.println("This is aop after method !");
	}

	public void afterReturning(){
		System.out.println("This is aop afterReturning method !");
	}

	public void afterThrowing(){
		System.out.println("This is aop afterThrowing method !");
	}

	public Object around(ProceedingJoinPoint m){
		System.out.println("This is aop around method START step!");
		try {
			return m.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}finally {
			System.out.println("This is aop around method END step!");
		}

		return null;
	}
}

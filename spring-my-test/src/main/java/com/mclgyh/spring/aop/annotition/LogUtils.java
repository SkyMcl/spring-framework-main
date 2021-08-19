package com.mclgyh.spring.aop.annotition;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 16:20 2021/8/13
 * @Modified By:
 **/
@Aspect
@Component
public class LogUtils {

	@Pointcut("execution(* com.mclgyh.spring.aop.annotition.*.*(..))")
	public void pointCut(){

	}

	@Before("pointCut()")
	public void start(){
		System.out.println("This is aop start method !");
	}

	@After("pointCut()")
	public void after(){
		System.out.println("This is aop after method !");
	}

	@AfterReturning(value = "pointCut()",returning = "result")
	public void afterReturning(JoinPoint joinpoint, Object result){
		Signature signature = joinpoint.getSignature();
		System.out.println("This is aop afterReturning method !");
		System.out.println(signature+" = signature result = "+result);
	}
	@AfterThrowing(value = "pointCut()",throwing = "e")
	public void afterThrowing(Throwable e){
		System.out.println("This is aop afterThrowing method !");
		e.printStackTrace();
	}

//	@Around("pointCut()")
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

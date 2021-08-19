package com.mclgyh.spring.aop.annotition;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 16:46 2021/8/13
 * @Modified By:
 **/
@Component("myRun")
public class MyRun {

	public String get(){
		System.out.println("-----------------------");
		for (int i = 2; i >= 0; i--) {
			System.out.println(1/i);
		}
		return "myrun";
	}

	public static void main(String[] args) {
		MyRun myRun = new MyRun();
		myRun.get();
		System.out.println(0/1);
	}

}

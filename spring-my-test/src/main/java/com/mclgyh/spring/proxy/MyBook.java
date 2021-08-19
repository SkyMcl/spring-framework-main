package com.mclgyh.spring.proxy;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 22:41 2021/8/8
 * @Modified By:
 **/
public class MyBook implements  Book{



	@Override
	public int add(int i, int k) {
		System.out.println("---------add---------- "+(i+k));
		return i+k;
	}

	@Override
	public int cut(int i, int k) {
		System.out.println("---------cut---------- "+(i-k));
		return i-k;
	}

	@Override
	public int sum(int i, int k) {
		System.out.println("---------sum---------- "+(i+k));
		return i+k;
	}
}

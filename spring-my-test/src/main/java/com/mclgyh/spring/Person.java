package com.mclgyh.spring;

public class Person {

	private String name;
	private int age;
	private String sex;

	Person(String name,String sex){
		this.name = name;
		this.sex = sex;
		System.out.println("----------person 2----------");
//		System.out.println(this.toString());
	}

	Person(int age,String name,String sex){
		this.age = age;
		this.name = name;
		this.sex = sex;
		System.out.println("----------person 3----------");
		System.out.println(this.toString());
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		System.out.println("this is toString method");
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", sex='" + sex + '\'' +
				'}';
	}
}

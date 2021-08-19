package com.mclgyh.spring;

import com.mclgyh.spring.dependens.A;
import com.mclgyh.spring.dependens.B;
import com.mclgyh.spring.editor.MyCustomEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpringTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		MyXmlApplicationContext context  = new MyXmlApplicationContext("applicationContext.xml");
//		Person person = (Person)context.getBean("person");
//		System.out.println("springText " +person);
//		System.out.println(person.getAge());

//		User person2 = (User)context.getBean("person2");
//
//		System.out.println("person2 = "+person2.getName());
//		System.out.println(person2);
//		Person p = new Person("s","F");
//		System.out.println("p="+p.getName());

//		MyCustomEditor myCustomEditor = (MyCustomEditor)context.getBean("myEditor");
//		System.out.println("myCustomEditor="+myCustomEditor.toString());
//		System.out.println("--------------end-------------------");
		A bean = context.getBean(A.class);
		System.out.println(bean.getB());
		B bean1 = context.getBean(B.class);
		System.out.println(bean1.getA());
	}
}

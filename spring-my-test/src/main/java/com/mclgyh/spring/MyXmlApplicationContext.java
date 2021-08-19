package com.mclgyh.spring;

import com.mclgyh.spring.editor.Address;
import com.mclgyh.spring.editor.MyEditor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @describe:

 * @return:
 * @author: mcl
 * @date:   2021/7/27
 * @time: 23:46
 */
public class MyXmlApplicationContext extends ClassPathXmlApplicationContext {


	public MyXmlApplicationContext(String s) {
		super(s);
	}

	@Override
	/**
	*
	* @Date
	* @Param
	* @return
	**/
	protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		//自定义 属性解析器 方法三
//		beanFactory.addPropertyEditorRegistrar(new MyEditorRegistar());
		//自定义 属性解析器 方法四
		beanFactory.registerCustomEditor(Address.class,MyEditor.class);
		super.prepareBeanFactory(beanFactory);
	}

	@Override
	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
		beanFactory.setAllowCircularReferences(false);
		beanFactory.setAllowCircularReferences(false);
		System.out.println("---------------customizeBeanFactory------------------");
		super.customizeBeanFactory(beanFactory);

	}


	@Override
	protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		super.postProcessBeanFactory(beanFactory);
	}
}

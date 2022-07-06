package com.jafa.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jafa.model.Board;

public class BeanTest {

	@Test
	public void beanTest() {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(Rootconfig.class);
		Board bean = ctx.getBean("testBean",Board.class);
		System.out.println(bean.getContent());
	}
}

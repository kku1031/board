package com.jafa.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;

public class SampleServiceImplTest extends Apptest{

	@Autowired
	SampleService service;
	
	@Test
	public void test() throws Exception {
		Integer doAdd = service.doAdd("10", "12");
		System.out.println(doAdd);
	}

}

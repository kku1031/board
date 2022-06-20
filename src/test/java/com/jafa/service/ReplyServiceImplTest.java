package com.jafa.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;

public class ReplyServiceImplTest extends Apptest{

	@Autowired
	ReplyService service;
	
	@Test
	public void test() {
		service.get(4L);
	}

}

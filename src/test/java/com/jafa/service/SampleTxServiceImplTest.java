package com.jafa.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;

public class SampleTxServiceImplTest extends Apptest {

	@Autowired
	SampleTxService service;
	
	@Test
	public void testTx() {
		service.addData("HELLOWORLD");
	}

}

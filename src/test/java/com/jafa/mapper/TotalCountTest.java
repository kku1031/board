package com.jafa.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;

public class TotalCountTest extends Apptest{

	@Autowired
	BoardMapper mapper;
	
	@Test
	public void test() {
		assertEquals(412, mapper.totalCount());
	}
	
}

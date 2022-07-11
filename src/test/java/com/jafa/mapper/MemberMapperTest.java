package com.jafa.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;
import com.jafa.model.MemberVO;

public class MemberMapperTest extends Apptest{

	@Autowired
	MemberMapper mapper;
	
	@Test
	public void selectMember() {
		MemberVO read = mapper.read("admin");
		read.getAuthList().forEach(System.out::println);
	}

}

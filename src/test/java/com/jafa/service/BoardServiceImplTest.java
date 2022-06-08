package com.jafa.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;
import com.jafa.model.Board;

public class BoardServiceImplTest extends Apptest{

	@Autowired
	BoardService service;
	
	
	@Test
	public void getListTest() {
		List<Board> list = service.getList();
		assertEquals(4, list.size());
	}

}

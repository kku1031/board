package com.jafa.paging;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;
import com.jafa.mapper.BoardMapper;
import com.jafa.model.Board;

public class DataInsertTest extends Apptest{

	@Autowired
	BoardMapper mapper;
	
	@Test
	public void dataInsert() {
		
		for (int i = 1; i <= 412; i++) {
			
			Board board = new Board();
			board.setTitle("페이징 처리 연습"+i);
			board.setContent("내용 페이징 처리 연습"+i);
			board.setWriter("작성자");
			mapper.insert(board);
		}
		
		
	}
}

package com.jafa.paging;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;
import com.jafa.mapper.BoardMapper;
import com.jafa.model.Board;

public class SearchData extends Apptest {

	@Autowired
	BoardMapper mapper;

	@Test
	public void dataInsert1() {

		for (int i = 1; i <= 212; i++) {

			Board board = new Board();
			board.setTitle("검색 처리 연습 : Spring" + i);
			board.setContent("검색처리 연습 : Spring Boot" + i);
			board.setWriter("강경구");
			mapper.insert(board);
		}
	}

	@Test
	public void dataInsert2() {

		for (int i = 1; i <= 212; i++) {

			Board board = new Board();
			board.setTitle("검색 처리 연습 : Java" + i);
			board.setContent("검색처리 연습 : JSP" + i);
			board.setWriter("김경구");
			mapper.insert(board);
		}
	}
}

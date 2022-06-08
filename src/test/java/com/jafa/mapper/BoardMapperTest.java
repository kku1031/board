package com.jafa.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;
import com.jafa.model.Board;

public class BoardMapperTest extends Apptest{

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void setUp() throws IOException, SQLException {
		Reader reader = Resources.getResourceAsReader("sql/board_ex.sql");
		ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
		runner.runScript(reader);
	}
	
	@Test
	public void getListTest() {
		List<Board> list = boardMapper.getList();
		assertEquals(4, list.size());
	}
	
	@Test
	public void getTest() {
		
		Board board = boardMapper.get(1L);
		assertEquals("게시물 제목입니다.1", board.getTitle());
		assertEquals("내용 테스트1", board.getContent());
		assertEquals("강경구1", board.getWriter());
		
	}
		
	@Test
	public void insertTest() {
		Board board = new Board();
		board.setTitle("제목 테스트임");
		board.setContent("내용 테스트임");
		board.setWriter("작성자");
		boardMapper.insert(board);		
		Board getBoard = boardMapper.get(5L);
		
		assertEquals(board.getTitle(), getBoard.getTitle());
		assertEquals(board.getContent(), getBoard.getContent());
		assertEquals(board.getWriter(), getBoard.getWriter());
		assertEquals(board.getBno(),getBoard.getBno());
	}
	
	
	@Test
	public void updateTest() {
		Board board = boardMapper.get(1L);
		board.setTitle("제목 수정");
		board.setContent("내용 수정");
		boardMapper.update(board);
		assertEquals("제목 수정", board.getTitle());
		assertEquals("내용 수정", board.getContent());
		assertEquals("강경구1", board.getWriter());
	}
	
	@Test
	public void deleteTest() {
		boardMapper.delete(1L);
		boardMapper.delete(2L);
		Board board1 = boardMapper.get(1L);
		Board board2 = boardMapper.get(2L);
		Board board3 = boardMapper.get(3L);		
		
		assertNull(board1);
		assertNull(board2);
		assertNotNull(board3);
		
	}
	
}

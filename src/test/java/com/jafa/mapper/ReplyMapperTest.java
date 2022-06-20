package com.jafa.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.Apptest;
import com.jafa.model.Criteria;
import com.jafa.model.ReplyVO;

public class ReplyMapperTest extends Apptest{

	@Autowired
	ReplyMapper mapper;
	
	@Test
	@Ignore
	public void test() {
		System.out.println(mapper.getListAll());
	}

	@Test
	@Ignore
	public void insertTest() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(1L);
		vo.setReply("댓글작성중");
		vo.setReplyer("댓글 작성자");
		int result = mapper.insert(vo);
		System.out.println("게시물 : " + result);
	}
	
	@Test
	@Ignore
	public void findByRnoTest() {
		System.out.println(mapper.read(2L));
	}

	@Test
	@Ignore
	public void deleteTest() {
		mapper.delete(2L);
	}
	
	@Test
	@Ignore
	public void updateTest() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(1L);
		vo.setReply("댓글 수정");
		mapper.update(vo);
	}
	
	@Test
	public void getListWithPagingTest() {
		List<ReplyVO> listWithPaging = mapper.getListWithPaging(new Criteria(), 1L);
		System.out.println(listWithPaging);
	}
}
	

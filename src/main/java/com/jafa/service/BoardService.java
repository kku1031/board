package com.jafa.service;

import java.util.List;

import com.jafa.model.Board;
import com.jafa.model.BoardAttachVO;
import com.jafa.model.Criteria;

public interface BoardService {

	List<Board> getList(Criteria criteria);
	Board get(Long bno, boolean isAddCount);
	void register(Board board);
	void modify(Board board);
	void remove(Long bno);
	int totalCount(Criteria criteria);
	List<BoardAttachVO> getAttachList(Long bno);
	
}

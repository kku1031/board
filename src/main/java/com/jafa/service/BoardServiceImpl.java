package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.BoardAttachMapper;
import com.jafa.mapper.BoardMapper;
import com.jafa.model.Board;
import com.jafa.model.BoardAttachVO;
import com.jafa.model.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;
		
	@Override
	public List<Board> getList(Criteria criteria) {
		return boardMapper.getList(criteria);
	}

	@Override
	public Board get(Long bno) {
		return boardMapper.get(bno);
	}

	
	@Transactional
	@Override
	public void register(Board board) {
		boardMapper.insert(board);
		if(board.getAttachList() == null || board.getAttachList().size()==0) return;
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
	}

	@Override
	public void modify(Board board) {
		boardMapper.update(board);
	}

	@Override
	public void remove(Long bno) {
		attachMapper.deleteAll(bno);
		boardMapper.delete(bno);
	}

	@Override
	public int totalCount(Criteria criteria) {
		return boardMapper.totalCount(criteria);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}

	
}

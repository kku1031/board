package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.BoardMapper;
import com.jafa.mapper.ReplyMapper;
import com.jafa.model.Criteria;
import com.jafa.model.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	private final static int REPLY_ADD = 1;
	private final static int REPLY_DEL = -1;
	
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	public void setMapper(ReplyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<ReplyVO> getList(Criteria criteria, Long bno) {
		return mapper.getListWithPaging(criteria, bno);
	}

	@Transactional
	@Override
	public int register(ReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), REPLY_ADD);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Transactional
	@Override
	public int remove(Long rno) {		
		boardMapper.updateReplyCnt(mapper.read(rno).getBno(),REPLY_DEL);
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

}

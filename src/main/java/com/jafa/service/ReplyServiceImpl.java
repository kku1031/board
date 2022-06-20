package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.mapper.ReplyMapper;
import com.jafa.model.Criteria;
import com.jafa.model.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	private ReplyMapper mapper;
	
	@Autowired
	public void setMapper(ReplyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<ReplyVO> getList(Criteria criteria, Long bno) {
		return mapper.getListWithPaging(criteria, bno);
	}

	@Override
	public int register(ReplyVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

}

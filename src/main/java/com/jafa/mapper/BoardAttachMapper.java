package com.jafa.mapper;

import java.util.List;

import com.jafa.model.BoardAttachVO;

public interface BoardAttachMapper {

	void insert(BoardAttachVO vo);
	void delete(String uuid);
	List<BoardAttachVO> findByBno(Long bno);
	void deleteAll(Long bno);
}

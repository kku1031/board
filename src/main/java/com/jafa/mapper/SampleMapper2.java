package com.jafa.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper2 {

	@Insert("insert into tb_sp2 values (#{data})")
	int insertCol(String data);
}

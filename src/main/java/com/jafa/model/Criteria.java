package com.jafa.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Criteria {

	int page;
	int perPageNum;
	
	String type; // 제목+ 내용 같이 쓰려고 배열로 처리
	String keyword;	
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPageStart() {
		return (this.page-1)*perPageNum;
	}
	
	public String[] getTypeCollection() {		
		return type !=null ? type.split("") : new String[] {};
	}
}

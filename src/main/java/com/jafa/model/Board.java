package com.jafa.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Board {
	private Long bno;
	private String title;
	private String content;  
	private String writer; 
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}

package com.jafa.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class MemberVO {
	private String userId;
	private String userPw;
	private String userName;
	private boolean enabled;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private  List<AuthVO> authList;
	
	
}

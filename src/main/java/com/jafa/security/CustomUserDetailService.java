package com.jafa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jafa.mapper.MemberMapper;
import com.jafa.model.MemberVO;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = mapper.read(username);
		return memberVO != null ? new CustomUser(memberVO) : null;
	}

	
}

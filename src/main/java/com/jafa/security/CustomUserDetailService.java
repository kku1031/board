package com.jafa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jafa.mapper.MemberMapper;
import com.jafa.model.MemberVO;

@Component
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberVO memberVO = mapper.read(username);
		if(memberVO==null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new CustomUser(memberVO);
	}

	
}

package com.jafa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jafa.security.CustomLoginSuccessHandler;
import com.jafa.security.CustomNoopPasswordEncoder;
import com.jafa.security.CustomUserDetailService;

@Configuration
public class SecurityBean {

	@Bean
	public AuthenticationSuccessHandler loginmSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public PasswordEncoder 뽈롱() {
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	public PasswordEncoder encoder(){
		return new CustomNoopPasswordEncoder();
	}
}

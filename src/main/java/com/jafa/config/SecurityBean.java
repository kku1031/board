package com.jafa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jafa.security.CustomLoginSuccessHandler;
import com.jafa.security.CustomNoopPasswordEncoder;
import com.jafa.security.CustomUserDetailService;
import com.jafa.security.LoginFailureHandler;

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
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		LoginFailureHandler lf = new LoginFailureHandler();
		lf.setLoginId("loginId");
		lf.setLoginPw("loginPw");
		lf.setErrorMessage("errorMessage");
		lf.setDefaultFailureUrl("/customLogin");
		return lf;
	}
}

package com.jafa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.jafa.security.CustomNoopPasswordEncoder;

@Configuration
public class SecurityBean {

//	@Bean
//	public AuthenticationSuccessHandler loginSuccessHandler() {
//		return new CustomLoginSuccessHandler();
//	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new CustomUserDetailService();
//	}
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PasswordEncoder 뽈롱() {
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	public PasswordEncoder encoder(){
		return new CustomNoopPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
//	@Bean
//	public AuthenticationFailureHandler failureHandler() {
//		LoginFailureHandler lf = new LoginFailureHandler();
//		lf.setLoginId("loginId");
//		lf.setLoginPw("loginPw");
//		lf.setErrorMessage("errorMessage");
//		lf.setDefaultFailureUrl("/customLogin");
//		return lf;
//	}
}

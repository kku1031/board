package com.jafa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Import(value = {SecurityBean.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	AuthenticationSuccessHandler loginSuccessHandler;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier(value = "뽈롱")
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationFailureHandler failureHandler;
	
	@Override	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
//		auth.inMemoryAuthentication()
//			.withUser("kku1031").password("{noop}1234").roles("MEMBER");
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}1234").roles("MEMBER", "ADMIN");	
			
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/security/all").permitAll()
			.antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/security/member").access("hasRole('ROLE_MEMBER')");
		http.formLogin()
			.usernameParameter("loginId")
			.passwordParameter("loginPw")
			.loginPage("/customLogin")
			.loginProcessingUrl("/member/login")
			.successHandler(loginSuccessHandler)
			.failureHandler(failureHandler);
			
		http.logout()
			.logoutUrl("/customLogout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me","JSESSION_ID");
		
	}
	
}

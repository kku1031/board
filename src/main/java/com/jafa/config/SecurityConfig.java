package com.jafa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
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
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@Import(value = {SecurityBean.class})
@ComponentScan("com.jafa.security")
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
	
	@Autowired
	PersistentTokenRepository persistentTokenRepository;
	
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
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
				filter.setEncoding("utf-8");
				filter.setForceEncoding(true);
				http.addFilterBefore(filter, CsrfFilter.class);
		
		http.csrf()
			.ignoringAntMatchers("/uploadAjaxAction","/deleteFile",
						"/replies/**");
		
		http.authorizeRequests()
			.antMatchers("/security/all").permitAll()
			.antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/security/member").access("hasRole('ROLE_MEMBER')")
			
		.and()
			.formLogin()
			.usernameParameter("loginId")
			.passwordParameter("loginPw")
			.loginPage("/customLogin")
			.loginProcessingUrl("/member/login")
			.successHandler(loginSuccessHandler)
			.failureHandler(failureHandler);
		http.rememberMe().key("project")
			.tokenRepository(persistentTokenRepository)
			.tokenValiditySeconds(604800);
			
		http.logout()
			.logoutUrl("/customLogout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me","JSESSION_ID");
		
	}
	
}

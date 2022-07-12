package com.jafa.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Value("loginId")
	private String loginId;
	
	@Value("loginPw")
	private String loginPw;
	
	@Value("errorMessage")
	private String errorMessage;
	
	@Value("/customLogin")
	private String defaultFailureUrl;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("로그인실패!!!");
		String username = request.getParameter(loginId);
		String password = request.getParameter(loginPw);
		String errorMsg = request.getParameter(errorMessage);
		
		if(exception instanceof BadCredentialsException) {
			errorMsg = "비밀번호 또는 아이디가 일치하지 않습니다.";
		} 
		
		request.setAttribute(loginId, username);
		request.setAttribute(loginPw, password);
		request.setAttribute(errorMessage, errorMsg);
		
		request.getRequestDispatcher(defaultFailureUrl)
				.forward(request, response);
		
	}

}

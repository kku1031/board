package com.jafa.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jafa.exception.NotMatchUserIdException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotMatchUserIdException.class)
	public String notMatchUserId() {
		System.out.println("NotMatchUserIdException 예외 발생");
		return "member/myPage_error";
	}
}

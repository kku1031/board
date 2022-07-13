package com.jafa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityTestController {
	
	@GetMapping("/all")
	public String doAll(@CookieValue(name = "myCookie", required = false) String myCookie, HttpServletRequest request) {
//				
//		Cookie[] cookies = request.getCookies();
//				
//		if(cookies != null) {
//			for(Cookie cookie : cookies) {
//				System.out.println("쿠키 이름" + cookie.getName());
//				System.out.println("쿠키 값" + cookie.getValue());
//			}
//			
//		}
		
		System.out.println(myCookie);
		
		System.out.println("모든 방문자");
		return "member/all";
	}
	
	@GetMapping("/member")
	public String doMember() {
		System.out.println("회원만");
		return "member/member";
	}
	
	@GetMapping("/admin")
	public String doAdmin() {
		System.out.println("관리자만");
		return "member/admin";
	}
}

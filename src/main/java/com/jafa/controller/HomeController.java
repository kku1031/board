package com.jafa.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(HttpServletResponse response) {
		Cookie cookie = new Cookie("myCookie","쿠키냠냠");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		return "home";
	}
	
	
	
	
	
}

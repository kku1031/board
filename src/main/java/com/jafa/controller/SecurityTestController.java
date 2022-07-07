package com.jafa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityTestController {
	
	@GetMapping("/all")
	public void doAll() {
		System.out.println("모든 방문");
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

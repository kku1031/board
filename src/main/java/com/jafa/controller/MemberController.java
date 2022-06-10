package com.jafa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/list")
	public String memberList() {
		System.out.println("회원목록");
		return "member/list";
	}
	
	@GetMapping("/register")
	public String memberRegister() {
		System.out.println("회원가입");
		return "member/register";
	}
}

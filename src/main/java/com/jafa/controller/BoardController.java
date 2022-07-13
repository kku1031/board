package com.jafa.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.model.Board;
import com.jafa.model.BoardAttachVO;
import com.jafa.model.Criteria;
import com.jafa.model.PageMaker;
import com.jafa.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/list")
	public String boardList(Model model, Criteria criteria) {
		PageMaker pagemaker = new PageMaker(criteria, service.totalCount(criteria));
		model.addAttribute("list",service.getList(criteria));
		model.addAttribute("pageMaker",pagemaker);
		return "board/list";
	}
	
	@GetMapping("/get")
	public String get(Long bno, Model model,
			@CookieValue(required = false) Cookie viewCount,
			 HttpServletRequest request, HttpServletResponse response){
		boolean isAddCount = false; // 조회수를 증가 시킬지 여부
		if(viewCount!=null) {
			// 이름이 viewCount인 쿠키가 있을 때
			String[] viewed = viewCount.getValue().split("/"); // 쿠키값 문자열을 배열로
			// contains() 메소드 사용을 위해 리스트 컬렉션으로 변환
			List<String> viewedList = Arrays.stream(viewed).collect(Collectors.toList());
			if(!viewedList.contains(bno.toString())) { // 조회한 게시물번호가 없다면
				viewCount.setValue(viewCount.getValue()+bno+"/"); // 기존 쿠키값 조회한 게시물
				response.addCookie(viewCount); // 쿠키 업데이트
				isAddCount = true; // 조회수 증가 매퍼 실행
			}
			
		} else {
			// 이름이 viewCount인 쿠키가 없을 때
			Cookie cookie = new Cookie("viewCount", bno+"/");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		}
		
		// 두 번째 인수 : 조회수 증가 여부
		model.addAttribute("board",service.get(bno,isAddCount));
		return "board/get";
	}
	
	@GetMapping("/modify")
	public String modifyForm(Long bno, Model model) {
		model.addAttribute("board",service.get(bno,false));
		return "board/modify";
	}
	
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(Board board, RedirectAttributes rttr) {
		service.modify(board);
		rttr.addFlashAttribute("message", board.getBno());
		return "redirect:list";
	}
	
	@PreAuthorize("principal.username == #writer") //작성자외에 사용할 수 없도록 설정
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr, String writer) {		
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		deleteFiles(attachList);
		
		service.remove(bno);		
		rttr.addFlashAttribute("message", bno);
		return "redirect:list";
	}	

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public String registerForm(Board board, Model model) {
		return "board/register";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String register(Board board, RedirectAttributes rttr) {		
		service.register(board);
		rttr.addFlashAttribute("message", board.getBno());
		return "redirect:list";
	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		return new ResponseEntity<>(attachList, HttpStatus.OK);
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size()==0) return;
		attachList.forEach(attach -> {
			// uploadPath, uuid, fileName			
			Path file = Paths.get("C:/storage/"+attach.getUploadPath()+"/"+attach.getUuid()+"_"+attach.getFileName());
			try {
				Files.deleteIfExists(file); //일반 파일까지 삭제
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:/storage/"+attach.getUploadPath()+"/s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.deleteIfExists(thumbNail);
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
	}	
}

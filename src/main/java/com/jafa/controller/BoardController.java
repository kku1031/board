package com.jafa.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String get(Long bno, Model model) {
		model.addAttribute("board",service.get(bno));
		return "board/get";
	}
	
	@GetMapping("/modify")
	public String modifyForm(Long bno, Model model) {
		model.addAttribute("board",service.get(bno));
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(Board board, RedirectAttributes rttr) {
		service.modify(board);
		rttr.addFlashAttribute("message", board.getBno());
		return "redirect:list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		deleteFiles(attachList);
		
		service.remove(bno);		
		rttr.addFlashAttribute("message", bno);
		return "redirect:list";
	}	

	@GetMapping("/register")
	public String registerForm(Board board, Model model) {
		model.addAttribute("board",board);
		return "board/register";
	}
	
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

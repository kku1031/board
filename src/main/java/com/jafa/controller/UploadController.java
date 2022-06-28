package com.jafa.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void upLoadForm() {
		
	}
	
	@PostMapping("uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {
		for(MultipartFile file : uploadFile) {
			System.out.println("==================");
			System.out.println("파일 이름 : "+file.getOriginalFilename());
			System.out.println("파일 사이즈 : "+file.getSize());
			File saveFile = new File("C:/storage/temp", file.getOriginalFilename());
			try {
				file.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}			
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {}
	
	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		File uploadPath = new File("c:/storage", getFolder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs(); // c:\\storage\\2022\\06\\22
		}			
		for(MultipartFile multipartFile : uploadFile) {
			File savefile = new File(uploadPath, multipartFile.getOriginalFilename());
			try {				
				multipartFile.transferTo(savefile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-", File.separator);
		// 2022/06/28
	}
}

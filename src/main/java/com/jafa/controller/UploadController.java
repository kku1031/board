package com.jafa.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.model.AttachFileDTO;

import net.coobird.thumbnailator.Thumbnailator;


@Controller
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void upLoadForm() {
		
	}
	
	@PostMapping("uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {
		
		for(MultipartFile file : uploadFile) {
			String uploadFileName = file.getOriginalFilename();
			
			
			
			File saveFile = new File("C:/storage/temp", uploadFileName);
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
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>(); //리스트 생성
		
		File uploadPath = new File("c:/storage", getFolder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs(); // c:\\storage\\2022\\06\\22
		}			
		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachFileDTO = new AttachFileDTO(); // 객체 생성
			String uploadFileName = multipartFile.getOriginalFilename();
			
			attachFileDTO.setFileName(uploadFileName); //uuid 적용전 원본 파일 세팅
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File savefile = new File(uploadPath, uploadFileName);
			try {				
				multipartFile.transferTo(savefile);
				attachFileDTO.setUuid(uuid.toString()); //uuid
				attachFileDTO.setUploadPath(getFolder()); // 업로드 폴더
								
				if(checkImageType(savefile)) {
					
					attachFileDTO.setImage(true); // 이미지 여부
					
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath,"S_"+uploadFileName));
					Thumbnailator
					.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
				}
				
				list.add(attachFileDTO); // 리스트 추가
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<AttachFileDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File("C:\\storage\\"+fileName);
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(
					FileCopyUtils.copyToByteArray(file),
					headers,
					HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-", File.separator);
		// 2022/06/28
		
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}

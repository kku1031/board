package com.jafa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class AttachFileDTO {

	private String filename;
	private String uploadPath;
	private String uuid;
	private boolean image;
	
}

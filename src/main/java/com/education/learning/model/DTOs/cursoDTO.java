package com.education.learning.model.DTOs;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class cursoDTO {
	private MultipartFile arquivo;
	private String nome;
}

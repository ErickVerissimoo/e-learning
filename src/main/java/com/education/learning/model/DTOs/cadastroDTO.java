package com.education.learning.model.DTOs;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class cadastroDTO {
	@Valid
	private subadminDTO subadmi = null;
	@Valid
	private alunoDTO aluno = null;
	private loginGeneric log = null;
	

}

package com.education.learning.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class subadminDTO {
	
private String nome;
private String senha;
@Pattern(regexp = ".*empresa.*", message = "email inválido")
@Email(message="email inválido")
private String email;
private String identificacao;
}

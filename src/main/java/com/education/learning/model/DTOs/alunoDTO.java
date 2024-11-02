package com.education.learning.model.DTOs;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class alunoDTO {
	
private String nome;
@Email(message = "email inválido")
private String email;
@Length(min = 10)
private String senha;
private String identificacao;
}
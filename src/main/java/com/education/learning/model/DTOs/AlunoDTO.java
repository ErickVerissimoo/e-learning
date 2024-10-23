package com.education.learning.model.DTOs;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public final class AlunoDTO {
	@NotBlank
	private String nome;
	@Email(message = "email inválido")
	private String email;
	@Size(min = 8)
	private String senha;
	@Nullable
	private String identificacao;
}

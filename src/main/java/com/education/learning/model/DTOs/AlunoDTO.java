package com.education.learning.model.DTOs;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public final class AlunoDTO {
	private String nome;
	private String email;
	private String senha;
	@Nullable
	private String identificacao;
}

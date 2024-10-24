package com.education.learning.model.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class loginGeneric {
	private String nome;
	private String senha;
	private String email;

}

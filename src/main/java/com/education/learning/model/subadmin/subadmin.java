package com.education.learning.model.subadmin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class subadmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String senha;
	private String nome;
	@NotNull(message = "indentificação não pode ser nula")
	private String identificacao;
	@Email(message = "Endereço de email invalido")
	private String email;

}

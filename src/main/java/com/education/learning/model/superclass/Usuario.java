package com.education.learning.model.superclass;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
//Não será mapeado para o banco de dados e permite que entidades herdem
@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Senha não pode ser nula")
	private String senha;
	@Email(message = "email inválido")
	private String email;
	@Column(unique = true, updatable = true)
	private String identificacao;
	private String nome;
}

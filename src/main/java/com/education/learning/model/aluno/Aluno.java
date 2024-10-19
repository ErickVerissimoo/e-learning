package com.education.learning.model.aluno;

import java.util.Set;

import com.education.learning.model.curso.Curso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Aluno {

	private String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany(fetch = FetchType.LAZY)
	@Column(nullable = true)
	private Set<Curso> curso;
	@Column(updatable = false, unique = true)
	private String identificacao;
	@Email(message = "Email inválido")
	@Column(unique = true, updatable = true)
	private String email;
	private String senha;


}

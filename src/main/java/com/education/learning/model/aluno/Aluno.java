package com.education.learning.model.aluno;

import java.util.Set;
import com.education.learning.model.curso.Curso;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	
	private String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	private Set<Curso> curso;
	private String identificacao;
	private String email;
	private String senha;
	@Getter
	@Transient
	private static final String tipo = "funcionario";
	
}

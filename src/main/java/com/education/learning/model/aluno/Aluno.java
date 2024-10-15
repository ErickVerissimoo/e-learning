package com.education.learning.model.aluno;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.education.learning.model.curso.Curso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Component
@Data
public class Aluno {
	
	private String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	private Set<Curso> curso;
	private String identificacao;
	
}

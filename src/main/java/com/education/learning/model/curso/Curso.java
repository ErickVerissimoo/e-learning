package com.education.learning.model.curso;

import java.util.Collection;

import com.education.learning.model.aluno.Aluno;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
@Entity
@Data
@Builder
@Table(name = "curso")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany(mappedBy = "cursos")
	Collection<Aluno> alunos;
	@Lob
	private byte[] dados;
	private boolean isCompleted;
	private byte[] pdf;
}

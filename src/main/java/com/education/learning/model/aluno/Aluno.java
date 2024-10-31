package com.education.learning.model.aluno;

import java.util.Set;

import com.education.learning.model.curso.Curso;
import com.education.learning.model.superclass.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "aluno")
@NoArgsConstructor
public final class Aluno extends Usuario {

	@Getter
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aluno_curso", joinColumns= @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name= "curso_id"))
	@Column(nullable = true)
	private Set<Curso> cursos;


}

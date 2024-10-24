package com.education.learning.model.aluno;

import java.util.Set;

import com.education.learning.model.curso.Curso;
import com.education.learning.model.superclass.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "aluno")
@NoArgsConstructor
public final class Aluno extends Usuario {


	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "alunos" )
	@Column(nullable = true)
	private Set<Curso> curso;



}

package com.education.learning.model.aluno;

import java.util.Set;

import com.education.learning.model.curso.Curso;
import com.education.learning.model.superclass.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
public final class Aluno extends Usuario {


	@ManyToMany(fetch = FetchType.LAZY)
	@Column(nullable = true)
	private Set<Curso> curso;



}

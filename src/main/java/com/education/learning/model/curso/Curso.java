package com.education.learning.model.curso;

import org.springframework.stereotype.Component;

import com.education.learning.model.aluno.Aluno;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import lombok.Data;
@Entity
@Component
@Data
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	private Aluno aluno;
	@Lob
	private byte[] dados;
}

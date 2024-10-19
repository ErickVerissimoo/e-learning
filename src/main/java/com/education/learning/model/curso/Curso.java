package com.education.learning.model.curso;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.certificado.Certificado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Component
@Data
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany
	@Column(nullable = true)
	Collection<Aluno> aluno;
	@Lob
	private byte[] dados;
	private boolean isCompleted;
	@OneToOne(mappedBy = "curso")
    @JoinColumn(name = "certificado_id")
	private Certificado certificado;
}

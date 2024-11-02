package com.education.learning.model.avaliacao;

import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.curso.Curso;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
public class Avaliacao {
@Id
@GeneratedValue
private Integer id;	
@ManyToOne
private Aluno aluno;
@ManyToOne(fetch = FetchType.LAZY)
private Curso curso;
@Min(value = 0)
@Max(5)
private Integer nota;
@Getter
private String comentario;
}

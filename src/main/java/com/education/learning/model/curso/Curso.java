package com.education.learning.model.curso;

import java.util.Collection;
import java.util.List;

import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.avaliacao.Avaliacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Builder
@Table(name = "curso")
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany(mappedBy = "cursos")
	Collection<Aluno> alunos;
	@Lob
	private byte[] dados;
   


    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted = false;
    
    
	private byte[] pdf;
	@OneToMany(mappedBy="")
	List<Avaliacao> avaliacao;
}

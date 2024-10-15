package com.education.learning.model.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class alunoService {
	@Autowired
	private alunoRepository repo;
	
	public List<Aluno> allAlunos(){
		return repo.findAll();
	}
	
	public void Adicionar(Aluno aluno) {
		repo.save(aluno);
	}
}

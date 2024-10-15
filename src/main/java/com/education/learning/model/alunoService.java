package com.education.learning.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class alunoService {
	@Autowired
	private alunoRepository repo;
	
	public List<aluno> allAlunos(){
		return repo.findAll();
	}
	
	public void Adicionar(aluno aluno) {
		repo.save(aluno);
	}
}

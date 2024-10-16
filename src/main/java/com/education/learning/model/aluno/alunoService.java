package com.education.learning.model.aluno;

import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class alunoService {
	@Autowired
	private alunoRepository repo;
	
	public List<Aluno> allAlunos(){
		return repo.findAll();
	}
	
	public void Cadastrar(Aluno aluno) {
		String identificador = gerarIdentificador();
		aluno.setIdentificacao(identificador);
		
		
		
			repo.save(aluno);
			
		
		
	}
	
	private static String gerarIdentificador() {
		
		SecureRandom random = new SecureRandom();
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i<10;i++) {
			builder.append(random.nextInt(0, 10));
		}
		String fine = new String (builder);
		return fine;
	}
}
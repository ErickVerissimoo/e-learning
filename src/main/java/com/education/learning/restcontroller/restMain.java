package com.education.learning.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.learning.model.aluno;
import com.education.learning.model.alunoService;

@RestController
public class restMain {
	@Autowired
	alunoService rep;
	@Autowired
	private aluno aluns;
	
	@PostMapping(value = "/Cadastrar")
	public ResponseEntity<String> Adicionar(@RequestParam(name = "nome") String nome){
		
		aluns.setNome(nome);
		rep.Adicionar(aluns);
		return new ResponseEntity<>("Aluno adicionado", HttpStatus.ACCEPTED);
		
	}
	
	
	
	
}

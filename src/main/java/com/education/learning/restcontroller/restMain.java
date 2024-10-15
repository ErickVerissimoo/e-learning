package com.education.learning.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;

@RestController
public class restMain {

	
	
	@Autowired
	alunoService rep;
	@Autowired
	private Aluno aluns;
	
	@PostMapping(value = "/Cadastrar")
	@ResponseBody
	public ResponseEntity<String> Adicionar(@RequestParam(name = "nome") String nome){
		
		aluns.setNome(nome);
		rep.Adicionar(aluns);
		return new ResponseEntity<>("Aluno adicionado", HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(value ="*/Deletar")
	public ResponseEntity<String> deleletar(@RequestParam(required = true) String identificador){
		
		if(identificador.matches("^[0-9]+") && identificador.matches(".*[abd].*")){
			
		}else if(identificador.matches("^[0-9]+$")) {
			
		}else {
			return new ResponseEntity<>("ID inválido ou não encontrado", ResponseEntity.badRequest().build().getStatusCode());
			
			
		}
		
		
		
		
		
		
		return new ResponseEntity<>("Usuario deletado", ResponseEntity.ok("Deletado").getStatusCode());
	}
	
}

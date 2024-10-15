package com.education.learning.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;

@RestController
public class restMain {
	private static final String NUMEROS = "123456789";
	private static final String CARACTERES_SUBAMIN = "abd";
	private static final String tudo = NUMEROS + CARACTERES_SUBAMIN;
	
	
	
	@Autowired
	alunoService rep;
	@Autowired
	private Aluno aluns;
	
	@PostMapping(value = "*/Cadastrar")
	public ResponseEntity<String> Adicionar(@RequestParam(name = "nome") String nome){
		
		aluns.setNome(nome);
		rep.Adicionar(aluns);
		return new ResponseEntity<>("Aluno adicionado", HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(value ="*/Deletar")
	public ResponseEntity<String> deleletar(@RequestParam(required = true) String id){
		for(char caracter : NUMEROS.toCharArray()) {
			String charac = String.valueOf(caracter);
			if(id.contains(charac)) {
				for(char carace : CARACTERES_SUBAMIN.toCharArray()) {
					String cars = String.valueOf(carace);
					if(id.contains(cars)) {
						
					}
				}
			}else {
				return new ResponseEntity<>("Não encontrado", ResponseEntity.badRequest().build().getStatusCode());
			}
			
		}
		
		if(id.contains("")) {
		
		}
	
		
		return new ResponseEntity<>("Usuario deletado", ResponseEntity.ok("Deletado").getStatusCode());
	}
	
}

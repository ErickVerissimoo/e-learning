package com.education.learning.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;

@RestController
public final class restMain {
	
	@Autowired
	alunoService rep;
	
	
	@PostMapping(value = "/Cadastrar")
	public ResponseEntity<String> Adicionar(@RequestParam("nome") String nome, @RequestParam("email") String email) {
		
		
		rep.Cadastrar(Aluno.builder().nome(nome).email(email).build());
		return new ResponseEntity<>("Aluno adicionado", HttpStatus.ACCEPTED);
		
	}

	@DeleteMapping(value = "/Deletar")
	public ResponseEntity<String> deleletar(@RequestParam("identificador") String identificador) {

		if (identificador.matches(".*[0-9].*") && identificador.matches(".*[abd].*")) {
			System.out.println("Funcionario");
		} else if (identificador.matches("^[0-9]+")) {
			System.out.println("Usuario");
		} else {
			return new ResponseEntity<>("ID inválido ou não encontrado",
					ResponseEntity.badRequest().build().getStatusCode());

		}

		return new ResponseEntity<>("Usuario deletado", ResponseEntity.ok("Deletado").getStatusCode());
	}
	@PutMapping("/Alterar")
	public ResponseEntity<String> alterar(@RequestParam(required = true) String identificador){
	
		
		
		return new ResponseEntity<>("Alterado",  ResponseEntity.accepted().build().getStatusCode());
		
	}
	
	@PostMapping("/Login")
	public ResponseEntity<String> login(@RequestParam("email")String email, @RequestParam(required = false) String identificador, @RequestParam(required = true) String senha){

		rep.Validar(email, senha);
		
		return new ResponseEntity<>("Alterado",  ResponseEntity.accepted().build().getStatusCode());
		
	}
	
	
}

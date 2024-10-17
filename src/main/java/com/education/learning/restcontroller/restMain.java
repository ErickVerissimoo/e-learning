package com.education.learning.restcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;
import com.education.learning.model.curso.Curso;
import com.education.learning.model.curso.cursoService;
import com.education.learning.model.subadmin.subadmin;
import com.education.learning.model.subadmin.subadminService;

@RestController
public final class restMain {

	@Autowired
	private alunoService rep;
	@Autowired
	private cursoService serv;
	@Autowired
	private subadminService sub;
	@PostMapping(value = "/Cadastrar")
	public ResponseEntity<String> Adicionar(@RequestParam("nome") String nome, @RequestParam("email") String email, @RequestParam("tipo") String tipo) {
		if(tipo.equalsIgnoreCase(Aluno.getTipo())) {
			rep.Cadastrar(Aluno.builder().nome(nome).email(email).build());

		}else if(tipo.equalsIgnoreCase(subadmin.getTipo())) {
			sub.Cadastrar(subadmin.builder().nome(nome).email(email).build());
		}
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
	public ResponseEntity<String> alterar(@RequestParam(required = true) String identificador) {

		return new ResponseEntity<>("Alterado", ResponseEntity.accepted().build().getStatusCode());

	}

	@PostMapping("/Login")
	public ResponseEntity<String> login(@RequestParam("email") String email,
			@RequestParam(required = false) String identificador, @RequestParam(required = true) String senha) {
if(	rep.isValid(email, senha)) {
	
}
	

		return new ResponseEntity<>("Alterado", ResponseEntity.accepted().build().getStatusCode());

	}
	@GetMapping("/Todos")
	public List<Aluno> alunos(){
		return rep.allAlunos();
	}
	
	@PostMapping("/CursoAdd")
	public String uploadCurso(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		ByteArrayInputStream bites = new ByteArrayInputStream(file.getBytes());
		serv.gravar(bites.readAllBytes(), new Curso());
		return "Curso salvo";
	}
	@GetMapping("/AssistirVideo")
	public byte[] assistir (@RequestParam("id") String id) {
		return serv.RetornarVideo(id).getDados();
	}
	
}

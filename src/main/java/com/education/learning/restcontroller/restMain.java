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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;
import com.education.learning.model.curso.Curso;
import com.education.learning.model.curso.cursoService;
import com.education.learning.model.subadmin.subadminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

@RestController
public final class restMain {

	@Autowired
	private alunoService rep;
	@Autowired
	private cursoService serv;
	@Autowired
	private subadminService sub;

	@PostMapping(value = { "/Usuario/Cadastrar", "/Funcionarios/Cadastrar" })
	public ResponseEntity<String> Adicionar(@RequestParam(name = "nome", required = true) String nome,
			@RequestParam(name = "email", required = true) String email, @RequestParam(value = "senha", required= true) String senha,

			@NotNull HttpServletRequest requisicao) {

		if (requisicao.getRequestURI().equals("/Usuario/Cadastrar")) {
			rep.Cadastrar(rep.getInstance(email, senha, nome));

		}

		else if (requisicao.getRequestURI().equals("/Funcionarios/Cadastrar")) {
			System.out.println(requisicao.getRequestURI());

		}

		return new ResponseEntity<>(" adicionado", HttpStatus.ACCEPTED);

	}

	@DeleteMapping(value = { "/Usuario/Deletar", "/Funcionario/Deletar" })
	public ResponseEntity<String> deleletar(
			@RequestParam(name = "identificador", required = false) String identificador,
			@RequestParam(required = true) String id) {

		if (identificador != null && identificador.matches(".*[0-9].*") && identificador.matches(".*[abd].*")) {
			System.out.println("Funcionario");
		} else if (identificador != null && identificador.matches("^[0-9]+")) {
			System.out.println("Usuario");
		} else if (id != null) {
			rep.DeletarAluno(id);
		}

		else {
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
	public ResponseEntity<Object> login(@RequestParam("email") String email,
			@RequestParam(required = true) String identificador, @RequestParam(required = true) String senha) {

		if (identificador.matches(".*[0-9].*") && identificador.matches(".*[abd].*")
				&& sub.isValid(identificador, email, senha)) {
			return new ResponseEntity<>(sub.Retornar(identificador, email, senha),
					ResponseEntity.ok("aceito").getStatusCode());
		} else if (identificador.matches("^[0-9]+") && rep.isValid(email, senha, identificador)) {
			return new ResponseEntity<>(rep.Voltar(email, senha, identificador),
					ResponseEntity.ok("Aceito").getStatusCode());
		} else {
			return new ResponseEntity<>("ID inválido ou não encontrado",
					ResponseEntity.badRequest().build().getStatusCode());

		}

	}

	@GetMapping("/Home/Todos")
	public List<Aluno> alunos() {
		return rep.allAlunos();
	}

	@PostMapping("/CursoAdd")
	public String uploadCurso(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		ByteArrayInputStream bites = new ByteArrayInputStream(file.getBytes());
		serv.gravar(bites.readAllBytes(), new Curso());
		return "Curso salvo";
	}

	@GetMapping("/Home/AssistirCurso")
	@ResponseStatus(code = HttpStatus.FOUND)
	public byte[] assistir(@RequestParam("id") String id) {
		return serv.RetornarVideo(id).getDados();
	}

}

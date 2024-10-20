package com.education.learning.restcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.education.learning.model.DTOs.AlunoDTO;
import com.education.learning.model.DTOs.subadminDTO;
import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;
import com.education.learning.model.curso.Curso;
import com.education.learning.model.curso.cursoService;
import com.education.learning.model.subadmin.Subadmin;
import com.education.learning.model.subadmin.subadminService;
import com.education.learning.model.superclass.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/home")
public final class restMain {

	@Autowired
	private alunoService rep;
	@Autowired
	private cursoService serv;
	@Autowired
	private subadminService sub;

	@PostMapping(value = { "/cadastrar", "/colaborador/cadastrar" })
	public final ResponseEntity<String> Adicionar(@RequestBody(required = false) AlunoDTO alunoDTO,
			@RequestBody(required= false) subadminDTO subadminDTO,
			@NotNull HttpServletRequest requisicao) {

		if (requisicao.getRequestURI().equals("/cadastrar")) {
			rep.Cadastrar(Aluno.builder().nome(alunoDTO.getNome()).email(alunoDTO.getEmail()).senha(alunoDTO.getSenha()).build());

		}

		else if (requisicao.getRequestURI().equals("/funcionarios/cadastrar")) {
			sub.Cadastrar(Subadmin.builder().nome(subadminDTO.getNome()).email(subadminDTO.getEmail()).senha(subadminDTO.getSenha()).build());

		} else {
			throw new UnsupportedOperationException("Cadastro inválido");
		}

		return new ResponseEntity<>(" adicionado", HttpStatus.ACCEPTED);

	}

	@DeleteMapping(value = { "/usuario/deletar", "/funcionarios/deletar" })
	public final ResponseEntity<String> deleletar(
			@RequestParam(name = "identificador", required = false) String identificador,
			@RequestParam(required = true) String id, HttpServletRequest req) {

		if (req.getRequestURI().equals("/usuario/deletar")) {
			rep.Deletar(id);
		} else if (req.getRequestURI().equals("/restrito/funcionario/deletar")) {
			sub.Deletar(id);
		}

		else {
			return new ResponseEntity<>("ID inválido ou não encontrado",
					ResponseEntity.badRequest().build().getStatusCode());

		}

		return new ResponseEntity<>("Usuario deletado", ResponseEntity.ok("Deletado").getStatusCode());
	}

	@PutMapping(value = { "/usuario/alterar", "/funcionario/deletar" })
	public ResponseEntity<String> alterar(HttpServletRequest req,
			@RequestParam(name = "identificador", required = true) String identificador,
			@RequestParam(name = "email", required = false) String email, @RequestParam("emailNovo") String novoEmail,
			@RequestParam(name = "senha", required = true) String senhaAtual,
			@RequestParam(name = "senhaNova", required = false) String senhaNova) {
		if (req.getRequestURI().equals("/usuario/alterar")) {
			Aluno alunoDadosAtuais = Aluno.builder().email(email).senha(senhaAtual).identificacao(identificador).build();
			rep.Atualizar(alunoDadosAtuais);


		} else if (req.getRequestURI().equals("/funcionario/deletar")) {

		}
		return new ResponseEntity<>("Alterado", ResponseEntity.accepted().build().getStatusCode());

	}

	@PostMapping("/Login")
	public ResponseEntity<Usuario> login(@RequestParam("email") String email,
			@RequestParam(name = "identificador", required = true) String identificador,
			@RequestParam(name = "senha", required = true) String senha) {

		if (sub.Login(email, senha, identificador)) {
			return new ResponseEntity<>(sub.entrar(identificador, email, senha),
					ResponseEntity.ok("aceito").getStatusCode());
		} else if (rep.Login(email, senha, identificador)) {
			return new ResponseEntity<>(rep.entrar(email, senha, identificador),
					ResponseEntity.ok("aceito").getStatusCode());
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("*/verTodos")
	public List<Aluno> alunos() {
		return rep.getAll();
	}

	@PostMapping("/funcionarios/cursoAdd")
	@ResponseStatus(code = HttpStatus.OK)
	public String uploadCurso(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		ByteArrayInputStream bites = new ByteArrayInputStream(file.getBytes());
		serv.gravar(bites.readAllBytes(), new Curso());

		return "Curso salvo";
	}

	@GetMapping(value = { "/procurar/{curso}", "*/{curso}" })
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Curso assistir(@PathVariable("curso") String nome) {
		return serv.cursoDados(nome);
	}

	@GetMapping("*/procurar")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Curso> procura(@RequestParam(name = "nome") String nome) {
		return serv.procura(nome);
	}

}

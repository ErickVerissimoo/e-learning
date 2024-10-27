package com.education.learning.restcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.education.learning.model.DTOs.alunoDTO;
import com.education.learning.model.DTOs.cadastroDTO;
import com.education.learning.model.DTOs.subadminDTO;
import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;
import com.education.learning.model.curso.Curso;
import com.education.learning.model.curso.cursoService;
import com.education.learning.model.subadmin.Subadmin;
import com.education.learning.model.subadmin.subadminService;
import com.education.learning.model.superclass.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Cleanup;

@RestController
@RequestMapping(value = "/home")
@CrossOrigin(origins = "*")
public final class restMain {

	@Autowired
	private alunoService rep;
	@Autowired
	private cursoService serv;
	@Autowired
	private subadminService sub;

	@PostMapping(value = { "/cadastrar", "/colaborador/cadastrar" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<String> Adicionar(@RequestBody cadastroDTO cadastro, HttpServletRequest requisicao) {

		alunoDTO alunus = cadastro.getAluno();
		subadminDTO subDTO = cadastro.getSubadmi();

		if (requisicao.getRequestURI().equals("/home/cadastrar") && alunus != null) {

			rep.Cadastrar(
					Aluno.builder().nome(alunus.getNome()).email(alunus.getEmail()).senha(alunus.getSenha()).build());

		}

		else if (requisicao.getRequestURI().equals("/home/funcionarios/cadastrar") && subDTO != null) {

			sub.Cadastrar(Subadmin.builder().nome(subDTO.getNome()).email(subDTO.getEmail()).senha(subDTO.getSenha())
					.build());

		} else {
			throw new UnsupportedOperationException("Cadastro inválido");
		}

		return new ResponseEntity<>(" adicionado", HttpStatus.ACCEPTED);

	}

	@DeleteMapping(value = { "/usuario/deletar", "/funcionarios/deletar" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<String> deleletar(@RequestParam(name = "id") String id, HttpServletRequest req) {

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

	@PutMapping(value = { "/usuario/alterar", "/funcionarios/deletar" })
	public ResponseEntity<String> alterar(HttpServletRequest req, @RequestBody(required = false) cadastroDTO userDTO,
			@RequestParam(name = "emailNovo", required = false) String novoEmail,

			@RequestParam(name = "senhaNova", required = false) String novaSenha) {
		if (req.getRequestURI().equals("/usuario/alterar") && userDTO != null) {
			alunoDTO velho = userDTO.getAluno();
			Aluno alunoDadosAtuais = Aluno.builder().email(novoEmail).senha(novaSenha)
					.identificacao(velho.getIdentificacao()).build();
			rep.Atualizar(alunoDadosAtuais);

		} else if (req.getRequestURI().equals("/funcionario/deletar") && userDTO != null) {
			subadminDTO velho = userDTO.getSubadmi();
			Subadmin sube = Subadmin.builder().email(mainHelper.atualiza(velho.getEmail(), novoEmail))
					.senha(mainHelper.atualiza(velho.getSenha(), novaSenha)).build();
			sub.Atualizar(sube);
		}
		return new ResponseEntity<>("Alterado", ResponseEntity.accepted().build().getStatusCode());

	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody(required = true) cadastroDTO cadastro) {
		var log = cadastro.getLog();
		if (sub.Login(log.getEmail(), log.getSenha(), log.getNome())) {
			Usuario usuario = sub.entrar(log.getEmail(), log.getSenha(), log.getNome());
			return ResponseEntity.ok(usuario);
		} else if (rep.Login(log.getEmail(), log.getSenha(), log.getNome())) {
			Usuario usuario = rep.entrar(log.getEmail(), log.getSenha(), log.getNome());
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.badRequest().body("Credenciais inválidas.");
		}
	}

	@GetMapping(value = "/verTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<Aluno> alunos() {
		return rep.getAll();
	}

	@PostMapping("/funcionarios/cursoAdd")
	@ResponseStatus(code = HttpStatus.OK)
	public String uploadCurso(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {

		@Cleanup
		ByteArrayInputStream bites = new ByteArrayInputStream(file.getBytes());
		serv.gravar(bites.readAllBytes(), new Curso());

		return "Curso salvo";
	}

	@GetMapping(value = { "/procurar/{curso}", "*/{curso}" })
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Curso assistir(@NotEmpty @PathVariable("curso") String nome) {
		return serv.cursoDados(nome);
	}

	@GetMapping("*/procurar")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Curso> procura(@NotBlank @RequestParam(name = "nome") String nome) {
		return serv.procura(nome);
	}

	@DeleteMapping("/funcionarios/resetar")
	public String resetar() {
		rep.apagar();
		return "Reset completo";
	}

	@PostMapping("/funcionarios/{curso}/adicionarCert")
	public ResponseEntity<String> addCertificado(@PathVariable(name = "curso") String curs, MultipartFile pdf)
			throws IOException {
		Curso cr = serv.cursoDados(curs);
		cr.setPdf(pdf.getBytes());
		return ResponseEntity.accepted().build();
	}

}

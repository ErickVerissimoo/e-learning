package com.education.learning.restcontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.education.learning.model.DTOs.cursoDTO;
import com.education.learning.model.DTOs.subadminDTO;
import com.education.learning.model.aluno.Aluno;
import com.education.learning.model.aluno.alunoService;
import com.education.learning.model.avaliacao.Avaliacao;
import com.education.learning.model.avaliacao.avaliacaoService;
import com.education.learning.model.curso.Curso;
import com.education.learning.model.curso.cursoService;
import com.education.learning.model.subadmin.Subadmin;
import com.education.learning.model.subadmin.subadminService;
import com.education.learning.model.superclass.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
	@Autowired
	private avaliacaoService ava;
	
	@PostMapping(value = { "/cadastrar", "/colaborador/cadastrar" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<String> Adicionar(@RequestBody @Validated cadastroDTO cadastro, HttpServletRequest requisicao) {

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
	public ResponseEntity<Object> login(@RequestBody(required = true) cadastroDTO cadastro, HttpSession sessao) {
		var log = cadastro.getLog();
		if (sub.Login(log.getEmail(), log.getSenha(), log.getNome())) {
			Usuario usuario = sub.entrar(log.getEmail(), log.getSenha(), log.getNome());
			sessao.setAttribute("email", usuario.getEmail());
			return ResponseEntity.ok(usuario);
		} else if (rep.Login(log.getEmail(), log.getSenha(), log.getNome())) {
			Usuario usuario = rep.entrar(log.getEmail(), log.getSenha(), log.getNome());
			sessao.setAttribute("email", usuario.getEmail());
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.badRequest().body("Credenciais inválidas.");
		}
	}

	@GetMapping(value = {"/verTodos"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<Aluno> alunos() {
		return rep.getAll();
	}

	@PostMapping("/funcionarios/cursoAdd")
	@ResponseStatus(code = HttpStatus.OK)
	public String uploadCurso(@ModelAttribute cursoDTO curso)
			throws IllegalStateException, IOException {
		
		
		serv.gravar(Curso.builder().dados(curso.getArquivo().getBytes()).nome(curso.getNome()).build());

		return "Curso salvo";
	}

	@GetMapping(value = { "/procurar/{curso}", "*/{curso}" })
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Curso assistir(@NotEmpty @PathVariable("curso") String nome) {
		return serv.cursoDados(nome);
	}

	@GetMapping("/procurar")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Curso> procura(@NotBlank @RequestParam(name = "nome") String nome) {
		return serv.procura(nome);
	}

	@DeleteMapping("/funcionarios/apagar")
	public String resetar() {
		rep.apagar();
		return "Reset completo";
	}

	@PostMapping("/funcionarios/{curso}/adicionarCert")
	public ResponseEntity<String> addCertificado(@PathVariable(name = "curso", required = true) String curs,
			@RequestParam(name = "arq", required = true) @NotNull MultipartFile pdf) throws IOException {
		Curso cr = serv.cursoDados(curs);
		cr.setPdf(pdf.getBytes());
		return ResponseEntity.accepted().build();
	}

	@PostMapping("/{curso}/matricular")
	public String cadastrar(@PathVariable(name = "curso") String curso, HttpSession sessao) {
		Curso curs = serv.cursoDados(curso);
		Aluno alunso = rep.getbyEmail((String) sessao.getAttribute("email"));
		rep.Matricular(curs, alunso);
		return "Matriculado";
	}

	@PostMapping("/logout")
	public String sair(HttpSession sessao) {
		sessao.invalidate();
		return "Usuario deslogado com sucesso";
	}
	@PostMapping("/{curso}/avaliar")
	public String avaliar(@PathVariable(value = "curso") String nome, HttpSession sessao, @RequestBody avaliarDTO avaliacao ) {
		Avaliacao avaliarcao = Avaliacao.builder().aluno(rep.getbyEmail((String)sessao.getAttribute("email"))).comentario(avaliacao.comentario).nota(avaliacao.avaliacao).curso(serv.cursoDados(nome)).build();
		ava.avaliar(avaliarcao);
		return "avaliado";
	}
	protected record avaliarDTO(String comentario, int avaliacao) {}
}

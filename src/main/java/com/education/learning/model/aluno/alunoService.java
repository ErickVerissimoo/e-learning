package com.education.learning.model.aluno;

import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class alunoService   {
	@Autowired
	private alunoRepository repo;
	public Aluno retornar(Long id) {
		return  repo.findById(id).orElseThrow(() -> new NoSuchElementException("Não encontrado"));
	}
public void AlterarDados(String identificador, String senha, String
		senhaAtual, String senhaNOVA) {
	
}

	public List<Aluno> allAlunos() {
		return repo.findAll();
	}

	public void Cadastrar(Aluno aluno) {
		String identificador = gerarIdentificador();
		aluno.setIdentificacao(identificador);
		repo.save(aluno);
	}

	 public static String gerarIdentificador() {
		SecureRandom random = new SecureRandom();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			builder.append(random.nextInt(0, 10));
		}
		String fine = new String(builder);
		return fine;
	}

	public void DeletarAluno(String id) {
		if(repo.existsById(Long.parseLong(id))) {

		repo.deleteById(Long.parseLong(id));}
		else {
			throw new NoSuchElementException("Usuario não existe");
		}
	}

	public void AtualizarEmail(String id, String email) {
		repo.updateEmail(email ,Long.parseLong(id));
	}


	public Boolean isAluno(String email, String senha, String identificacao) {
		if(identificacao == null || identificacao.isEmpty()) {
			return false;
		}
		
		String regex = "^\\d{10}$";
		return repo.Validar(email, senha, identificacao) != null && identificacao.matches(regex);
	}
	public Aluno Voltar(String email, String senha, String identificacao) {
		return repo.Validar(email, senha, identificacao);
	}
	public void DeletarIdentificador(String identificador) {
		repo.DeleteByIdentificador(identificador);
	}
}

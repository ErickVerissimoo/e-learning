package com.education.learning.model.aluno;

import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.learning.model.superclass.userService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class alunoService implements userService<Aluno, String>   {
	@Autowired
	private alunoRepository repo;
	public Aluno retornar(Long id) {
		return  repo.findById(id).orElseThrow(() -> new NoSuchElementException("Não encontrado"));
	}
	@Override
public void Atualizar(Aluno atualizar)  throws EntityNotFoundException{

}

	@Override
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


	public void AtualizarEmail(String id, String email) {
		repo.updateEmail(email ,Long.parseLong(id));
	}


	public Boolean isAluno(String email, String senha, String identificacao) {
		if(Objects.isNull(identificacao)) {

			return false;
		}

		String regex = "^\\d{10}$";
		return repo.Validar(email, senha, identificacao) != null && identificacao.matches(regex);
	}


	@Override
	public void Deletar(String id) throws EntityNotFoundException {
		if(repo.existsById(Long.parseLong(id))) {

			repo.deleteById(Long.parseLong(id));}
			else {
				throw new NoSuchElementException("Usuario não existe");
			}
	}
	@Override
	public Aluno Buscar(String id) throws EntityNotFoundException {

		return repo.findById(Long.parseLong(id)).orElseThrow();
	}

	@Override
	public List<Aluno> getAll() {

		return repo.findAll();
	}

	@Override
	public Aluno entrar(String email, String senha, String identificador) {
		Optional<Aluno> teste = Optional.of(repo.Validar(email, senha, identificador));

		return teste.orElseThrow();
	}
	@Override
	public boolean Login(String email, String senha, String identificador) {
		return repo.Validar(email, senha, identificador)!=null && this.isAluno(email, senha, identificador);
	}
}

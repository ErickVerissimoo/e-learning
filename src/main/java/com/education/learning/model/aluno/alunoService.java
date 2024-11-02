package com.education.learning.model.aluno;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.learning.model.curso.Curso;
import com.education.learning.model.superclass.userService;

import jakarta.persistence.EntityExistsException;
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
		repo.Update(atualizar.getEmail(), atualizar.getSenha(), atualizar.getId());
}

	@Override
	public void Cadastrar(Aluno aluno)  {
		if(repo.findByEmail(aluno.getEmail()) !=null) {
			throw new EntityExistsException("Aluno já cadastrado");
		}
		
		String identificador = this.gerarIdentificador();
		aluno.setIdentificacao(identificador);
		repo.saveAndFlush(aluno);
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
	public Aluno entrar(String email, String senha, String nome) {
		Optional<Aluno> teste = Optional.of(repo.Validar(email, senha, nome));
		
		return teste.orElseThrow();
	}
	@Override
	public boolean Login(String email, String senha, String nome) {
		return repo.Validar(email, senha, nome)!=null;
	}
	
	public void apagar() {
		repo.deleteAll();
		repo.resetar();
	}
	@Transactional
	public void Matricular(Curso curso, Aluno aluno) {
		aluno.getCursos().add(curso);
		repo.save(aluno);
	}
	
	public Aluno getbyEmail(String email) {
		return repo.findByEmail(email);
	}
}

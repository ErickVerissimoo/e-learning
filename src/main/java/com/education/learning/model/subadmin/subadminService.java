package com.education.learning.model.subadmin;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class subadminService  {
	@Autowired
	private subadminRepository repo;
	
	public void Deletar(String id) {
		repo.deleteById(Integer.parseInt(id));
		
	}
	
	public subadmin Retornar(String id) {
		return repo.findById(Integer.parseInt(id)).orElseThrow(() -> new NoSuchElementException("Elemento não encontrado"));
	}
	public void Cadastrar(subadmin admin) {
		repo.save(admin);
		
	}
	
	public boolean isValid(String identificacao, String email, String senha) {
		return repo.Validar(identificacao, email, senha) !=null;
	}
	
	public subadmin Retornar(String identificacao, String email, String senha) {
		return repo.Retornar(identificacao, email, senha);
	}
	
	
	
}

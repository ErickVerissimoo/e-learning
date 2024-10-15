package com.education.learning.model.subadmin;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class subadminService {
	@Autowired
	private subadminRepository repo;
	
	public void Deletar(String id) {
		repo.deleteById(Integer.parseInt(id));
		
	}
	
	public subadmin Retornar(String id) {
		return repo.findById(Integer.parseInt(id)).orElseThrow(() -> new NoSuchElementException("Elemento não encontrado"));
	}
	public void atualizarNome(String id, String nome) {
		var admin = this.Retornar(id);
		
	}
	
}

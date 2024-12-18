package com.education.learning.model.curso;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class cursoService {
	@Autowired
	private cursoRepository rep;
	@Transactional
public void gravar(Curso video) throws IOException {
	
	
	
	rep.save(video);
}

public Curso RetornarVideo(String id) {
	return rep.findById(Long.parseLong(id)).orElseThrow(() -> new NoSuchElementException("Não encontrado"));
}
public List<Curso> procura(String nome){
	return rep.findAllbyName("%"+nome+"%");
}
	public Curso cursoDados(String nome) {
		return rep.findByName(nome);
	}
}

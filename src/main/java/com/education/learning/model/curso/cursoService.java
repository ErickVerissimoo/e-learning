package com.education.learning.model.curso;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cursoService {
	@Autowired
	private cursoRepository rep;
public void gravar(byte[] bytes, Curso video) {
	video.setDados(bytes);
	rep.save(video);
}

public Curso RetornarVideo(String id) {
	return rep.findById(Long.parseLong(id)).orElseThrow(() -> new NoSuchElementException("Não encontrado"));
}
}

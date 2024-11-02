package com.education.learning.model.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class avaliacaoService {
	@Autowired avaliacaoRepository repo;
public void avaliar(Avaliacao avaliar) {
	repo.save(avaliar);
}
}

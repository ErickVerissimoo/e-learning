package com.education.learning.model.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface avaliacaoRepository extends JpaRepository<Avaliacao, Integer>{

}

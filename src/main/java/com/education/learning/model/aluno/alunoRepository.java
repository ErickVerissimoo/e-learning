package com.education.learning.model.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface alunoRepository extends JpaRepository<Aluno, Long>{

	
}

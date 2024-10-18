package com.education.learning.model.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface cursoRepository extends JpaRepository<Curso, Long>{

	
}

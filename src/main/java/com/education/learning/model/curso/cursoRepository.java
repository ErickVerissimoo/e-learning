package com.education.learning.model.curso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface cursoRepository extends JpaRepository<Curso, Long>{
	@Query("SELECT p FROM Curso p WHERE p.nome LIKE :nome")
	List<Curso> findByNome(@Param("nome") String nome);

}

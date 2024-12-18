package com.education.learning.model.curso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface cursoRepository extends JpaRepository<Curso, Long>{
	@Query("SELECT p FROM Curso p WHERE p.nome LIKE :nome")
	List<Curso> findAllbyName(@Param("nome") String nome);
	@Query("Select p from Curso p Where p.nome=:nome")
	Curso findByName (@Param("nome") String nome);
	
}

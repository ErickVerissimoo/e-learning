package com.education.learning.model.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;


@Repository
public interface alunoRepository extends JpaRepository<Aluno, Long>{

	@Query("Select p from Aluno p where p.email = :email and p.senha = :senha and p.nome=:nome")
	 Aluno Validar(@Param("email") String email, @Param("senha") String senha,
			 @Param("nome") String nome);
	
	@Modifying
	@Query("update Aluno p SET p.email = :email, p.senha=:senha where p.id = :id")
	void Update(@Param("email") String email, @Param("senha") String senha, @Param("id") long id);
	
	
	@Modifying
	@Transactional
	@Query(value ="alter table aluno AUTO_INCREMENT = 1", nativeQuery=true)
	void resetar();
	
	Aluno findByEmail(String email);
	

}

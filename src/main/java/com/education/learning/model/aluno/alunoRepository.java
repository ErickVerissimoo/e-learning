package com.education.learning.model.aluno;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface alunoRepository extends JpaRepository<Aluno, Long>{
	@Modifying
	@Query("Update aluno p SET p.email = :email where p.id = :id")
	void updateEmail(@Param("email") String email, @Param("id") String id);
	
	@Query("Select aluno p where p.email = :email and p.senha = :senha")
	 Aluno Validar(@Param("email") String email, @Param("senha") String senha);
}

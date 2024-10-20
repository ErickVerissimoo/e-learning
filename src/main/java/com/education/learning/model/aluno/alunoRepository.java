package com.education.learning.model.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface alunoRepository extends JpaRepository<Aluno, Long>{
	@Modifying
	@Query("Update Aluno p SET p.email = :email where p.id = :id")
	void updateEmail(@Param("email") String email, @Param("id")Long id);

	@Query("Select p from Aluno p where p.email = :email and p.senha = :senha and p.identificacao=:identificacao")
	 Aluno Validar(@Param("email") String email, @Param("senha") String senha,
			 @Param("identificacao") String identificacao);
	@Modifying @Query("Delete from Aluno p where p.identificacao = :identificacao")
	void DeleteByIdentificador(@Param("identificacao")String identificacao);

	@Modifying
	@Query("Update Aluno p SET p.senha=:senhaNova where p.senha=:senha")
	void updateSenha(@Param("senha") String senhaAtual, @Param("senhaNova") String nova);

}

package com.education.learning.model.subadmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface subadminRepository extends JpaRepository<Subadmin, Integer>{
		@Modifying
		@Query("UPDATE subadmin u SET u.nome = :nome where u.id = :id")
		void updateNome (@Param("nome") String nome, @Param("id")Long id);
	@Query("Select p from subadmin p where p.identificacao=:identificacao and p.email=:email and p.senha=:senha")
	 Subadmin Validar(@Param("identificacao") String identificacao, @Param("email")String email, @Param("senha")String senha);
	@Query("select p from subadmin p where p.identificacao=:identificacao and p.email=:email and p.senha=:senha")
	Subadmin Retornar(@Param("identificacao") String identificacao, @Param("email") String email, @Param("senha") String senha);
}

package com.education.learning.model.subadmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface subadminRepository extends JpaRepository<Subadmin, Integer>{
		@Modifying
		@Query("UPDATE Subadmin u SET u.email = :email, u.senha = :senha where u.id = :id")
		void update (@Param("email") String email, @Param("senha") String senha, @Param("id") long id);

	@Query("select p from Subadmin p where p.nome=:nome and p.email=:email and p.senha=:senha")
	Subadmin Retornar(@Param("nome") String nome, @Param("email") String email, @Param("senha") String senha);
	
	
}

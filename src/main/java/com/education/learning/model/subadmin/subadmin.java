package com.education.learning.model.subadmin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Builder
public class subadmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String senha;
	private String nome;
	private String identificacao;
	
	private String email;
	@Transient 
	@Getter
	private static final String tipo = "subadmin";
}

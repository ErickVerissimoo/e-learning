package com.education.learning.restcontroller;

import org.springframework.context.annotation.Description;

import lombok.experimental.UtilityClass;
@Description("Classe auxiliar que desafoga o RestController")
@UtilityClass
public class mainHelper {
	public String atualiza(String valorAntigo, String valornovo){
	
		return valornovo == null || valornovo.isEmpty()? valorAntigo : valornovo ;
	}
	

}

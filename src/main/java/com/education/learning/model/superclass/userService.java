package com.education.learning.model.superclass;

import java.util.Random;

public interface userService <T, ID> extends GenericService<T, ID> {
	boolean Login(String email, String senha, String identificador);
	T entrar(String email, String senha, String identificador);
	 default String gerarIdentificador() {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			builder.append(random.nextInt(0, 10));
		}
		String fine = new String(builder);
		return fine;
	}
}

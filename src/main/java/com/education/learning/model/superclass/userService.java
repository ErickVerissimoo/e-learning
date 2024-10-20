package com.education.learning.model.superclass;

public interface userService <T, ID> extends GenericService<T, ID> {
	boolean Login(String email, String senha, String identificador);
	T entrar(String email, String senha, String identificador);

}

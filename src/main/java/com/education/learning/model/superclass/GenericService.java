package com.education.learning.model.superclass;

public interface GenericService<T> {
void Deletar(String identificacao);
void Cadastrar(T cadastrar);
T Buscar(String identificador, String email, String senha);
void Atualizar(String email, String senha);

}

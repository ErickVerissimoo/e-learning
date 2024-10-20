package com.education.learning.model.superclass;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

public interface GenericService<T, ID> {

 void Deletar(ID id) throws EntityNotFoundException ;
 void Cadastrar(T cadastrar);
 T Buscar(ID id) throws EntityNotFoundException;
 void Atualizar(T entity) throws EntityNotFoundException;
 List<T> getAll ();
}

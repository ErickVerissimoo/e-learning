package com.education.learning.model.subadmin;

import static com.education.learning.model.aluno.alunoService.gerarIdentificador;

import java.security.SecureRandom;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class subadminService  {
	@Autowired
	private subadminRepository repo;

	public void Deletar(String id) {
		repo.deleteById(Integer.parseInt(id));

	}

	public subadmin Retornar(String id) {
		return repo.findById(Integer.parseInt(id)).orElseThrow(() -> new NoSuchElementException("Elemento não encontrado"));
	}
	public void Cadastrar(subadmin admin) {


		admin.setIdentificacao(geraridentificador());
		repo.save(admin);

	}

	public boolean isValid(String identificacao, String email, String senha) {
		return repo.Validar(identificacao, email, senha) !=null;
	}

	public subadmin Retornar(String identificacao, String email, String senha) {
		return repo.Retornar(identificacao, email, senha);
	}
private static String geraridentificador() {
	StringBuilder builder = new StringBuilder(gerarIdentificador());
	String abd = "abcdefg";
	SecureRandom random = new SecureRandom();
	for(int i =0; i<abd.length();i++) {
		builder.append(abd.charAt(random.nextInt(0, abd.length())));
	}
	return new String(builder);
}


}

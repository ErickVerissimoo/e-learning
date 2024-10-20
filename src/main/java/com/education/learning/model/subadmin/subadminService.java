package com.education.learning.model.subadmin;

import static com.education.learning.model.aluno.alunoService.gerarIdentificador;

import java.security.SecureRandom;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.learning.model.superclass.GenericService;
@Service
public class subadminService implements GenericService<subadmin>  {
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

	public boolean isSubadmin(String identificacao, String email, String senha) {
	    String regex = "^(?=.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d)(?=[^abcdefg]*[abcdefg]{2}$).*";

		return repo.Validar(identificacao, email, senha) !=null && identificacao.matches(regex);
	}
	@Override
	public subadmin Buscar(String identificacao, String email, String senha) {
		return repo.Retornar(identificacao, email, senha);
	}
private static String geraridentificador() {
	StringBuilder builder = new StringBuilder(gerarIdentificador());
	String abd = "abcdefg";
	SecureRandom random = new SecureRandom();
	for(int i =0; i<=3;i++) {
		builder.append(abd.charAt(random.nextInt(0, abd.length())));
	}
	return new String(builder);
}


@Override
public void Atualizar(String nomeOrEmail, String senha) {
	// TODO Auto-generated method stub
	
}


}

package com.education.learning.model.subadmin;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.learning.model.superclass.userService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class subadminService implements userService<Subadmin, String>  {
	@Autowired
	private subadminRepository repo;
	@Override
	public void Deletar(String id) {
		repo.deleteById(Integer.parseInt(id));

	}

	@Override
	public void Cadastrar(Subadmin admin) throws EntityNotFoundException {




		admin.setIdentificacao(geraridentificador());
		repo.save(admin);

	}

	public boolean isSubadmin(String identificacao, String email, String senha) {
	    String regex = "^(?=.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d)(?=[^abcdefg]*[abcdefg]{2}$).*";

		return repo.Retornar(identificacao, email, senha) !=null && identificacao.matches(regex);
	}

 private String geraridentificador() {

	StringBuilder builder = new StringBuilder(this.gerarIdentificador());
	String abd = "abcdefg";
	Random random = new Random();
	for(int i =0; i<=3;i++) {
		builder.append(abd.charAt(random.nextInt(0, abd.length())));
	}
	return new String(builder);
}






@Override
public List<Subadmin> getAll() {

	return repo.findAll();
}


@Override
public boolean Login(String email, String senha, String nome) {

	return repo.Retornar(nome, email, senha) !=null;
}


@Override
public Subadmin Buscar(String id) throws EntityNotFoundException {

	return repo.findById(Integer.parseInt(id)).orElseThrow();
}


@Override
public Subadmin entrar(String email, String senha, String nome) {

	return repo.Retornar(nome, email, senha);
}

@Override
public void Atualizar(Subadmin entity) throws EntityNotFoundException {
	repo.update(entity.getEmail(), entity.getSenha(), entity.getId());

}


}

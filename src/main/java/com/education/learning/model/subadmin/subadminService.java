package com.education.learning.model.subadmin;

import static com.education.learning.model.aluno.alunoService.gerarIdentificador;

import java.security.SecureRandom;
import java.util.List;

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

		return repo.Validar(identificacao, email, senha) !=null && identificacao.matches(regex);
	}

 private final static String geraridentificador() {
	StringBuilder builder = new StringBuilder(gerarIdentificador());
	String abd = "abcdefg";
	SecureRandom random = new SecureRandom();
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
public boolean Login(String email, String senha, String identificador) {

	return repo.Validar(identificador, email, senha) !=null && this.isSubadmin(identificador, email, senha);
}


@Override
public Subadmin Buscar(String id) throws EntityNotFoundException {

	return repo.findById(Integer.parseInt(id)).orElseThrow();
}


@Override
public Subadmin entrar(String email, String senha, String identificador) {

	return repo.Validar(identificador, email, senha);
}

@Override
public void Atualizar(Subadmin entity) throws EntityNotFoundException {
	// TODO Auto-generated method stub

}


}

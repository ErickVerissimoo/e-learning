package com.education.learning;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.education.learning.model.aluno;
import com.education.learning.model.alunoRepository;

@SpringBootTest
class ELearningApplicationTests {
	@Autowired
	private alunoRepository rep;
	@Test
	void teste() {
		List<aluno> alunoS = rep.findAll();
		alunoS.forEach(System.out::println);
	}

}

package br.edu.unoesc.alunos_H2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.unoesc.alunos_H2.model.Aluno;
import br.edu.unoesc.alunos_H2.repository.AlunoRepository;

@SpringBootApplication
public class AlunosH2Application {

	public static void main(String[] args) {
		SpringApplication.run(AlunosH2Application.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(AlunoRepository repositorio) {
		return args -> {
			Aluno a = new Aluno(null, "Joana", new BigDecimal(3500), LocalDate.of(1980, 02, 25));
			a = repositorio.save(a);
			System.out.println(a);
			
			//repositorio.delete(a);
			
			Optional<Aluno> b = repositorio.findById(2L);
			if (b.isEmpty()) {
				System.out.println("Aluno não encontrado!");
			} else {
				System.out.println(b.get());				
			}
			
			System.out.println(repositorio.findAll());
			
			for (var aluno: repositorio.findAll()) {
				System.out.println(aluno);
			}
		};
	}
}
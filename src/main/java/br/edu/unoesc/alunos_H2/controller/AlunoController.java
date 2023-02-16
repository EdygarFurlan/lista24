package br.edu.unoesc.alunos_H2.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.alunos_H2.model.Aluno;
import br.edu.unoesc.alunos_H2.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	@Autowired
	private AlunoRepository repositorio;
	
	@GetMapping("/filtro")
	List<Aluno> listarComFiltro(@RequestParam("filtro") String filtro) {
		return repositorio.findByFiltro(filtro);
	}
	
	@GetMapping("/salario")
	List<Aluno> listarPorSalario(@RequestParam("salario") BigDecimal salario) {
		return repositorio.porSalario(salario);
	}
	
	@GetMapping
	public Iterable<Aluno> listarTudo() {
		return repositorio.findAll();
	}

	@PostMapping("/incluir")
	public Aluno incluirAluno(@RequestBody Aluno aluno)  {
		System.out.println("Incluindo um aluno ...");
		return repositorio.save(aluno);
	}
	
	@PutMapping("/alterar")
	public Aluno alterarAluno(@RequestBody Aluno aluno) {
		Optional<Aluno> a = repositorio.findById(aluno.getId());
		Aluno b = a.get();
		if (a.isEmpty()) {
			System.out.println("Aluno não encontrado!");
		} else {
			System.out.println("Alterando um aluno ...");

			b.setNome(aluno.getNome());
			b.setSalario(aluno.getSalario());
			b.setNascimento(aluno.getNascimento());
			repositorio.save(b);
		}
		return b;
	}

	@DeleteMapping("/deletar/{id}")
	public void deletarAluno(@PathVariable Long id) {
		Optional<Aluno> a = repositorio.findById(id);
		if (a.isEmpty()) {
			System.out.println("Aluno não encontrado!");
		} else {
			System.out.println("Deletando o aluno [" + id + "]...");
			repositorio.delete(a.get());
		}
	}
}

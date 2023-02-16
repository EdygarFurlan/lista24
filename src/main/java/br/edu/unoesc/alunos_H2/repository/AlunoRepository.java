package br.edu.unoesc.alunos_H2.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.unoesc.alunos_H2.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
	
	@Query("Select a from Aluno a where a.salario >= :salario")
	public List<Aluno> porSalario(@Param("salario") BigDecimal salario);
	
	@Query("Select a from Aluno a where upper(a.nome) like upper(concat('%', :filtro, '%')) order by nome")
	public List<Aluno> findByFiltro(@Param("filtro") String filtro);
}

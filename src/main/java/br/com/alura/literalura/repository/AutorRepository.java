package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>
{
	Optional<Autor> findDistinctByNomeContainingIgnoreCase(String nome);
	
	@Query(value = "SELECT * FROM autores AS autor WHERE (:ano >= autor.ano_nascimento) AND (autor.ano_falecimento IS NULL OR :ano <= autor.ano_falecimento)", nativeQuery = true)
	List<Autor> findAutoresVivosPorAno(@Param("ano") int ano);
}
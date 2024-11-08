package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>
{
	Optional<Livro> findByTituloContainingIgnoreCase(String nome);
	
	@Query(value = "SELECT * FROM livros WHERE EXISTS (SELECT * FROM unnest(livros.assuntos) AS assunto WHERE LOWER(assunto) LIKE CONCAT('%', LOWER(:assunto), '%'))", nativeQuery = true)
	List<Livro> findByAssunto(@Param("assunto") String assunto);
	
	@Query(value = "SELECT * FROM livros WHERE :idioma = ANY (livros.idiomas)", nativeQuery = true)
	List<Livro> findByIdioma(@Param("idioma") String idioma);
}
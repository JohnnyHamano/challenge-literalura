package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService
{
	private final LivroRepository livroRepository;
	
	@Autowired
	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	public LivroRepository getLivroRepository() {
		return livroRepository;
	}
	
	public Optional<Livro> findLivroPorNome(String nome)
	{
		return livroRepository.findByTituloContainingIgnoreCase(nome);
	}
	
	public List<Livro> findLivrosPorAsssunto(String genero)
	{
		return livroRepository.findByAssunto(genero);
	}
	
	public List<Livro> findLivrosPorIdioma(String idioma)
	{
		return livroRepository.findByIdioma(idioma);
	}
}
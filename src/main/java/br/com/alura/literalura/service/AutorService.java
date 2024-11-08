package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService
{
	private final AutorRepository autorRepository;
	
	@Autowired
	public AutorService(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}
	
	public AutorRepository getAutorRepository() {
		return autorRepository;
	}
	
	public Optional<Autor> findAutorPorNome(String nome) {
		return autorRepository.findDistinctByNomeContainingIgnoreCase(nome);
	}
	
	public List<Autor> findAutoresPorAno(int ano)
	{
		return autorRepository.findAutoresVivosPorAno(ano);
	}
	
	
	
}

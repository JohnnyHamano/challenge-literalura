package br.com.alura.literalura.util;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.repository.Repositories;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class Database
{
	private static Repositories repositories;
	
	public Database(LivroRepository repositorioLivros, AutorRepository repositorioAutores) {
		repositories = new Repositories(repositorioLivros, repositorioAutores);
	}
	
	/**
	 * @param obj Deve ser do tipo Livro ou Autor
	 * Adiciona o objeto no banco de dados.
	 */
	public static void adicionar(Object obj)
	{
		String classname = obj.getClass().getSimpleName();
		String identifier;
		
		try
		{
			switch (classname)
			{
				case "Livro": assert obj instanceof Livro;
					identifier = ((Livro) obj).getTitulo();
					repositories.livroRepository().save((Livro) obj);
					break;
				case "Autor": assert obj instanceof Autor;
					identifier = ((Autor) obj).getNome();
					repositories.autorRepository().save((Autor) obj);
					break;
				default: throw new IllegalArgumentException("Objeto deve ser do tipo \u001B[32mLivro\u001B[0m ou \u001B[32mAutor\u001B[0m");
			}
			
			System.out.println("Salvo no banco de dados: " + identifier);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
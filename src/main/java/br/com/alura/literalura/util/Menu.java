package br.com.alura.literalura.util;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.AutorService;
import br.com.alura.literalura.service.LivroService;
import br.com.alura.literalura.service.Services;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class Menu
{
	private static Services services;
	
	public Menu(LivroService livroService, AutorService autorService) {
		services = new Services(livroService, autorService);
	}
	
	public static void exibeMenuPrincipal()
	{
		exibeTitulo("MENU");
		
		var menu = """
			\u001B[32m\u001B[40m 1 \u001B[0m - Buscar livros por título
			\u001B[32m\u001B[40m 2 \u001B[0m - Listar livros registrados no banco de dados
			\u001B[32m\u001B[40m 3 \u001B[0m - Buscar autores por nome
			\u001B[32m\u001B[40m 4 \u001B[0m - Listar autores registrados no banco de dados
			\u001B[32m\u001B[40m 5 \u001B[0m - Listar autores vivos em um determinado ano
			\u001B[32m\u001B[40m 6 \u001B[0m - Listar livros de um determinado assunto
			\u001B[32m\u001B[40m 7 \u001B[0m - Listar livros em um determinado idioma
			\u001B[32m\u001B[40m 8 \u001B[0m - Listar Top 10 livros mais baixados
			\u001B[31m\u001B[40m 0 \u001B[0m - Sair
			""";
		
		System.out.println(menu);
	}
	
	public static void exibeMenuIdiomas()
	{
		exibeTitulo("IDIOMAS");
		
		String menu = """
			\u001B[32m\u001B[40m pt \u001B[0m - Português
			\u001B[32m\u001B[40m en \u001B[0m - Inglês
			\u001B[32m\u001B[40m es \u001B[0m - Espanhol
			\u001B[32m\u001B[40m fr \u001B[0m - Francês
			\u001B[31m\u001B[40m 0 \u001B[0m - Voltar
			""";
		
		System.out.println(menu);
	}
	
	public static void exibeLivro(Livro livro)
	{
		exibeTitulo("LIVRO");
		String titulo = livro.getTitulo();
		
		System.out.printf("""
			Título: %s
			Autores: %s
			Assuntos: %s
			Idiomas: %s
			Downloads: %d
			""", titulo, livro.getAutores(), livro.getAssuntos(), livro.getIdiomas(), livro.getDownloads());
		
		try
		{
			Optional<Livro> optional = services.livroService().findLivroPorNome(titulo);
			
			// Adiciona o Livro no banco de dados, caso este não se encontre registrado ainda
			if (optional.isEmpty()) {
				Database.adicionar(livro);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		System.out.println("======================\n");
	}
	
	public static void exibeAutor(Autor autor)
	{
		String nome = autor.getNome();
		
		exibeTitulo("AUTOR");
		System.out.println("Nome: " + nome);
		if (autor.getAnoNascimento() > 0) System.out.println("Ano de nascimento: " + autor.getAnoNascimento());
		if (autor.getAnoFalecimento() > 0) System.out.println("Ano de falecimento: " + autor.getAnoFalecimento());
		
		try
		{
			Optional<Autor> optional = services.autorService().findAutorPorNome(nome);
			
			// Adiciona o autor no banco de dados, caso este não se encontre registrado ainda
			if (optional.isEmpty()) {
				Database.adicionar(autor);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		System.out.println("======================\n");
	}
	
	public static void exibeResultadoDePesquisa(int count)
	{
		System.out.print("Sua pesquisa retornou " + count);
		if (count == 1) System.out.println(" resultado.\n");
		else System.out.println(" resultados.\n");
	}
	
	private static void exibeTitulo(String titulo)
	{
		System.out.println("===== " + titulo.toUpperCase() + " ==========");
	}
}
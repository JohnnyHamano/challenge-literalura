package br.com.alura.literalura;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.AutorDTO;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.model.LivroDTO;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.repository.Repositories;
import br.com.alura.literalura.util.API;
import br.com.alura.literalura.util.Menu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Main
{
	private final String API_URI = "https://gutendex.com/books/";
	private final String API_SEARCH = API_URI + "?search=";
	private final Scanner scanner = new Scanner(System.in);
	private final Repositories repositories;
	
	public Main(LivroRepository repositorioLivros, AutorRepository repositorioAutores) {
		this.repositories = new Repositories(repositorioLivros, repositorioAutores);
	}
	
	public void iniciaPrograma()
	{
		var opcao = -1;
		
		while (opcao != 0)
		{
			Menu.exibeMenuPrincipal();
			
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcao)
			{
				case 1:
					buscarLivroPorTitulo();
					break;
				case 2:
					ListarLivrosRegistrados();
					break;
				case 3:
					BuscarAutorPorNome();
					break;
				case 4:
					ListarAutoresRegistrados();
					break;
				case 5:
					ListarAutoresVivosPorAno();
					break;
				case 6:
					ListarLivrosPorAssunto();
					break;
				case 7:
					ListarLivrosPorIdioma();
					break;
				case 8:
					Top10MaisBaixados();
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida");
			}
		}
	}
	
	private void buscarLivroPorTitulo()
	{
		System.out.println("Qual o nome do livro que deseja buscar?");
		String livroTitulo = scanner.nextLine();
		System.out.println("Buscando...");
		
		try
		{
			JSONObject json = new JSONObject(API.buscarDados(API_SEARCH + livroTitulo.replace(" ", "+")));
			JSONArray results = json.getJSONArray("results");
			
			Menu.exibeResultadoDePesquisa(json.getInt("count"));
			
			for (int i = 0 ; i < results.length(); i++)
			{
				JSONObject obj = results.getJSONObject(i);
				Menu.exibeLivro(new Livro(API.converteJSON(obj.toString(), LivroDTO.class)));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void ListarLivrosRegistrados()
	{
		System.out.println("Buscando...");
		
		List<Livro> livros = repositories.livroRepository().findAll();
		Menu.exibeResultadoDePesquisa(livros.size());
		
		for (Livro livro : livros) {
			Menu.exibeLivro(livro);
		}
	}
	
	private void BuscarAutorPorNome()
	{
		System.out.println("Qual o nome do autor que deseja buscar?");
		String nomeAutor = scanner.nextLine();
		
		System.out.println("Buscando...");
		
		try
		{
			JSONObject json = new JSONObject(API.buscarDados(API_SEARCH + nomeAutor.replace(" ", "+")));
			JSONArray results = json.getJSONArray("results");
			
			Menu.exibeResultadoDePesquisa(json.getInt("count"));
			
			for (int i = 0 ; i < results.length(); i++)
			{
				JSONObject jsonAutores = results.getJSONObject(i);
				JSONArray autores = jsonAutores.getJSONArray("authors");
				
				for (Object obj : autores)
				{
					Menu.exibeAutor(new Autor(API.converteJSON(obj.toString(), AutorDTO.class)));
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void ListarAutoresRegistrados()
	{
		System.out.println("Buscando...");
		
		List<Autor> autores = repositories.autorRepository().findAll();
		Menu.exibeResultadoDePesquisa(autores.size());
		
		for (Autor autor : autores) {
			Menu.exibeAutor(autor);
		}
	}
	
	private void ListarAutoresVivosPorAno()
	{
		System.out.println("Qual ano deseja pesquisar?");
		
		var ano = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Buscando...");
		
		List<Autor> autores = repositories.autorRepository().findAutoresVivosPorAno(ano);
		Menu.exibeResultadoDePesquisa(autores.size());
		
		for (Autor autor : autores) {
			Menu.exibeAutor(autor);
		}
	}
	
	private void ListarLivrosPorAssunto()
	{
		System.out.println("Qual assunto deseja pesquisar?");
		var genero = scanner.nextLine();
		System.out.println("Buscando...");
		
		List<Livro> livros = repositories.livroRepository().findByAssunto(genero);
		Menu.exibeResultadoDePesquisa(livros.size());
		
		for (Livro livro : livros) {
			Menu.exibeLivro(livro);
		}
	}
	
	private void ListarLivrosPorIdioma()
	{
		Menu.exibeMenuIdiomas();
		var idioma = scanner.nextLine();
		
		if (!idioma.equals("0"))
		{
			List<Livro> livrosPorIdioma = repositories.livroRepository().findByIdioma(idioma);
			Menu.exibeResultadoDePesquisa(livrosPorIdioma.size());
			
			for (Livro livro : livrosPorIdioma) {
				Menu.exibeLivro(livro);
			}
		}
		else System.out.println("Voltando...");
	}
	
	private void Top10MaisBaixados()
	{
		System.out.println("Buscando...");
		
		try
		{
			JSONObject json = new JSONObject(API.buscarDados(API_URI + "?sort=popular"));
			JSONArray results = json.getJSONArray("results");
			
			for (int i = 0 ; i < 10; i++)
			{
				JSONObject obj = results.getJSONObject(i);
				Menu.exibeLivro(new Livro(API.converteJSON(obj.toString(), LivroDTO.class)));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
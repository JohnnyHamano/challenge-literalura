package br.com.alura.literalura.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Autor> autores = new ArrayList<>();
	private List<String> assuntos = new ArrayList<>();
	private List<String> idiomas = new ArrayList<>();
	private int downloads;
	
	public Livro() {}
	
	public Livro(LivroDTO livro)
	{
		this.titulo = livro.titulo();
		this.autores = livro.autores();
		this.assuntos = livro.assuntos();
		this.idiomas = livro.idiomas();
		this.downloads = livro.downloads();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public List<String> getAssuntos() {
		return assuntos;
	}
	
	public void setAssuntos(List<String> assuntos) {
		this.assuntos = assuntos;
	}
	
	public List<String> getIdiomas() {
		return idiomas;
	}
	
	public void setIdiomas(List<String> idiomas) {
		this.idiomas = idiomas;
	}
	
	public int getDownloads() {
		return downloads;
	}
	
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
}
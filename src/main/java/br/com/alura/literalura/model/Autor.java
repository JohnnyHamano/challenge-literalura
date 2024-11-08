package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "autores", uniqueConstraints = { @UniqueConstraint(columnNames = { "nome" }) })
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("name") private String nome;
	@JsonProperty("birth_year") private int anoNascimento;
	@JsonProperty("death_year") private int anoFalecimento;
	
	public Autor() {}
	
	public Autor(AutorDTO autor)
	{
		this.nome = autor.nome();
		this.anoNascimento = autor.anoNascimento();
		this.anoFalecimento = autor.anoFalecimento();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getAnoNascimento() {
		return anoNascimento;
	}
	
	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	
	public int getAnoFalecimento() {
		return anoFalecimento;
	}
	
	public void setAnoFalecimento(int anoFalecimento) {
		this.anoFalecimento = anoFalecimento;
	}
}
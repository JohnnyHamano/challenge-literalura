package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDTO(@JsonAlias("name") String nome,
					   @JsonAlias("birth_year") int anoNascimento,
					   @JsonAlias("death_year") int anoFalecimento)
{
	//
}
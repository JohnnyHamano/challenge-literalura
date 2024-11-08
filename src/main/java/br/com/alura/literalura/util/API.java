package br.com.alura.literalura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API
{
	public static String buscarDados(String uri)
	{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
		HttpResponse<String> response;
		
		try
		{
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		return response.body();
	}
	
	public static <T> T converteJSON(String json, Class<T> classe)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			return mapper.readValue(json, classe);
		}
		catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}
	}
}
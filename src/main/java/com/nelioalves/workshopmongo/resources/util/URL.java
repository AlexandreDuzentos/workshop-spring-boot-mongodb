package com.nelioalves.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
/* Classe utilitária para todas as classes do pacote resources */
public class URL {
  
	/* Essa classe conterá um método para decodificar parâmetros
	 * da URL.
	 * 
	 * Quando passamos parâmetros para a URL usando o padrão
	 * ?chave1="valor"&chave2="valor", estamos obedecendo a sintaxe do
	 * protocolo http para passagem de parâmetros para a url.
	 * 
	 * A necessidade de codificar(encode) os valores dos parâmetros
	 * da URL é remover os carateres especiais, como espaços em branco
	 * por exemplo, o encode é feito no cliente quando a requisição está
	 * sendo enviada.
	 * 
	 * por exemplo: a URL http://localhost:8080/posts?saudacao="bom dia" - na hora de fazer uma requisição
	 * os caractereres especiais dentro do valor de saudacao são codificados(encoded), ou seja,
	 * substituidos por outra coisa, afim de remover o caracterer especial
	 * que era espaço em branco, o resultado final da codificaçao(encoding) será ?saudacao="bom%20dia",
	 * o meu backend tem de ser capaz de decodificar esse parâmetro da URL, para obtermos o valor original do parâmetro saudacao
	 * que seria o "bom dia".
	 **/
	
	/* No front-end quem codifica a URL é o javascript, por meio de
	 * uma função chamada encodeURIConponent, e aqui no backend quem 
	 * decoficará essa URL é esse método. */
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	/* 
	 * Método responsável por converter uma date do formato de String
	 * para o formato Date, e caso a conversão falhe uma data padrão será
	 * setada.
	 * */
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/* Setando o Timezone da data para GMT, isso é para que
		 * data gerada pela conversão esteja no formato GMT */
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}
		
	}
	
}

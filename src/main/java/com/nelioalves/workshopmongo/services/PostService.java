package com.nelioalves.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
  
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		
		try {
		   return post.get();
		} catch(NoSuchElementException e) {
			throw new ObjectNotFoundException("Objecto não encontrado!");
		}			
    }
	
	public List<Post> findByTitle(String text){
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		/* Macete: na maxDate precisará ser adicionado um dia, por que?
		 * porque essa maxDate será gerada no dia informado pelo usuário, só
		 * que a meia-noite, na verdade, seu eu quero buscar os posts até
		 * aquela data, não pode ser até meia noite daquele dele dia, tem de ser
		 * até 24 horas daquele dia, ou seja, já deve estar no final do dia e não no início(que a meia-noite),
		 * por que? porque a nossa data é armazenada na forma de milissegundos, ela é um
		 * instante, ela não é simplesmente uma data, dia, mês e ano, ela tem hora, minuto e segundo e milissegundo então se eu
		 * quero encontrar um post até o maxDate, eu tenho que considerar até
		 * o final daquele dia, até as 24 horas daquele dia */
		
		/*
		 * Acrescentando um dia maxDate informada pelo usuário
		 * 
		 *  valores sendo somados a quantidade de milissegundos da maxDate:
		 *  primeiro: 1000 equivale a 1 segundo.
		 * */
		maxDate = new Date(maxDate.getTime() + 1000 * 60 * 60 * 24);
		
		return postRepository.fullSearch(text, minDate, maxDate);
		
		
		
	}
	
}

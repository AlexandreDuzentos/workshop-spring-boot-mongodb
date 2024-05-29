package com.nelioalves.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
  
	/* Esse é um query method do spring data, ou seja, é um método que 
	 * faz uma consulta, query methods são método especiais que o
	 * spring data oferece para ele gerar as consultas automaticamente
	 * para nós.
	 * 
	 * O método abaixo é responsável por fazer uma busca pelo título
	 * de um post e a busca será insensível a maiúsculas e minúsculas.
	 * */
	List<Post> findByTitleContainingIgnoreCase(String text);
}

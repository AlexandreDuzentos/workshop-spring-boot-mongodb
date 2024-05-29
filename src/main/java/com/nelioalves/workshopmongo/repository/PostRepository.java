package com.nelioalves.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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
	 * de um post e a busca será insensível a maiúsculas e minúsculas,
	 * esse método não pode possuir qualquer identificador pois ele não
	 * é personalizado, o idenficador para ele ficará amarrado a nomenclaturas
	 * usadas pelo spring data.
	 * */
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	/* 
	 * O Método agora pode receber qualquer identificador, pois a
	 * consulta será personalizada, ela não está amarrado a nenhuma
	 * nomenclatura do spring data.
	 * 
	 * @Query - essa annotation permite que a gente possa entrar com uma
	 * consulta do mongodb na forma de texto JSON.
	 * 
	 * parâmetros de uma consulta no forma de texto JSON: title - representa
	 * o identificador do campo que se deseja usar na busca, regex representa
	 * a expressão regular para que a busca seja efetuada, o ?0 indica que
	 * estou utilizando o primeiro parâmetro passado para o método, e o $options
	 * pode receber uma série de opções, a especificada indica que o consulta
	 * será insensível a maiúsculas e a minúsculas.
	 * */
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
}

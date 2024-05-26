package com.nelioalves.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalves.workshopmongo.DTO.AuthorDTO;
import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	private static final SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		/* Setando o TimeZone do objeto sdf para GMT */
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		/* Limpando os dados da coleção user no MongoDB */
		userRepository.deleteAll();
		
		/* Limpando os dados da coleção post no MongoDB */
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		/*
		 *  Salvando novos registros na coleção user no MongoDB
		 *  
		 *  Os users estão sendo salvos antes da associação entre
		 *  posts e os authors para que ele tenham um id próprio, que
		 *  é gerado pelo banco de dados e para que esse id seja copiado
		 *  para o AuthorDTO, caso contrário o id do AuthorDTO seria null.
		 *  
		 *  O banco de dados gera automaticamente o id dos objetos
		 *  a serem salvos e a ferramenta de ORM(jpa) se encarregará
		 *  de buscar o último registro salvo na coleção
		 *  correspondente a aquele
		 *  objeto do JPA(já com o id) para popular o objeto que carregou os dados para serem
		 *  salvos no banco de dados e é assim que os objeto ficam populados
		 *  com o dados que foram salvos no banco de dados, incluindo o id que foi
		 *  gerado automáticamente.
		 *  
		 *  Dentro do método saveAll já possui uma lógica para salvar
		 *  os objeto passados como argumento para ele e também para
		 *  buscar os objetos que foram salvos para popular os objetos
		 *  passados como argumento com os dados que foram salvos no
		 *  banco de dados.
		 *  
		 *   */
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));	
		
		/* Salvando novos registros na coleção post no MongoDB */
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
			
	}

}

package com.nelioalves.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;
	
	/*
	 *  /{id} é um complemento do endpoint raíz
	 *  
	 *  Método responsável por recuperar um Post de um User.
	 *  */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = postService.findById(id);
		
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/titlesearch")
	/*
	 * @RequestParam(value="text", defaultValue="") - annotation que
	 * informa para o spring que os parâmetros serão passados para a
	 * URL obedecendo a sintaxe do protocolo HTTP para passagem de
	 * parâmetros, que é a seguinte: ?chave1="valor"&chave2="valor",
	 * dentro da annotation o parâmetro value indica o nome do parâmetro
	 * esperado e o defaultValue indica o valor que o parâmetro assumirá
	 * caso ele não seja informado.
	 * */
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> posts = postService.findByTitle(text);
		
		return ResponseEntity.ok().body(posts);
	}
	
	
}

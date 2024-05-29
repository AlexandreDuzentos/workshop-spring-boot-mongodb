package com.nelioalves.workshopmongo.services;

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
	
}

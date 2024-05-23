package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.DTO.UserDTO;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
    
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		
		try {
		   return user.get();
		} catch(NoSuchElementException e) {
			throw new ObjectNotFoundException("Objecto não encontrado!");
		}			
    }
	
	public User insert(User obj) {
		return userRepository.insert(obj);	
	}
	
	/* Método responsável por converter um objeto do tipo UserDTO para um objeto
	 * do tipo User.
	 * 
	 * Esse método estar na classe de UserService quebra o princípio
	 * da responsabilidade única, uma vez que quem deve saber como
	 * converter um UserDTO para um User é a própria classe UserDTO,
	 * essa abordagem foi utilizada aqui porque dependendo da situação
	 * (não vai ser o caso aqui) para instânciar um User eu posso querer
	 * acessar o banco de dados, e quem já tem a dependência para o
	 * banco de dados é a classe UserService, então para já ficar uma
	 * operação que seja possível fazer uma manutenção no futuro que poss
	 * a ter um acesso a dados eu vou colocar aqui no UserService.
	 * */
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(),
				        objDto.getEmail());	
	}
	
	public void delete(String id) {
		  findById(id);
		  userRepository.deleteById(id); 	
	}
	
	public User update(User obj) {
		Optional<User> user = userRepository.findById(obj.getId());
		User newObj = user.get();
		updateData(newObj, obj);
		
		return userRepository.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());	
	}
}

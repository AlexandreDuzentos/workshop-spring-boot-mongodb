package com.nelioalves.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.workshopmongo.DTO.UserDTO;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.services.UserService;

/*
 * @RestController - annotation para informar para o spring que essa
 * classe será um recurso rest.
 * 
 * @RequestMapping - annotation para informar para o spring o caminho
 * raíz(endpoint raíz) desse recurso rest. 
 * */
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
   
	/* @GetMapping - annotation para informar que esse método é um endpoint rest
	 * no caminho "/users", que fornecerá informações para o cliente
	 **/
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = userService.findAll();
		
		/* Convertendo uma List<User> para uma List<UserDTO>*/
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		/* O método ok instância o ResponseEntity já com o código 
		 * de resposta http que a resposta ocorreu com sucesso
		 * 
		 * O método body conterá o corpo da resposta http.
		 * */
		return ResponseEntity.ok().body(listDto);
	} 
	
	/* /{id} é um complemento do endpoint raíz */
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User user = userService.fromDTO(objDto);
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

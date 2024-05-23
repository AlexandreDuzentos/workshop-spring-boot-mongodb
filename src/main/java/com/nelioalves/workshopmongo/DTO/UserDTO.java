package com.nelioalves.workshopmongo.DTO;

import java.io.Serializable;

import com.nelioalves.workshopmongo.domain.User;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	/* Construtor vazio */
	public UserDTO() {
	}
	
	/*
	 * Construtor para instânciar o DTO a partir do objeto entity
	 * correspondente(User)
	 * 
	 * O método construtor é o responsável por converter um objeto
	 * do tipo user para um objeto do tipo UserDTO.
	 * */
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

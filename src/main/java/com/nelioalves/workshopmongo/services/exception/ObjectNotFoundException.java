package com.nelioalves.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/* 
	 * para o construtor será passado um msg e ele por sua vez repassará
	 * essa msg para o construtor da superclasse, pois é a superclasse
	 * quem sabe, ou seja, quem possui a lógica de como se lança uma
	 * exceção.
	 * */
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

}

package com.nelioalves.workshopmongo.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/*
 * @ControllerAdvice - annotation para informar que essa classe irá
 * tratar possíveis erros nas minhas requisições, ela sobreporá o tratamento
 * padrão de exceções do spring.
 * */
@ControllerAdvice
public class ResourceExceptionHandler {
   
	/* Quando ocorrer uma exceção do tipo objectNotFoundException, esse
	 * método será o responsável por tratar essa exceção e devolver
	 * um objeto do tipo StandardError para o cliente contendo todas
	 * as informações sobre a exceção.
	 * 
	 * @ExceptionHandler(classequelancaraaexcecao.class) - annotation
	 * para informar qual exceção será interceptada pelo método
	 * objetNotFound afim dela ser tratada pelo método
	 * objectNotFound.
	 * */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
}

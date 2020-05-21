package com.qintess.eventos.api.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeanInternalServerErrorException extends RuntimeException  {
	
	private static final long serialVersionUID = 1L;

	public BeanInternalServerErrorException(String message) {
		    super(message);
		  }

}

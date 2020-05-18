package com.qintess.eventos.api.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.qintess.eventos.api.domain.Cliente;
import com.qintess.eventos.api.service.ClienteService;



@Component
public class StringToClienteConversor implements Converter<String , Cliente>  {
	
	@Autowired
	private ClienteService service;
	
	@Override
	public Cliente convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.findById(id); 
	}



}

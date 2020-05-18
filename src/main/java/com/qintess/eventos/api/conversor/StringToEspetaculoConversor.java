package com.qintess.eventos.api.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.service.EspetaculoService;



@Component
public class StringToEspetaculoConversor implements Converter<String , Espetaculo>  {
	
	@Autowired
	private EspetaculoService service;
	
	@Override
	public Espetaculo convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.findById(id); 
	}



}

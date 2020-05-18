package com.qintess.eventos.api.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.qintess.eventos.api.domain.Casa;
import com.qintess.eventos.api.service.CasaService;



@Component
public class StringToCasaConversor implements Converter<String , Casa>  {
	
	@Autowired
	private CasaService service;
	
	@Override
	public Casa convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.findById(id); 
	}



}

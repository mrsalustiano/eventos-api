package com.qintess.eventos.api.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.qintess.eventos.api.domain.Perfil;
import com.qintess.eventos.api.service.PerfilService;



@Component
public class StringToPerfilConversor implements Converter<String , Perfil>  {
	
	@Autowired
	private PerfilService perfilService;
	
	@Override
	public Perfil convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return perfilService.findById(id);
	}



}

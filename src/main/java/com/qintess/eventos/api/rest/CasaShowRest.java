package com.qintess.eventos.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.eventos.api.domain.Casa;
import com.qintess.eventos.api.service.CasaServiceImpl;

@RestController
@RequestMapping(value = "casas")
public class CasaShowRest {

	@Autowired
	private CasaServiceImpl service;

	@GetMapping
	public ResponseEntity<List<Casa>> listAll() {

		List<Casa> casas = service.findAll();

		return ResponseEntity.ok(casas);

	}

	@PostMapping
	public ResponseEntity<Casa> save(@RequestBody Casa casa){
		
		
			Casa save = new Casa(casa.getNome(), null,  
				casa.getLogradouro(), casa.getNumero(), casa.getComplemento(), casa.getBairro(), 
				casa.getCep(), casa.getCidade(), casa.getUF());
		
			System.out.println(save);
		
			service.save(save);
			return ResponseEntity.status(HttpStatus.CREATED).body(casa);
	
	}
	
	
	
}

package com.qintess.eventos.api.rest;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.service.EspetaculoService;

@RestController
@RequestMapping(value = "espetaculos")
public class EspetaculoRest {
	

	
	@Autowired
	private EspetaculoService service;
	
	@GetMapping
	public ResponseEntity<List<Espetaculo>> listAll() {

		List<Espetaculo> espetaculos = service.findAll();

		return ResponseEntity.ok(espetaculos);

	}

	
	@PostMapping
	public ResponseEntity<Espetaculo> save(@RequestBody Espetaculo espetaculo){
		
			Espetaculo save = new Espetaculo(
					espetaculo.getFaixaEtaria(),espetaculo.getDataEspetaculo(),
					espetaculo.getValor(),espetaculo.getCapacidade(),	espetaculo.getDestaque(),
					espetaculo.getImagemCasa(),espetaculo.getCasa(), espetaculo.getDescricao(), 
					espetaculo.getNome(),espetaculo.getVendas());					
			
			LocalDate DataEspetaculo  = espetaculo.getDataEspetaculo();
			espetaculo.setDataEspetaculo(DataEspetaculo);
			
			save.setCasa(espetaculo.getCasa());
			System.out.println(save);
		
			service.save(save);
			return ResponseEntity.status(HttpStatus.CREATED).body(espetaculo);
	
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	@Modifying
	public ResponseEntity<Espetaculo> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Espetaculo espetaculo){
		
		Espetaculo espetaculoU = espetaculo; 
		espetaculoU.setId(id);
		
		service.update(espetaculoU);
		
		return ResponseEntity.ok(espetaculoU);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Espetaculo> delete(@PathVariable(name = "id") Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

}

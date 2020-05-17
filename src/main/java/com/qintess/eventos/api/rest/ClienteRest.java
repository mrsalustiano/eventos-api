package com.qintess.eventos.api.rest;

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

import com.qintess.eventos.api.domain.Casa;
import com.qintess.eventos.api.domain.Cliente;
import com.qintess.eventos.api.service.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteRest {

	@Autowired
	ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listAll() {

		List<Cliente> clientes = service.findAll();

		return ResponseEntity.ok(clientes);

	}
	
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
		
			Cliente save = new Cliente(cliente.getCelular(), cliente.getCpf(), cliente.getEmail(), 
					cliente.getNewsletter(), cliente.getNome(), cliente.getSenhaCliente());
			
			System.out.println(save);
		
			service.save(save);
			return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	@Modifying
	public ResponseEntity<Cliente> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Cliente cliente){
		
		Cliente clienteU = cliente; 
		clienteU.setId(id);
		
		service.update(clienteU);
		
		return ResponseEntity.ok(clienteU);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable(name = "id") Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

}

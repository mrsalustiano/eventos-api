package com.qintess.eventos.api.rest;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.qintess.eventos.api.domain.Cliente;
import com.qintess.eventos.api.rest.exception.BeanBadRequestException;
import com.qintess.eventos.api.rest.exception.BeanNotFoundException;
import com.qintess.eventos.api.rest.exception.ExceptionError;
import com.qintess.eventos.api.service.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteRest {

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<List<Cliente>> listAll() {

		List<Cliente> clientes = service.findAll();

		return ResponseEntity.ok(clientes);

	}

	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente) {
		
		List<Cliente> cli = service.findByCpf(cliente.getCpf());
		int soma = 0;
		
		for (Cliente cliente2 : cli) {
			
			soma = soma + 1;
		}
		
		if (soma > 0) {
			throw new BeanBadRequestException("Já existe Cliente com este CPF: " + cliente.getCpf());

		}

		Cliente save = new Cliente(cliente.getCelular(), cliente.getCpf(), cliente.getEmail(), cliente.getNewsletter(),
				cliente.getNome(), cliente.getSenhaCliente(),cliente.getLogradouro(), cliente.getNumero(), cliente.getComplemento(), cliente.getBairro(), cliente.getCidade(), 
				cliente.getUf(), cliente.getCep() );
		System.out.println(save);

		service.save(save);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> listaPorId(@PathVariable(name = "id") Long id) {
		try {
			Cliente cliente = service.findById(id);
			if (cliente == null) {
				throw new BeanNotFoundException("Nao existe esse Cliente com id: " + id);
			} else {
				return ResponseEntity.ok(cliente);
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe esse Cliente com id: " + id);

		}

	}

	@PutMapping(value = "/{id}")
	@Transactional
	@Modifying
	public ResponseEntity<Cliente> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Cliente cliente) {

		try {

			Cliente clienteU = service.findById(id);
			if (clienteU != null) {
				clienteU = cliente;
				clienteU.setId(id);
				service.update(clienteU);
				return ResponseEntity.ok(cliente);
			} else {
				throw new BeanNotFoundException("Nao existe esse Cliente com id: " + id);
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe esse Cliente com id: " + id);

		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable(name = "id") Long id) {

		try {

			Cliente clienteU = service.findById(id);
			if (clienteU != null) {
				service.delete(id);
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				throw new BeanNotFoundException("Nao existe esse Cliente com id: " + id);
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe esse Cliente com id: " + id);

		}

	}


}

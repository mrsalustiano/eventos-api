package com.qintess.eventos.api.rest;

import java.io.IOException;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qintess.eventos.api.domain.Casa;
import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.rest.exception.BeanNotFoundException;
import com.qintess.eventos.api.service.CasaService;
import com.qintess.eventos.api.service.EspetaculoService;

@RestController
@RequestMapping(value = "espetaculos")
public class EspetaculoRest {

	@Autowired
	private EspetaculoService service;
	
	@Autowired
	private CasaService casaService;

	@GetMapping
	public ResponseEntity<List<Espetaculo>> listAll() {

		List<Espetaculo> espetaculos = service.findAll();

		return ResponseEntity.ok(espetaculos);

	}

	@PostMapping
	public ResponseEntity<Espetaculo> save(@RequestBody @Valid Espetaculo espetaculo) {

		Espetaculo save = new Espetaculo();
		Casa casa   = casaService.findById(espetaculo.casa.getId());
		if ( casa == null) {
			throw new BeanNotFoundException("Nao existe Case de Show com id informado ");
		} else {
		
		save.setCapacidade(espetaculo.getCapacidade());
		save.setCasa(espetaculo.getCasa());
		save.setDataEspetaculo(espetaculo.getDataEspetaculo());
		save.setDescricao(espetaculo.getDescricao());
		save.setDestaque(espetaculo.getDestaque());
		save.setFaixaEtaria(espetaculo.getFaixaEtaria());
		save.setNome(espetaculo.getNome());
		save.setValor(espetaculo.getValor());
		save.setImagemCasa(espetaculo.getImagemCasa());

		LocalDate DataEspetaculo = espetaculo.getDataEspetaculo();
		espetaculo.setDataEspetaculo(DataEspetaculo);

		save.setCasa(espetaculo.getCasa());
		System.out.println(save);

		service.save(save);
		return ResponseEntity.status(HttpStatus.CREATED).body(espetaculo);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Espetaculo> listaPorId(@PathVariable(name = "id") Long id) {
		try {
			Espetaculo espetaculo = service.findById(id);
			if (espetaculo == null) {
				throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);
			} else {
				return ResponseEntity.ok(espetaculo);
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);

		}

	}

	@PutMapping(value = "/{id}")
	@Transactional
	@Modifying
	public ResponseEntity<Espetaculo> update(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Espetaculo espetaculo) {

		try {
			Espetaculo espetaculoS = service.findById(id);
			if (espetaculoS == null) {
				throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);
			} else {
				Espetaculo espetaculoU = espetaculo;
				espetaculoU.setId(id);
				service.update(espetaculoU);
				return ResponseEntity.ok(espetaculoU);
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Espetaculo> delete(@PathVariable(name = "id") Long id) {
		try {
			Espetaculo espetaculoS = service.findById(id);
			if (espetaculoS == null) {
				throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);
			} else {
				service.delete(id);
				return ResponseEntity.status(HttpStatus.OK).build();
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);
		}

		
		

	}

	@Transactional
	@Modifying
	@RequestMapping(method = RequestMethod.POST, value = "/imagem/{id}")
	public ResponseEntity<String> receiveData(@PathVariable(name = "id") Long id, MultipartFile imagem)
			throws IOException {

		try {
			Espetaculo espetaculo = service.findById(id);
			if (espetaculo == null) {
				throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);
			} else {
				byte[] bImagem;
				bImagem = imagem.getBytes();

				espetaculo.setImagemCasa(bImagem);

				service.update(espetaculo);

				return ResponseEntity.ok("Imagem Atualizada no banco!");
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe o espetaculo com id: " + id);
		}
		
		
	}

}

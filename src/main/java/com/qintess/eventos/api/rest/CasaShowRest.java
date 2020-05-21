package com.qintess.eventos.api.rest;

import java.io.IOException;
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
import com.qintess.eventos.api.rest.exception.BeanNotFoundException;
import com.qintess.eventos.api.service.CasaService;

@RestController
@RequestMapping(value = "casas")
public class CasaShowRest {

	@Autowired
	private CasaService service;

	@GetMapping
	public ResponseEntity<List<Casa>> listAll() {

		List<Casa> casas = service.findAll();

		return ResponseEntity.ok(casas);

	}

	// listar por id
	@GetMapping(value = "/{id}")

	public ResponseEntity<Casa> listaPorId(@PathVariable(name = "id") Long id) {
		try {
			Casa casa = service.findById(id);
			if (casa == null) {
				throw new BeanNotFoundException("Nao existe essa Casa de Show com id: " + id);
			} else {
				return ResponseEntity.ok(casa);
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe essa Casa de Show com id: " + id);

		}

	}

	@PostMapping()
	public ResponseEntity<Casa> save(@RequestBody  @Valid Casa casa) {

		Casa save = new Casa();

		save.setBairro(casa.getBairro());
		save.setCep(casa.getCep());
		save.setNome(casa.getNome());
		save.setCidade(casa.getCidade());
		save.setComplemento(casa.getComplemento());
		save.setLogradouro(casa.getLogradouro());
		save.setNumero(casa.getNumero());
		save.setUF(casa.getUF());

		service.save(save);
		return ResponseEntity.status(HttpStatus.CREATED).body(casa);

	}

	@PutMapping(value = "/{id}")
	@Transactional
	@Modifying
	public ResponseEntity<Casa> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Casa casa) {

		try {
			Casa casaU = service.findById(id);
			if (casaU != null) {
				casaU = casa;
				casaU.setId(id);
				service.update(casaU);
				return ResponseEntity.ok(casaU);
			} else {
				
				throw new BeanNotFoundException("Nao existe essa Casa de Show com id: " + id);
			}

		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe essa Casa de Show com id : " + id);

		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Casa> delete(@PathVariable(name = "id") Long id) {
		try {
			Casa casaU = service.findById(id);
			if (casaU != null) {
				service.delete(id);
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				throw new  BeanNotFoundException("Nao existe essa Casa de Show com id: " + id);

			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe essa Casa de Show com id : " + id);

		}
	}

	@Transactional
	@Modifying
	@RequestMapping(method = RequestMethod.POST, value = "/imagem/{id}")
	public ResponseEntity<String> receiveData(@PathVariable(name = "id") Long id, MultipartFile imagem)
			throws IOException {


		try {

			Casa casa = service.findById(id);
			if (casa != null) {

				byte[] bImagem;
				bImagem = imagem.getBytes();

				casa.setImagemCasa(bImagem);

				service.update(casa);

				return ResponseEntity.ok("Imagem Atualizada no banco!");
			} else {
				throw new BeanNotFoundException("Nao existe essa Casa de Show com id : " + id);
			}
		} catch (NoSuchElementException e) {
			throw new BeanNotFoundException("Nao existe essa Casa de Show com id : " + id);

		}
	}
	
	

}

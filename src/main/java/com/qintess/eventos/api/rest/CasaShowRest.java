package com.qintess.eventos.api.rest;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public void save(@RequestPart(required = false, value="file")  MultipartFile file,  @RequestBody Casa casa) throws IOException{
		
		
			Casa save = new Casa();
			
			save.setBairro(casa.getBairro());
			save.setCep(casa.getCep());
			save.setNome(casa.getNome());
			save.setCidade(casa.getCidade());
			save.setComplemento(casa.getComplemento());
			save.setLogradouro(casa.getLogradouro());
			save.setNumero(casa.getNumero());
			save.setUF(casa.getUF());
			
			byte[] bImagem;
			
			try {
				
				if(file != null && file.getSize() > 0) {
					bImagem = file.getBytes();
					
					save.setImagemCasa(bImagem);
				}
				
				
				System.out.println(save);
				
				service.save(save);
				
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		//	return ResponseEntity.status(HttpStatus.CREATED).body(casa);
			
			
	
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	@Modifying
	public ResponseEntity<Casa> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Casa casa){
		
		Casa casaU = casa; 
		casaU.setId(id);
		
		service.update(casaU);
		
		return ResponseEntity.ok(casaU);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Casa> delete(@PathVariable(name = "id") Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

	
	
}

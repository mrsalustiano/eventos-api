package com.qintess.eventos.api.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.eventos.api.domain.Venda;
import com.qintess.eventos.api.service.VendaService;

@RestController
@RequestMapping("/api")
public class IndexController {

	@Autowired

	private VendaService vService;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/*
	 * @GetMapping("/espetaculos/list") public List<Espetaculo> getEspetaculos(){
	 * 
	 * return service.findAll(); }
	 */
	
	@GetMapping("/usuario/list")
	public List<Venda> lista(){
		
		return vService.findAll();
	}
	
	

}

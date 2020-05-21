package com.qintess.eventos.api.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.eventos.api.domain.Cliente;
import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.domain.Venda;
import com.qintess.eventos.api.service.ClienteService;
import com.qintess.eventos.api.service.EspetaculoService;
import com.qintess.eventos.api.service.VendaService;

@RestController
@RequestMapping(value = "vendas")
public class VendaRest {
	
	@Autowired
	private VendaService service;
	
	@Autowired 
	private EspetaculoService espService;
	
	@Autowired
	private ClienteService cliService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> save(@RequestBody @Valid  Venda venda){
		
			Cliente cli = cliService.findById(venda.getCliente().getId()) ;
	
			Espetaculo esp = espService.findById(venda.getEspetaculo().getId());
		
			int qtd = 0;
			BigDecimal valores;
			BigDecimal total;
			int qtdJaVendida = 0;
		
			
			List<Venda> vendaAntiga = service.findByClienteEspetaculo(cli, esp);
			
			for (Venda venda2 : vendaAntiga) {
				qtdJaVendida = qtdJaVendida + venda2.getQuantidade();
			}
			
			if (qtdJaVendida > 3 ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade Excedida, somente 4 ingressos por Show para cada Cliente");
			}
			
			qtd = venda.getQuantidade();
			valores = esp.getValor();
			total =  valores.multiply(new BigDecimal(qtd));
			qtd = qtdJaVendida + qtd;
			
			if (qtd > 4) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Limite de ingresso excedido somente 4 por evento por cliente");
				
			}
			
			venda.setValor(total);
			
			int capacidade = esp.getCapacidade();
			capacidade = (capacidade - venda.getQuantidade());
		
			if (capacidade < 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade de ingresso maior que disponivel");


			} else {
				esp.setCapacidade(capacidade);

			}

			// processo apos as validacoes

			Long valor = venda.getId();
			if (valor == null) {

				service.save(venda);
				espService.update(esp);
				return ResponseEntity.status(HttpStatus.OK).body("Venda efetuada com sucesso");
			} else {
				service.update(venda);
				espService.update(esp);
				return ResponseEntity.status(HttpStatus.OK).body("Venda efetuada com sucesso");
			}
				
	
	}
	
	
	@GetMapping
	public ResponseEntity<List<Venda>> listAll() {

		List<Venda> vendas = service.findAll();

		return ResponseEntity.ok(vendas);

	}
	
	

}

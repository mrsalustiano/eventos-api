package com.qintess.eventos.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qintess.eventos.api.domain.Cliente;
import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.domain.Venda;
import com.qintess.eventos.api.service.ClienteService;
import com.qintess.eventos.api.service.EspetaculoService;
import com.qintess.eventos.api.service.VendaService;

@Controller
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private VendaService service;

	@Autowired
	private EspetaculoService espetaculoService;

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/")
	public String homeVenda(Venda venda, ModelMap model) {
		return "venda/compras";
	}

	@GetMapping("/comprar/")
	public String comprar(Venda venda, ModelMap model, RedirectAttributes attr) {

		model.addAttribute("vendas", service.findAll());

		return "venda/compras";

	}

	@GetMapping("/listar")
	public String listar(Venda venda, ModelMap model, BindingResult result) {
		model.addAttribute("venda", service.findAll());

		return "admin/venda/listar";
	}

	@Transactional
	@RequestMapping("/salva")
	public String salvar(@ModelAttribute Venda venda, RedirectAttributes attr) {

		Long idEspetaculo = venda.getEspetaculo().getId().longValue();
		Espetaculo esp = espetaculoService.findById(idEspetaculo);

		int qtd = 0;
		BigDecimal valores;
		BigDecimal total;
		int qtdJaVendida = 0;

		// pega a lista das compras dos ingressos do show e compara para nao passar de 4
		// ingressos

		List<Venda> vendaAntiga = service.findByClienteEspetaculo(venda.getCliente(), venda.getEspetaculo());

		for (Venda venda2 : vendaAntiga) {
			qtdJaVendida = qtdJaVendida + venda2.getQuantidade();
		}

		if (qtdJaVendida > 3 ) {
			attr.addFlashAttribute("mensagemErro", "Limite de ingresso excedido que disponivel");
			return "redirect:/vendas/";
		}

		qtd = venda.getQuantidade();
		valores = venda.getEspetaculo().getValor();
		total = valores.multiply(new BigDecimal(qtd));
		qtd += qtdJaVendida;
		
		if (qtd > 4) {
			attr.addFlashAttribute("mensagemErro", "Limite de ingresso excedido somente 4 por evento");
			return "redirect:/vendas/";
		}

		// Verifica se o total nao vai ficar negativo
		venda.setValor(total);

		int capacidade = esp.getCapacidade();
		capacidade = (esp.getCapacidade() - qtd);
		if (capacidade < 0) {
			attr.addFlashAttribute("mensagemErro", "Quantidade de ingresso maior que disponivel");
			return "redirect:/vendas/";

		} else {
			esp.setCapacidade(capacidade);

		}

		// processo apos as validacoes

		Long valor = venda.getId();
		if (valor == null) {

			service.save(venda);
			espetaculoService.update(esp);
			attr.addFlashAttribute("mensagemSucesso", "Venda efetuada com sucesso");
		} else {
			service.update(venda);
			espetaculoService.update(esp);
			attr.addFlashAttribute("mensagemSucesso", "Venda efetuada com sucesso");
		}

		return "redirect:/";
	}

	@PostMapping("/editar")
	public String editar(@Valid Venda venda, BindingResult result) {
		service.update(venda);

		return "redirect:/vendas/comprar";
	}

	@GetMapping("/deleta/{id}")
	@Transactional
	public String deleta(@PathVariable(name = "id") Long id, RedirectAttributes attr) {

		service.delete(id);
		attr.addFlashAttribute("mensagemSucesso", "Venda cancelada com sucesso");
		return "redirect:/vendas/comprar";

	}

	@GetMapping("/altera/{id}")
	public String carregaAlterar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes attr) {
		try {

			Venda venda = service.findById(id);
			model.addAttribute("vendas", service.findAll());
			model.addAttribute("venda", venda);

		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());

		}
		return "venda/compras";

	}

	@ModelAttribute("espetaculos")
	public List<Espetaculo> listaEspetaculos() {
		return espetaculoService.findAll();
	}

	@ModelAttribute("clientes")
	public List<Cliente> listaClientes() {
		return clienteService.findAll();
	}
}

package com.qintess.eventos.api.controller;

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
import com.qintess.eventos.api.service.ClienteService;

@Controller
@RequestMapping("/clientes1")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping("")
	public String cadastrarL(Cliente cliente, ModelMap model) {
		model.addAttribute("clientes", service.findAll());

		return "cliente/cadastro";
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente, ModelMap model) {
		model.addAttribute("clientes", service.findAll());

		return "cliente/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
	
		
		model.addAttribute("clientes", service.findAll());

		return "cliente/listar";
	}

	@Transactional
	@RequestMapping("/salva")
	public String salvar(@ModelAttribute Cliente cliente, RedirectAttributes attr) {

		Long valor = cliente.getId();
		if (valor == null) {
			service.save(cliente);
			attr.addFlashAttribute("mensagemSucesso", "Cliente adicionado com sucesso");
		} else {
			service.update(cliente);
			attr.addFlashAttribute("mensagemSucesso", "Cliente editado com sucesso");
		}

		return "redirect:/clientes/cadastrar";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result) {
		
		if (result.hasErrors()) {
			return "cliente/cadastro";
		}
		
		service.update(cliente);

		return "redirect:/clientes/cadastro";
	}

	@GetMapping("/deleta/{id}")
	@Transactional
	public String deleta(@PathVariable(name = "id") Long id, RedirectAttributes attr) {

		service.delete(id);
		attr.addFlashAttribute("mensagemSucesso", "Cliente removido com sucesso");
		return "redirect:/clientes/listar";

	}

	@GetMapping("/altera/{id}")
	public String carregaAlterar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes attr) {
		try {

			Cliente cliente = service.findById(id);
			model.addAttribute("clientes", service.findAll());
			model.addAttribute("cliente", cliente);

		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());

		}
		return "cliente/cadastro";

	}

}

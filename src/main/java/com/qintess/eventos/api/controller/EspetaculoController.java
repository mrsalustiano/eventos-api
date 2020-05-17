package com.qintess.eventos.api.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qintess.eventos.api.domain.Casa;
import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.service.CasaService;
import com.qintess.eventos.api.service.EspetaculoService;

@Controller
@RequestMapping("/admin/espetaculos1")
public class EspetaculoController {

	@Autowired
	CasaService casaService;
	
	@Autowired
	EspetaculoService service;
	
	@GetMapping("")
	public String cadastro(Espetaculo espetaculo, ModelMap model) {
		model.addAttribute("espetaculos", service.findAll());		
		return "admin/espetaculo/cadastro";
	}
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Espetaculo espetaculo, ModelMap model) {
		model.addAttribute("espetaculos", service.findAll());		
		return "admin/espetaculo/cadastro";
	}
	
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("espetaculo", service.findAll());
		return "admin/espetaculo/listar";
	}
	
	@RequestMapping("/salva")
	@Transactional
	public String Salva(@ModelAttribute Espetaculo espetaculo, BindingResult result,
			@RequestParam(required=false, value="imagem") MultipartFile imagem,
			RedirectAttributes attr ) {
		
		byte[] bImagem;
		
		try {
			if(imagem != null && imagem.getSize() > 0) {
				bImagem = imagem.getBytes();
				espetaculo.setImagemCasa(bImagem);
			}
		
		
			Long valor = espetaculo.getId();
			if (valor == null) {
				service.save(espetaculo);
				attr.addFlashAttribute("mensagemSucesso", "espetaculo adicionado com sucesso");	
			} else {
				service.update(espetaculo);
				attr.addFlashAttribute("mensagemSucesso", "espetaculo editado com sucesso");
			}
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		
		return "redirect:/admin/espetaculos/cadastrar";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Espetaculo espetaculo,BindingResult result) {
		 service.update(espetaculo);
		 return "redirect:/admin/espetaculos/cadastro";
	}
	
	@GetMapping("/deleta/{id}")
	@Transactional
	public String deleta(@PathVariable(name = "id") Long id, RedirectAttributes attr) {
		
		service.delete(id);
		attr.addFlashAttribute("mensagemSucesso", "Espetaculo removido");
		return "redirect:/admin/espetaculos/listar";
		
	}
	
	@GetMapping("/altera/{id}")
	public String carregaAlterar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes attr) {
		try {
			Espetaculo espetaculo = service.findById(id);
			
			byte[] encodeBase64 = Base64.getEncoder().encode(espetaculo.getImagemCasa());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			espetaculo.setImagemEncoded(base64Encoded);
			
			
			model.addAttribute("espetaculos", service.findAll());
			model.addAttribute("espetaculo", espetaculo);
		} catch (UnsupportedEncodingException e) {
			attr.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "admin/espetaculo/cadastro";
	}
	
	@ModelAttribute("casa")
	public List<Casa> listaPerfis() {
		return casaService.findAll();
	}	
}
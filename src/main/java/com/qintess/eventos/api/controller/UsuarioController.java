package com.qintess.eventos.api.controller;

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

import com.qintess.eventos.api.domain.Perfil;
import com.qintess.eventos.api.domain.Usuario;
import com.qintess.eventos.api.service.PerfilService;
import com.qintess.eventos.api.service.UsuarioService;



@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping("")
	public String cadastrarL(Usuario usuario, ModelMap model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		
		return "admin/usuario/cadastro";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Usuario usuario, ModelMap model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		
		return "admin/usuario/cadastro";
	}
	
	
	@GetMapping("/listar")
	public String listar(ModelMap model	) {
		model.addAttribute("usuarios", usuarioService.findAll());
		
		return "admin/usuario/listar";
	}
	

	@Transactional
	@RequestMapping("/salva")
	public String salvar(@ModelAttribute Usuario usuario, RedirectAttributes attr ) {
		
		Long valor = usuario.getId();
		if (valor == null) {
			usuarioService.save(usuario);
			attr.addFlashAttribute("mensagemSucesso", "Usuario adicionado com sucesso");	
		} else {
			usuarioService.update(usuario);
			attr.addFlashAttribute("mensagemSucesso", "Usuario editado com sucesso");
		}
		
		
		return "redirect:/admin/usuarios/cadastrar";
	}
	
	
	@PostMapping("/editar")
	public String editar(@Valid Usuario usuario, BindingResult result) {
	usuarioService.update(usuario);
		
		return "redirect:/admin/usuarios/cadastro";
	}
	
	
	@GetMapping("/deleta/{id}")
	@Transactional
	public String deleta(@PathVariable(name = "id") Long id, RedirectAttributes attr) {
	
		usuarioService.delete(id);
		attr.addFlashAttribute("mensagemSucesso", "Usuario removido com sucesso");
		return "redirect:/admin/usuarios/listar";
		
	}
	
	@GetMapping("/altera/{id}")
	public String carregaAlterar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes attr) {
		try {

			Usuario usuario = usuarioService.findById(id);
			model.addAttribute("usuarios", usuarioService.findAll());
			model.addAttribute("usuario", usuario);

		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());

		}
		return "admin/usuario/cadastro";
	
	}
	
	
	
	
	
	@ModelAttribute("perfis")
	public List<Perfil> listaPerfis() {
		return perfilService.findAll();
	}	


}

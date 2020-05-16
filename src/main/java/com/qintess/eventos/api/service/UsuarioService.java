package com.qintess.eventos.api.service;

import java.util.List;

import com.qintess.eventos.api.domain.Usuario;

public interface UsuarioService {
	
	void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Long id);

    Usuario findById(Long id);

    List<Usuario> findAll();
    
    List<Usuario> findByLogin(String login);
    
  

}

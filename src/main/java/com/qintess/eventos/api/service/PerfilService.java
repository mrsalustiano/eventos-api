package com.qintess.eventos.api.service;

import java.util.List;

import com.qintess.eventos.api.domain.Perfil;


public interface PerfilService {

	void save(Perfil perfil);

    void update(Perfil perfil);

    void delete(Long id);

    Perfil findById(Long id);

    List<Perfil> findAll();
    
    List<Perfil> findByNome(String nome);
    
  

	
}

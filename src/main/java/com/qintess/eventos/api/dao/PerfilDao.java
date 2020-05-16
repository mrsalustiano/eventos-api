package com.qintess.eventos.api.dao;

import java.util.List;

import com.qintess.eventos.api.domain.Perfil;

public interface PerfilDao {

	void save(Perfil perfil);

    void update(Perfil perfil);

    void delete(Long id);

    Perfil findById(Long id);

    List<Perfil> findAll();
    
    List<Perfil> findByNome(String nome);
    


	}

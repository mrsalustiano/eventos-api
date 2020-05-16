package com.qintess.eventos.api.dao;

import java.util.List;

import com.qintess.eventos.api.domain.Espetaculo;

public interface  EspetaculoDao {
	

    void save(Espetaculo espetaculo);

    void update(Espetaculo espetaculo);

    void delete(Long id);

    Espetaculo findById(Long id);

    List<Espetaculo> findAll();

}

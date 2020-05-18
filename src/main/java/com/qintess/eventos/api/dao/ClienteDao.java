package com.qintess.eventos.api.dao;

import java.util.List;

import com.qintess.eventos.api.domain.Cliente;

public interface ClienteDao {

	void save(Cliente Cliente);

    void update(Cliente Cliente);
    
    Cliente findById(Long id); 

    void delete(Long id);

    List<Cliente> findAll();
    
    List<Cliente> findByEmail(String email);
    
    List<Cliente> findByCpf(String cpf);

}

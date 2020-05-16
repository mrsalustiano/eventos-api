package com.qintess.eventos.api.dao;

import java.util.List;

import com.qintess.eventos.api.domain.Cliente;
import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.domain.Venda;

public interface VendaDao {

	void save(Venda venda);

    void update(Venda venda);
    
    Venda findById(Long id); 

    void delete(Long id);

    List<Venda> findAll();
    
    Venda findByEspetaculo(Espetaculo espetaculo);
    
    Venda findByCliente(Cliente cliente);
    
    List<Venda> findByClienteEspetaculo(Cliente cliente, Espetaculo espetaculo);
    

    
}

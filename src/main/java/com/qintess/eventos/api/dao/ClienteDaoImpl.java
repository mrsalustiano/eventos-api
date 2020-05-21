package com.qintess.eventos.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qintess.eventos.api.domain.Cliente;
@Repository
public class ClienteDaoImpl extends AbstractDao<Cliente, Long> implements ClienteDao{

	@Override
	public List<Cliente> findByEmail(String email) {
		
		return createQuery("select c from Cliente c where c.email = ?1 ) ", email);
	}

	@Override
	public List<Cliente> findByCpf(String cpf) {
		
		return createQuery("select c from Cliente c where c.cpf = ?1  ", cpf); 
	}


	

}

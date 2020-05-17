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
		
		return createQuery("select c from Cliete c where c.cpf = ?1 ) ", cpf);
	}

	@Override
	public List<Cliente> findByEmailAndSenha(String email, String senha){
		return createQuery("select c Cliente u where c.email = ?1 and c.senhaCliente = ?2)", email, senha);
	}
	

}

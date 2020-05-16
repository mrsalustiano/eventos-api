package com.qintess.eventos.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qintess.eventos.api.domain.Usuario;
@Repository
public class UsuarioDaoImpl extends AbstractDao<Usuario, Long> implements UsuarioDao{



	@Override
	public List<Usuario> findByLogin(String login) {
		
		return createQuery("select u from Usuario u where u.login = ?1  ", login);
		
	}

}

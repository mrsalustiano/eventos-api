package com.qintess.eventos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qintess.eventos.api.dao.UsuarioDao;
import com.qintess.eventos.api.domain.Usuario;
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao dao;

	@Override
	@Transactional
	public void save(Usuario usuario) {
		dao.save(usuario);
		
	}

	@Override
	@Transactional
	public void update(Usuario usuario) {
		dao.update(usuario);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
		
	}

	@Override
	public Usuario  findById(Long id) {
		
		return dao.findById(id);
	}

	@Override
	public List<Usuario> findAll() {  
	 
		return dao.findAll();
	}

	@Override
	public List<Usuario> findByLogin(String login) {
		
		return dao.findByLogin(login);
	}





	

	
	




}

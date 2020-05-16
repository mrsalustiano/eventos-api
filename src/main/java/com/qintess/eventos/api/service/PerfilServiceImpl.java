package com.qintess.eventos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qintess.eventos.api.dao.PerfilDao;
import com.qintess.eventos.api.domain.Perfil;
@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilDao dao;
	
	@Transactional
	@Override
	public void save(Perfil perfil) {
		dao.save(perfil);
		
	}

	@Transactional
	@Override
	public void update(Perfil perfil) {
		dao.update(perfil);
		
	}

	@Transactional
	@Override
	public void delete(Long id) {
		dao.delete(id);
		
	}

	@Override
	public Perfil findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Perfil> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Perfil> findByNome(String nome) {
		return dao.findByNome(nome);
	}





}

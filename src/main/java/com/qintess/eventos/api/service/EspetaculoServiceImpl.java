package com.qintess.eventos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qintess.eventos.api.dao.EspetaculoDao;
import com.qintess.eventos.api.domain.Espetaculo;

@Service
public class EspetaculoServiceImpl implements EspetaculoService {


	@Autowired
	private EspetaculoDao dao;
	
	@Override
	@Transactional
	public void save(Espetaculo espetaculo) {
		dao.save(espetaculo);
		
	}

	@Override
	@Transactional
	public void update(Espetaculo espetaculo) {
		dao.update(espetaculo);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
		
	}

	@Override
	public Espetaculo findById(Long id) {
		
		return dao.findById(id);
	}

	@Override
	public List<Espetaculo> findAll() {
		
		return dao.findAll();
	}

}

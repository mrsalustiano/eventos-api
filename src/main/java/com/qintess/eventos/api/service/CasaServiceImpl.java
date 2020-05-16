package com.qintess.eventos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qintess.eventos.api.dao.CasaDao;
import com.qintess.eventos.api.domain.Casa;

@Service
public class CasaServiceImpl implements CasaService {

	@Autowired
	private CasaDao dao;
	
	
	@Override
	@Transactional
	public void save(Casa casa) {
		dao.save(casa);
		
	}

	@Override
	@Transactional
	public void update(Casa casa) {
		dao.update(casa);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
		
	}

	@Override
	public Casa findById(Long id) {
		
		return dao.findById(id);
	}

	@Override
	public List<Casa> findAll() {
		
		return dao.findAll();
	}



	

}

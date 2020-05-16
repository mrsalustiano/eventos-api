package com.qintess.eventos.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qintess.eventos.api.dao.VendaDao;
import com.qintess.eventos.api.domain.Cliente;
import com.qintess.eventos.api.domain.Espetaculo;
import com.qintess.eventos.api.domain.Venda;
@Service
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendaDao dao;

	@Override
	@Transactional
	public void save(Venda venda) {
		dao.save(venda);
		
	}

	@Override
	@Transactional
	public void update(Venda venda) {
		dao.update(venda);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
		
	}

	@Override
	public Venda  findById(Long id) {
		
		return dao.findById(id);
	}

	@Override
	public List<Venda> findAll() {  
	 
		return dao.findAll();
	}

	@Override
	public Venda findByEspetaculo(Espetaculo espetaculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venda findByCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venda> findByClienteEspetaculo(Cliente cliente, Espetaculo espetaculo) {
	
		return dao.findByClienteEspetaculo(cliente, espetaculo);
	}




}

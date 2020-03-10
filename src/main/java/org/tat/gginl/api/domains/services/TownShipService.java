package org.tat.gginl.api.domains.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.SalePoint;
import org.tat.gginl.api.domains.Township;
import org.tat.gginl.api.domains.repository.TownshipRepository;

@Service
public class TownShipService {
	
	@Autowired
	private TownshipRepository townshipRepository;
	
	
	@Transactional
	public List<Township> findAll() {
		return townshipRepository.findAll();
	}
	
	
	public List<Object[]> findAllNativeObject(){
		return townshipRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return townshipRepository.findAllColumnName();
	}

}

package org.tat.gginl.api.domains.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.SalePoint;
import org.tat.gginl.api.domains.TownshipCode;
import org.tat.gginl.api.domains.repository.TownShipCodeRepository;
import org.tat.gginl.api.domains.repository.TownshipRepository;

@Service
public class TownShipCodeService {
	
	@Autowired
	private TownShipCodeRepository repository;
	
	
	@Transactional
	public List<TownshipCode> findAll() {
		return repository.findAll();
	}
	
	
	public List<Object[]> findAllNativeObject(){
		return repository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return repository.findAllColumnName();
	}
	
	
	

}

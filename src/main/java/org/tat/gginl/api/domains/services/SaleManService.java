package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Bank;
import org.tat.gginl.api.domains.SaleMan;
import org.tat.gginl.api.domains.repository.SaleManRepository;

@Service
public class SaleManService {
	
	@Autowired
	private SaleManRepository saleManRepository;
	
	public List<SaleMan> findAll(){
		return saleManRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return saleManRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return saleManRepository.findAllColumnName();
	}

}

package org.tat.gginl.api.domains.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.SalePoint;
import org.tat.gginl.api.domains.repository.SalePointRepository;

@Service
public class SalePointService  {
	
	@Autowired
	private SalePointRepository salePointRepository;

	
	@Transactional
	public List<SalePoint> findAll() {
		return salePointRepository.findAll();
	}
	
	
	public List<Object[]> findAllNativeObject(){
		return salePointRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return salePointRepository.findAllColumnName();
	}

	
}

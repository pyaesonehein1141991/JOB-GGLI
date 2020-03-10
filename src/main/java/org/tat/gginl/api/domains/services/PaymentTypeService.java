package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Agent;
import org.tat.gginl.api.domains.PaymentType;
import org.tat.gginl.api.domains.repository.PaymentTypeRepository;

@Service
public class PaymentTypeService {
  
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	
	public List<PaymentType> findAll(){
		return paymentTypeRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return paymentTypeRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return paymentTypeRepository.findAllColumnName();
	}
	
}

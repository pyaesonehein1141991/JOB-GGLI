package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Bank;
import org.tat.gginl.api.domains.repository.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankRepository;
	
	public List<Bank> findAll(){
		return bankRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return bankRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return bankRepository.findAllColumnName();
	}

}

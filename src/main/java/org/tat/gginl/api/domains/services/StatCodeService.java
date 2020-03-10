package org.tat.gginl.api.domains.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.StateCode;
import org.tat.gginl.api.domains.repository.StatCodeRepository;
@Service
public class StatCodeService {
	@Autowired
	private StatCodeRepository statCodeRepo;

	
	@Transactional
	public List<StateCode> findAll() {
		return statCodeRepo.findAll();
	}
	
	
	public List<Object[]> findAllNativeObject(){
		return statCodeRepo.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return statCodeRepo.findAllColumnName();
	}

}

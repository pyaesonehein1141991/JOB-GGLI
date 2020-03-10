package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Occupation;
import org.tat.gginl.api.domains.repository.OccupationRepository;

@Service
public class OccupationService {
	
	@Autowired
	private OccupationRepository occupationRepository;
	
	public List<Occupation> findAll(){
		return occupationRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return occupationRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return occupationRepository.findAllColumnName();
	}

}

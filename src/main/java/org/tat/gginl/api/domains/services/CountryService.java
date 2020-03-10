package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Country;
import org.tat.gginl.api.domains.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	

	
	public List<Country> findAll(){
		return countryRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return countryRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return countryRepository.findAllColumnName();
	}

}

package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Occupation;
import org.tat.gginl.api.domains.Organization;
import org.tat.gginl.api.domains.repository.OrganizationRepository;

@Service
public class OrganizationService {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	public List<Organization> findAll(){
		return organizationRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return organizationRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return organizationRepository.findAllColumnName();
	}
}

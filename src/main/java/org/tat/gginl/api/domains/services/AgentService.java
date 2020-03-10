package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Agent;
import org.tat.gginl.api.domains.repository.AgentRepository;

@Service
public class AgentService {
	
	@Autowired
	private AgentRepository repository;
	
	public List<Agent> findAll(){
		return repository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return repository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return repository.findAllColumnName();
	}


}

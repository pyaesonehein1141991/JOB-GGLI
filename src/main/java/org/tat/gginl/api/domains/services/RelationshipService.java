package org.tat.gginl.api.domains.services;

import java.util.List;

import org.aspectj.asm.internal.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Province;
import org.tat.gginl.api.domains.repository.RelationshipRepository;

@Service
public class RelationshipService {
	
	@Autowired
	private RelationshipRepository relationshipRepository;
	

	public List<Object[]> findAllNativeObject(){
		return relationshipRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return relationshipRepository.findAllColumnName();
	}


}

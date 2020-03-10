package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.Branch;
import org.tat.gginl.api.domains.GradeInfo;
import org.tat.gginl.api.domains.repository.GradeInfoRepository;

@Service
public class GradeInfoService {
	
	@Autowired
	private GradeInfoRepository gradeInfoRepository;
	
	public List<GradeInfo> findAll(){
		return gradeInfoRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return gradeInfoRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return gradeInfoRepository.findAllColumnName();
	}
	

}

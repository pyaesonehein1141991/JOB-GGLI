package org.tat.gginl.api.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.GradeInfo;
import org.tat.gginl.api.domains.Product;
import org.tat.gginl.api.domains.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public List<Object[]> findAllNativeObject(){
		return productRepository.findAllNativeObject();
	}
	
	public List<Object> findAllColumnName(){
		return productRepository.findAllColumnName();
	}
	

}

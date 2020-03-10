package org.tat.gginl.api.domains.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tat.gginl.api.domains.repository.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;


  public List<Object[]> findAllNativeObject(Date createdDate, Date updatedDate) {
    return customerRepository.findAllNativeObject(createdDate, updatedDate, createdDate,
        updatedDate);
  }



  public List<Object[]> findAllNativeObject1(Date runtimeDate, Date nowDate) {
    return customerRepository.findAllNativeObject1(runtimeDate, nowDate);
  }

  public List<Object> findAllColumnName() {
    return customerRepository.findAllColumnName();
  }


}

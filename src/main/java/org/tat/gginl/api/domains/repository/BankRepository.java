package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.Bank;

public interface BankRepository extends JpaRepository<Bank,String> {
	
	String query = "SELECT Rtrim(ID) as ID,Rtrim(NAME) as NAME,Rtrim(DESCRIPTION) as DESCRIPTION,Rtrim(CREATEDUSERID) as CREATEDUSERID,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSERID,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(VERSION) as VERSION,Rtrim(ACODE) as ACODE,Rtrim(MAPKEY) as MAPKEY,Rtrim(CSC_BANK_CODE) as CSC_BANK_CODE" + 
			"  FROM BANK";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'BANK'",nativeQuery = true)
	List<Object> findAllColumnName();
}

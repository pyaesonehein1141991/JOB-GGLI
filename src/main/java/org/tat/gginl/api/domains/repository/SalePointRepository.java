package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.SalePoint;

public interface SalePointRepository extends JpaRepository<SalePoint, String>{
	String query = "SELECT Rtrim(ID) as  ID,Rtrim(SALEPOINTCODE) as SALEPOINTCODE,Rtrim(NAME) as NAME,Rtrim(PHONE) as PHONE,Rtrim(DESCRIPTION) as DESCRIPTION,Rtrim(ADDRESS) as ADDRESS,Rtrim(TOWNSHIPID) as TOWNSHIPID,Rtrim(CREATEDUSERID) as CREATEDUSERID,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSERID,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(VERSION) as VERSION,Rtrim(EMAIL) as EMAIL " + 
			"  FROM SALEPOINT";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'SALEPOINT'",nativeQuery = true)
	List<Object> findAllColumnName();


}

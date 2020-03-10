package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.Township;

public interface TownshipRepository extends JpaRepository<Township, String>{
	String query = "SELECT Rtrim(ID) as ID,Rtrim(NAME) as NAME,Rtrim(DESCRIPTION) as DESCRIPTION,Rtrim(CREATEDUSERID) as CREATEDUSERID,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSERID,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(VERSION) as VERSION,Rtrim(PROVINCEID) as PROVINCEID,Rtrim(MAPKEY) as MAPKEY,Rtrim(RISKACC) as RISKACC" + 
			"  FROM TOWNSHIP";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TOWNSHIP'",nativeQuery = true)
	List<Object> findAllColumnName();

}

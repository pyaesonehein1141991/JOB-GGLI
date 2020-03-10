package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.Province;

public interface ProvienceRepository extends JpaRepository<Province, String> {
	String query = "SELECT Rtrim(ID) AS ID,Rtrim(NAME) AS NAME,Rtrim(DESCRIPTION) AS DESCRIPTION,Rtrim(COUNTRYID) AS COUNTRYID,Rtrim(CREATEDUSERID) AS CREATEDUSERID,Rtrim(CREATEDDATE) AS CREATEDDATE,Rtrim(UPDATEDUSERID) AS UPDATEDUSERID,Rtrim(UPDATEDDATE) AS UPDATEDDATE,Rtrim(VERSION) AS VERSION,Rtrim(MAPKEY) AS MAPKEY" + 
			"  FROM PROVINCE";
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PROVINCE'",nativeQuery = true)
	List<Object> findAllColumnName();
}

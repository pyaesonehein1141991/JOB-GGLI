package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.TownshipCode;

public interface TownShipCodeRepository  extends JpaRepository<TownshipCode,String>{
	String query = "SELECT Rtrim(ID) as ID,Rtrim(NAME) as NAME,Rtrim(TOWNSHIPCODENO) as TOWNSHIPCODENO,Rtrim(STATECODEID) as STATECODEID,Rtrim(VERSION) as VERSION,Rtrim(CREATEDUSERID) as CREATEDUSERID,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSERID,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(MAPKEY) as MAPKEY" + 
			"  FROM TOWNSHIPCODE";
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TOWNSHIPCODE'",nativeQuery = true)
	List<Object> findAllColumnName();
}

package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.RelationShip;

public interface RelationshipRepository extends JpaRepository<RelationShip, String>{
	String query = "SELECT Rtrim(ID) as ID,Rtrim(NAME) as NAME,Rtrim(DESCRIPTION) as DESCRIPTION,Rtrim(CREATEDUSERID) as CREATEDUSERID,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSERID,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(VERSION) as VERSION,Rtrim(MAPKEY) as MAPKEY" + 
			"  FROM RELATIONSHIP";
	@Query(value =query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'RELATIONSHIP'",nativeQuery = true)
	List<Object> findAllColumnName();
}

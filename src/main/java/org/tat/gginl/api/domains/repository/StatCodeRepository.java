package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.StateCode;

public interface StatCodeRepository extends JpaRepository<StateCode, String> {
	String query = "SELECT RTRIM([ID]) AS ID,RTRIM([NAME]) AS NAME,RTRIM([CODENO]) AS CODENO,RTRIM([VERSION]) AS VERSION,RTRIM([CREATEDUSERID]) AS CREATEDUSERID,RTRIM([CREATEDDATE]) AS CREATEDDATE,RTRIM([UPDATEDUSERID]) AS UPDATEDUSERID,RTRIM([UPDATEDDATE]) AS UPDATEDDATE,RTRIM([MAPKEY]) AS MAPKEY" + 
			"  FROM STATECODE";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'STATECODE'",nativeQuery = true)
	List<Object> findAllColumnName();
}

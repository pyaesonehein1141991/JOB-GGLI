package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.Occupation;

public interface OccupationRepository extends JpaRepository<Occupation, String>{
	String query ="SELECT RTRIM([ID]) AS ID,RTRIM([NAME]) AS NAME,RTRIM([DESCRIPTION]) AS DESCRIPTION,RTRIM([CREATEDUSERID]) AS CREATEDUSERID,RTRIM([CREATEDDATE]) AS CREATEDDATE,RTRIM([UPDATEDUSERID]) AS UPDATEDUSERID,RTRIM([UPDATEDDATE]) AS UPDATEDDATE,RTRIM([VERSION]) AS VERSION,RTRIM([MAPKEY]) AS MAPKEY" + 
			"  FROM OCCUPATION";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Occupation'",nativeQuery = true)
	List<Object> findAllColumnName();

}

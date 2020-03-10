package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.School;

public interface SchoolRepository extends JpaRepository<School, String> {

	String query = "SELECT RTRIM([ID]) AS ID,RTRIM([NAME]) AS NAME,RTRIM([ADDRESS]) AS ADDRESS,RTRIM([SCHOOLTYPE]) AS SCHOOLTYPE,RTRIM([SCHOOLLEVELTYPE]) AS SCHOOLLEVELTYPE,RTRIM([TOWNSHIPID]) AS TOWNSHIPID,RTRIM([PHONENO]) AS PHONENO,RTRIM([SCHOOLCODENO]) AS SCHOOLCODENO,RTRIM([CREATEDUSERID]) AS CREATEDUSERID,RTRIM([CREATEDDATE]) AS CREATEDDATE,RTRIM([UPDATEDUSERID]) AS UPDATEDUSERID,RTRIM([UPDATEDDATE]) AS UPDATEDDATE,RTRIM([VERSION]) AS VERSION "
			+ "  FROM SCHOOL";

	@Query(value = query, nativeQuery = true)
	List<Object[]> findAllNativeObject();

	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'SCHOOL'", nativeQuery = true)
	List<Object> findAllColumnName();

}

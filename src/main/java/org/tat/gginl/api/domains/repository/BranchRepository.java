package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.Branch;

public interface BranchRepository extends JpaRepository<Branch, String>{
	
	
	
	String query = "SELECT Rtrim(ID) AS ID,Rtrim(BRANCHCODE) AS BRANCHCODE,Rtrim(NAME) AS NAME,Rtrim(DESCRIPTION) AS DESCRIPTION,Rtrim(ADDRESS) AS ADDRESS,Rtrim(TOWNSHIPID) AS TOWNSHIPID,Rtrim(CREATEDUSERID) AS CREATEDUSERID,Rtrim(CREATEDDATE) AS CREATEDDATE,Rtrim(UPDATEDUSERID) AS UPDATEDUSERID,Rtrim(UPDATEDDATE) AS UPDATEDDATE,Rtrim(VERSION) AS VERSION,Rtrim(ISCOINSUACCESS) AS ISCOINSUACCESS,Rtrim(MAPKEY) AS MAPKEY,Rtrim(EMAIL) AS EMAIL,Rtrim(LATITUDE) AS LATITUDE,Rtrim(LONGITUDE) AS LONGITUDE,Rtrim(URL) AS URL,Rtrim(PHONE) AS PHONE,Rtrim(CONTACTTYPE) AS CONTACTTYPE,Rtrim(ENTITYSID) AS ENTITYSID" + 
			"  FROM BRANCH";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'BRANCH'",nativeQuery = true)
	List<Object> findAllColumnName();

}

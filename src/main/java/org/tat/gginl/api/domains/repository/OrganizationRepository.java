package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String>{
	String query = "SELECT Rtrim(ID) as ID,Rtrim(NAME) as NAME,Rtrim(ADDRESS) as ADDRESS,Rtrim(TOWNSHIP_ID) as TOWNSHIP_ID,Rtrim(CODE_NO) as CODE_NO,Rtrim(REG_NO) as REG_NO,Rtrim(PHONE) as PHONE,Rtrim(FAX) as FAX,Rtrim(MOBILE) as MOBILE,Rtrim(EMAIL) as EMAIL,Rtrim(CREATEDUSERID) as CREATEDUSERID,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSERID,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(DATETIME) as DATETIME,Rtrim(VERSION) as VERSION,Rtrim(OWNER_NAME) as OWNER_NAME,Rtrim(BRANCHID) as BRANCHID,Rtrim(ACTIVEPOLICY) as ACTIVEPOLICY,Rtrim(ACTIVEDDATE) as ACTIVEDDATE,Rtrim(MAPKEY) as MAPKEY" + 
			"  FROM ORGANIZATION";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'ORGANIZATION'",nativeQuery = true)
	List<Object> findAllColumnName();

}

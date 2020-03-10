package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.common.Hospital;

public interface HospitalRepository  extends JpaRepository<Hospital, String>{
	String query ="SELECT Rtrim(ID) as ID,Rtrim(NAME) as Name" + 
			"	  ,Rtrim(DESCRIPTION) as Description,Rtrim(PERMANENTADDRESS) as PermanentAddress,Rtrim(TOWNSHIPID) as TownshipID,Rtrim(PHONE) as Phone,Rtrim(FAX) as Fax,Rtrim(MOBILE) as Mobile,Rtrim(EMAIL) as Email,Rtrim(CREATEDUSERID) as CreatedUserID,Rtrim(CREATEDDATE) as CreatedDate,Rtrim(UPDATEDUSERID) as UpdatedUserID,Rtrim(UPDATEDDATE) as UpdatedDate,Rtrim(PERMANENTTOWNSHIPID) as PermanentTownshipID,Rtrim(VERSION) as Version,Rtrim(MAPKEY) as MapKey" + 
			"  FROM HOSPITAL";
 
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'HOSPITAL'",nativeQuery = true)
	List<Object> findAllColumnName();
}

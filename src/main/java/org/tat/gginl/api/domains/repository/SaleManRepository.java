package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.SaleMan;

public interface SaleManRepository extends JpaRepository<SaleMan, String>{
	String query = "SELECT Rtrim(ID) as ID,Rtrim(INITIALID) as INITIALID,Rtrim(FIRSTNAME) as FIRSTNAME,Rtrim(MIDDLENAME) as MIDDLENAME,Rtrim(LASTNAME) as LASTNAME,Rtrim(IDNO) as IDNO,Rtrim(IDTYPE) as IDTYPE,Rtrim(DATEOFBIRTH) as DATEOFBIRTH,Rtrim(CODENO) as CODENO,Rtrim(LICENSENO) as LICENSENO,Rtrim(BRANCHID) as BRANCHID,Rtrim(ADDRESS) as ADDRESS,Rtrim(TOWNSHIPID) as TOWNSHIPID,Rtrim(VoicePhoneNo) as VoicePhoneNo,Rtrim(FAX) as FAX,Rtrim(EMAIL) as EMAIL,Rtrim(SMSPhoneNo) as SMSPhoneNo,Rtrim(CREATEDUSERID) as CREATEDUSERID,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSERID,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(VERSION) as VERSION,Rtrim(MAPKEY) as MAPKEY,Rtrim(ViberPhoneNo) as ViberPhoneNo,Rtrim(SALEPOINTID) as SALEPOINTID,Rtrim(STATUS) as STATUS" + 
			"  FROM SALEMAN";
	
	@Query(value = query,nativeQuery = true)
	List<Object[]> findAllNativeObject();
	
	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'SALEMAN'",nativeQuery = true)
	List<Object> findAllColumnName();
}

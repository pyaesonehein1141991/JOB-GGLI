package org.tat.gginl.api.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tat.gginl.api.domains.Entitys;

public interface EntityRepository extends JpaRepository<Entitys, String> {

	String query = "SELECT Rtrim(ID) as ID,Rtrim(NAME) as NAME,Rtrim(DESCRIPTION) as DESCRIPTION ,Rtrim(CREATEDDATE) as CREATEDDATE,Rtrim(CREATEDUSERID) as CREATEDUSER,Rtrim(UPDATEDDATE) as UPDATEDDATE,Rtrim(UPDATEDUSERID) as UPDATEDUSER,Rtrim(VERSION) as VERSION "
			+ "  FROM ENTITY";

	@Query(value = query, nativeQuery = true)
	List<Object[]> findAllNativeObject();

	@Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'ENTITY'", nativeQuery = true)
	List<Object> findAllColumnName();
}

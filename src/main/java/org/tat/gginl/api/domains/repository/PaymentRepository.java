package org.tat.gginl.api.domains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tat.gginl.api.domains.Payment;

public interface PaymentRepository extends JpaRepository<Payment,String> {
	
	@Query("SELECT a.accountCode FROM CoaSetup a WHERE a.acccountName = :acccountName AND a.currency = :currency AND a.branchCode = :branchCode")
	public String findCheckOfAccountNameByCode(@Param("acccountName") String accountName,@Param("branchCode") String branchCode,@Param("currency") String currency);
	
	@Query("SELECT p FROM Payment p WHERE p.referenceNo = :referenceNo")
	public Payment findByPaymentReferenceNo(@Param("referenceNo") String referenceNo);

}

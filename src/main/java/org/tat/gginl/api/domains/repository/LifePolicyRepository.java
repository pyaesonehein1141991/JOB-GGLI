package org.tat.gginl.api.domains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tat.gginl.api.domains.LifePolicy;

public interface LifePolicyRepository extends JpaRepository<LifePolicy, String>{
	

}

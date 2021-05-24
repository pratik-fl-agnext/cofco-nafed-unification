package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agnext.unification.entity.nafed.Licence;

public interface LicenceRepository extends JpaRepository<Licence, Long>{

	@Query("from Licence l where l.clientId=:clientId")
	List<Licence> getSubscriptionsByClient(Long clientId);
	
	Licence findByLicenceNo(String licenceNo);
}

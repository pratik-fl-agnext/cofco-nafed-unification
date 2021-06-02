package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agnext.unification.entity.cofco.Licence;

public interface LicenceRepository extends JpaRepository<Licence, Long>{

	@Query("from Licence l where l.clientId=:clientId")
	List<Licence> getSubscriptionsByClient(Long clientId);
	
	Licence findByLicenceNo(String licenceNo);
}

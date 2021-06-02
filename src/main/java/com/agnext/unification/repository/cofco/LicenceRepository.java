package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.Licence;

@Repository("cofcoLicenceRepository")
public interface LicenceRepository extends JpaRepository<Licence, Long>{

	@Query("from Licence l where l.clientId=:clientId")
	List<Licence> getSubscriptionsByClient(Long clientId);
	
	Licence findByLicenceNo(String licenceNo);
}

package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Long> {

	List<StateEntity> findByCountryId(Long countryId);

	StateEntity findByName(String state);
	

	StateEntity findByNameAndCountryId(String state,Long countryId);


}

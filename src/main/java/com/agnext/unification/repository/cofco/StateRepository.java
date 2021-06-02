package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.cofco.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Long> {

	List<StateEntity> findByCountryId(Long countryId);

	StateEntity findByName(String state);

}

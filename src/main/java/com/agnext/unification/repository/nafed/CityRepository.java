package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

	List<CityEntity> findByStateId(Long stateId);
	
	CityEntity findByNameAndStateId(String name,Long stateId);
	
	CityEntity findByName(String name);

}

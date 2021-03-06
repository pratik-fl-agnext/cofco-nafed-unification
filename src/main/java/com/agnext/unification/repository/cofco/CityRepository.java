package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CityEntity;

@Repository("cofcoCityRepository")
public interface CityRepository extends JpaRepository<CityEntity, Long> {

	List<CityEntity> findByStateId(Long stateId);

}

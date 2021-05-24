package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

	CountryEntity findByName(String name);
}

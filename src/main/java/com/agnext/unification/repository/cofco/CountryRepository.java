package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CountryEntity;

@Repository("cofcoCountryRepository")
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

}

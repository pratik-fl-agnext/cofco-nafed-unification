package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.LicenceHistory;

@Repository("cofcoLicenceHistoryRepository")
public interface LicenceHistoryRepository extends JpaRepository<LicenceHistory, Long> {

}

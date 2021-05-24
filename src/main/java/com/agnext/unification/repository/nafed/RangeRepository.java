package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.ClientsAnalyticsRange;

public interface RangeRepository extends JpaRepository<ClientsAnalyticsRange, Long> {

}

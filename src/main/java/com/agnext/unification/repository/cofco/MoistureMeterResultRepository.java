package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.cofco.MoistureMeterResult;

public interface MoistureMeterResultRepository extends JpaRepository<MoistureMeterResult, Long> {

    MoistureMeterResult findBySampleId(String sampleId);
    
    List<MoistureMeterResult> findByClientId(Long operatorId,Pageable pageable);

@Query("select count(m) from MoistureMeterResult m where  m.clientId= :clientId")
    Long findCountByClientId(@Param("clientId") Long clientId);

}

package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.DcmSensorProfile;


@Repository("cofcoDcmSensorProfileRepository")
public interface DcmSensorProfileRepository extends JpaRepository<DcmSensorProfile, Long>{

}

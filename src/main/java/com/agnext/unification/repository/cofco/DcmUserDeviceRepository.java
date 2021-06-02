package com.agnext.unification.repository.cofco;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.DcmUserDevice;

@Repository("cofcoDcmUserDeviceRepository")
public interface DcmUserDeviceRepository extends JpaRepository<DcmUserDevice, Long> {

	//DcmUserDevice findByUserId(Long userId);
	
	DcmUserDevice findByUserId(Long userId);

}

package com.agnext.unification.repository.nafed;


import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.DcmUserDevice;

public interface DcmUserDeviceRepository extends JpaRepository<DcmUserDevice, Long> {

	//DcmUserDevice findByUserId(Long userId);
	
	DcmUserDevice findByUserId(Long userId);

}

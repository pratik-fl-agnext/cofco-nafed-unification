package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agnext.unification.entity.nafed.AndroidAppVersion;

public interface AndroidAppVersionRepository extends JpaRepository<AndroidAppVersion, Long> {

	@Query("SELECT aav FROM AndroidAppVersion aav where aav.createdOn=(select max(createdOn) from AndroidAppVersion)")
	AndroidAppVersion findByVersionName();

}

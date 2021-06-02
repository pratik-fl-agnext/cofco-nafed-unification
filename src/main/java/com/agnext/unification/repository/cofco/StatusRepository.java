package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.StatusEntity;

@Repository("cofcoStatusRepository")
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

	StatusEntity findByStatusId(Long status);

	StatusEntity findByStatusDesc(String statusDesc);

}

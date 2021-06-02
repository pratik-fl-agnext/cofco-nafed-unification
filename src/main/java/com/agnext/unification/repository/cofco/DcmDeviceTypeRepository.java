/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.DcmDeviceType;

/**
 * The Interface DcmDeviceTypeRepository.
 */
@Repository("cofcoDcmDeviceTypeRepository")
public interface DcmDeviceTypeRepository extends JpaRepository<DcmDeviceType, Long> {

	DcmDeviceType findByDeviceTypeDesc(String devicetype);
//
//	List<DcmDeviceType> findBy(Long id, Pageable pageable);




}

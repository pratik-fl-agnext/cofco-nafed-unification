/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.DcmDeviceType;

/**
 * The Interface DcmDeviceTypeRepository.
 */
public interface DcmDeviceTypeRepository extends JpaRepository<DcmDeviceType, Long> {

	DcmDeviceType findByDeviceTypeDesc(String devicetype);
//
//	List<DcmDeviceType> findBy(Long id, Pageable pageable);




}

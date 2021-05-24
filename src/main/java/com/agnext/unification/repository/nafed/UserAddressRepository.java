/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.UserAddressEntity;

/**
 * The Interface UserAddressRepository.
 */
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

	UserAddressEntity findByAddressId(Long addressId);

	List<UserAddressEntity> findByUserUserIdIn(List<Long> userIds);


}

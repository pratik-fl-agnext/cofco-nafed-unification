/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.RolePermissionEntity;

/**
 * The Interface RolePermissionRepository.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {


}

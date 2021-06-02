/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.cofco.RolePermissionEntity;

/**
 * The Interface RolePermissionRepository.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {


}

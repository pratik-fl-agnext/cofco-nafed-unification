/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.RolePermissionEntity;

/**
 * The Interface RolePermissionRepository.
 */
@Repository("cofcoRolePermissionRepository")
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {


}

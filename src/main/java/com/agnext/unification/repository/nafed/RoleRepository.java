/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.RoleEntity;

/**
 * The Interface RoleRepository.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByRoleCodeIgnoringCaseAndStatusStatusId(String roleCode, Long status);

	RoleEntity findByRoleIdAndStatusStatusId(Long roleId, Long id);

	List<RoleEntity> findByRoleCodeInAndStatusStatusId(List<String> roleIds, Long statusId);

	List<RoleEntity> findByStatusStatusId(Long id);

}

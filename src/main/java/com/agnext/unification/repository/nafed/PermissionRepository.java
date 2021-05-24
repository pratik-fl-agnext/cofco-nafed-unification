package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

	PermissionEntity findByPermissionCodeIgnoringCaseAndStatusStatusId(String permissionCode, Long status);

	PermissionEntity findByPermissionIdAndStatusStatusId(Long permissionId, Long statusId);

	List<PermissionEntity> findByPermissionIdInAndStatusStatusId(List<Long> permissions, Long id);
	List<PermissionEntity> findByPermissionCodeInAndStatusStatusId(List<String> permissions, Long id);

}

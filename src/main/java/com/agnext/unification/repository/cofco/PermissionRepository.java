package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.PermissionEntity;

@Repository("cofcoPermissionRepository")
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

	PermissionEntity findByPermissionCodeIgnoringCaseAndStatusStatusId(String permissionCode, Long status);

	PermissionEntity findByPermissionIdAndStatusStatusId(Long permissionId, Long statusId);

	List<PermissionEntity> findByPermissionIdInAndStatusStatusId(List<Long> permissions, Long id);
	List<PermissionEntity> findByPermissionCodeInAndStatusStatusId(List<String> permissions, Long id);

}

/*
 * @author Vishal Bansal
 * @version 1.0
 */
package com.agnext.unification.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.PermissionEntity;
import com.agnext.unification.entity.nafed.RoleEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.model.RoleModel;
import com.agnext.unification.repository.nafed.PermissionRepository;
import com.agnext.unification.repository.nafed.RolePermissionRepository;
import com.agnext.unification.repository.nafed.RoleRepository;
import com.agnext.unification.validator.PermissionValidator;
import com.agnext.unification.validator.RoleValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RoleService extends GenericService {

	private static Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	RoleValidator validator;

	@Autowired
	RoleRepository repository;

	@Autowired
	PermissionValidator permissionValidator;

	@Autowired
	RolePermissionRepository rolePermissionRepository;

	@Autowired
	PermissionRepository permissionRepository;

	/**
	 * Creates the role.
	 *
	 * @param request the request
	 * @return the role model
	 * @throws IMException the IM exception
	 */
	public RoleModel createRole(RoleModel request) throws IMException {
		logger.debug("RoleService.createRole creating role in system");
		validator.validateCreateRole(request);
		RoleEntity entity = repository
				.save(VOToEntityAssembler.createRole(request, getStatusEntity(Constants.STATUS.ACTIVE.getId())));
		logger.info("RoleService.createRole role saved successfully");
		RoleModel response = EntityToVOAssembler.convert(entity);
		logger.info("RoleService.createRole response is :" + response);
		return response;
	}

	public List<RoleModel> assignPermissionsToRole(List<PermissionModel> permissions, Long roleId) throws IMException {

		RoleEntity roleEntity = validator.validateIfRoleExists(roleId);
		List<PermissionEntity> permissionEntities = permissionValidator.validateIfPermissionsExist(permissions);
		List<RoleEntity> roleEntities = prepareRolePermissionsEntities(roleEntity, permissionEntities);
		return EntityToVOAssembler.convertRoleEntityToRoleModel(repository.saveAll(roleEntities));
	}

	private List<RoleEntity> prepareRolePermissionsEntities(RoleEntity roleEntity,
			List<PermissionEntity> permissionEntities) {
		List<RoleEntity> roleLst = new ArrayList<>();
		for (PermissionEntity permissionEntity : permissionEntities) {
			roleEntity.addPermission(permissionEntity);
			roleLst.add(roleEntity);

		}
		return roleLst;
	}

	public List<RoleModel> getAllRoles() {
		List<RoleEntity> roles = repository.findByStatusStatusId(Constants.STATUS.ACTIVE.getId());
		return EntityToVOAssembler.convertRole(roles);
	}

	public List<RoleModel> getPermissionsForRole() {
		List<RoleEntity> roleEntities=repository.findAll();
		return EntityToVOAssembler.convertRolePermission(roleEntities);
	}
}

/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.PermissionEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.repository.nafed.PermissionRepository;
import com.agnext.unification.validator.PermissionValidator;

@Service
public class PermissionService extends GenericService {

	private static Logger logger = LoggerFactory.getLogger(PermissionService.class);

	@Autowired
	PermissionValidator validator;

	@Autowired
	PermissionRepository repository;

	/**
	 * Creates the permission.
	 *
	 * @param request the request
	 * @return the permission model
	 * @throws IMException the IM exception
	 */
	public PermissionModel createPermission(PermissionModel request) throws IMException {
		logger.debug("PermissionService.createPermission creating permission in system");
		validator.validateCreatePermission(request);
		PermissionEntity entity = repository
				.save(VOToEntityAssembler.createPermission(request, getStatusEntity(Constants.STATUS.ACTIVE.getId())));
		logger.info("PermissionService.createPermission permission saved successfully");
		PermissionModel response = EntityToVOAssembler.convert(entity);
		logger.info("RoleService.createPermission response is :" + response);
		return response;
	}

	/**
	 * Delete permission.
	 *
	 * @param id the id
	 * @throws IMException the IM exception
	 */
	public void deletePermission(Long id) throws IMException {
		logger.debug("PermissionService.deletePermission delete permission in system");
		validator.validateIfPermissionExists(id);
		Optional<PermissionEntity> optEntity = repository.findById(id);
		if(optEntity.isPresent()) {
			logger.info("PermissionService.deletePermission permission deleted successfully");
			repository.delete(optEntity.get());
		}
	}

}

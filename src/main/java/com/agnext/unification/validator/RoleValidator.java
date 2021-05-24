/*
 * @author Vishal B.
 * @version 1.0
 */

package com.agnext.unification.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.RoleEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.RoleModel;
import com.agnext.unification.repository.nafed.RoleRepository;
import com.agnext.unification.repository.nafed.StatusRepository;

@Component
public class RoleValidator extends Validator {

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	StatusRepository statusRepo;

	/**
	 * Validate create role.
	 *
	 * @param request the request
	 * @throws IMException the IM exception
	 */
	public void validateCreateRole(RoleModel request) throws IMException {

		RoleEntity roleEntity = roleRepo.findByRoleCodeIgnoringCaseAndStatusStatusId(request.getRoleCode(),
				Constants.STATUS.ACTIVE.getId());
		if (roleEntity != null)
			throw new IMException(Constants.ErrorCode.ROLE_CODE_ALREADY_EXIST,
					Constants.ErrorMessage.ROLE_CODE_ALREADY_EXIST);
		validateMaxLength(request.getRoleCode(), Constants.FieldLength.ROLE_CODE_MAX_LENGTH,
				Constants.ErrorCode.ROLE_CODE_INVALID_MAX_LENGTH, Constants.ErrorMessage.ROLE_CODE_INVALID_MAX_LENGTH);
		validateMaxLength(request.getRoleDesc(), Constants.FieldLength.ROLE_DESC_MAX_LENGTH,
				Constants.ErrorCode.ROLE_CODE_INVALID_MAX_LENGTH, Constants.ErrorMessage.ROLE_DESC_INVALID_MAX_LENGTH);

	}

	public RoleEntity validateIfRoleExists(Long roleId) throws IMException {

		RoleEntity roleEntity = roleRepo.findByRoleIdAndStatusStatusId(roleId, Constants.STATUS.ACTIVE.getId());
		if (roleEntity == null)
			throw new IMException(Constants.ErrorCode.ROLE_NOT_FOUND, Constants.ErrorMessage.ROLE_NOT_FOUND);

		return roleEntity;

	}

	public List<RoleEntity> validateIfRolesExist(List<String> roles) throws IMException {
		// List<String> roleIds = convertToIdList(roles);
		if (roles == null) {
			throw new IMException(Constants.ErrorCode.ROLE_NOT_FOUND, Constants.ErrorMessage.ROLE_NOT_FOUND);
		}

		List<RoleEntity> roleEntities = roleRepo.findByRoleCodeInAndStatusStatusId(roles,
				Constants.STATUS.ACTIVE.getId());
		if (roleEntities == null || (roleEntities.size() != roleEntities.size())) {
			throw new IMException(Constants.ErrorCode.ROLES_ID_NOT_EXIST,
					Constants.ErrorMessage.ROLES_ID_NOT_EXIST + fetchDeltaRoles(roles, roleEntities));
		}

//		for (RoleEntity roleEntity : roleEntities) {
//			RoleEntity entity = roleRepo.findByRoleIdAndStatusStatusId(roleEntity.getRoleId(),
//					Constants.STATUS.ACTIVE.getId());
//			if (entity != null) {
//				if (CommonUtil.isEmpty(entity.getPermissions())) {
//					throw new IMException(Constants.ErrorCode.PERMISSION_ID_NOT_EXIST,
//							Constants.ErrorMessage.PERMISSION_ID_NOT_EXIST);
//				}
//			}
//		}

		return roleEntities;
	}

	private String fetchDeltaRoles(List<String> roleCode, List<RoleEntity> roleEntities) {
		StringBuffer buffer = new StringBuffer();
		for (String role : roleCode) {
			for (RoleEntity roleEntity : roleEntities) {
				if (roleEntity.getRoleCode().equals(role)) {
					continue;
				}
			}
			buffer.append(role + ",");
		}

		return buffer.substring(0, buffer.length() - 1);
	}

	@SuppressWarnings("unused")
	private List<String> convertToIdList(List<RoleModel> roles) {
		List<String> response = new ArrayList<String>();

		for (RoleModel role : roles) {
			response.add(role.getRoleCode());
		}

		return response;
	}

	 
}

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
import com.agnext.unification.entity.nafed.PermissionEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.repository.nafed.PermissionRepository;
import com.agnext.unification.repository.nafed.StatusRepository;

@Component
public class PermissionValidator extends Validator {
	
	@Autowired
	PermissionRepository permissionRepo;
	
	@Autowired
	StatusRepository statusRepo;

	/**
	 * Validate create permission.
	 *
	 * @param request the request
	 * @throws IMException the IM exception
	 */
	public void validateCreatePermission(PermissionModel request) throws IMException {
		
		PermissionEntity permissionEntity = permissionRepo.findByPermissionCodeIgnoringCaseAndStatusStatusId(request.getPermissionCode(),
				Constants.STATUS.ACTIVE.getId());
		if (permissionEntity != null)
			throw new IMException(Constants.ErrorCode.PERMISSION_CODE_ALREADY_EXIST,
					Constants.ErrorMessage.PERMISSION_CODE_ALREADY_EXIST);
		validateMaxLength(request.getPermissionCode(), Constants.FieldLength.ROLE_CODE_MAX_LENGTH,
				Constants.ErrorCode.ROLE_CODE_INVALID_MAX_LENGTH, Constants.ErrorMessage.ROLE_CODE_INVALID_MAX_LENGTH);
		validateMaxLength(request.getPermissionDesc(), Constants.FieldLength.ROLE_DESC_MAX_LENGTH,
				Constants.ErrorCode.ROLE_CODE_INVALID_MAX_LENGTH, Constants.ErrorMessage.ROLE_DESC_INVALID_MAX_LENGTH);
		
	}
	
	/**
	 * Validate permission.
	 *
	 * @param permissionId the permission id
	 * @throws IMException the IM exception
	 */
	public void validateIfPermissionExists(Long permissionId) throws IMException {
		PermissionEntity permissionEntity = permissionRepo.findByPermissionIdAndStatusStatusId(permissionId,
				Constants.STATUS.ACTIVE.getId());
		if (permissionEntity == null)
			throw new IMException(Constants.ErrorCode.PERMISSION_ID_NOT_EXIST,
					Constants.ErrorMessage.PERMISSION_ID_NOT_EXIST);
	}
	
	public List<PermissionEntity> validateIfPermissionsExist(List<PermissionModel> permissions) throws IMException {
		List<String> permissionCodes = convertToIdList(permissions);
		List<PermissionEntity> permissionEntities = permissionRepo.findByPermissionCodeInAndStatusStatusId(permissionCodes,
				Constants.STATUS.ACTIVE.getId());
		if (permissionEntities == null || (permissionEntities.size() != permissions.size())) {
			throw new IMException(Constants.ErrorCode.PERMISSIONS_ID_NOT_EXIST,
					Constants.ErrorMessage.PERMISSIONS_ID_NOT_EXIST+fetchDeltaPermissions(permissionCodes,permissionEntities));
		}
		
		return permissionEntities;
			
	}

	private List<String> convertToIdList(List<PermissionModel> permissions) {
		List<String> response = new ArrayList<String>();
		for(PermissionModel permission : permissions) {
			response.add(permission.getPermissionCode());
		}
		return response;
	}

	private String fetchDeltaPermissions(List<String> permissions, List<PermissionEntity> permissionEntities) {
		StringBuffer buffer = new StringBuffer();
		for (String permission : permissions) {
			for (PermissionEntity permissionEntity : permissionEntities) {
				if (permissionEntity.getPermissionCode().equals(permission)) {
					continue;
				}
			}
			buffer.append(permission + ",");
		}

		return buffer.substring(0, buffer.length()-1);
	}

}

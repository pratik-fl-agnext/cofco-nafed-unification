package com.agnext.unification.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.DcmCommodityCategory;
import com.agnext.unification.entity.nafed.DcmDeviceType;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.repository.nafed.DcmCommodityCategoryRepository;
import com.agnext.unification.repository.nafed.DeviceTypeRepository;
import com.agnext.unification.repository.nafed.ScanLocationRepository;

@Component
public class BaseValidator extends Validator {

	@Autowired
	DcmCommodityCategoryRepository categoryRepository;
	
	@Autowired
	DeviceTypeRepository deviceTypeRepo;
	
	@Autowired
	ScanLocationRepository locationRepo;
	
	/**
	 * Validate commodity category id valid.
	 *
	 * @param request the request
	 * @throws DCMException the DCM exception
	 */

	public DcmCommodityCategory commodityCategoryValidate(Long categoryId) throws IMException {
		Optional<DcmCommodityCategory> optCategory = categoryRepository.findByIdAndStatusStatusId(categoryId,
				Constants.STATUS.ACTIVE.getId());
		if (optCategory == null)
			throw new IMException(Constants.ErrorCode.COMMODITY_CATEGORY_ID_NOT_EXIST,
					Constants.ErrorMessage.COMMODITY_CATEGORY_ID_NOT_EXIST);
		return optCategory.get();
	}
	
	public DcmDeviceType validateDcmDeviceType(Long id) throws IMException {
		if (id == null || id == 0) {
			if (id == null) {
				throw new IMException(Constants.ErrorCode.TYPE_ID_NOT_EXIST, Constants.ErrorMessage.TYPE_ID_NOT_EXIST);
			}
		}
		DcmDeviceType type = deviceTypeRepo.getOne(id);
		if (type == null) {
			throw new IMException(Constants.ErrorCode.TYPE_ID_NOT_EXIST, Constants.ErrorMessage.TYPE_ID_NOT_EXIST);
		}
		return type;
	}

	public ScanLocation validateDcmCommercialLocation(Long id) throws IMException {
		ScanLocation clocation = locationRepo.findByIdAndStatusStatusId(id,
				Constants.STATUS.ACTIVE.getId());
		if (clocation == null) {
			throw new IMException(Constants.ErrorCode.COMMERCIAL_LOCATION_ID_NOT_EXIST,
					Constants.ErrorMessage.COMMERCIAL_LOCATION_ID_NOT_EXIST);
		}
		return clocation;
	}
}

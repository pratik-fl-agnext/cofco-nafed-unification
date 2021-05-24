package com.agnext.unification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.AndroidAppVersion;
import com.agnext.unification.model.AndroidVersionModel;
import com.agnext.unification.repository.nafed.AndroidAppVersionRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class BypassedRequestService extends GenericService {

	@Autowired
	AndroidAppVersionRepository androidAppVersionRepo;

	public AndroidVersionModel getAndroidAppVersion() {

		AndroidAppVersion appVersionEntity = new AndroidAppVersion();
		AndroidVersionModel versionModel = new AndroidVersionModel();
		appVersionEntity = androidAppVersionRepo.findByVersionName();

		if (appVersionEntity != null) {
			versionModel.setCreatedOn(appVersionEntity.getCreatedOn());
			versionModel.setVersionName(appVersionEntity.getVersionName());
			versionModel.setLink(appVersionEntity.getLink());
			versionModel.setValidTill(appVersionEntity.getValidTill());
			versionModel.setStatus(Constants.APPVERSIONSTATUS.getAbbr(appVersionEntity.getStatus()));
			versionModel.setForceUpload(appVersionEntity.isForceUpdateFlag());
		}

		return versionModel;
	}

}

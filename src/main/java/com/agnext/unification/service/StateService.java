/*
 * @author Vishal Bansal
 * @version 1.0
 */
package com.agnext.unification.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.model.StateModel;
import com.agnext.unification.repository.nafed.ScanLocationRepository;
import com.agnext.unification.repository.nafed.ScmScanRepository;
import com.agnext.unification.repository.nafed.StateRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class StateService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(StateService.class);

    @Autowired
    StateRepository repo;

    @Autowired
    ScmScanRepository scanRepo;

    @Autowired
    ScanLocationRepository scanLocationRepo;

    public List<StateModel> getAllStates(Long countryId) {
	logger.info("get list of states by country id");
	List<StateEntity> stateEntities = repo.findByCountryId(countryId);
	return EntityToVOAssembler.convertStates(stateEntities);
    }

    public List<StateModel> getStatesFromScan() {
	Long customerId = null;

	if (applicationContext.getRequestContext().getCustomerId() != null) {
	    customerId = applicationContext.getRequestContext().getCustomerId();
	    List<Long> installationCenterIds = scanRepo.getInstallationCenterByCustomerId(customerId);
	    if (!installationCenterIds.isEmpty()) {
		List<StateEntity> stateEntities = scanLocationRepo.getStateByIdAndStatusStatusId(installationCenterIds,
			Constants.STATUS.ACTIVE.getId());

		return EntityToVOAssembler.convertStates(stateEntities);
	    }
	}
	return null;
    }

}

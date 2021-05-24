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
import com.agnext.unification.entity.nafed.CityEntity;
import com.agnext.unification.model.CityModel;
import com.agnext.unification.repository.nafed.CityRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CityService extends GenericService {

	private static Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	CityRepository repo;


	public List<CityModel> getAllCities(Long stateId) {
		logger.info("get list of cities by state id");
		List<CityEntity> cityEntities= repo.findByStateId(stateId);
		return EntityToVOAssembler.convertCities(cityEntities);
	}

}

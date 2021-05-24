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
import com.agnext.unification.entity.nafed.CountryEntity;
import com.agnext.unification.model.CountryModel;
import com.agnext.unification.repository.nafed.CountryRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CountryService extends GenericService {

	private static Logger logger = LoggerFactory.getLogger(CountryService.class);

	@Autowired
	CountryRepository repo;

	public List<CountryModel> getAllCountries() {

		logger.info("fetch all countries list");
		List<CountryEntity> countryEntities = repo.findAll();
		return EntityToVOAssembler.convertCountries(countryEntities);
		
	}

}

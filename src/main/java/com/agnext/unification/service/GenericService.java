package com.agnext.unification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agnext.unification.config.ApplicationContext;
import com.agnext.unification.entity.nafed.StatusEntity;
import com.agnext.unification.exception.ErrodModel;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.repository.nafed.StatusRepository;

@Service
public class GenericService {
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	ApplicationContext applicationContext;
	
	private static final String GENERIC_ERROR_CODE="GEN0001";
	
 
	public ResponseEntity<?> handleException(Exception e){
		e.printStackTrace();
		if(e instanceof IMException) {
			IMException ex = (IMException) e;
			return new ResponseEntity<>(new ErrodModel(ex.getErrorCode(), ex.getMessage()),HttpStatus.BAD_REQUEST);
		}
		else
			return new ResponseEntity<>(new ErrodModel(GENERIC_ERROR_CODE, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public StatusEntity getStatusEntity(Long statusId) {
		return statusRepository.findByStatusId(statusId);
	}
	
	
}


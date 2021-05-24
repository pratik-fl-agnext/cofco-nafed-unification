package com.agnext.unification.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.entity.nafed.DcmUserDevice;
import com.agnext.unification.model.UserDeviceModel;
import com.agnext.unification.repository.nafed.DcmUserDeviceRepository;

@Service
@Transactional
public class UserDeviceTokenService extends GenericService{
	
	@Autowired
	DcmUserDeviceRepository repository;

	public UserDeviceModel saveOrUpdateDeviceToken(UserDeviceModel request) {
		DcmUserDevice dcmUserDevice = repository.findByUserId(applicationContext.getRequestContext().getUserId());
		if(dcmUserDevice==null) {
			dcmUserDevice= new DcmUserDevice();
			dcmUserDevice.setCreatedOn(Instant.now().getEpochSecond());
		}
		dcmUserDevice.setUserId(applicationContext.getRequestContext().getUserId());
		dcmUserDevice.setDeviceToken(request.getDeviceToken());
		dcmUserDevice.setModifiedOn(Instant.now().getEpochSecond());
		dcmUserDevice.setCreatedBy(applicationContext.getRequestContext().getUserId());
		dcmUserDevice.setModifiedBy(applicationContext.getRequestContext().getUserId());
		DcmUserDevice userDevice=repository.save(dcmUserDevice);
		return EntityToVOAssembler.convertUserDevice(userDevice);
	}
}

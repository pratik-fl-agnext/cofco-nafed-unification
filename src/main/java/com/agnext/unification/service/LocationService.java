package com.agnext.unification.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.config.RequestContext;
import com.agnext.unification.config.ServerContext;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.LocationListModel;
import com.agnext.unification.model.LocationModel;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.FilterRepository;
import com.agnext.unification.repository.nafed.ScanLocationRepository;
import com.agnext.unification.repository.nafed.StateRepository;



@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class LocationService extends GenericService{
	
	private static Logger logger = LoggerFactory.getLogger(LocationService.class);
	
	@Autowired
	FilterRepository filterRepo;
	
	@Autowired
	ServerContext serverContext;
	
	@Autowired
	StateRepository stateRepo;
	
	@Autowired
	ScanLocationRepository repo;
	
	@Autowired
	DeviceRepository deviceRepo;

	public List<LocationModel> getAllCommercialLocationByFilter(String keySearch, Integer pageNumber, Integer limit,
			Long customerId2, Long regionId, Long installationCenterTypeId) throws IMException {
		logger.info("get  comnmercial locations fetching");
		Long customerId=null;
		if(customerId2 !=null) {
		 customerId = setCustomerId(customerId2);
		}
		RequestContext requestContext = serverContext.getRequestContext();

		List<Long> instCenterIds=deviceRepo.getInstCenterId(requestContext.getUserId());
		
		

		List<ScanLocation> list = filterRepo.getAllCommercialLocationByFilter(keySearch, customerId,
				pageNumber, limit, regionId, installationCenterTypeId,instCenterIds);
		Long count = filterRepo.getAllCommercialLocationByFilterCount(keySearch, customerId, regionId);

		List<LocationModel> locationsList = new ArrayList<>();
		for (ScanLocation loc : list) {
			LocationModel model = EntityToVOAssembler.convertLocationTypeDetial(loc, count);
			locationsList.add(model);
		}

		return locationsList;
	}

//	private Long setCustomerId(Long customerId2) throws IMException {
//		logger.info(" Inside setCustomerId method, the customer id to set is : "+ customerId2);
//		System.out.println(" Request Context : "+applicationContext.getRequestContext());
//		Long customerId = null;
//		if (Constants.CustomerType.SERVICE_PROVIDER.equals(applicationContext.getRequestContext().getCustomerType())) {
//			if (customerId2 == null)
//				throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_PROVIDED,
//						Constants.ErrorMessage.CUSTOMER_ID_NOT_PROVIDED);
//
//			customerId = customerId2;
//
//		} else if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
//			customerId = applicationContext.getRequestContext().getCustomerId();
//
//		}
//		return customerId;
//	}
	
	private Long setCustomerId(Long customerId2) throws IMException {
	    logger.info(" Inside setCustomerId method, the customer id to set is : " + customerId2);
	    System.out.println(" Request Context : " + applicationContext.getRequestContext());
	    Long customerId = null;
	    if (Constants.CustomerType.CLIENT.equals(applicationContext.getRequestContext().getCustomerType())) {
	    if (customerId2 == null) {
	    customerId = applicationContext.getRequestContext().getCustomerId();
	    } else {
	    customerId = customerId2;
	    }

	    } else if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
	    customerId = null;

	    }else if(applicationContext.getRequestContext().getRoles().contains("state_admin")) {
			customerId=applicationContext.getRequestContext().getCustomerId();
		}
	    return customerId;
	    }

	public LocationModel saveCommercialLocation(LocationModel request) throws IMException {
		Long customerId=null;
		if(request.getCustomerId() !=null) {
				customerId=setCustomerId(request.getCustomerId());
		}
		logger.info("save commercial location detail");
		List<ScanLocation> isWarehouseExist= new ArrayList<ScanLocation>();
		isWarehouseExist=repo.findLocationDuplicatesByWarehouseName(request.getWarehouseName(),request.getCode());
		if(isWarehouseExist !=null)
		logger.debug("No of duplicates exists : "+isWarehouseExist.size());
		if(isWarehouseExist !=null && isWarehouseExist.size()>0) {
			for (ScanLocation existingLocation : isWarehouseExist) {
				if(existingLocation !=null) {
					if(existingLocation.getCode().equalsIgnoreCase(request.getCode())) {
						throw new IMException("CODE-EXIST-101", "Provided Location Code Already Exists In The System");
					} 
					else if(existingLocation.getLocationName().equalsIgnoreCase(request.getWarehouseName()) ){
						throw new IMException("WH-EXIST-101", "Provided Warehouse Already Exists In The System");
					}
				}
			}
		}
		//DcmCommercialLocationType commercialLocationType = validator
//				.validateCommercialLocationType(request.getCommercialLocationTypeId());

		RequestContext requestContext = serverContext.getRequestContext();
		
		ScanLocation locationRequest = new ScanLocation();
		

//		validator.validateCommercialLocationName(request);

//		DcmRegion dcmRegion = validator.validateRegionForCustomer(request);
//		DcmSite dcmSite = validator.validateSite(request.getSiteId());
        StateEntity state= stateRepo.getOne(request.getStateId());
		ScanLocation location = VOToEntityAssembler.convertCommercialLocation(request,
				 getStatusEntity(Constants.STATUS.ACTIVE.getId()),
				false,state,requestContext.getUserId(),locationRequest);

		return EntityToVOAssembler
				.convertCommercialLocationTypeDetial(repo.save(location), 0L);
	}

	public LocationModel updateCommercialLocation(Long installationCenterId, LocationModel request) throws IMException {

		Long customerId=null;
		if(request.getCustomerId() !=null) {
				customerId=setCustomerId(request.getCustomerId());
		}
		logger.info("save commercial location detail");
		
//		DcmCommercialLocationType commercialLocationType = validator
//				.validateCommercialLocationType(request.getCommercialLocationTypeId());

		RequestContext requestContext = serverContext.getRequestContext();
		ScanLocation location = repo.getOne(installationCenterId);

		

//		validator.validateCommercialLocationName(request);

//		DcmRegion dcmRegion = validator.validateRegionForCustomer(request);
//		DcmSite dcmSite = validator.validateSite(request.getSiteId());
        StateEntity state= stateRepo.getOne(request.getStateId());
		ScanLocation processedlocation = VOToEntityAssembler.convertCommercialLocation(request,
				 getStatusEntity(Constants.STATUS.ACTIVE.getId()),
				true,state,requestContext.getUserId(),location);

		return EntityToVOAssembler
				.convertCommercialLocationTypeDetial(repo.save(processedlocation), 0L);
	}

	public void deleteCommercialLocation(Long installationCenterId) {

		String locationName="";
		String warehouseName="";
		
		ScanLocation location =repo.getOne(installationCenterId);
		locationName=location.getLocationName();
		warehouseName=location.getWarehouseName();
		location.setStatus(getStatusEntity(Constants.STATUS.DELETED.getId()));
		repo.save(location);
		
	}

	public LocationModel getLocationById(Long installationCenterId) {
		ScanLocation scanLocation=repo.getOne(installationCenterId);
		return EntityToVOAssembler.convertCommercialLocationTypeDetial(scanLocation, 0l);
	}

	public LocationListModel getLocationByStateId(Long stateId) {
	
		LocationListModel locationListModel= new LocationListModel();
		List<LocationModel> locations= new ArrayList<LocationModel>();
		
		List<ScanLocation> scanLocatioList = repo.findByStateId(stateId);
		HashMap<String, ScanLocation> districtMap = new HashMap<String, ScanLocation>();
		scanLocatioList.forEach(e -> districtMap.put(e.getWarehouseName().trim(), e));
		scanLocatioList.clear();
		districtMap.forEach((key, value) -> scanLocatioList.add(value));
		
		for (ScanLocation location : scanLocatioList) {
			LocationModel locationModel= new LocationModel();
			
			if(location !=null) {
				locationModel.setInstallationCenterId(location.getId());
				locationModel.setWarehouseName(location.getLocationName());
				locationModel.setLocationName(location.getWarehouseName());
				locationModel.setStateId(location.getState().getId());
				locationModel.setStateName(location.getState().getName());
				locationModel.setCode(location.getCode());
				
				locations.add(locationModel);
			}
			
		}
		locationListModel.setLocationsByState(locations);
		locationListModel.setSize(locations.size());
		return locationListModel;
	}
}

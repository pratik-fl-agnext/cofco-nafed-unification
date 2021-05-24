package com.agnext.unification.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.cofco.CofcoCommodityEntity;
import com.agnext.unification.entity.nafed.CommodityVarietyEntity;
import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.DcmCommodityCategory;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.DeviceCommodityEntity;
import com.agnext.unification.entity.nafed.StateManagerOperatorsEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.CategoryCommodityVarietyModel;
import com.agnext.unification.model.CategoryCommodityVarietyWrapperModel;
import com.agnext.unification.model.CommoditiesModel;
import com.agnext.unification.model.CommodityCategoryModel;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.model.CommodityVarietyModel;
import com.agnext.unification.model.VarietyModel;
import com.agnext.unification.repository.cofco.CofcoCommodityRepository;
import com.agnext.unification.repository.nafed.CommodityVarietyRepository;
import com.agnext.unification.repository.nafed.DcmCommodityCategoryRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.repository.nafed.DeviceCommodityRepository;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.FilterRepository;
import com.agnext.unification.repository.nafed.ScmScanRepository;
import com.agnext.unification.repository.nafed.StateManagerOperatorRepository;
import com.agnext.unification.validator.BaseValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CommodityService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(CommodityService.class);

    @Autowired
    DcmCommodityCategoryRepository repo;

    @Autowired
    DcmCommodityRepository commodityRepo;

    @Autowired
    BaseValidator validator;

    @Autowired
    FilterRepository filterRepo;

    @Autowired
    ScmScanRepository scanRepo;
    
    @Autowired
    DeviceCommodityRepository deviceCommodityRepo;
    
    @Autowired
    CommodityVarietyRepository cVRepo;
    
    @Autowired
    StateManagerOperatorRepository stateManagerOperatorRepo;
    
    @Autowired
    DeviceRepository deviceRepo;
    
    @Autowired
    CofcoCommodityRepository cofcoCommRepo;

    public List<CommodityCategoryModel> getCommodityCategory(Integer pageNumber, Integer limit, Long customerId2) {
	logger.info("get commodity category details list");
	Sort sort = Sort.by(Direction.DESC, "id");
	Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
		Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT), sort);
	//		if(customerId !=null) {
	//			return commodityCategoryByCustomerId(customerId);
	//		}
	Long customerId=setCustomerId(customerId2);
	Long operatoeId=setOperatorId();
	List<DcmCommodityCategory> categoriesList = new ArrayList<>();
	if(applicationContext.getRequestContext().getCustomerType().equals(Constants.CustomerType.SERVICE_PROVIDER)) {
		categoriesList = repo.findAll();
	}
	
	else if(applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.STATE_ADMIN)) {
	    List<Long> categoryIds = scanRepo.getCategoriesForStateAdmin(applicationContext.getRequestContext().getStateAdmin());
	    if (!categoryIds.isEmpty()) {
		    categoriesList = repo.findAllCategoryById(categoryIds);
	    }
	    return EntityToVOAssembler.convertCommodityCategory(categoriesList);
	}
	
	else {
	List<Long> categoryIds = scanRepo.getCategoryIdsByCustomer(customerId,operatoeId);
	if (categoryIds != null && categoryIds.size() != 0) {
	    categoriesList = repo.findAllCategoryById(categoryIds);
	} else {
	    categoriesList = repo.findAll();
	}
	}
	return EntityToVOAssembler.convertCommodityCategory(categoriesList);

    }

    public List<CommodityModel> getCommodityOrCategoryId(Long commodityCategoryId, Integer pageNumber, Integer limit)
	    throws IMException {
	Sort sort = Sort.by(Direction.ASC, "id");
	Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
		Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT_100), sort);

	List<CommodityModel> models = new ArrayList<CommodityModel>();
	List<DcmCommodity> commodities= null;
	List<Long> commodityIds=null;
	Long customerId=null;
	
	if (commodityCategoryId == null) {
	    logger.info("get commodity category list");
	    List<DcmCommodity> commodityList = commodityRepo.findByStatusStatusId(Constants.STATUS.ACTIVE.getId());
	    
	    DcmCommodity customCommodity =  commodityRepo.getCommodityName("Custom Commodity");	
		if(customCommodity != null){
		    commodityList.remove(customCommodity);
		}
		
	    models = EntityToVOAssembler.convertCommodity(commodityList);
	}

		if (commodityCategoryId != null) {
			logger.info("get commodity detail by category id ");
			System.out.println(" applicationContext.getRequestContext() :  "+applicationContext.getRequestContext()+" applicationContext.getRequestContext().getListRoles() : "+ applicationContext.getRequestContext().getRoles());
			
			if(!applicationContext.getRequestContext().getRoles().contains(Constants.Roles.CUSTOMER_ADMIN)) {
				customerId=applicationContext.getRequestContext().getCustomerId();
			}
			if (applicationContext.getRequestContext().getCustomerType().equals(Constants.CustomerType.SERVICE_PROVIDER)) {
				customerId= null;
			}

			DcmCommodityCategory commodityCategory = validator.commodityCategoryValidate(commodityCategoryId);

			if(applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.STATE_ADMIN)) {
				return getCommodityForStateAdmin(commodityCategoryId, pageNumber, limit);
			}
			
			commodityIds = scanRepo.getAllCommodityIdByCategory(commodityCategory.getId(),
					customerId);
			if (commodityIds != null && commodityIds.size() !=0) {
				commodities = commodityRepo.findByDcmCommodityCategoryIdAndStatusStatusIdAndIdIn(
						commodityCategory.getId(), Constants.STATUS.ACTIVE.getId(), commodityIds, pageable);
			} else {

				commodities = commodityRepo.findByDcmCommodityCategoryIdAndStatusStatusId(commodityCategory.getId(),
						Constants.STATUS.ACTIVE.getId(), pageable);
			}
	DcmCommodity customCommodity =  commodityRepo.getCommodityName("Custom Commodity");	
	if(customCommodity != null){
	    commodities.remove(customCommodity);
	}
	    models = EntityToVOAssembler.convertCommodityByCategoryId(commodities);
	    
	}
	return models;
    }
    
    
    public List<CommodityModel> getCommodityForStateAdmin(Long commodityCategoryId, Integer pageNumber, Integer limit){
	List<DcmCommodity> commodities= null;
	Sort sort = Sort.by(Direction.ASC, "id");
	Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
		Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT_100), sort);
	
	List<Long> commodityIds = scanRepo.getAllCommodityForStateAdmin(commodityCategoryId, applicationContext.getRequestContext().getStateAdmin());
	
	if (commodityIds != null && commodityIds.size() !=0) {
	commodities = commodityRepo.findByDcmCommodityCategoryIdAndStatusStatusIdAndIdIn(
		commodityCategoryId, Constants.STATUS.ACTIVE.getId(), commodityIds, pageable);
	}
	return EntityToVOAssembler.convertCommodityByCategoryId(commodities);    
    }

    public List<CommodityModel> getAllCommodities() {
	List<DcmCommodity> allCommoditiesList = commodityRepo
		.findByStatusStatusIdNotIn(Constants.STATUS.DELETED.getId());

	return EntityToVOAssembler.convertCommodity(allCommoditiesList);
    }

    //	private List<CommodityCategoryModel> commodityCategoryByCustomerId(Long customerId) {
    //		List<CommodityCategoryModel> ccMlist= new ArrayList<CommodityCategoryModel>();
    //		List<Object[]> list= new ArrayList<>();
    //		list=filterRepo.getSubscribedCategory( customerId);
    //
    //		if(list !=null && list.size() != 0) {
    //		for (Object[] dcc : list) {
    //			CommodityCategoryModel ccM= new CommodityCategoryModel();
    //			if(dcc[0] !=null)
    //			ccM.setCommodityCategoryId((Long)dcc[0]);
    //			if(dcc[1] !=null )
    //			ccM.setCommodityCategoryName((String)dcc[1]);
    //			ccMlist.add(ccM);
    //		}
    //		}
    //		return ccMlist;
    //	}
    
	private Long setCustomerId(Long customerId2) {

		logger.info(" Inside setCustomerId method ");
		System.out.println(" Request Context : " + applicationContext.getRequestContext());

		Long customerId = null;
		Set<String> roles = applicationContext.getRequestContext().getRoles();
		System.out.println(" Roles : " + roles);
		if (applicationContext.getRequestContext().getCustomerType()
				.equalsIgnoreCase(Constants.CustomerType.CUSTOMER)) {
			if (roles.iterator().next().equalsIgnoreCase(Constants.Roles.OPERATOR)) {
				return null;
			} else if (roles.iterator().next().equalsIgnoreCase(Constants.Roles.CLIENT)) {
				return customerId = customerId2;
			} else if (roles.iterator().next().equalsIgnoreCase(Constants.Roles.CUSTOMER_ADMIN)) {
				return null;
			}else if(applicationContext.getRequestContext().getRoles().contains("state_admin")) {
				customerId=applicationContext.getRequestContext().getCustomerId();
			}

		}
		return customerId;
	}

	public List<CommodityModel> getCommodityByDevice(Long deviceId, Integer pageNumber, Integer limit) {
		Sort sort = Sort.by(Direction.ASC, "id");
		Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
			Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT_100), sort);

		List<CommodityModel> models = new ArrayList<CommodityModel>();
		List<DcmCommodity> commodities= null;
		List<Long> commodityIds=new ArrayList<Long>();
		List<DeviceCommodityEntity> dce= deviceCommodityRepo.findByDeviceId(deviceId);
		for (DeviceCommodityEntity dc : dce) {
			commodityIds.add(dc.getCommodity().getId());
		}
		commodities=commodityRepo.findByIdIn(commodityIds);
		models = EntityToVOAssembler.convertCommodityByCategoryId(commodities);
		return models;
	}

	public CommodityVarietyModel getCommodityVariety(Integer pageNumber, Integer limit, Long commodityId) {
		CommodityVarietyModel cvModel= new CommodityVarietyModel();
		List<CommodityVarietyEntity> cvList= new ArrayList<CommodityVarietyEntity>();
		if(commodityId !=null) {
		cvList=cVRepo.findByCommodityId(commodityId);
		}else {
			cvList=cVRepo.findAll();
		}
		
		return EntityToVOAssembler.convertVarieties(cvList,cvModel);
	}

	public CategoryCommodityVarietyWrapperModel getCategoryCommodityVarietyByDevice(Long deviceId, Integer pageNumber,
			Integer limit) {
		
		Sort sort = Sort.by(Direction.ASC, "id");
		Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
			Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT_100), sort);

		CategoryCommodityVarietyWrapperModel wrapperModel = new CategoryCommodityVarietyWrapperModel();
		List<DcmCommodity> commodities= null;
		List<Long> commodityIds=new ArrayList<Long>();
		List<DeviceCommodityEntity> dce= deviceCommodityRepo.findByDeviceId(deviceId);
		for (DeviceCommodityEntity dc : dce) {
			commodityIds.add(dc.getCommodity().getId());
		}
		commodities=commodityRepo.findByIdIn(commodityIds);
		
		wrapperModel = convertCategoriesCommoditiesVarietiesByDevice(wrapperModel,commodities);
		return wrapperModel;
	}

	private CategoryCommodityVarietyWrapperModel convertCategoriesCommoditiesVarietiesByDevice(
			CategoryCommodityVarietyWrapperModel wrapperModel, List<DcmCommodity> commodities) {

		List<CategoryCommodityVarietyModel> ccvModelList= new ArrayList<CategoryCommodityVarietyModel>();
		for (DcmCommodity commodity : commodities) {
			
			CategoryCommodityVarietyModel ccvModel = new CategoryCommodityVarietyModel();
			CommoditiesModel commodityModel= new CommoditiesModel();
			
			List<VarietyModel> varietyList= new ArrayList<VarietyModel>();
			List<CommodityVarietyEntity> cvList= new ArrayList<CommodityVarietyEntity>();
			List<CommoditiesModel> commodityModelList= new ArrayList<CommoditiesModel>();
			
			
			
			
			if(commodity !=null && commodity.getId() !=null) {
				cvList=cVRepo.findByCommodityId(commodity.getId());
				}else {
					cvList=cVRepo.findAll();
				}
			for (CommodityVarietyEntity cv : cvList) {
				VarietyModel varietyModel= new VarietyModel();
				varietyModel.setVarietyId(cv.getId());
				varietyModel.setVarietyName(cv.getVarietyName());
				varietyList.add(varietyModel);
				
			}
			commodityModel.setCommodityId(commodity.getId());
			commodityModel.setCommodityName(commodity.getCommodityName());
			commodityModel.setVarieties(varietyList);
			
			commodityModelList.add(commodityModel);
			ccvModel.setCommodities(commodityModelList);
			
			ccvModel.setCommodityCategoryId(commodity.getDcmCommodityCategory().getId());
			ccvModel.setCommodityCategoryName(commodity.getDcmCommodityCategory().getCommodityCategoryName());
			ccvModelList.add(ccvModel);
			
		}
		wrapperModel.setCcvModel(ccvModelList);
		
		return wrapperModel;
	}

	
    public Long setOperatorId() {
	Long operatorId = null;
	logger.info(" Inside setOperatorId method ");
	System.out.println(" Request Context : " + applicationContext.getRequestContext());

	Set<String> roles = applicationContext.getRequestContext().getRoles();
	if (roles.iterator().next().equalsIgnoreCase(Constants.CustomerType.OPERATOR)) {
	    operatorId = applicationContext.getRequestContext().getUserId();
	}
	return operatorId;
    }

    public List<CommodityModel> getCommodityByState() {
	Long stateAdminId = null;
	List<Long> operatorsList = new ArrayList<>();
	
	Set<String> roles = applicationContext.getRequestContext().getRoles();
	if (roles.iterator().next().equalsIgnoreCase(Constants.CustomerType.STATE_ADMIN)) {
	    stateAdminId = applicationContext.getRequestContext().getStateAdmin();
	    
	    if(stateAdminId != null){
		List<StateManagerOperatorsEntity> entities = stateManagerOperatorRepo.findBystateManagerIdAndStatusNotIn(stateAdminId, Constants.STATUS.DELETED.getId().intValue());
		if(!entities.isEmpty()){
		    entities.forEach(a -> operatorsList.add(a.getOperatorId()));
		    
		  if(!operatorsList.isEmpty()){
		    List<DcmDevice> deviceList = deviceRepo.findByUserIdInAndDcmStatusStatusIdNotIn(operatorsList, Constants.STATUS.DELETED.getId());
		    if(!deviceList.isEmpty()){
			List<Long> deviceIdList = new ArrayList<>();
			deviceList.forEach(a -> deviceIdList.add(a.getId()));
			
			LinkedHashSet<DcmCommodity> commoditySet=new LinkedHashSet<DcmCommodity>();  
			List<DcmCommodity> commodityList = new ArrayList<>();
				
			List<DeviceCommodityEntity> deviceCommodityEntity= deviceCommodityRepo.findByDeviceIdInAndStatusIdNotIn(deviceIdList, Constants.STATUS.DELETED.getId().intValue());
			if(!deviceCommodityEntity.isEmpty()){
			deviceCommodityEntity.forEach(a -> commoditySet.add(a.getCommodity()));
			commodityList.addAll(commoditySet);
			
			return EntityToVOAssembler.convertCommodityByCategoryId(commodityList);
			}
		     }    
		  }
		}
	    }    
	}	
	return null;	
    }
    
    public List<CommodityModel> getCofcoCommoditied() {
	List<CommodityModel> response = new ArrayList<>();
   	
   	List<CofcoCommodityEntity> comm = cofcoCommRepo.findAll();
   	comm.forEach(e ->
   	{
   	    CommodityModel m = new CommodityModel();
   	    m.setCommodityId(e.getId());
   	    m.setCommodityName(e.getCommodityName());
   	    response.add(m);
   	}	
   	);
   	
   	return response;
    }
    
    public List<CommodityModel> getNafedCommoditied() {
   	List<CommodityModel> response = new ArrayList<>();
   	
   	List<DcmCommodity> comm = commodityRepo.findAll();
   	comm.forEach(e ->
   	{
   	    CommodityModel m = new CommodityModel();
   	    m.setCommodityId(e.getId());
   	    m.setCommodityName(e.getCommodityName());
   	    response.add(m);
   	}	
   	);
   	
   	return response;
       }
	
}

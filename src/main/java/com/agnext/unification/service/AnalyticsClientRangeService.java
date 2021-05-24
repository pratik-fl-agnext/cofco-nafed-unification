package com.agnext.unification.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.Analytics;
import com.agnext.unification.entity.nafed.ClientsAnalyticsRange;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.StatusEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.AnalyticsRangeModel;
import com.agnext.unification.model.ClientModel;
import com.agnext.unification.model.RangeDetailsModel;
import com.agnext.unification.model.RangeModel;
import com.agnext.unification.model.ScanLocationModel;
import com.agnext.unification.repository.nafed.AnalyticsRepository;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.repository.nafed.FilterRepository;
import com.agnext.unification.repository.nafed.RangeRepository;
import com.agnext.unification.repository.nafed.ScanLocationRepository;
import com.agnext.unification.repository.nafed.StatusRepository;

@Service
public class AnalyticsClientRangeService extends GenericService {

	@Autowired
	RangeRepository rangeRepo;

	@Autowired
	AnalyticsRepository anaRepo;

	@Autowired
	DcmCommodityRepository commodityRepo;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	ScanLocationRepository locationRepo;

	@Autowired
	StatusRepository statusRepo;

	@Autowired
	FilterRepository filterRepo;

	public void saveRange(RangeModel rangeModel) throws IMException {
		Analytics ana = new Analytics();
		ClientsAnalyticsRange range = new ClientsAnalyticsRange();
		DcmCommodity commodity = commodityRepo.getOne(rangeModel.getCommodityId());
		CustomerEntity client = customerRepo.getOne(rangeModel.getClientId());
		ScanLocation location = locationRepo.getOne(rangeModel.getLocationId());
		StatusEntity status = statusRepo.getOne(Constants.STATUS.ACTIVE.getId());

		if (rangeModel.getAnalyticName() != null) {
			ana = anaRepo.findByAnalyticName(rangeModel.getAnalyticName());
			if (ana == null || ana.getId() == null) {
				throw new IMException(" NoRecordFound !! ", " No data has been found for this Analytics ");
			}
		}
		range.setCommodityId(commodity);
		range.setAnalytics(ana);
		range.setClient(client);
		range.setLocation(location);
		if (rangeModel.getWarehouseName() != null && !rangeModel.getWarehouseName().isEmpty()) {
			range.setWarehouseName(rangeModel.getWarehouseName().trim());
		} else {
			range.setWarehouseName(location.getWarehouseName());
		}
		range.setMinRange("1");
		range.setMaxRange(rangeModel.getMaxRange());
		range.setStatus(status);
		range.setCreatedOn(Instant.now().getEpochSecond());
		System.out.println(" Range :  "+range);
		rangeRepo.save(range);

	}

	public AnalyticsRangeModel getAllRange(Long clientId, String warehosueName, Long commodityId) {
		String wHouse=null;
		if(warehosueName !=null && !warehosueName.isEmpty()) {
			wHouse=warehosueName.trim();
		}
		List<ClientsAnalyticsRange> range = filterRepo.getFilteredRange(clientId, wHouse, commodityId);
		Long count = filterRepo.getFilteredRangeCount(clientId, warehosueName, commodityId);
		AnalyticsRangeModel responseRange = new AnalyticsRangeModel();
		responseRange.setCount(count);
		return EntityToVOAssembler.convertRange(responseRange, range);
	}

	public RangeDetailsModel getFilteredDataByCommodity(Long commodityId) {
		
		List<Object[]> objArray= new ArrayList<Object[]>();
		RangeDetailsModel rangeDetails= new RangeDetailsModel();
		List<ClientModel> clients= new ArrayList<>();
		
		
		
		objArray= filterRepo.getClientsByCommodity(commodityId);
//		Long count = filterRepo.getFilteredDataByCommodityCount(commodityId);
		
		for (Object[] obj : objArray) {
			
			List<Object[]> locationsObj= new ArrayList<Object[]>();
			ClientModel client= new ClientModel();
			List<ScanLocationModel> locations=new ArrayList<ScanLocationModel>();
			
			client.setClientId((Long) obj[0]);
			client.setClientName((String)obj[1]);
			locationsObj=filterRepo.getLocations((Long)obj[0]);
			for (Object[] lObj : locationsObj) {
				ScanLocationModel location = new ScanLocationModel();
				
				location.setLocationId((Long) lObj[0]);
				location.setLocationName((String) lObj[1]);
				location.setWarehouseName((String) lObj[2]);
				
				locations.add(location);
			}
			client.setLocations(locations);
			clients.add(client);
	
			
		}
		rangeDetails.setClient(clients);
		
		return rangeDetails;
	}

}

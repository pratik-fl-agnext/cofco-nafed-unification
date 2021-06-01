package com.agnext.unification.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.unification.entity.CommodityBaseEntity;
import com.agnext.unification.entity.cofco.CofcoCommodityEntity;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.repository.CommodityBaseRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;

@Component("NAFED")
public class NafedServiceImpl implements ICommodityService {

	private static Logger logger = LoggerFactory.getLogger(NafedServiceImpl.class);
	
	@Autowired
	DcmCommodityRepository commodityRepo;
		
	@Override
	public List<CommodityModel> getAllCommodityList(String urlId,CommodityBaseRepository<? extends CommodityBaseEntity> commBaseRepo) {
	    List<CommodityModel> response = new ArrayList<>();
	    
	    List<? extends CommodityBaseEntity> comm = commBaseRepo.findAll();
	    
	    List<CofcoCommodityEntity> cofcoCommodity = new ArrayList<>();
	   	comm.forEach(e ->
	   	{
	   	    
	   	    cofcoCommodity.add((CofcoCommodityEntity)e);
	   	    /*CommodityModel m = new CommodityModel();
	   	    m.setCommodityId(e.getId());
	   	    m.setCommodityName(e.getCommodityName());
	   	    response.add(m);*/
	   	}	
	   	);
	   	
	   	cofcoCommodity.stream().forEach(commcofco -> {
	   	    System.out.println(commcofco.getCommodityName());
	   	    
	   	    
	   	});
	   		   	
	   	return response;
	}
				
}
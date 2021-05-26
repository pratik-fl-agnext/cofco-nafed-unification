package com.agnext.unification.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.entity.cofco.CofcoCommodityEntity;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.repository.cofco.CofcoCommodityRepository;

@Service
public class CofcoServiceImpl {
    
    private static Logger logger = LoggerFactory.getLogger(CofcoServiceImpl.class);
    
    @Autowired
    CofcoCommodityRepository cofcoCommRepo;

	
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

}
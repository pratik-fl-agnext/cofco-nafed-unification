package com.agnext.unification.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;

@Service
public class NafedServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(NafedServiceImpl.class);
	
	@Autowired
	DcmCommodityRepository commodityRepo;
	
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
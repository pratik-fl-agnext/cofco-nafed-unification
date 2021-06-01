package com.agnext.unification.service;

import java.util.List;

import com.agnext.unification.entity.CommodityBaseEntity;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.repository.CommodityBaseRepository;

public interface ICommodityService {
  

    List<CommodityModel> getAllCommodityList(String urlId,
	    CommodityBaseRepository<? extends CommodityBaseEntity> commBaseRepo);


}

package com.agnext.unification.repository.cofco;

import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.entity.cofco.CofcoCommodityEntity;
import com.agnext.unification.repository.CommodityBaseRepository;

@Transactional
public interface CofcoCommodityRepository extends CommodityBaseRepository<CofcoCommodityEntity>{

   
}

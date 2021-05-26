package com.agnext.unification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.common.Constants;
import com.agnext.unification.model.CommodityModel;

@Service
public class ICommodityService {
    
    @Autowired
    CofcoServiceImpl cofcoImpl;
    
    @Autowired
    NafedServiceImpl nafedImpl;
       
    public List<CommodityModel> getCommodityList(String urlId){
	
	    switch(urlId){	    
	    case Constants.COFCO: 
		return cofcoImpl.getCofcoCommoditied();  
	    
	    case Constants.NAFED: 
		return nafedImpl.getNafedCommoditied();	
	    }
	    return null;    
    }
}

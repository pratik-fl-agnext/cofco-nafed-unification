package com.agnext.unification.utility;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.agnext.unification.repository.cofco.CofcoCommodityRepository;



public class FactoryUtility {
    
    @Autowired
    BeanFactory bf;
    
    @Autowired
    CofcoCommodityRepository cr;
    
    public <T> T getRepo(String s){
	
	 return (T) cr;

	  /*if(s.equals("NAFED")){// give your impl here
	     return new CommodityBaseRepository(); // your bean here
	   }else{
	      return new CofcoCommodityRepository (); // your bean here
	   }*/
	 }

}

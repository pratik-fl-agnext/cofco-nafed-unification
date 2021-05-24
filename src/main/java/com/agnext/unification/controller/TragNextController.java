package com.agnext.unification.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.notification.lib.service.EmailService;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.entity.nafed.Tragnext;
import com.agnext.unification.model.TragnextModel;
import com.agnext.unification.repository.nafed.TragnextRepository;
import com.agnext.unification.utility.EmailUtil;
import com.agnext.unification.utility.NotificationsProperties;

@RestController
@RequestMapping(value = "tragnext/submit", produces = "application/json")
public class TragNextController {

    private static Logger logger = LoggerFactory.getLogger(TragNextController.class);
    
    @Autowired
    EmailUtil emailUtilService;
    
    @Autowired
    NotificationsProperties nProperties;
    
    public final TragnextRepository tragnextRepository;

    public TragNextController(TragnextRepository tragnextRepository) {
        this.tragnextRepository = tragnextRepository;
    }

    @PostMapping()
    public String submit(@RequestBody TragnextModel tragnextModel) {
        logger.info("Tragnext Rquest Received : {}", tragnextModel);
        String response = "Exception saving data, please contact admin at : support@agnext.in";
        if(isNotNull(tragnextModel)){
            Tragnext save = tragnextRepository.save(VOToEntityAssembler.convertTragnextModelToEntity(tragnextModel));
            if(save!=null){
        	sendEmail(tragnextModel);
                response = "Thanks for your query, we will contact you soon.";
            }
        }
        return response;
    }

    /**
     * Verfiy whether we have name, email or Phone number
     *
     * @param tragnextModel
     * @return
     */
    private boolean isNotNull(TragnextModel tragnextModel) {
        Boolean returnFlag = true;
        if(tragnextModel != null){
            if(StringUtils.isNotBlank(tragnextModel.getName()) ||
                    (StringUtils.isNotBlank(tragnextModel.getEmail()))||
                    (StringUtils.isNotBlank(tragnextModel.getPhoneNumber()))){
                returnFlag = true;
            }
        }
        return returnFlag;
    }
    
    public void sendEmail(TragnextModel tragnextModel){
	 final String SUBJECT_TRAGNEXT="Tragnext Calculator";
	 final String EMAIL_TYPE_HTML="text/html";
	 
	 final String[] to = {"contact@agnext.com", "gurpreet@agnext.com"};
	 final String[] cc = {"mitesh@agnext.in"};
	 
	 final String TRAGNEXT_EMAIL_CONTENT = "<p>Name: {name}</p>"
		 +"<p>Email: {email}</p>"
		 +"<p>Phone Number: {phnumber}</p>"
		 +"<p>Avg weight of truck (kg): {avgweight}</p>"
		 +"<p>Number of trucks per day: {nooftrucks}</p>"
		 +"<p>Percentage of bought leaves (%): {leaves}</p>"
		 +"<p>Delta between FLC slabs (Rs/kg): {delta}</p>"
		 +"<p>Average price of FLC (Rs/kg): {avgprice}</p>"
		 +"<p>Avg percentage of water on tea leaves(%): {avgpercentage}</p>";
	
	String content = TRAGNEXT_EMAIL_CONTENT.replace("{name}", tragnextModel.getName())
		.replace("{email}", tragnextModel.getEmail())
		.replace("{phnumber}", tragnextModel.getPhoneNumber())
		.replace("{avgweight}", tragnextModel.getParameters().getAvgWeightOfTruck())
		.replace("{nooftrucks}", tragnextModel.getParameters().getNoOfTrucksPerDay())
		.replace("{leaves}", tragnextModel.getParameters().getPercentageOfBoughtLeaves())
		.replace("{delta}", tragnextModel.getParameters().getDeltaBetweenFLCSlab())
		.replace("{avgprice}", tragnextModel.getParameters().getAvgPriceOfFlc())
		.replace("{avgpercentage}", tragnextModel.getParameters().getAvgPercentageOfWater());
	
	EmailService.sendMail(nProperties.getEmailFrom(), to, cc, nProperties.getEmailAdmin(), 
		SUBJECT_TRAGNEXT, content, EMAIL_TYPE_HTML, nProperties.getServerUrl());
    }

}

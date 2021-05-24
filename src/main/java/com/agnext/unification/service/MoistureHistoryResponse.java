package com.agnext.unification.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.unification.common.CommodityAnalyticsMap;
import com.agnext.unification.common.Constants;
import com.agnext.unification.config.AnalyticsVariations;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.MoistureMeterResult;
import com.agnext.unification.entity.nafed.ScanEntity;
import com.agnext.unification.entity.nafed.ScanResultEntity;
import com.agnext.unification.model.AnalysisResultVO;
import com.agnext.unification.model.HistoryModel;
import com.agnext.unification.model.HistoryMoistureWrapperModel;
import com.agnext.unification.model.MoistureHistoryModel;
import com.agnext.unification.model.ScanHistoryModel;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.MoistureMeterResultRepository;
import com.agnext.unification.utility.Utility;

@Component
public class MoistureHistoryResponse {

    @Autowired
    CustomerRepository custRepo;

    @Autowired
    MoistureMeterResultRepository moistureMeterRepo;
    
    @Autowired
    ScanService scanService;
    
    @Autowired
    CommodityAnalyticsMap commodityAnalyticsMap;

    @Autowired
	AnalyticsVariations analyticsVariations;

    public HistoryMoistureWrapperModel convertHistoryForMoistureData(
	    ArrayList<LinkedHashMap<ScanEntity, MoistureMeterResult>> listOfMaps, Map<String, Long> deviceMap,Long totalCount, Integer limit) {
 

	HistoryMoistureWrapperModel model = new HistoryMoistureWrapperModel();
	List<HistoryModel> historyModelList = new ArrayList<>();

	if (!listOfMaps.isEmpty()) {
	    for (LinkedHashMap<ScanEntity, MoistureMeterResult> map : listOfMaps) {
	       	Boolean isImageExist=false;
		HistoryModel historyModel = new HistoryModel();
		ScanHistoryModel scanHistoryModel = new ScanHistoryModel();
		MoistureHistoryModel moistureHistoryModel = new MoistureHistoryModel();

		historyModel.setSampleId(map.keySet().iterator().next().getSampleId());

		if (map.keySet().iterator().next().getIsValid() == Boolean.TRUE) {
		    scanHistoryModel.setBatchId(map.keySet().iterator().next().getBatchId());
		    scanHistoryModel.setCommodityId(map.keySet().iterator().next().getCommodityId());
		    scanHistoryModel.setDate_done(map.keySet().iterator().next().getCreatedOnDate().toString());
		    scanHistoryModel.setDeviceType(map.keySet().iterator().next().getDeviceType());
		    scanHistoryModel.setDeviceSerialNo(map.keySet().iterator().next().getDeviceSerialNo());
		    scanHistoryModel.setScanId(map.keySet().iterator().next().getId());
		    scanHistoryModel
			    .setDeviceId(deviceMap.get(map.keySet().iterator().next().getDeviceSerialNo()).toString());
//		    scanHistoryModel.setIsImageExist(scanService.isImageExist(map.keySet().iterator().next().getImageUniqueId()));
		    scanHistoryModel.setDeviceTypeId(map.keySet().iterator().next().getDeviceTypeId());
		    scanHistoryModel.setCommodityName(map.keySet().iterator().next().getCommodityName());
		    scanHistoryModel.setQualityScore(map.keySet().iterator().next().getQualityScore());
		    scanHistoryModel.setTotalCount(null);
		    scanHistoryModel.setWeight(map.keySet().iterator().next().getWeight());
		    scanHistoryModel.setApproval(map.keySet().iterator().next().getApproval());
		    if (map.keySet().iterator().next().getApproval() == 0) {
			scanHistoryModel.setApprovalDesc("Pending");
		    } else if (map.keySet().iterator().next().getApproval() == 1) {
			scanHistoryModel.setApprovalDesc("Approved");
		    } else if (map.keySet().iterator().next().getApproval() == 2) {
			scanHistoryModel.setApprovalDesc("Rejected");
		    } else {
			scanHistoryModel.setApprovalDesc("Undefined");
		    }

		    scanHistoryModel.setUnit(map.keySet().iterator().next().getQuantityUnit());
		    scanHistoryModel.setCustomerId(map.keySet().iterator().next().getCustomerId());
		    if (map.keySet().iterator().next().getCustomerId() != null) {
			CustomerEntity customerEntity = custRepo.getOne(map.keySet().iterator().next().getCustomerId());
			scanHistoryModel.setCustomerName(customerEntity.getCustomerName());
		    }

		    MoistureMeterResult moistureEntity =null;
//		    Code below is to handle multiple duplicates in moisture meter table
		    
		    List<MoistureMeterResult> moistureEntityList= new ArrayList<MoistureMeterResult>();
		    
		    if(map.keySet().iterator().next().getSampleId() !=null && map.keySet().iterator().next().getCommodityName() !=null && !map.keySet().iterator().next().getSampleId().trim().equalsIgnoreCase("") && !map.keySet().iterator().next().getCommodityName().trim().equalsIgnoreCase("") )
		    	moistureEntityList =moistureMeterRepo
			    .findBySampleIdAndCommodityName(map.keySet().iterator().next().getSampleId(),map.keySet().iterator().next().getCommodityName());
		   if(moistureEntityList !=null && !moistureEntityList.isEmpty() && moistureEntityList.size()>0) {
			  
			   Boolean escapeCheck=Boolean.TRUE;
				for(MoistureMeterResult moisture:moistureEntityList) {
					if(moisture !=null && escapeCheck && moisture.getMoisture() !=null) {
						moistureEntity=moisture;
						escapeCheck=Boolean.FALSE;
					}
					
				}
		   }
		    
		    if (moistureEntity != null) {
			scanHistoryModel.setMoisture(moistureEntity.getMoisture());
			scanHistoryModel.setTemperature(moistureEntity.getTemperature());
		    }

		    scanHistoryModel.setSampleId(map.keySet().iterator().next().getSampleId());
		    scanHistoryModel.setVarietyId(map.keySet().iterator().next().getVarietyId());
		    scanHistoryModel.setVarietyName(map.keySet().iterator().next().getVarietyName());
		    scanHistoryModel.setTruckNumber(map.keySet().iterator().next().getTruckNumber());
		    scanHistoryModel.setSocietyName(map.keySet().iterator().next().getSocietyName());
		    scanHistoryModel.setNumberOfBags(map.keySet().iterator().next().getBag());
		    scanHistoryModel.setAcceptedBags(map.keySet().iterator().next().getAcceptedBags());
		    scanHistoryModel.setRejectedBags(map.keySet().iterator().next().getRejectedBags());
		    scanHistoryModel.setQuantity(map.keySet().iterator().next().getQuantity());
		    Map<Long, List<String>> commoditiesAnalyticsMap= new HashMap<>();
			commoditiesAnalyticsMap=commodityAnalyticsMap.getMap();
			List<String> standardList = commoditiesAnalyticsMap.get(scanHistoryModel.getCommodityId());
		    scanHistoryModel
			    .setAnalysisResultList(convertAnalysisData(map.keySet().iterator().next().getResults(),standardList));
		    
		    //Image urls added
		    scanHistoryModel.setImageModels(scanService.downloadImagesForHistoryAPI(map.keySet().iterator().next().getId()));
		    if( scanHistoryModel.getImageModels() !=null &&  scanHistoryModel.getImageModels().size() >0 && !scanHistoryModel.getImageModels().isEmpty()) {
		    	 isImageExist=true;
		    }
		    scanHistoryModel.setIsImageExist(isImageExist);
		    
		}

		if (map.get(map.entrySet().iterator().next().getKey()) != null) {
		    moistureHistoryModel.setId(map.values().iterator().next().getId());
		    moistureHistoryModel.setSampleId(map.values().iterator().next().getSampleId());
		    moistureHistoryModel.setClientId(map.values().iterator().next().getClientId());
		    moistureHistoryModel.setCommodityName(map.values().iterator().next().getCommodityName());
		    moistureHistoryModel.setMoisture(map.values().iterator().next().getMoisture());
		    moistureHistoryModel.setTemperature(map.values().iterator().next().getTemperature());
		    moistureHistoryModel.setTruckNumber(map.values().iterator().next().getTruckNumber());
		    moistureHistoryModel.setToken(map.values().iterator().next().getToken());
		    moistureHistoryModel.setWeight(map.values().iterator().next().getWeight());
		    moistureHistoryModel.setVarietyId(map.values().iterator().next().getVarietyId());
		    moistureHistoryModel.setCreatedOn(map.values().iterator().next().getVarietyName());
		}

		if (map.keySet().iterator().next().getIsValid() == Boolean.TRUE) {
		    historyModel.setScanHistoryModel(scanHistoryModel);
		} else {
		    historyModel.setScanHistoryModel(null);
		}

		if (map.get(map.entrySet().iterator().next().getKey()) != null) {
		    historyModel.setMoistureHistory(moistureHistoryModel);
		} else {
		    historyModel.setMoistureHistory(null);
		}

		historyModelList.add(historyModel);
	    }
	    List<HistoryModel> filteredHistoryModelList = historyModelList.stream()
		    .filter(a -> a.getScanHistoryModel() != null || a.getMoistureHistory() != null)
		    .collect(Collectors.toList());
	    if(!filteredHistoryModelList.isEmpty()){
		historyModelList.clear();
		historyModelList.addAll(filteredHistoryModelList);
	    }
	    
	    model.setTotalCount(totalCount);
	    model.setHistory(limit >= historyModelList.size() ? historyModelList.subList(0, historyModelList.size()) : historyModelList.subList(0, limit));
	    return model;
	}
	return null;
    }

    private List<AnalysisResultVO> convertAnalysisData(List<ScanResultEntity> results, List<String> standardList) {
	List<AnalysisResultVO> response = new ArrayList<AnalysisResultVO>();
	
	Boolean moisturePresent = false;
	BigDecimal moisture = null;
	
	
	for (ScanResultEntity result : results) {
		
		/* Standardization of Analytics Parameters */
	
			if (result.getAnalysisName() != null) {
				 AnalysisResultVO analyticsNew= new  AnalysisResultVO();
				
				if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.ADMIXTURE.getAnalytics()).contains(result.getAnalysisName())) {
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.ADMIXTURE.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.DAMAGED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}

				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.FOREIGNMATTER.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.FOREIGNMATTER.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} 
//				else if (result.getAnalysisName().trim().equalsIgnoreCase("immature")) {
//					
//					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
//					analyticsNew.setAnalysisUnit("%");
//					analyticsNew.setAnalysisType(result.getAnalysisName());
//					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
////					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
////					analyticsNew.setTotalAmount(
////						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
////
////					if (map != null && map.containsKey(result.getAnalysisName())) {
////						analyticsNew.setByDensityResult(Utility
////						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
////					}
//
//				} 
				else if (!moisturePresent && 
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.MOISTURECONTENT.getAnalytics()).contains(result.getAnalysisName())) {
					
					 moisture = result.getResult();
					    moisturePresent = true;
					
						if (moisture != null) {
							analyticsNew.setAnalysisUnit("");
							analyticsNew.setAnalysisType(Constants.ANALYTICS.MOISTURECONTENT.getAbbr());
							analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(moisture)).toString());
//							analyticsNew.setTotalAmount(String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(moisture))));
						}
					    
//					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
//					analyticsNew.setAmountUnit("%");
//					analyticsNew.setAnalysisName(customAnalyticsModel.getMoisturecontent());
//					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));


				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.PODSOFOTHERVAR.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.PODSOFOTHERVAR.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHELLING.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.SHELLING.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHRIVELLEDANDIMMATURE.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.SHRIVELLEDANDIMMATURE.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SLIGHTLYDAMAGED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.SLIGHTLYDAMAGED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.WEEVILLED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.WEEVILLED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGEDANDWEEVILLED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.DAMAGEDANDWEEVILLED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.IMMATURE.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.IMMATURE.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.OTHERFOODGRAINS.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.OTHERFOODGRAINS.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
				
				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SMALLATROPHIEDSEEDS.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.SMALLATROPHIEDSEEDS.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SPLITCRACKED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAnalysisUnit("%");
					analyticsNew.setAnalysisType(Constants.ANALYTICS.SPLITCRACKED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())).toString());
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
				
				if(analyticsNew !=null && analyticsNew.getAnalysisType() !=null && !analyticsNew.getAnalysisType().equalsIgnoreCase(""))
               response.add(analyticsNew);
			}
	
	
//	for (ScanResultEntity result : results) {
//	    AnalysisResultVO aResult = new AnalysisResultVO();
//	    if(result.getResult() !=null && !result.getResult().equals("")) {
//	    aResult.setResult(String.format("%.2f", result.getResult()) + "");
//	    }
//	    aResult.setAnalysisType(result.getAnalysisName());
//	    response.add(aResult);
//		}

    }
	return response;
    }
}

package com.agnext.unification.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.config.AnalyticsVariations;
import com.agnext.unification.model.CSVModel;
import com.agnext.unification.model.ImageModel;
import com.agnext.unification.model.StateAnalysisDataModel;
import com.agnext.unification.utility.Utility;

@Service
public class AnalysisDataService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(AnalysisDataService.class);
    private static Integer PARTITION_SIZE = 200;

    @Autowired
    NafedDataService nafedDataService;

    @Autowired
    AnalyticsVariations analyticsVariations;

    @Autowired
    ExcelService excelService;


    /**
     * @param commodityId
     * @param stateId
     * @return
     * @throws SQLException
     */

    public ByteArrayInputStream generateExcelFromNafedData(Long commodityId, Long stateId) throws SQLException, ExecutionException, InterruptedException {
        return sendAnalysisData(commodityId, stateId, Boolean.TRUE);
    }

    public ByteArrayInputStream sendAnalysisData(Long commodityId, Long stateId, boolean isDateCriteria) throws SQLException, ExecutionException, InterruptedException {
        Connection con = nafedDataService.getConnection();
        if (con != null) {
            try {
                //Retrieve all the records from the DB
                Map<Long, CSVModel> csvModelMap = nafedDataService.generateSQLQuery(commodityId, stateId);
                Long daysDifference = getDaysDifference();
                logger.debug("Days Difference value :{}", daysDifference);
                //If Date Criteria need to be applied, this code will give the result matching Date criterion
                if (isDateCriteria) {
                    Map<Long, CSVModel> relevantRecords = csvModelMap.entrySet().stream().filter(entry -> {
                        if (entry.getValue() != null) {
                            return isDateCriteriaMet(entry.getValue().getDateOfScan(), daysDifference);
                        } else {
                            return false;
                        }
                    }).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
                    //Repopulation of Relevant Records into the CSV0 Model Objects
                    csvModelMap = relevantRecords;
                }

                //Verify if we have records, there might be case where this list is empty.
                if (!csvModelMap.isEmpty()) {
                    //Get State List.
                    Map<Long, String> stateMap = nafedDataService.getStateIds(con);
                    //Get Analysis Parame ters for the records we have shortlisted.
                    Map<Long, StateAnalysisDataModel> stateDataMap = new HashMap<>();
                    ArrayList<StateAnalysisDataModel> stateList = stateMap.entrySet().stream().map(entry -> {
                        StateAnalysisDataModel state = new StateAnalysisDataModel();
                        state.setStateId(entry.getKey());
                        state.setState(entry.getValue());
                        stateDataMap.put(entry.getKey(), state);
                        return state;
                    }).collect(ArrayList<StateAnalysisDataModel>::new, List<StateAnalysisDataModel>::add, List<StateAnalysisDataModel>::addAll);
                /*    Set<String> analysisParams = nafedDataService.retrieveUniqueAnalysisValues();
                    if (analysisParams != null) {
                        analysisParams.forEach(param -> logger.debug("Unique Param Names :{}", param));
                    }*/
                    List<CSVModel> finalList = getCsvModels(csvModelMap);
                    //logger.debug("Unique Analysis Params: {}", nafedDataService.retrieveUniqueAnalysisValues());
                    populateAndProcessStateData(csvModelMap, stateDataMap);
                    return excelService.createExcel(stateDataMap, finalList);
                } else {
                    logger.debug("No Relevant Record Found for date Criteria : {}", getDaysDifference());
                }
                return null;
            } finally {
                if (null != con) {
                    logger.info("connection closed");
                }
            }
        }
        return null;
    }

    /**
     * Get Days Difference from 1st march 2021
     * @return
     */
    private Long getDaysDifference() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = LocalDate.of(2021,03,01);
        return ChronoUnit.DAYS.between(startDate, currentDate);
    }


    /**
     * Processing Data w.r.t states.
     *
     * @param csvModelMap
     * @param stateDataMap
     */
    private void populateAndProcessStateData(Map<Long, CSVModel> csvModelMap, Map<Long, StateAnalysisDataModel> stateDataMap) {
        List<CSVModel> allRecords = csvModelMap.values().stream().collect(Collectors.toList());
        //Associate Relevant Records with there respective States
        for (CSVModel record : allRecords) {
            if (record.getStateId() != null) {
                StateAnalysisDataModel stateData = stateDataMap.get(record.getStateId());
                if (stateData != null) {
                    populateStateData(record, stateData);
                } else {
                    logger.debug("StateData is null for sample ID :{}", record.getSampleId());
                }
            } else {
                logger.debug("StateId is null for sample ID :{}", record.getSampleId());
            }
        }
        //Process All the State Data
        processStateDataMap(stateDataMap);
    }


    /**
     * Partion the whole data in equal size chunks execute all the chunks parallely and then joins them and provide result.
     *
     * @param csvModelMap
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private List<CSVModel> getCsvModels(Map<Long, CSVModel> csvModelMap) throws InterruptedException, ExecutionException {
        List<CSVModel> finalList = new ArrayList<>();
        if (!csvModelMap.isEmpty()) {
            List<CSVModel> originalList = csvModelMap.values().stream().collect(Collectors.toList());
            //Integer[] partionedSize = factorSize(originalList.size(), PARTITION_SIZE);

            if (originalList.size() > PARTITION_SIZE) {
                /*List<List<CSVModel>> partitions = new LinkedList<List<CSVModel>>();
                int startIndex = 0;
                for (int i = 0; i < partionedSize.length; i++) {
                    partitions.add(originalList.subList(startIndex, startIndex + partionedSize[i]));
                    startIndex = startIndex + partionedSize[i];
                }*/

                int partitionSize = PARTITION_SIZE;
                List<List<CSVModel>> listPartitions = new LinkedList<List<CSVModel>>();
                for (int i = 0; i < originalList.size(); i += partitionSize) {
                    listPartitions.add(originalList.subList(i,
                            Math.min(i + partitionSize, originalList.size())));
                }

                final List<CompletableFuture<List<CSVModel>>> completableFutures = listPartitions.stream()
                        .map(list -> nafedDataService.processResults(list))
                        .collect(Collectors.toList());

                CompletableFuture<Void> allFutures = CompletableFuture
                        .allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

                CompletableFuture<List<List<CSVModel>>> listCompletableFuture = allFutures.thenApply(future -> {
                    return completableFutures.stream()
                            .map(completableFuture -> completableFuture.join())
                            .collect(Collectors.toList());
                });

                CompletableFuture<List<CSVModel>> completableFutureList = listCompletableFuture.thenApply(lists -> {
                    return lists.stream().collect(ArrayList<CSVModel>::new, List<CSVModel>::addAll, List::addAll);
                });

                return completableFutureList.get();

            } else {
                finalList = nafedDataService.getCsvModels(csvModelMap);
                return finalList;
            }

        }
        return finalList;
    }

    private Integer[] factorSize(int totalSize, int partition) {
        Integer[] factored = new Integer[partition];
        if (totalSize % partition == 0) {
            for (int i = 0; i < partition; i++) {
                factored[i] = totalSize / partition;
            }
        } else {
            int nearPoint = partition - (totalSize % partition);
            int partionPoint = totalSize / partition;
            for (int i = 0; i < partition; i++) {
                if (i >= nearPoint) {
                    factored[i] = partionPoint + 1;
                } else {
                    factored[i] = partionPoint;
                }
            }
        }
        return factored;
    }

    /**
     * Is Date Criteria Within Range
     *
     * @param date
     * @param daysCriterion
     * @return
     */
    private Boolean isDateCriteriaMet(String date, Long daysCriterion) {
        Boolean returnVal = Boolean.FALSE;
        if (StringUtils.isNotBlank(date)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime tempDateTime = LocalDateTime.from(localDateTime);
            Long days = tempDateTime.until(currentTime, ChronoUnit.DAYS);
            if(daysCriterion >= days){
                returnVal = true;
            }
            /*if (days != null && daysCriterion != null) {
                returnVal = Objects.equals(daysCriterion, days);
            }*/
        }
        return returnVal;
    }


    /**
     * Iterate Over state Map , and Build the relevant logic to Print/Obtain the data w.r.t to States.
     *
     * @param stateDataMap
     */
    private void processStateDataMap(Map<Long, StateAnalysisDataModel> stateDataMap) {
        stateDataMap.forEach((state, data) -> {
            logger.debug("###########################################" + data.getState() + "###########################################");
            logger.debug("Name of the State : {} ", data.getState());
            String totalSum = getTotalSum(data.getTotalWeightList());
            if (StringUtils.isNotBlank(totalSum)) {
                logger.debug("Total Weight Procurred : {}", totalSum);
            }
            String acceptedWeight = getTotalSum(data.getAcceptedWeightList());
            if (StringUtils.isNotBlank(acceptedWeight)) {
                logger.debug("Total Accepted Weight Procurred : {}", acceptedWeight);
            }
            String rejectedWeight = getTotalSum(data.getRejectedWeightList());
            if (StringUtils.isNotBlank(rejectedWeight)) {
                logger.debug("Total Rejected Weight Procurred : {}", rejectedWeight);
            }
            String totalTruckCount = getCountFromMap(data.getTruckCount());
            if (StringUtils.isNotBlank(totalTruckCount)) {
                logger.debug("Total Truck Count : {}", totalTruckCount);
            }
            String acceptedTruckCount = getCountFromMap(data.getAcceptedTruckCount());
            if (StringUtils.isNotBlank(acceptedTruckCount)) {
                logger.debug("Total Accepted Truck COunt : {}", acceptedTruckCount);
            }
            String rejectedTruckCount = getCountFromMap(data.getRejectedTruckCount());
            if (StringUtils.isNotBlank(rejectedTruckCount)) {
                logger.debug("Total Rejected Truck Count : {}", rejectedTruckCount);
            }
            String moistureAverage = getAverage(data.getMoistureList());
            if (StringUtils.isNotBlank(moistureAverage)) {
                logger.debug("Average Moisture : {}", moistureAverage);
            }

            String foreignMatter = getAverage(data.getFmList());
            if (StringUtils.isNotBlank(foreignMatter)) {
                logger.debug("Average foreignMatter : {}", foreignMatter);
            }

            String admixture = getAverage(data.getAdMixtureList());
            if (StringUtils.isNotBlank(admixture)) {
                logger.debug("Average admixture : {}", admixture);
            }

            String damaged = getAverage(data.getDamagedList());
            if (StringUtils.isNotBlank(damaged)) {
                logger.debug("Average damaged : {}", damaged);
            }

            String weevilled = getAverage(data.getWevilledList());
            if (StringUtils.isNotBlank(weevilled)) {
                logger.debug("Average weevilled : {}", weevilled);
            }

            String shrivelledandimmature = getAverage(data.getShrivelledList());
            if (StringUtils.isNotBlank(shrivelledandimmature)) {
                logger.debug("Average shrivelledandimmature : {}", shrivelledandimmature);
            }

            String slightlydamaged = getAverage(data.getSlightlyDamagedList());
            if (StringUtils.isNotBlank(slightlydamaged)) {
                logger.debug("Average slightlydamaged : {}", slightlydamaged);
            }

            String podsofothervariety = getAverage(data.getPodsOfOtherVarietyList());
            if (StringUtils.isNotBlank(podsofothervariety)) {
                logger.debug("Average podsofothervariety : {}", podsofothervariety);
            }

            String shelling = getAverage(data.getShellingList());
            if (StringUtils.isNotBlank(shelling)) {
                logger.debug("Average shelling : {}", shelling);
            }

            String damagedandweevilled = getAverage(data.getDamagedAndWevilledList());
            if (StringUtils.isNotBlank(damagedandweevilled)) {
                logger.debug("Average damagedandweevilled : {}", damagedandweevilled);
            }

            String immature = getAverage(data.getImmatureList());
            if (StringUtils.isNotBlank(immature)) {
                logger.debug("Average immature : {}", immature);
            }

            String otherfoodgrains = getAverage(data.getOtherFoodGrainsList());
            if (StringUtils.isNotBlank(otherfoodgrains)) {
                logger.debug("Average otherfoodgrains : {}", otherfoodgrains);
            }


            String smallatrophiedseeds = getAverage(data.getSmallAtrophoeSeeds());
            if (StringUtils.isNotBlank(smallatrophiedseeds)) {
                logger.debug("Average smallatrophiedseeds : {}", smallatrophiedseeds);
            }

            logger.debug("###########################################");

        });
    }

    /**
     * Get Sum of All teh values again there keys.
     *
     * @param countMap
     * @return
     */
    private String getCountFromMap(Map<String, Integer> countMap) {
        String result = null;
        Collection<Integer> values = countMap.values();
        if (CollectionUtils.isNotEmpty(values)) {
            Integer sum = values.stream().reduce(0, (a, b) -> a + b);
            result = String.valueOf(sum);
        }
        return result;
    }

    /**
     * Get Sum of All the Values in a list.
     *
     * @param totalWeightList
     * @return
     */
    private String getTotalSum(List<BigDecimal> totalWeightList) {
        String result = null;
        if (CollectionUtils.isNotEmpty(totalWeightList)) {
            BigDecimal totalSum = totalWeightList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            result = String.valueOf(Utility.formatDecimal(totalSum));
        }
        return result != null ? result + " Quintals" : null;
    }


    /**
     * Get Average of All the Values in the list.
     *
     * @param dataList
     * @return
     */
    private String getAverage(List<BigDecimal> dataList) {
        String result = null;
        if (CollectionUtils.isNotEmpty(dataList)) {
            BigDecimal[] totalWithCount
                    = dataList.stream()
                    .filter(bd -> bd != null)
                    .map(bd -> new BigDecimal[]{bd, BigDecimal.ONE})
                    .reduce((a, b) -> new BigDecimal[]{a[0].add(b[0]), a[1].add(BigDecimal.ONE)})
                    .get();
            BigDecimal mean = totalWithCount[0].divide(totalWithCount[1], RoundingMode.HALF_DOWN);
            result = String.valueOf(Utility.formatDecimal(mean));
        }
        return result != null ? result + " %" : null;
    }

    /**
     * Populate the Statge Data Object.
     *
     * @param record
     * @param stateData
     */
    private void populateStateData(CSVModel record, StateAnalysisDataModel stateData) {
        stateData.getCsvList().add(record);
        //Populate Truck Info
        String truckNumber = record.getTruckNumber();
        String approval = record.getApproval();
        Double currentWeight = record.getWeight();
        //Add Current Weight to Total Weight List
        if (currentWeight != null) {
            stateData.getTotalWeightList().add(Utility.getBigDecimalValue(currentWeight));
        }
        Boolean accepted = null;
        if (approval != null) {
            if (StringUtils.equalsAnyIgnoreCase(approval, "1")) {
                accepted = true;
            } else if (StringUtils.equalsAnyIgnoreCase(approval, "2")) {
                accepted = false;
            }
        }
        Map<String, Integer> acceptedTruckCount = stateData.getAcceptedTruckCount();
        Map<String, Integer> rejectedTruckCount = stateData.getRejectedTruckCount();
        Map<String, Integer> totalTruckCount = stateData.getTruckCount();

        //Total Truck Count
        if (totalTruckCount.get(truckNumber) != null) {
            Integer truckCount = totalTruckCount.get(truckNumber);
            truckCount = truckCount + 1;
            totalTruckCount.put(truckNumber, truckCount);
        } else {
            totalTruckCount.put(truckNumber, 1);
        }

        if (accepted != null) {
            if (accepted) {
                //Set Accepted Weight
                if (currentWeight != null) {
                    stateData.getAcceptedWeightList().add(Utility.getBigDecimalValue(currentWeight));
                }
                //Accepted Truck Count
                if (acceptedTruckCount.get(truckNumber) != null) {
                    Integer acceptCount = acceptedTruckCount.get(truckNumber);
                    acceptCount = acceptCount + 1;
                    acceptedTruckCount.put(truckNumber, acceptCount);
                } else {
                    acceptedTruckCount.put(truckNumber, 1);
                }
            } else {
                //Set Rejected Weight
                if (currentWeight != null) {
                    stateData.getRejectedWeightList().add(Utility.getBigDecimalValue(currentWeight));
                }
                //RejectedTruck Count
                if (rejectedTruckCount.get(truckNumber) != null) {
                    Integer rejectCount = rejectedTruckCount.get(truckNumber);
                    rejectCount = rejectCount + 1;
                    rejectedTruckCount.put(truckNumber, rejectCount);
                } else {
                    rejectedTruckCount.put(truckNumber, 1);
                }
            }
        }

        //Set Analysis Values.
        //moisturecontent
        addValue(record.getMoisturecontent(), stateData.getMoistureList());
        //foreignmatter
        addValue(record.getForeignmatter(), stateData.getFmList());
        //admixture
        addValue(record.getAdmixture(), stateData.getAdMixtureList());
        //damaged
        addValue(record.getDamaged(), stateData.getDamagedList());
        //weevilled
        addValue(record.getWeevilled(), stateData.getWevilledList());
        //shrivelledandimmature
        addValue(record.getShrivelledAndImmature(), stateData.getShrivelledList());
        //slightlydamaged
        addValue(record.getSlightlydamaged(), stateData.getSlightlyDamagedList());
        //podsofothervariety
        addValue(record.getPodsofothervar(), stateData.getPodsOfOtherVarietyList());
        //shelling
        addValue(record.getShelling(), stateData.getShellingList());
        //damagedandweevilled
        addValue(record.getDamagedAndWeevilled(), stateData.getDamagedAndWevilledList());
        //immature
        addValue(record.getImmature(), stateData.getImmatureList());
        //otherfoodgrains
        addValue(record.getOtherfoodgrains(), stateData.getOtherFoodGrainsList());
        //smallatrophiedseeds
        addValue(record.getSmallAtrophiedSeeds(), stateData.getSmallAtrophoeSeeds());
    }

    private void addValue(Double value, List<BigDecimal> list) {
        if (value != null) {
            list.add(Utility.getBigDecimalValue(value));
        }
    }

    public ByteArrayInputStream verifyImageExists(Boolean aTrue) {
        try {
            //Retrieve all the records from the DB
            Map<Long, CSVModel> csvModelMap = nafedDataService.generateSQLQuery(null, null );
            if (!csvModelMap.isEmpty()) {
                List<CSVModel> csvModelList = getIMageInfo(csvModelMap);
                csvModelList.forEach(model -> {
                    if (model.getImageModel() != null && !model.getImageModel().getIsImageExists()) {
                        System.out.println("SampleId : " + model.getSampleId() + " imageExist:" + model.getImageModel().getIsImageExists());
                    }
                });
            }
        } catch (Exception e) {
            logger.debug("Exception Caught");
            e.getLocalizedMessage();
        }
        return null;
    }

    public List<CSVModel> getIMageInfo(Map<Long, CSVModel> csvModelMap) {
        ExecutorService service = Executors.newFixedThreadPool(1000);
        List<Callable<CSVModel>> taskList = new ArrayList<>();
        csvModelMap.forEach((k, v) -> {
            taskList.add(getImageData(k, v));
        });
        try {
            List<Future<CSVModel>> futures = service.invokeAll(taskList);
        } catch (InterruptedException e) {
            logger.debug("Caught Exception");
            e.printStackTrace();
        }
        return csvModelMap.values().stream().collect(Collectors.toList());
    }

    private Callable<CSVModel> getImageData(Long k, CSVModel v) {
        return () -> {
            CSVModel model = v;

            try {
                String sampleId = v.getSampleId();
                v.setImageModel(setFileParams(v.getImageId()));
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

            return model;
        };


    }


    private ImageModel setFileParams(String imageId) {
        if (imageId.contains(":")) {
            String[] imageIds = imageId.split(":");
            return getImageModel(imageIds[0]);
        } else {
            return getImageModel(imageId);
        }


    }

    private ImageModel getImageModel(String imageId) {
        ImageModel image = new ImageModel();
        String fileName = "";
        String finalURL = "";
        HttpURLConnection httpConnection = null;
        try {

            finalURL = "https://agnext-jasmine.s3.us-east-2.amazonaws.com/visio_desktop/nafed_images/" + imageId
                    + "/scanned_image.jpg";

            httpConnection = getHttpConnection(finalURL);
            httpConnection.setConnectTimeout(15000);
            httpConnection.setReadTimeout(15000);
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
             /*   fileName = getFileName(httpConnection, finalURL);

                image.setImageId(imageId);
                image.setFileName(imageId + "_" + fileName);
                image.setFileExtension(".jpg");
                image.setUrl(finalURL);*/
                image.setIsImageExists(Boolean.TRUE);
            } else {
                image.setIsImageExists(Boolean.FALSE);
            }
            httpConnection.disconnect();
        } catch (IOException e) {
            logger.error("image not caputered for :{}, exception is  : {}", imageId, e.getLocalizedMessage());
            e.printStackTrace();
            httpConnection.disconnect();
        }
        if (httpConnection != null) {
            httpConnection.disconnect();
        }
        return image;
    }

    private HttpURLConnection getHttpConnection(String fileURL) throws IOException {
        URL url = new URL(fileURL);
        // logger.debug("URL is : {}", url.toString());
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        return httpConn;
    }

    private String getFileName(HttpURLConnection httpConn, String fileURL) {
        String fileName = "";
        String disposition = httpConn.getHeaderField("Content-Disposition");
        String contentType = httpConn.getContentType();
        int contentLength = httpConn.getContentLength();
        /*logger.debug("Content-Type = {}", contentType);
        logger.debug("Content-Disposition ={} ", disposition);
        logger.debug("Content-Length = {}", contentLength);
        logger.debug("fileName = {}", fileName);*/

        if (disposition != null) {
            // extracts file name from header field
            int index = disposition.indexOf("filename=");
            if (index > 0) {
                fileName = disposition.substring(index + 10, disposition.length() - 1);
            }
        } else {
            // extracts file name from URL
            fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
        }
        return fileName;
    }


}


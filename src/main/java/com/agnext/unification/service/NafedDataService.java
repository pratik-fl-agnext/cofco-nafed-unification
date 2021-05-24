package com.agnext.unification.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.agnext.unification.common.Constants;
import com.agnext.unification.config.AnalyticsVariations;
import com.agnext.unification.config.DbConnection;
import com.agnext.unification.entity.nafed.StateAdminAndStateEntity;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.model.CSVMeanModel;
import com.agnext.unification.model.CSVModel;
import com.agnext.unification.repository.nafed.StateAdminAndStateRepository;

/**
 * Create Excel fron Nafed Data
 */

@Component
public class NafedDataService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(NafedDataService.class);

    Connection con = DbConnection.getConnection();
    
   // volatile Set<String> uniqueAnalysisParams = new HashSet<>();

    @Autowired
    AnalyticsVariations analyticsVariations;

    @Autowired
    StateAdminAndStateRepository stateAdminStateRepo;

    public ByteArrayInputStream generateExcelFromNafedData(Long commodityId, Long stateId) throws SQLException {
        logger.info("inside generate class");

        if (con != null) {
            try {
                Map<Long, CSVModel> csvModelMap = generateSQLQuery(commodityId, stateId);

                return calculateAnalysisValues(csvModelMap, con);
            } finally {
                if (null != con) {
                    logger.info("connection closed");
                }
            }
        }
        return null;
    }

    /*public Map<Long, CSVModel>  generateSQLQuery(Connection con){
        return generateSQLQuery(null,null,con);
    }*/

    public Map<Long, CSVModel> generateSQLQuery(Long commodityId, Long stateId) {
        Statement scanStmt = null;
        StringBuilder sb = new StringBuilder();

        try {
            scanStmt = con.createStatement();
            if (applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.CUSTOMER_ADMIN)) {
                sb.append("SELECT \n" + "    s.id,s.sample_id,s.truck_number,s.bag,s.weight,\n"
                        + "    from_unixtime(floor(s.created_on/1000)) as date_of_scan,\n"
                        + "    sl.location_name AS warehouse_name,\n"
                        + "    sl.warehouse_name AS location_name,s.accepted_bags,s.rejected_bags,s.remark,s.commodity_name,s.variety,s.approval,s.is_valid,sl.state_id,s.image_unique_id\n"
                        + "FROM\n" + " scm_scans s\n" + "            RIGHT JOIN\n"
                        + " scan_location sl ON s.installation_center_id = sl.id\n where s.is_valid = true");
                if (commodityId != null) {
                    sb.append(" AND s.commodity_id = " + commodityId);
                }
                if (stateId != null) {
                    sb.append(" AND sl.state_id = " + stateId);
                }
                sb.append(" GROUP BY s.sample_id");
                logger.info("Customer Admin query:{} ", sb.toString());
            } else if (applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.STATE_ADMIN)) {
                Long stateAdminId = applicationContext.getRequestContext().getStateAdmin();
                sb.append("SELECT \n" + "    s.id,s.sample_id,s.truck_number,s.bag,s.weight,\n"
                        + "    from_unixtime(floor(s.created_on/1000)) as date_of_scan,\n"
                        + "    sl.location_name AS warehouse_name,\n"
                        + "    sl.warehouse_name AS location_name,s.accepted_bags,s.rejected_bags,s.remark,s.commodity_name,s.variety,s.approval,s.is_valid,sl.state_id,s.image_unique_id\n"
                        + "FROM\n" + " scm_scans s\n" + "            RIGHT JOIN\n"
                        + " scan_location sl ON s.installation_center_id = sl.id\n where s.is_valid = true");
                if (stateAdminId != null) {
                    sb.append(" AND s.state_admin = " + stateAdminId);
                }
                sb.append(" GROUP BY s.sample_id");
                logger.info("State Admin query:{} ", sb.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createModel(scanStmt, sb);
    }

    private Map<Long, CSVModel> createModel(Statement scanStmt, StringBuilder sb) {
        String query = sb.toString();
        ResultSet scanRs;
        Map<Long, CSVModel> csvModelMap = new HashMap<>();
        try {
            scanRs = scanStmt.executeQuery(query);
            logger.info("Query Executed");
            while (scanRs.next()) {
                CSVModel model = new CSVModel();
                model.setScanId(scanRs.getLong(1));
                model.setSampleId(scanRs.getString(2));
                model.setTruckNumber(scanRs.getString(3));
                model.setBagCount(scanRs.getString(4));
                model.setWeight(scanRs.getDouble(5));
                model.setDateOfScan(scanRs.getString(6));
                model.setWarehousename(scanRs.getString(7));
                model.setLocationname(scanRs.getString(8));
                model.setAcceptedBag(scanRs.getString(9));
                model.setRejectedBag(scanRs.getString(10));
                model.setRemarks(scanRs.getString(11));
                model.setCommodity(scanRs.getString(12));
                model.setVariety(scanRs.getString(13));
                model.setApproval(scanRs.getString(14));
				model.setValid(scanRs.getBoolean(15));
				model.setStateId(scanRs.getLong(16));
				model.setImageId(scanRs.getString(17));
                csvModelMap.put(model.getScanId(), model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return csvModelMap;
    }

    private ByteArrayInputStream calculateAnalysisValues(Map<Long, CSVModel> csvModelMap, Connection con) throws SQLException {
        List<CSVModel> collect = getCsvModels(csvModelMap);
        return createExcel(collect);

    }

    public List<CSVModel> getCsvModels(Map<Long, CSVModel> csvModelMap) {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<CSVModel>> taskList = new ArrayList<>();
        csvModelMap.forEach((k, v) -> {
            taskList.add(getFuture(con, k, v));
        });
        try {
            List<Future<CSVModel>> futures = service.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return csvModelMap.values().stream().collect(Collectors.toList());
    }

    public List<CSVModel> getCsvModels1(List<CSVModel> csvModelList, Connection con) {
        //ExecutorService service = Executors.newFixedThreadPool(100);
        //List<Callable<CSVModel>> taskList = new ArrayList<>();
        return csvModelList.stream().map((record) -> {
            return getCsvModel(record.getScanId(),record);
        }).collect(Collectors.toList());
        /*try {
            List<Future<CSVModel>> futures = service.invokeAll(taskList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //return csvModelList;
    }


    public List<CSVModel> getCsvModels(List<CSVModel> csvModelList, Connection con) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        List<Callable<CSVModel>> taskList = new ArrayList<>();
        csvModelList.forEach((record) -> {
            taskList.add(getFuture(con, record.getScanId(), record));
        });
        try {
            List<Future<CSVModel>> futures = service.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return csvModelList;
    }




    @Async
    public CompletableFuture<List<CSVModel>> processResults(List<CSVModel> resultList)  {
        final long start = System.currentTimeMillis();
        logger.info("Calculating results for  {} records", resultList.size());
        List<CSVModel> csvModels = getCsvModels1(resultList, con);
        long elaspedMs = System.currentTimeMillis() - start;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(elaspedMs);
        logger.info("Elapsed time in MS: {}, and in Min:{}", (System.currentTimeMillis() - start), minutes);
        return CompletableFuture.completedFuture(csvModels);
    }

    private Callable<CSVModel> getFuture(Connection con, Long k, CSVModel v) {
        return () -> {
            return getCsvModel(k, v);
        };
    }

    private CSVModel getCsvModel(Long k, CSVModel v) {
        CSVModel model = v;
        if (con != null) {
            try {
                Statement scanStmt = con.createStatement();
                ResultSet scanResultRs = scanStmt.executeQuery(
                        "SELECT analysis_name, result FROM scm_scan_results where scan_id = " + k);
                while (scanResultRs.next()) {
                    Map<String, List<String>> analytics = analyticsVariations.getAnalytics();
                    String analysisName = scanResultRs.getString(1);
                //    uniqueAnalysisParams.add(analysisName);
                    //String analysisResult = String.format("%.2f", scanResultRs.getDouble(2));
                    Double analysisResult = scanResultRs.getDouble(2);

                    if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.ADMIXTURE.getAnalytics()).contains(analysisName)) {
                     //   logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setAdmixture(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGED.getAnalytics()).contains(analysisName)) {
                       // logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setDamaged(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.FOREIGNMATTER.getAnalytics()).contains(analysisName)) {
                       // logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setForeignmatter(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.IMMATURE.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setImmature(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.MOISTURECONTENT.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setMoisturecontent(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.PODSOFOTHERVAR.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setPodsofothervar(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHELLING.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setShelling(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHRIVELLEDANDIMMATURE.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setShrivelledAndImmature(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SLIGHTLYDAMAGED.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setSlightlydamaged(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.WEEVILLED.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setWeevilled(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGEDANDWEEVILLED.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setDamagedAndWeevilled(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.OTHERFOODGRAINS.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setOtherfoodgrains(analysisResult);
                    } else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SMALLATROPHIEDSEEDS.getAnalytics()).contains(analysisName)) {
                        //logger.debug(" result.getAnalysisName() " + analysisName);
                        model.setSmallAtrophiedSeeds(analysisResult);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return model;
    }

    /*public Set<String> retrieveUniqueAnalysisValues(){
        return uniqueAnalysisParams;
    }*/

    private List<CSVModel> getAnalysisValues(Map<Long, CSVModel> csvModelMap, Connection con) {
        List<CSVModel> collect = getCsvModels(csvModelMap);
        return collect;

    }

    private List<CSVMeanModel> getAnalysisNamesAndCalculateMean(Long stateId) {
        List<CSVMeanModel> modelList = new ArrayList<>();

        List<String> analysisNameList = new ArrayList<>();
        analysisNameList.add("moisturecontent");
        analysisNameList.add("admixture");
        analysisNameList.add("foreignmatter");
        analysisNameList.add("damaged");
        analysisNameList.add("immature");
        analysisNameList.add("shelling");

        for (String s : analysisNameList) {
            modelList.add(calculateMean(s, stateId, con));
        }
        return modelList;
    }





    private CSVMeanModel calculateMean(String analysisName, Long stateId, Connection con) {
        Statement scanStmt = null;
        StringBuilder sb = new StringBuilder();

        try {
            scanStmt = con.createStatement();

            if (applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.CUSTOMER_ADMIN)) {
                sb.append("SELECT sr.analysis_name, avg(sr.result) FROM scm_scan_results sr "
                        + "right JOIN scm_scans s ON sr.scan_id = s.id "
                        + "right join scan_location sl ON s.installation_center_id = sl.id "
                        + "where s.is_valid = true ");
                if (analysisName != null) {
                    sb.append("and sr.analysis_name = '" + analysisName + "'");
                }
                if (stateId != null) {
                    sb.append(" and sl.state_id = " + stateId);
                }
                logger.info("Customer Admin mean query:{} ", sb.toString());
            } else if (applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.STATE_ADMIN)) {
                Long stateAdminId = applicationContext.getRequestContext().getStateAdmin();

                sb.append("SELECT sr.analysis_name, avg(sr.result) FROM scm_scan_results sr "
                        + "right JOIN scm_scans s ON sr.scan_id = s.id "
                        + "right join scan_location sl ON s.installation_center_id = sl.id "
                        + "where s.is_valid = true ");
                if (analysisName != null) {
                    sb.append("and sr.analysis_name = '" + analysisName + "'");
                }
                if (stateAdminId != null) {
                    sb.append(" AND s.state_admin = " + stateAdminId);
                }
                logger.info("State Admin mean query:{} ", sb.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createCsvMeanModel(analysisName, scanStmt, sb);
    }

    private CSVMeanModel createCsvMeanModel(String analysisName, Statement scanStmt, StringBuilder sb) {
        String query = sb.toString();
        ResultSet scanRs;
        CSVMeanModel model = new CSVMeanModel();
        try {
            scanRs = scanStmt.executeQuery(query);
            while (scanRs.next()) {
                model.setAnalysisName(scanRs.getString(1) != null ? scanRs.getString(1) : analysisName);
                model.setMeanValue(scanRs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public ByteArrayInputStream createExcel(List<CSVModel> csvModelList) throws SQLException {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("nafeddata.xlsx");

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            XSSFWorkbook nafedDataWorkBook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
            XSSFSheet allScansSheet = nafedDataWorkBook.getSheetAt(0);
            XSSFSheet gujaratSheet = nafedDataWorkBook.getSheetAt(1);
            XSSFSheet rajasthanSheet = nafedDataWorkBook.getSheetAt(2);
            XSSFSheet maharashtraSheet = nafedDataWorkBook.getSheetAt(3);

            //mean analysis sheets
            XSSFSheet allMeanAnalysisSheet = nafedDataWorkBook.getSheetAt(4);
            XSSFSheet gujaratMeanAnalysisSheet = nafedDataWorkBook.getSheetAt(5);
            XSSFSheet rajasthanMeanAnalysisSheet = nafedDataWorkBook.getSheetAt(6);
            XSSFSheet maharashtraMeanAnalysisSheet = nafedDataWorkBook.getSheetAt(7);

            DataFormat format = nafedDataWorkBook.createDataFormat();
            XSSFCellStyle style = nafedDataWorkBook.createCellStyle();
            style.setDataFormat(format.getFormat("0.00"));

            for (int i = 1; i < 65000; i++) {
                Row r1 = allScansSheet.getRow(i);
                Row r2 = gujaratSheet.getRow(i);
                Row r3 = rajasthanSheet.getRow(i);
                Row r4 = maharashtraSheet.getRow(i);

                Row r5 = allMeanAnalysisSheet.getRow(i);
                Row r6 = gujaratMeanAnalysisSheet.getRow(i);
                Row r7 = rajasthanMeanAnalysisSheet.getRow(i);
                Row r8 = maharashtraMeanAnalysisSheet.getRow(i);
                if (null != r1 || null != r2 || null != r3 || null != r4 || null != r5 || null != r6 || null != r7 || null != r8) {
                    allScansSheet.removeRow(r1);
                    gujaratSheet.removeRow(r2);
                    rajasthanSheet.removeRow(r3);
                    maharashtraSheet.removeRow(r4);

                    allMeanAnalysisSheet.removeRow(r5);
                    gujaratMeanAnalysisSheet.removeRow(r6);
                    rajasthanMeanAnalysisSheet.removeRow(r7);
                    maharashtraMeanAnalysisSheet.removeRow(r8);
                }
            }

            createTabs(allScansSheet, csvModelList, style);

            if (applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.CUSTOMER_ADMIN)) {
                Map<Long, String> stateIds = getStateIds(this.con);
                List<Long> stateIdList = stateIds.keySet().stream().collect(Collectors.toList());
                //Gujarat
                Map<Long, CSVModel> gujaratSheetModel = generateSQLQuery(null, stateIdList.get(0));
                List<CSVModel> gujaratSheetModelList = getAnalysisValues(gujaratSheetModel, con);
                createTabs(gujaratSheet, gujaratSheetModelList, style);

                //Rajasthan
                Map<Long, CSVModel> rajasthanSheetModel = generateSQLQuery(null, stateIdList.get(1));
                List<CSVModel> rajasthanSheetModelList = getAnalysisValues(rajasthanSheetModel, con);
                createTabs(rajasthanSheet, rajasthanSheetModelList, style);

                //Maharashtra
                Map<Long, CSVModel> maharashtraSheetModel = generateSQLQuery(null, stateIdList.get(2));
                List<CSVModel> maharashtraSheetModelList = getAnalysisValues(maharashtraSheetModel, con);
                createTabs(maharashtraSheet, maharashtraSheetModelList, style);

                //All Mean Analysis
                List<CSVMeanModel> allMeanModelList = getAnalysisNamesAndCalculateMean(null);
                createTabsForMean(allMeanAnalysisSheet, allMeanModelList, style);

                //Gujarat Mean Analysis
                List<CSVMeanModel> gujaratMeanModelList = getAnalysisNamesAndCalculateMean(stateIdList.get(0));
                createTabsForMean(gujaratMeanAnalysisSheet, gujaratMeanModelList, style);

                //Rajasthan Mean Analysis
                List<CSVMeanModel> rajasthanMeanModelList = getAnalysisNamesAndCalculateMean(stateIdList.get(1));
                createTabsForMean(rajasthanMeanAnalysisSheet, rajasthanMeanModelList, style);

                //Maharashtra Mean Analysis
                List<CSVMeanModel> maharashtraMeanModelList = getAnalysisNamesAndCalculateMean(stateIdList.get(2));
                createTabsForMean(maharashtraMeanAnalysisSheet, maharashtraMeanModelList, style);
            } else {
                nafedDataWorkBook.removeSheetAt(7);
                nafedDataWorkBook.removeSheetAt(6);
                nafedDataWorkBook.removeSheetAt(5);

                //nafedDataWorkBook.removeSheetAt(4);
                List<CSVMeanModel> allMeanModelList = getAnalysisNamesAndCalculateMean(null);
                createTabsForMean(allMeanAnalysisSheet, allMeanModelList, style);

                nafedDataWorkBook.removeSheetAt(3);
                nafedDataWorkBook.removeSheetAt(2);
                nafedDataWorkBook.removeSheetAt(1);
            }
            nafedDataWorkBook.write(out);
            logger.info("Complete creating file, sending data by Email");
            inputStream.close();
            nafedDataWorkBook.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("failed to import data to CSV file: " + e.getMessage());
        }
    }


    public Map<Long,String> getStateIds(Connection con) throws SQLException {
        Map<Long,String> stateMap = new HashMap<>();
        Statement scanStmt = con.createStatement();
        ResultSet stateIds = scanStmt.executeQuery("SELECT distinct sl.state_id,ss.name FROM nafed_pilot.scm_scans s RIGHT join nafed_pilot.scan_location sl ON s.installation_center_id = sl.id left join nafed_pilot.states ss on ss.id=sl.state_id where s.installation_center_id is not null");
        while (stateIds.next()) {
            stateMap.put(stateIds.getLong(1),stateIds.getString(2));
        }
        return stateMap;

    }

    private void createTabs(XSSFSheet sheet, List<CSVModel> csvModelList, XSSFCellStyle style) {
        int rowCount = 0;
        for (CSVModel v : csvModelList) {
            int columnCount = 0;
            Row row = sheet.createRow(++rowCount);

            Cell cell = row.createCell(columnCount);
            cell.setCellValue(
                    null != String.valueOf(v.getSampleId()) ? String.valueOf(String.valueOf(v.getSampleId())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getDateOfScan())
                    ? String.valueOf(String.valueOf(v.getDateOfScan())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getCommodity())
                    ? String.valueOf(String.valueOf(v.getCommodity())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(
                    null != String.valueOf(v.getVariety()) ? String.valueOf(String.valueOf(v.getVariety())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getLocationname())
                    ? String.valueOf(String.valueOf(v.getLocationname())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getWarehousename())
                    ? String.valueOf(String.valueOf(v.getWarehousename())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            cell.setCellValue(null != String.valueOf(v.getBagCount()) ? String.valueOf(String.valueOf(v.getBagCount())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            cell.setCellValue(null != v.getWeight() ? v.getWeight() : new Double(""));

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getTruckNumber())
                    ? String.valueOf(String.valueOf(v.getTruckNumber())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getMoisturecontent())
                cell.setCellValue(v.getMoisturecontent());
            /*else if (null != v.getMoisture())
                cell.setCellValue(v.getMoisture());*/

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getAdmixture())
                cell.setCellValue(v.getAdmixture());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getForeignmatter())
                cell.setCellValue(v.getForeignmatter());
            /*else if (null != v.getFm())
                cell.setCellValue(v.getFm());*/

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getDamaged())
                cell.setCellValue(v.getDamaged());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getWeevilled())
                cell.setCellValue(v.getWeevilled());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getPodsofothervar())
                cell.setCellValue(v.getPodsofothervar());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getImmature())
                cell.setCellValue(v.getImmature());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getSlightlydamaged())
                cell.setCellValue(v.getSlightlydamaged());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getShelling())
                cell.setCellValue(v.getShelling());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getOtherfoodgrains())
                cell.setCellValue(v.getOtherfoodgrains());
        }
    }

    private void createTabsForMean(XSSFSheet sheet, List<CSVMeanModel> csvModelList, XSSFCellStyle style) {
        int rowCount = 0;
        for (CSVMeanModel v : csvModelList) {
            int columnCount = 0;
            Row row = sheet.createRow(++rowCount);

            Cell cell = row.createCell(columnCount);
            cell.setCellValue(null != String.valueOf(v.getAnalysisName()) ? String.valueOf(String.valueOf(v.getAnalysisName())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            cell.setCellValue(null != v.getMeanValue() ? v.getMeanValue() : new Double(""));
        }
    }

    private static String getAnalysisValue(String aName) {
        if (aName != null && aName != "") {
            return aName;
        } else {
            return "";
        }
    }

    public StateEntity getStateIdForStateAdmin(Long stateAdminId) {

	StateAdminAndStateEntity stateAdminStateEntity = stateAdminStateRepo.findByStateAdminUserId(stateAdminId);
	if(stateAdminStateEntity != null){
	    return stateAdminStateEntity.getState();
	}
	return null;
    }

    public Connection getConnection() {
        return con;
    }
}

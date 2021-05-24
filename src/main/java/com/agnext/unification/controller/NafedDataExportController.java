package com.agnext.unification.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.common.Constants;
import com.agnext.unification.config.ApplicationContext;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.service.AnalysisDataService;
import com.agnext.unification.service.NafedDataService;

@RestController
@RequestMapping(value = "api/export", produces = "application/json")
public class NafedDataExportController {

    private static Logger logger = LoggerFactory.getLogger(NafedDataExportController.class);

    @Autowired
    NafedDataService nafedDataService;

    @Autowired
    AnalysisDataService analysisDataService;
    
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping()
    public ResponseEntity<?> exportData(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                                        @RequestParam(value = "commodity_id", required = false) Long commodityId,
                                        @RequestParam(value = "state_id", required = false) Long stateId) throws IOException, SQLException {
        HttpHeaders headers = new HttpHeaders();
        try {
            //check if request is from customer admin or state admin
            //if request is from state admin, then pass the state id to service
            if (applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.STATE_ADMIN)) {
                StateEntity stateEntity = nafedDataService.getStateIdForStateAdmin(applicationContext.getRequestContext().getStateAdmin());
                if (stateEntity != null) {
                    stateId = stateEntity.getId();
                } else {
                    throw new IMException(Constants.ErrorCode.STATE_NOT_FOUND, Constants.ErrorMessage.STATE_NOT_FOUND);
                }
            }
            ByteArrayInputStream response = analysisDataService.generateExcelFromNafedData(commodityId, stateId);
            //ByteArrayInputStream response = nafedDataService.generateExcelFromNafedData(commodityId, stateId);
            InputStreamResource resource = new InputStreamResource(response);
            headers.add("Content-Disposition", "attachment;filename=result1234.xlsx");
            headers.add("Content-Type", "application/vnd.ms-excel");
            return ResponseEntity.ok().headers(headers).body(resource);
        } catch (Exception e) {
            logger.error("ScanController.downloadCSVFile exception is ", e.getMessage());
            return nafedDataService.handleException(e);
        }
    }

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @GetMapping(value ="/analyseData")
    public ResponseEntity<?> sendData(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException, SQLException {
        HttpHeaders headers = new HttpHeaders();
        try {
            ByteArrayInputStream response = analysisDataService.sendAnalysisData(null, null, Boolean.TRUE);
            InputStreamResource resource = new InputStreamResource(response);
            headers.add("Content-Disposition", "attachment;filename=result12345.xlsx");
            headers.add("Content-Type", "application/vnd.ms-excel");
            return ResponseEntity.ok().headers(headers).body(resource);
        } catch (Exception e) {
            logger.error("ScanController.downloadCSVFile exception is ", e.getMessage());
            return nafedDataService.handleException(e);
        }
    }


    @GetMapping(value ="/imageExists")
    public ResponseEntity<?> imageExists(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException, SQLException {
        HttpHeaders headers = new HttpHeaders();
        try {
            ByteArrayInputStream response = analysisDataService.verifyImageExists(Boolean.FALSE);
            InputStreamResource resource = new InputStreamResource(response);
            headers.add("Content-Disposition", "attachment;filename=result1234.xlsm");
            headers.add("Content-Type", "application/vnd.ms-excel");
            return ResponseEntity.ok().headers(headers).body(resource);
        } catch (Exception e) {
            logger.error("ScanController.downloadCSVFile exception is ", e.getMessage());
            return nafedDataService.handleException(e);
        }
    }
}

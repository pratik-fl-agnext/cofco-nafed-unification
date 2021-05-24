package com.agnext.unification.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.ImageDetails;
import com.agnext.unification.model.ImageModel;
import com.agnext.unification.service.ScanService;


@RestController
@RequestMapping(value = "api/chemical/device", produces = "application/json")
public class ProDeviceScanController {
	private static Logger logger = LoggerFactory.getLogger(ScanController.class);

	@Autowired
	ScanService scanService;

	/*@PutMapping(value = "/data/{device_id}", params = "v=1")
	public ResponseEntity<?> saveDeviceData(@RequestBody ProScanModel model,
			@PathVariable("device_id") String deviceId) {

		try {
			logger.info("update the chemical device when data posting :" + model);

			scanService.updateProDeviceScan(null, model, deviceId);
			ScanResponseModel response = new ScanResponseModel();
			response.setMessage("Scan Successful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return scanService.handleException(e);
		}

	}*/

	@GetMapping("/visio/download/pdf/{id}")
	public ResponseEntity fetchScansByIdWithResults(HttpServletResponse response, @PathVariable("id") Long scanId)
			throws DocumentException, IOException, Exception {
		return scanService.generatePdfForScanResults(scanId, response);
	}

	@GetMapping("/download-physical/image/{id}")
	public ImageDetails downloadImage(HttpServletResponse response, @PathVariable("id") Long id) {
		final List<ImageModel> imageModels = scanService.downloadFile(id, response);
		ImageDetails imageDetails = new ImageDetails();
		imageDetails.setTotalCount(String.valueOf(imageModels.size()));
		imageDetails.setImageList(imageModels);
		return imageDetails;
	}

	@GetMapping("/download-physical/download/{id}")
	public void downloadIndividualImage(HttpServletResponse response, @PathVariable("id") String id) throws Exception {
		scanService.downloadImageById(id, response);
	}
	
	
	//POC code on HTML to PDF
	@GetMapping("/test/{id}")
	public ResponseEntity htmlToPdf(@PathVariable("id") Long scanId)
			throws DocumentException, IOException, Exception {
		return scanService.htmlToPdf(scanId);
	}
}

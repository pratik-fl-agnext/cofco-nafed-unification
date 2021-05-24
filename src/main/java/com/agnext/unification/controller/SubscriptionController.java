package com.agnext.unification.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.common.Response;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.CustomSubscriptionModel;
import com.agnext.unification.model.DeviceCommodityPurchasedVO;
import com.agnext.unification.model.DeviceTypeVO;
import com.agnext.unification.model.DevicesVO;
import com.agnext.unification.model.LicenceVO;
import com.agnext.unification.model.PackagesModel;
import com.agnext.unification.model.PaymentRequestedResponseAsJson;
import com.agnext.unification.service.PaymentService;
import com.agnext.unification.service.SubscriptionService;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {

	private static Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	private final SubscriptionService subscriptionService;
	private final PaymentService paymentService;

	public SubscriptionController(SubscriptionService subscriptionService, PaymentService paymentService) {
		this.subscriptionService = subscriptionService;
		this.paymentService = paymentService;
	}

	@GetMapping(params = "v=1")
	public Response getSubscriptionsByClient(@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			List<DeviceTypeVO> subscriptionList = subscriptionService.getSubscriptionsByClient(clientId);
			Collection<DeviceTypeVO> analyticCostVOCol = new ArrayList<>();
			analyticCostVOCol.addAll(subscriptionList);
			return Response.of("True", "Success", subscriptionList, subscriptionList.size(), true);
		} catch (Exception e) {
			return Response.of("false", "somthing went wrong");
		}
	}

	@PutMapping(value = "/buy/licence", params = "v=1", produces = MediaType.APPLICATION_JSON_VALUE)
	public PaymentRequestedResponseAsJson checkRoleAndProcessForPayment(
			@RequestParam(value = "clientId", required = false) Long clientId, @RequestBody LicenceVO licenceVO) {
		try {
			String redirectURL = subscriptionService.checkRoleAndProcess(clientId, licenceVO);
			if (redirectURL != null) {
				PaymentRequestedResponseAsJson response = new PaymentRequestedResponseAsJson();
				response.setRedirectUrl(redirectURL);
				return response;
			} else {
				throw new IMException("AR162", "RedirectURL is null");
			}
		} catch (Exception e) {
			subscriptionService.handleException(e);
			// throw e;
		}
		return null;
	}

	@PostMapping(value = "/buy/licence1", params = "v=1")
	public ResponseEntity<?> findPackagesByDeviceType(@RequestParam(value = "clientId", required = false) Long clientId,
			@RequestBody LicenceVO licenceVO) {
		try {
			subscriptionService.saveDeviceCommodityPurchased(clientId, licenceVO);
			return new ResponseEntity<LicenceVO>(HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);
		}
	}

	@PostMapping(value = "/buy/commodity", params = "v=1")
	public ResponseEntity<?> buyNewCommodity(@RequestBody LicenceVO licenceVO) {
		try {
			subscriptionService.buyNewCommodity(licenceVO);
			return new ResponseEntity<LicenceVO>(HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);
		}
	}

	@PostMapping(value = "/licence/renew", params = "v=1")
	public ResponseEntity<?> renewLicence(@RequestBody DeviceCommodityPurchasedVO deviceCommodityPurchasedVO) {
		subscriptionService.renewLicence(deviceCommodityPurchasedVO);
		return new ResponseEntity<LicenceVO>(HttpStatus.OK);
	}

	@PostMapping(params = "v=1")
	public ResponseEntity<?> saveLicence(@RequestParam(value = "clientId", required = false) Long clientId,
			@RequestBody LicenceVO licenceVO) {
		try {
			subscriptionService.saveLicence(clientId, licenceVO);
			return new ResponseEntity<LicenceVO>(HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);
		}
	}

	@GetMapping(value = "/package", params = "v=1")
	public ResponseEntity<?> findPackagesByDeviceType(
			@RequestParam(value = "deviceType", required = false) Long deviceType,
			@RequestParam(value = "customer_id", required = false) Long customer_id) {
		try {
			DevicesVO devicePackages = subscriptionService.findPackagesByDeviceType(deviceType, customer_id);
			Collection<DevicesVO> packagesVOLst = new ArrayList<>();
			packagesVOLst.add(devicePackages);
			return new ResponseEntity<DevicesVO>(devicePackages, HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);
		}

	}

	@GetMapping(value = "/{packageId}")
	public ResponseEntity<?> findPackagesDetailById(@PathVariable(value = "packageId") Long packageId) {
		try {
			PackagesModel packagevo = subscriptionService.findPackagesDetailById(packageId);
			return new ResponseEntity<PackagesModel>(packagevo, HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);

		}
	}

	@GetMapping
	public ResponseEntity<?> findAllPackages() {
		try {
			List<PackagesModel> packagevo = subscriptionService.findAllPackages();
			return new ResponseEntity<List<PackagesModel>>(packagevo, HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);

		}
	}

	@PostMapping
	public ResponseEntity<?> savePackages(@RequestBody PackagesModel packagesModel) {
		try {
			// subscriptionService.savePackages(packagesModel);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);

		}
	}

	@PutMapping
	public ResponseEntity<?> updatePackages(@RequestBody PackagesModel packagesModel) {
		try {
			// subscriptionService.updatePackages(packagesModel);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return subscriptionService.handleException(e);

		}
	}

	@PostMapping(value = "/custom")
	public PaymentRequestedResponseAsJson saveCustomSubscriptionDetails(
			@RequestBody CustomSubscriptionModel customSubscriptionModel) {
		try {
			String redirectURL = subscriptionService.saveCustomSubscription(customSubscriptionModel);
			if (redirectURL != null) {
				PaymentRequestedResponseAsJson response = new PaymentRequestedResponseAsJson();
				response.setRedirectUrl(redirectURL);
				return response;
			} else {
				throw new IMException("ARYA162", "RedirectURL is null");
			}
		} catch (Exception e) {
			subscriptionService.handleException(e);
			// throw e;
		}
		return null;
	}

}

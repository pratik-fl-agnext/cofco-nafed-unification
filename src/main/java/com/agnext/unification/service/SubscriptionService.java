package com.agnext.unification.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.config.RequestContext;
import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.DeviceCommodityPurchased;
import com.agnext.unification.entity.nafed.Licence;
import com.agnext.unification.entity.nafed.LicenceHistory;
import com.agnext.unification.entity.nafed.PackageCommoditiesPrice;
import com.agnext.unification.entity.nafed.Packages;
import com.agnext.unification.entity.nafed.Payment;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.CommodityPriceModel;
import com.agnext.unification.model.CustomSubscriptionModel;
import com.agnext.unification.model.DeviceCommodityPurchasedVO;
import com.agnext.unification.model.DeviceTypeVO;
import com.agnext.unification.model.DevicesVO;
import com.agnext.unification.model.LicenceVO;
import com.agnext.unification.model.PackageCommoditiesPriceVO;
import com.agnext.unification.model.PackagesModel;
import com.agnext.unification.model.PackagesVO;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.repository.nafed.DeviceCommodityAssociationRepository;
import com.agnext.unification.repository.nafed.DeviceCommodityPurchasedRepository;
import com.agnext.unification.repository.nafed.DeviceCostRepository;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.DeviceTypeRepository;
import com.agnext.unification.repository.nafed.FilterNativeRepository;
import com.agnext.unification.repository.nafed.LicenceHistoryRepository;
import com.agnext.unification.repository.nafed.LicenceRepository;
import com.agnext.unification.repository.nafed.PackageCommoditiesPriceRepository;
import com.agnext.unification.repository.nafed.PackageSubscriptionTypeRepository;
import com.agnext.unification.repository.nafed.PackagesRepository;
import com.agnext.unification.utility.Utility;

@Service
@Transactional
public class SubscriptionService extends GenericService {

	private static Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

	@Value("${ag.paymentEnvironmentId}")
	private String paymentEnvironmentId;

	private final DeviceCommodityPurchasedRepository deviceCommodityPurchasedRepo;
	private final DeviceTypeRepository deviceTypeRepo;
	private final DeviceCostRepository deviceCostRepo;
	private final PackagesRepository packagesRepo;
	private final PackageSubscriptionTypeRepository packageSubscriptionTypeRepo;
	private final DeviceCommodityAssociationRepository deviceCommodityAssociationRepo;
	private final PackageCommoditiesPriceRepository packageCommoditiesPriceRepo;
	private final DcmCommodityRepository commoditiesRepo;
	private final LicenceRepository licenceRepo;
	private final LicenceHistoryRepository licenceHistoryRepo;
	private final DeviceRepository dcmDeviceRepo;
	private final FilterNativeRepository filterNativeRepo;
	private final PaymentService paymentService;

	public SubscriptionService(DeviceCommodityPurchasedRepository deviceCommodityPurchasedRepo,
			DeviceTypeRepository deviceTypeRepo, DeviceCostRepository deviceCostRepo, PackagesRepository packagesRepo,
			PackageSubscriptionTypeRepository packageSubscriptionTypeRepo,
			DeviceCommodityAssociationRepository deviceCommodityAssociationRepo,
			PackageCommoditiesPriceRepository packageCommoditiesPriceRepo, DcmCommodityRepository commoditiesRepo,
			LicenceRepository licenceRepo, LicenceHistoryRepository licenceHistoryRepo, DeviceRepository dcmDeviceRepo,
			FilterNativeRepository filterNativeRepo, PaymentService paymentService) {
		this.deviceCommodityPurchasedRepo = deviceCommodityPurchasedRepo;
		this.deviceTypeRepo = deviceTypeRepo;
		this.deviceCostRepo = deviceCostRepo;
		this.packagesRepo = packagesRepo;
		this.packageSubscriptionTypeRepo = packageSubscriptionTypeRepo;
		this.deviceCommodityAssociationRepo = deviceCommodityAssociationRepo;
		this.packageCommoditiesPriceRepo = packageCommoditiesPriceRepo;
		this.commoditiesRepo = commoditiesRepo;
		this.licenceRepo = licenceRepo;
		this.licenceHistoryRepo = licenceHistoryRepo;
		this.dcmDeviceRepo = dcmDeviceRepo;
		this.filterNativeRepo = filterNativeRepo;
		this.paymentService = paymentService;
	}

	public PackagesModel findPackagesDetailById(Long packageId) {
		Packages p = packagesRepo.getOne(packageId);
		return EntityToVOAssembler.convertPackage(p);

	}

	public List<PackagesModel> findAllPackages() {
		List<Packages> pList = packagesRepo.findAll();
		return EntityToVOAssembler.convertPackageList(pList);
	}

	public void saveLicence(Long clientId, LicenceVO licenceVO) {
		List<DcmCommodity> commodityList = commoditiesRepo.findAllById(licenceVO.getCommodities());
		List<Licence> lList = new ArrayList<>();
		for (String deviceCode : licenceVO.getDevicesCode()) {
			for (DcmCommodity c : commodityList) {
				String licenceNo = String.valueOf("AG" + Calendar.getInstance().getTimeInMillis());
				Licence l = new Licence();
				l.setClientId(clientId);
				l.setCommodities(c);
				l.setLicenceNo(licenceNo);
				l.setDeviceCode(deviceCode);
				l.setPackages(packagesRepo.getOne(licenceVO.getPackages()));
				l.setStatus(1);
				lList.add(l);
			}
		}
		List<Licence> dbList = licenceRepo.saveAll(lList);

	}

	/**
	 * @param clientId
	 * @param licenceVO
	 */
	public String checkRoleAndProcess(Long clientId, LicenceVO licenceVO) throws IMException {
		String redirectURL = null;
		final RequestContext requestContext = applicationContext.getRequestContext();
		final Long loggedInUserId = requestContext.getUserId();
		final long loggedInCustomerId = requestContext.getCustomerId();
		final Set<String> roles = requestContext.getRoles();
		final Set<String> permissions = requestContext.getPermissions();
		Long currentTimeInMillis = Instant.now().toEpochMilli();
		String refId = "REF" + Utility.randomStringGenerator(8);
		if (roles.contains("customer_admin") && licenceVO != null) {
			BigDecimal amount = new BigDecimal("1.00");
			Payment paymentDetails = paymentService.setPaymentDetails(refId, loggedInUserId, loggedInCustomerId,
					loggedInUserId, currentTimeInMillis, 1, currentTimeInMillis, "WEB", amount, "INITITATED");

			// Get the Amount
			if (saveDeviceCommodity(clientId, licenceVO, Boolean.FALSE, paymentDetails)) {
				// BigDecimal amount = new BigDecimal("1.00");
				Payment payment = paymentService.setPayment(refId, loggedInUserId, loggedInCustomerId, loggedInUserId,
						currentTimeInMillis, 1, currentTimeInMillis, "WEB", amount, "INITITATED");
				redirectURL = "http:///23.98.216.140/payment/web?client_id=" + paymentEnvironmentId + "&ref_id=" + refId
						+ "&totalPayment=" + amount;

			} else {
				throw new IMException("ARYA161",
						"Multiple Records for Licence Info Exists - pls contact administrator");
			}

		}
		return redirectURL;
	}

//	 public DeviceCommodityPurchasedVO buyNewCommodity(DeviceCommodityPurchasedVO
//	 deviceCommodityPurchasedVO) {
//	 Licence l = new Licence();
//	 l.setClientId(deviceCommodityPurchasedVO.getClientId());
//	 l.setCommodityCode(deviceCommodityPurchasedVO.getCommodityCode());
//	 l.setLicenceNo(deviceCommodityPurchasedVO.getLicenceNo());
//	 l.setDeviceSerialNo(deviceCommodityPurchasedVO.getDeviceCode());
//	 l.setExpiredOn(deviceCommodityPurchasedVO.getExpiredOn());
//	 l.setTotalScans(deviceCommodityPurchasedVO.getTotalScan());
//	 l.setPackages(packagesRepo.getOne(deviceCommodityPurchasedVO.getPackageId()));
//	 l.setStatus(1);
//	 l.setDeviceType(deviceCommodityPurchasedVO.getDeviceTypeId());
//	 Licence dbRes = licenceRepo.save(l);
//	 if (dbRes != null) {
//	 saveLicenceHistory(dbRes);
//	 return deviceCommodityPurchasedVO;
//	 }
//	 return null;
//	 }

	public void saveLicenceHistory(Licence dbRes) {
		LicenceHistory l = new LicenceHistory();
		l.setClientId(dbRes.getClientId());
		l.setCommodityCode(dbRes.getCommodityCode());
		l.setLicenceNo(dbRes.getLicenceNo());
		l.setDeviceSerialNo(dbRes.getDeviceSerialNo());
		l.setPackages(dbRes.getPackages());
		l.setStatus(1);
		l.setDeviceType(dbRes.getDeviceType());
		licenceHistoryRepo.save(l);

	}

	// public void savePackages(PackagesModel packagesModel) {
	// Packages pack = new Packages();
	// if (packagesModel.getPackageSubscriptionType() != null) {
	// pack.setSubscriptionType(packageSubscriptionTypeRepo.getOne(packagesModel.getPackageSubscriptionType()));
	// }
	// if (packagesModel.getDeviceType() != null) {
	// pack.setDeviceType(devicesTypeRepo.getOne(packagesModel.getDeviceType()));
	// }
	// pack.setCode(UUID.randomUUID().toString());
	// pack.setCreatedBy(packagesModel.getCreatedBy());
	// pack.setPackageName(packagesModel.getPackageName());
	// long now = Instant.now().toEpochMilli();
	// //pack.setStatus(Constants.STATUS.ACTIVE.getId());
	// pack.setCreatedOn(now);
	// packagesRepo.save(pack);
	//
	// }

	// public void updatePackages(PackagesModel packagesModel) {
	// Packages pack = packagesRepo.getOne(packagesModel.getId());
	// if (packagesModel.getPackageSubscriptionType() != null) {
	// pack.setSubscriptionType(packageSubscriptionTypeRepo.getOne(packagesModel.getPackageSubscriptionType()));
	// }
	// if (packagesModel.getDeviceType() != null) {
	// pack.setDeviceType(devicesTypeRepo.getOne(packagesModel.getDeviceType()));
	// }
	// pack.setPackageName(packagesModel.getPackageName());
	// // pack.setStatus(Constants.STATUS.ACTIVE.getId());
	// packagesRepo.save(pack);
	// }

	public List<DeviceTypeVO> getSubscriptionsByClient(Long clientId) {

		List<DeviceCommodityPurchased> subscriptions = deviceCommodityPurchasedRepo.getSubscriptionsByClient(clientId);
		List<DeviceTypeVO> subscriptionList = new ArrayList<>();
		Long deviceTypId = Long.valueOf(0);
		for (DeviceCommodityPurchased l : subscriptions) {
			DeviceTypeVO dcp = new DeviceTypeVO();
			// String deviceName = getDeviceTypeName(dcp.getDeviceTypeId());

			if (deviceTypId != l.getDeviceTypeId()) {
				dcp.setId(l.getDeviceTypeId());
				deviceTypId = l.getDeviceTypeId();
				dcp.setDeviceType(deviceTypeRepo.getName(l.getDeviceTypeId()));
				// dcp.setDevicePrice(deviceCostRepo.getCostByDeviceType(l.getDeviceTypeId()).get(0));
				List<DcmDevice> list = deviceCommodityPurchasedRepo.getSubscriptionsByClientAndDeviceType(clientId,
						l.getDeviceTypeId());
				List<DevicesVO> dList = new ArrayList<>();
				Long deviceId = Long.valueOf(0);
				for (DcmDevice d : list) {

					if (deviceId != d.getId()) {
						DevicesVO dvo = new DevicesVO();
						dvo.setDeviceId(d.getId());
						dvo.setDeviceName(d.getSerialNumber());
						dList.add(dvo);
						deviceId = d.getId();
						List<DeviceCommodityPurchased> commodities = deviceCommodityPurchasedRepo
								.getSubscriptionsByClientAndDeviceId(clientId, d.getId());
						List<PackageCommoditiesPriceVO> pcList = new ArrayList<>();
						for (DeviceCommodityPurchased c : commodities) {
							PackageCommoditiesPriceVO pc = new PackageCommoditiesPriceVO();
							pc.setCommodityId(c.getCommodity().getId());
							pc.setCommodityName(c.getCommodity().getCommodityName());
							pc.setPackagesId(c.getPackageId());
							pc.setLicenceNo(c.getLicenceNo());
							pc.setConsumedScan(c.getConsumedScan());
							pc.setPackagesName(packagesRepo.getName(c.getPackageId()));
							pc.setTotalScans(c.getTotalScans());
							pc.setConsumedScan(c.getConsumedScan());
							if (c.getExpiredOn() != null) {
								pc.setExpiredOn(Utility.formatLocalDateTimeToString(c.getExpiredOn()));
							}
							// pc.setPackagesName(getPackageName(c.getPackageId()));
							// pc.setTotalScans(c.getPackageId());
							pc.setExpiredOn(Utility.formatLocalDateTimeToString(c.getExpiredOn()));
							pcList.add(pc);
							dvo.setPcList(pcList);
							dcp.setDevices(dList);
						}

					}
				}

				subscriptionList.add(dcp);
			}

		}
		return subscriptionList;

	}

	public void buyNewCommodity(LicenceVO licenceVO) {
		List<DcmCommodity> commodityList = commoditiesRepo.findAllById(licenceVO.getCommodities());
		List<DeviceCommodityPurchased> lList = new ArrayList<>();
		Packages pData = packagesRepo.getOne(licenceVO.getPackageId());
		PackagesModel p = EntityToVOAssembler.convertPackage(pData);
		for (Long deviceId : licenceVO.getDevicesId()) {
			for (DcmCommodity c : commodityList) {

				String licenceNo = String.valueOf("AG" + Calendar.getInstance().getTimeInMillis());
				DeviceCommodityPurchased l = new DeviceCommodityPurchased();
				l.setClientId(licenceVO.getClientId());
				l.setCommodity(c);
				l.setLicenceNo(licenceNo);
				if (p.getDurationUnit().equalsIgnoreCase("Year")) {
					LocalDateTime today = LocalDateTime.now();
					LocalDateTime expiryDate = today.plusMonths(p.getDurationPeriod());
					Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
					l.setTotalScans(p.getTotalScans());
				} else if (p.getDurationUnit().equalsIgnoreCase("Month")) {
					LocalDateTime today = LocalDateTime.now();
					LocalDateTime expiryDate = today.plusYears(p.getDurationPeriod());
					Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
					l.setTotalScans(p.getTotalScans());
				}

				// if (p.getPackageName().equalsIgnoreCase("Silver")) {
				// LocalDateTime today = LocalDateTime.now();
				// LocalDateTime tomorrow = today.plusDays(90);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// } else if (p.getPackageName().equalsIgnoreCase("Gold")) {
				// LocalDateTime today = LocalDateTime.now();
				// LocalDateTime tomorrow = today.plusDays(180);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// } else if (p.getPackageName().equalsIgnoreCase("Platinum")) {
				// LocalDateTime today = LocalDateTime.now();
				// LocalDateTime tomorrow = today.plusDays(365);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// } else if (p.getPackageName().equalsIgnoreCase("Diamond")) {
				// LocalDateTime today = LocalDateTime.now();
				// LocalDateTime tomorrow = today.plusDays(730);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// }
				l.setDevice(dcmDeviceRepo.getOne(deviceId));
				l.setPackageId(licenceVO.getPackageId());
				l.setStatus(1);
				l.setDeviceTypeId(licenceVO.getDeviceTypeId());
				lList.add(l);

			}
		}
		List<DeviceCommodityPurchased> dlList = deviceCommodityPurchasedRepo.saveAll(lList);
		if (dlList != null) {
			for (DeviceCommodityPurchased lis : dlList) {
				DeviceCommodityPurchasedVO dv = new DeviceCommodityPurchasedVO();
				dv.setClientId(licenceVO.getClientId());
				dv.setCommodity(lis.getCommodity().getId());
				dv.setLicenseNo(lis.getLicenceNo());
				dv.setDevice(lis.getDevice().getId());
				dv.setExpiredOn(lis.getExpiredOn());
				dv.setTotalScan(lis.getTotalScans());
				dv.setPackageId(lis.getPackageId());
				dv.setDeviceTypeId(lis.getDeviceTypeId());
				dv.setStatus(1);
				// DeviceCommodityPurchasedVO res = saveBuyNewCommodityToLAP(dv);
			}

		}
	}

	private boolean saveDeviceCommodity(Long clientId, LicenceVO licenceVO, Boolean status, Payment paymentDetails) {
		Boolean returnFlag = true;
		List<DcmCommodity> commodityList = commoditiesRepo.findAllById(licenceVO.getCommodities());
		List<DeviceCommodityPurchased> lList = new ArrayList<>();
		Packages pData = packagesRepo.getOne(licenceVO.getPackageId());
		PackagesModel p = EntityToVOAssembler.convertPackage(pData);
		DcmDevice device = filterNativeRepo.getDeviceBySerialNo(licenceVO.getDeviceSerialNo(),
				licenceVO.getDeviceTypeId());
		if (device != null) {
			for (DcmCommodity c : commodityList) {
				// Create Licence Number
				String licenceNo = String.valueOf("AG" + Calendar.getInstance().getTimeInMillis()
						+ licenceVO.getClientId() + c.getId() + device.getId() + Utility.randomStringGenerator(4));
				DeviceCommodityPurchased l = new DeviceCommodityPurchased();
				l.setClientId(licenceVO.getClientId());
				l.setCommodity(c);
				l.setLicenceNo(licenceNo);
				l.setDevice(device);
				l.setPaymentReference(paymentDetails);

				if (p.getDurationUnit().equalsIgnoreCase("Month")) {
					LocalDateTime today = LocalDateTime.now();
					LocalDateTime expiryDate = today.plusMonths(p.getDurationPeriod());
					Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
					l.setTotalScans(p.getTotalScans());
				} else if (p.getDurationUnit().equalsIgnoreCase("Year")) {
					LocalDateTime today = LocalDateTime.now();
					LocalDateTime expiryDate = today.plusYears(p.getDurationPeriod());
					Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
					l.setTotalScans(p.getTotalScans());
				}
				l.setPackageId(licenceVO.getPackageId());
				l.setStatus(0);
				l.setDeviceTypeId(licenceVO.getDeviceTypeId());
				lList.add(l);
			}
		}

		List<DeviceCommodityPurchased> validRecordList = new ArrayList<>();
		for (DeviceCommodityPurchased licenceRecord : lList) {
			final List<DeviceCommodityPurchased> licenceList = deviceCommodityPurchasedRepo
					.findByClientIdAndCommodityAndDeviceAndDeviceTypeIdAndPackageId(licenceRecord.getClientId(),
							licenceRecord.getCommodity(), licenceRecord.getDevice(), licenceRecord.getDeviceTypeId(),
							licenceRecord.getPackageId());
			if (CollectionUtils.isNotEmpty(licenceList)) {
				if (licenceList.size() == 1 && licenceList.get(0).getStatus() == 0) {
					validRecordList.add(licenceRecord);
				} else {
					returnFlag = false;
					logger.error(
							"#############Something Wrong with Records - Please validate DB#######################");
				}
			} else if (CollectionUtils.isEmpty(licenceList)) {
				validRecordList.add(licenceRecord);
			}
			List<DeviceCommodityPurchased> dlList = deviceCommodityPurchasedRepo.saveAll(validRecordList);
		}
		return returnFlag;
	}

	public void saveDeviceCommodityPurchased(Long clientId, LicenceVO licenceVO) {
		// List<Long> commodities= new
		// ArrayList<Long>(Arrays.asList(licenceVO.getCommodityIds()));
		List<DcmCommodity> commodityList = commoditiesRepo.findAllById(licenceVO.getCommodities());
		List<DeviceCommodityPurchased> lList = new ArrayList<>();
		// PackagesVO p = restTemplateCalls.getPackageDetail(licenceVO.getPackageId());
		Packages pData = packagesRepo.getOne(licenceVO.getPackageId());
		PackagesModel p = EntityToVOAssembler.convertPackage(pData);
		// List<DcmDevice> deviceList =
		// filterNativeRepo.getDevices(licenceVO.getDeviceTypeId(),
		// licenceVO.getNumberOfDevices());
		DcmDevice device = filterNativeRepo.getDeviceBySerialNo(licenceVO.getDeviceSerialNo(),
				licenceVO.getDeviceTypeId());
		if (device != null) {
			for (DcmCommodity c : commodityList) {
				String licenceNo = String.valueOf("AG" + Calendar.getInstance().getTimeInMillis()
						+ licenceVO.getClientId() + c.getId() + device.getId());
				DeviceCommodityPurchased l = new DeviceCommodityPurchased();
				l.setClientId(licenceVO.getClientId());
				l.setCommodity(c);
				l.setLicenceNo(licenceNo);
				l.setDevice(device);

				if (p.getDurationUnit().equalsIgnoreCase("Year")) {
					LocalDateTime today = LocalDateTime.now();
					LocalDateTime expiryDate = today.plusMonths(p.getDurationPeriod());
					Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
					l.setTotalScans(p.getTotalScans());
				} else if (p.getDurationUnit().equalsIgnoreCase("Month")) {
					LocalDateTime today = LocalDateTime.now();
					LocalDateTime expiryDate = today.plusYears(p.getDurationPeriod());
					Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
					l.setTotalScans(p.getTotalScans());
				}

				// if (p.getPackageName().equalsIgnoreCase("Silver")) {
				// LocalDateTime today = LocalDateTime.now();
				// today.plusMonths(3);
				// //LocalDateTime tomorrow = today.plusDays(90);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// } else if (p.getPackageName().equalsIgnoreCase("Gold")) {
				// LocalDateTime today = LocalDateTime.now();
				// LocalDateTime tomorrow = today.plusDays(180);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// } else if (p.getPackageName().equalsIgnoreCase("Platinum")) {
				// LocalDateTime today = LocalDateTime.now();
				// LocalDateTime tomorrow = today.plusDays(365);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// } else if (p.getPackageName().equalsIgnoreCase("Diamond")) {
				// LocalDateTime today = LocalDateTime.now();
				// LocalDateTime tomorrow = today.plusDays(730);
				// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
				// l.setTotalScans(p.getTotalScans());
				// }
				l.setPackageId(licenceVO.getPackageId());
				l.setStatus(1);
				l.setDeviceTypeId(licenceVO.getDeviceTypeId());

				lList.add(l);
			}
		}

		List<DeviceCommodityPurchased> dlList = deviceCommodityPurchasedRepo.saveAll(lList);
		if (dlList != null) {
			for (DeviceCommodityPurchased lis : dlList) {
				DeviceCommodityPurchasedVO dv = new DeviceCommodityPurchasedVO();
				dv.setClientId(licenceVO.getClientId());
				dv.setCommodity(lis.getCommodity().getId());
				dv.setLicenseNo(lis.getLicenceNo());
				dv.setDevice(lis.getDevice().getId());
				dv.setExpiredOn(lis.getExpiredOn());
				dv.setTotalScan(lis.getTotalScans());
				dv.setPackageId(lis.getPackageId());
				dv.setDeviceTypeId(lis.getDeviceTypeId());
				dv.setStatus(1);
				// DeviceCommodityPurchasedVO res = saveBuyNewCommodityToLAP(dv);
			}

		}
	}

	public DevicesVO findPackagesByDeviceType(Long deviceType, Long customer_id) {
		// List<PackagesVO> pList = restTempCalls.getAllPackage();
		// List<Packages> pkList = packagesRepo.findAll();
		List<Packages> pkList = packagesRepo.findByDeviceTypeIdAndIsDefaultPackage(deviceType, Boolean.TRUE);

		if (customer_id != null) {
			pkList.addAll(packagesRepo.findByClientIdAndIsDefaultPackage(customer_id, Boolean.FALSE));
		}

		List<PackagesModel> pList = EntityToVOAssembler.convertPackageList(pkList);
		DevicesVO d = new DevicesVO();
		// d.setDeviceTypeName(dviceTypeRepo.getNameById(deviceType));
		d.setDeviceTypeId(deviceType);
		List<PackagesVO> packages = new ArrayList<>();
		List<Long> dIds = dcmDeviceRepo.getByDeviceTypeId(deviceType);
		List<Long> cIds = deviceCommodityAssociationRepo.getCropsIdsByDeviceIds(dIds);
		if (cIds.isEmpty()) {
			cIds = null;
		}

		// d.setDevicePrice(deviceCostRepo.getCostByDeviceType(deviceType).get(0));
		for (PackagesModel p : pList) {
			PackagesVO pckg = new PackagesVO();
			pckg.setDurationPeriod(p.getDurationPeriod());
			pckg.setDurationUnit(p.getDurationUnit());
			pckg.setTotalScans(p.getTotalScans());
			pckg.setPackageName(p.getPackageName());
			pckg.setPackageId(p.getId());
			List<PackageCommoditiesPrice> pcList = packageCommoditiesPriceRepo.findByPackages(p.getId(), cIds);
			List<PackageCommoditiesPriceVO> cpList = new ArrayList<>();
			for (PackageCommoditiesPrice pc : pcList) {
				PackageCommoditiesPriceVO pcp = new PackageCommoditiesPriceVO();
				pcp.setCommodityId(pc.getCommodities().getId());
				pcp.setCommodityName(pc.getCommodities().getCommodityName());
				if (pc != null && pc.getCommodities() != null && pc.getCommodities().getId() != null
						&& pc.getCommodities().getDcmCommodityCategory() != null
						&& pc.getCommodities().getDcmCommodityCategory().getId() != null) {
					pcp.setCategoryId(pc.getCommodities().getDcmCommodityCategory().getId());
					pcp.setCategoryName(pc.getCommodities().getDcmCommodityCategory().getCommodityCategoryName());
				}
				pcp.setPrice(pc.getPrice());
				cpList.add(pcp);
			}
			pckg.setCommodities(cpList);
			packages.add(pckg);
		}
		d.setPackages(packages);
		return d;

	}

	public void renewLicence(DeviceCommodityPurchasedVO deviceCommodityPurchasedVO) {

		System.out.println(" License Number to be renewed :  " + deviceCommodityPurchasedVO.getLicenseNo());
		DeviceCommodityPurchased l = deviceCommodityPurchasedRepo
				.fetchLicenseByLicenseNo(deviceCommodityPurchasedVO.getLicenseNo());

		if (deviceCommodityPurchasedVO.getPackageId() == l.getPackageId()) {
			String unit = packagesRepo.getDurationUnit(l.getPackageId());
			Long totalScan = packagesRepo.getTotalScans(l.getPackageId());
			Long period = Long.parseLong(packagesRepo.getDurationPeriod(l.getPackageId()));
			if (unit.equalsIgnoreCase("Year")) {
				LocalDateTime today = LocalDateTime.now();
				LocalDateTime expiryDate = today.plusYears(period);
				Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
				l.setTotalScans(totalScan);
			} else if (unit.equalsIgnoreCase("Month")) {
				LocalDateTime today = LocalDateTime.now();
				// today.plusYears(Long.parseLong(l.getPackages().getDurationPeriod()));
				LocalDateTime expiryDate = today.plusMonths(period);
				Optional.ofNullable(expiryDate).ifPresent(l::setExpiredOn);
				l.setTotalScans(totalScan + (l.getTotalScans() - l.getConsumedScan()));
			}

			// Packages p=packagesRepo.getOne(id)
			// if (l.getPackages().getPackageName().equalsIgnoreCase("Silver")) {
			// LocalDateTime today = LocalDateTime.now();
			// LocalDateTime tomorrow = today.plusDays(90);
			// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
			// l.setTotalScans(l.getPackages().getTotalScans());
			// } else if (l.getPackages().getPackageName().equalsIgnoreCase("Gold")) {
			// LocalDateTime today = LocalDateTime.now();
			// LocalDateTime tomorrow = today.plusDays(180);
			// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
			// l.setTotalScans(l.getPackages().getTotalScans());
			// } else if (l.getPackages().getPackageName().equalsIgnoreCase("Platinum")) {
			// LocalDateTime today = LocalDateTime.now();
			// LocalDateTime tomorrow = today.plusDays(365);
			// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
			// l.setTotalScans(l.getPackages().getTotalScans());
			// } else if (l.getPackages().getPackageName().equalsIgnoreCase("Diamond")) {
			// LocalDateTime today = LocalDateTime.now();
			// LocalDateTime tomorrow = today.plusDays(730);
			// Optional.ofNullable(tomorrow).ifPresent(l::setExpiredOn);
			// l.setTotalScans(l.getPackages().getTotalScans());
			// }
			l.setPackageId(deviceCommodityPurchasedVO.getPackageId());
			l.setRenewedOn(Utility.getCurrentDateTime());
		}

		deviceCommodityPurchasedRepo.save(l);
		// if (dbRes != null) {
		// saveLicenceHistory(dbRes);
		// // return null;
		// }

	}

	public String saveCustomSubscription(CustomSubscriptionModel customSubscriptionModel) throws IMException {
		String redirectURL = null;
		final RequestContext requestContext = applicationContext.getRequestContext();
		final Long loggedInUserId = requestContext.getUserId();
		final Set<String> roles = requestContext.getRoles();
		Long currentTimeInMillis = Instant.now().toEpochMilli();
		String refId = "REF" + Utility.randomStringGenerator(8);

		if (roles.contains("admin") && customSubscriptionModel != null) {

			// save package details first
			DcmDevice deviceEntity = dcmDeviceRepo
					.findBySerialNumberIgnoreCase(customSubscriptionModel.getDeviceSerialNumber());
			Packages packages = VOToEntityAssembler.convertCustomPackageDetails(customSubscriptionModel, deviceEntity);
			Packages savedPackage = packagesRepo.save(packages);

			// save license purchase and then payment details
			BigDecimal amount = new BigDecimal("1.00");
			Payment paymentDetails = paymentService.setPaymentDetails(refId, loggedInUserId,
					customSubscriptionModel.getCustomerId(), loggedInUserId, currentTimeInMillis, 1,
					currentTimeInMillis, "WEB", amount, "INITITATED");

			LicenceVO licenceVO = new LicenceVO();
			licenceVO.setClientId(customSubscriptionModel.getCustomerId());
			licenceVO.setDeviceTypeId(deviceEntity.getDcmDeviceType().getId());
			licenceVO.setPackageId(savedPackage.getId());
			List<CommodityPriceModel> model = customSubscriptionModel.getCommodity();
			List<Long> commoditiIdList = new ArrayList<>();
			for (CommodityPriceModel m : model) {
				commoditiIdList.add(m.getCommodityId());
			}
			licenceVO.setCommodities(commoditiIdList);
			licenceVO.setDeviceSerialNo(customSubscriptionModel.getDeviceSerialNumber());

			if (saveDeviceCommodity(customSubscriptionModel.getCustomerId(), licenceVO, Boolean.FALSE,
					paymentDetails)) {
				Payment payment = paymentService.setPayment(refId, loggedInUserId,
						customSubscriptionModel.getCustomerId(), loggedInUserId, currentTimeInMillis, 1,
						currentTimeInMillis, "WEB", amount, "INITITATED");
				redirectURL = "http:///23.98.216.140/payment/web?client_id=" + paymentEnvironmentId + "&ref_id=" + refId
						+ "&totalPayment=" + amount;

			} else {
				throw new IMException("DCM161", "Multiple Records for Licence Info Exists - pls contact administrator");
			}

		}
		return redirectURL;

	}

}

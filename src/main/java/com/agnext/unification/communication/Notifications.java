package com.agnext.unification.communication;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.DcmUserDevice;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.NotificationServerVO;
import com.agnext.unification.model.PushNotificationData;
import com.agnext.unification.model.ScansModel;
import com.agnext.unification.repository.nafed.DcmUserDeviceRepository;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class Notifications {
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	DcmUserDeviceRepository dcmUserDeviceRepository;
	
	@Autowired
	UserRepository repository;
	
	
	public void send(ScansModel scanModel) throws IMException, JsonProcessingException {
		try {
//		    	String token = scanModel.getToken();
//		    	System.out.println("token====="+token);
		    	
			System.out.println(" Operator's id  : "+scanModel.getOperatorId());
			System.out.println(" Admin's Id     : "+scanModel.getAdminId());
			System.out.println(" Client's Id    : "+scanModel.getClientId());
			String operatorDeviceToken=null;
			String adminDeviceToken = null;
			String clientDeviceToken="";
			String operatorsAdminId = null;
			
			DcmDevice device=deviceRepository.findBySerialNumberAndUserId(scanModel.getDeviceSerialNum(),scanModel.getOperatorId());
			
			if(device!=null) {
				DcmUserDevice dcmUserDevice =dcmUserDeviceRepository.findByUserId(scanModel.getOperatorId());
				if(dcmUserDevice!=null && dcmUserDevice.getDeviceToken() !=null && !dcmUserDevice.getDeviceToken().isEmpty()) {
					operatorDeviceToken=dcmUserDevice.getDeviceToken();
				}
					
//					 operatorsAdminId = scanModel.getAdminId();
					 System.out.println(" Admin's id : "+scanModel.getAdminId());
					 if(scanModel.getAdminId() != null){
					    DcmUserDevice adminDevice = dcmUserDeviceRepository.findByUserId(scanModel.getAdminId());
					    
					    if(adminDevice!=null && adminDevice.getDeviceToken() !=null && !adminDevice.getDeviceToken().isEmpty()) {
						adminDeviceToken=adminDevice.getDeviceToken();
					    }
					}
					 if(scanModel.getClientId() !=null) {
						 DcmUserDevice clientDevice = dcmUserDeviceRepository.findByUserId(scanModel.getClientId());
						 System.out.println(" clientDevice : "+clientDevice);
						 if(clientDevice !=null) {
						 if(clientDevice.getDeviceToken() !=null && !clientDevice.getDeviceToken().isEmpty()) {
						 clientDeviceToken=clientDevice.getDeviceToken();
						 }
						 }
					 }
				
			}else {
				throw new IMException(Constants.ErrorCode.DEVICE_SERIAL_NUMBER_NOT_EXIST, Constants.ErrorMessage.DEVICE_SERIAL_NUMBER_NOT_EXIST);
			}
			if (operatorDeviceToken != null && !operatorDeviceToken.isEmpty()) {
			    System.out.println("operator device token ====="+operatorDeviceToken);
				NotificationServerVO notificationServerVO = new NotificationServerVO();

				notificationServerVO.setNotificationTypeId(3);

				PushNotificationData pnData = new PushNotificationData();
				pnData.setClient(Constants.ARYA_SERVER_KEY);

				pnData.setData(prepareJSon(scanModel.getScanId(), operatorDeviceToken,device,scanModel.getScanStatusId(),scanModel.getScanStatusDesc()));
				notificationServerVO.setPushNotification(pnData);
				NotificationServerVO nsVO = scanComplete(notificationServerVO);
				
				System.out.println("Push notification successfully sent to operator");
			}
			
			if (adminDeviceToken != null && !adminDeviceToken.isEmpty()) {
			    System.out.println("operator admin device token ====="+adminDeviceToken);
				NotificationServerVO notificationServerVO = new NotificationServerVO();

				notificationServerVO.setNotificationTypeId(3);

				PushNotificationData pnData = new PushNotificationData();
				pnData.setClient(Constants.ARYA_SERVER_KEY);

				pnData.setData(prepareJSon(scanModel.getScanId(), adminDeviceToken, device,scanModel.getScanStatusId(),scanModel.getScanStatusDesc()));
				notificationServerVO.setPushNotification(pnData);
				NotificationServerVO nsVO = scanComplete(notificationServerVO);
				
				System.out.println("Push notification successfully sent to operator's admin");
			}
			if (clientDeviceToken != null && !clientDeviceToken.isEmpty()) {
			    System.out.println("operator admin device token ====="+clientDeviceToken);
				NotificationServerVO notificationServerVO = new NotificationServerVO();

				notificationServerVO.setNotificationTypeId(3);

				PushNotificationData pnData = new PushNotificationData();
				pnData.setClient(Constants.ARYA_SERVER_KEY);

				pnData.setData(prepareJSon(scanModel.getScanId(), clientDeviceToken, device,scanModel.getScanStatusId(),scanModel.getScanStatusDesc()));
				notificationServerVO.setPushNotification(pnData);
				NotificationServerVO nsVO = scanComplete(notificationServerVO);
				
				System.out.println("Push notification successfully sent to Client ");
			}
			
//			amqpTemplate.convertAndSend(exchange, routingkey, scanModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
	
	private NotificationServerVO scanComplete(NotificationServerVO notificationServerVO) {
		String uri = Constants.NOTIFICATION_SERVER_HOST_URL;
		System.out.println("ns : " + notificationServerVO);
		RestTemplate restTemplate = new RestTemplate();
		NotificationServerVO result = restTemplate.postForObject(uri, notificationServerVO, NotificationServerVO.class);
		return result;
	}
	@SuppressWarnings("unchecked")
	private JSONObject prepareJSon(String scanId, String deviceToken, DcmDevice device,Integer scanStatusId,String scanStatusDesc) {
		JSONObject response = new JSONObject();
		JSONObject notification = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject to = new JSONObject();
		data.put("body", " Scan data received successfully !!");
		data.put("tittle","Save data");

		data.put("scan_id", scanId);
		data.put("type_id", device.getDcmDeviceType().getId());
		data.put("type_name", device.getDcmDeviceType().getDeviceTypeDesc());
		data.put("scan_status_id",scanStatusId );
		data.put("scan_status_desc", scanStatusDesc);
		

//		response.put("notification", notification);
		response.put("data", data);
		response.put("to", deviceToken);
		return response;
	}

	
	public String findOperatorAdmin(Long operatorId) {
	    
	    UserEntity operatorEntity = repository.findByUserIdAndStatusStatusId(operatorId, Constants.STATUS.ACTIVE.getId());
	    if(operatorEntity != null){
		UserEntity operatorAdminEntity = repository.findOperatorsAdmin(operatorEntity.getCustomer().getCustomerId());
		return operatorAdminEntity.getUserId().toString();
	    }
	    return null;
	}
	
	public void sendClientStatusChangeNotification(ScansModel scanModel) throws IMException, JsonProcessingException {
		try {

			System.out.println(" Operator's id  : " + scanModel.getOperatorId());
			System.out.println(" Client's Id    : " + scanModel.getClientId());
			String operatorDeviceToken = null;

			DcmDevice device = deviceRepository.findBySerialNumberAndUserId(scanModel.getDeviceSerialNum(),
					scanModel.getOperatorId());

			if (device != null) {
				DcmUserDevice dcmUserDevice = dcmUserDeviceRepository.findByUserId(scanModel.getOperatorId());
				if (dcmUserDevice != null && dcmUserDevice.getDeviceToken() != null
						&& !dcmUserDevice.getDeviceToken().isEmpty()) {
					operatorDeviceToken = dcmUserDevice.getDeviceToken();
				}

			} else {
				throw new IMException(Constants.ErrorCode.DEVICE_SERIAL_NUMBER_NOT_EXIST,
						Constants.ErrorMessage.DEVICE_SERIAL_NUMBER_NOT_EXIST);
			}
			if (operatorDeviceToken != null && !operatorDeviceToken.isEmpty()) {
				System.out.println("operator device token :  " + operatorDeviceToken);
				NotificationServerVO notificationServerVO = new NotificationServerVO();

				notificationServerVO.setNotificationTypeId(3);

				PushNotificationData pnData = new PushNotificationData();
				pnData.setClient(Constants.ARYA_SERVER_KEY);

				pnData.setData(prepareJsonForOperator(scanModel.getScanId(), operatorDeviceToken, device,
						scanModel.getScanStatusId(), scanModel.getScanStatusDesc(), scanModel.getOldScanStatusDesc(),
						scanModel.getNewScanStatusDesc()));
				notificationServerVO.setPushNotification(pnData);
				NotificationServerVO nsVO = scanComplete(notificationServerVO);

				System.out.println("Push notification about status change successfully sent to operator ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private JSONObject prepareJsonForOperator(String scanId, String deviceToken, DcmDevice device, Integer scanStatusId,
			String scanStatusDesc, String oldScanStatusDesc, String newScanStatusDesc) {
		JSONObject response = new JSONObject();
		JSONObject notification = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject to = new JSONObject();
		data.put("body", " Client has changed the status of scan ");
		data.put("tittle", " Status Changed ");

		data.put("scan_id", scanId);
		data.put("type_id", device.getDcmDeviceType().getId());
		data.put("type_name", device.getDcmDeviceType().getDeviceTypeDesc());
		data.put("scan_status_id", scanStatusId);
		data.put("scan_status_desc", scanStatusDesc);

//		response.put("notification", notification);
		response.put("data", data);
		response.put("to", deviceToken);
		return response;
	}

}

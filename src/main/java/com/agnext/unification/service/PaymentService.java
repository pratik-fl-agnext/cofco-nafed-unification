package com.agnext.unification.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.agnext.unification.entity.nafed.Payment;
import com.agnext.unification.model.PaymentResponseModel;
import com.agnext.unification.repository.nafed.PaymentRepository;

@Service
@Transactional
public class PaymentService extends GenericService{

	 private static Logger logger = LoggerFactory.getLogger(PaymentService.class);

	    private final PaymentRepository paymentRepo;

	    public PaymentService(PaymentRepository paymentRepo) {
		this.paymentRepo = paymentRepo;
	    }

	    /**
	     * @param refId
	     * @param creatorId
	     * @param customerId
	     * @param userId
	     * @param currentTime
	     * @param i
	     * @param modifiedTime
	     * @param mode
	     * @param amount
	     * @param paymentStatus
	     * @return
	     */
	    public Payment setPayment(String refId, Long creatorId, long customerId, Long userId, Long currentTime, int i,
		    Long modifiedTime, String mode, BigDecimal amount, String paymentStatus) {
		Payment payment = new Payment();
		payment.setReferenceId(refId);
		payment.setAmount(amount);
		payment.setCreatedBy(creatorId);
		payment.setCustomerId(customerId);
		payment.setUserId(userId);
		payment.setCreatedOn(currentTime);
		payment.setStatus(Boolean.FALSE);
		payment.setModifiedOn(modifiedTime);
		payment.setMode(mode);
		payment.setPaymentStatus(paymentStatus);
		return paymentRepo.save(payment);
	    }

	    public Payment setPaymentDetails(String refId, Long creatorId, long customerId, Long userId, Long currentTime,
		    int i, Long modifiedTime, String mode, BigDecimal amount, String paymentStatus) {
		Payment payment = new Payment();
		payment.setReferenceId(refId);
		payment.setAmount(amount);
		payment.setCreatedBy(creatorId);
		payment.setCustomerId(customerId);
		payment.setUserId(userId);
		payment.setCreatedOn(currentTime);
		payment.setStatus(Boolean.FALSE);
		payment.setModifiedOn(modifiedTime);
		payment.setMode(mode);
		payment.setPaymentStatus(paymentStatus);
		return payment;
	    }

	    public void confirmPaymentFromMicroservice(@RequestBody PaymentResponseModel paymentResponseModel)
		    throws Exception {
		logger.info("confirmPaymentFromMicroservice is being called {} ", paymentResponseModel);
		Payment payment;
		try {
		    payment = paymentRepo.findByReferenceId(paymentResponseModel.getReferenceId());
		    logger.debug("Associated Payment : {}", payment);
		} catch (Exception e1) {
		    e1.printStackTrace();
		    throw new SQLException("AG-003", "Error while getting the record from Payment based on Reference Id.");
		}
		createPaymentFromPaymentResponseModel(payment, paymentResponseModel);
		try {
		    paymentRepo.save(payment);
		    if (paymentResponseModel.getAggregatorStatus().equals("Success")) {
			logger.info("############################################################");
			logger.info("LOGIC for SUCCESS");
			logger.info("############################################################");
		    } else {
			logger.info("############################################################");
			logger.info("LOGIC for Failure");
			logger.info("############################################################");
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new SQLException("AG-003", "Error while updating the Payment and Test Status after Payment.");
		}

	    }

	    private void createPaymentFromPaymentResponseModel(Payment payment, PaymentResponseModel paymentResponseModel) {
		Optional.ofNullable(paymentResponseModel.getOrderId()).ifPresent(payment::setOrderId);
		Optional.ofNullable(paymentResponseModel.isStatus()).ifPresent(payment::setStatus);
		Optional.ofNullable(paymentResponseModel.getAggregatorStatus()).ifPresent(payment::setPaymentStatus);
		Optional.ofNullable(paymentResponseModel.getCcBankRefNo()).ifPresent(payment::setBankRefId);
		Optional.ofNullable(paymentResponseModel.getCcTrackingId()).ifPresent(payment::setTrackingId);
	    }
}

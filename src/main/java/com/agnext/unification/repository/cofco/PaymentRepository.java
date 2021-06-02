package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.Payment;

@Repository("cofcoPaymentRepository")
public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment findByReferenceId(String referenceId);

}

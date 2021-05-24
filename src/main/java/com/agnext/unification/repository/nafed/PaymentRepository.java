package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment findByReferenceId(String referenceId);

}

package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.cofco.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment findByReferenceId(String referenceId);

}

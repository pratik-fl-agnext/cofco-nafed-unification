/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.cofco.CustomerEntity;

/**
 * The Interface CustomerRepository.
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByCustomerId(Long coustomerId);

    CustomerEntity findByCustomerIdAndStatusStatusId(Long coustomerId, Long statusId);

    List<CustomerEntity> findByStatusStatusId(Long id, Pageable pageable);

    CustomerEntity findByCustomerUuidAndStatusStatusId(String customerUuid, Long id);

    CustomerEntity findByCustomerIdAndStatusStatusIdNotIn(Long customerId, Long statusId);

    CustomerEntity findByCustomerEmail(String email);

    List<CustomerEntity> findByStatusStatusIdNotIn(Long statusId, Pageable pageable);

    CustomerEntity findByCustomerEmailAndCustomerIdNotIn(String email, Long customerId);

    CustomerEntity findByCustomerIdAndPartnerIdAndStatusStatusId(Long customerId, Long partnerId, Long id);

    List<CustomerEntity> findByPartnerIdOrderByStatusStatusId(Long customerId);

    CustomerEntity findByCustomerGst(String gst);

    CustomerEntity findByCustomerName(String name);

    CustomerEntity findByCustomerPan(String pan);
    //	Page<CustomerEntity> findByStatusStatusIdNotIn(Long statusId, Pageable pageable);
    //	Page<CustomerEntity> findAllByStatusStatusId(Long statusId, Pageable pageable );

    Long countByStatusStatusIdNotIn(Long statusId);

    Long countByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(Long customerTypeId, Long statusId);

    @Query("select c.customerName from CustomerEntity c where c.customerId=:customerId")
    String findCustomerNameById(@Param("customerId") Long customerId);

    @Query("select c.customerId from CustomerEntity c where c.customerType.customerTypeId=:customerTypeId And c.status.statusId !=:statusId")
    List<Long> findByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(@Param("customerTypeId") Long customerTypeId,
	    @Param("statusId") Long statusId);

    @Query("select count(*) from CustomerEntity c where c.createdBy in (:customerTypeIds) And c.status.statusId !=:statusId")
    Long getCustomersUnderPartnersCount(@Param("customerTypeIds") List<Long> partnerIds,
	    @Param("statusId") Long statusId);

}

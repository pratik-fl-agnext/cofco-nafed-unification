package com.agnext.unification.repository.cofco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.cofco.CustomerEntity;
import com.agnext.unification.entity.cofco.DcmDevice;
import com.agnext.unification.entity.cofco.UserEntity;

/**
 * Filter ColdStore Native Repository
 * 
 * @author VISHAL B.
 * @since 1.0
 */

@Repository
@Transactional
public class FilterNativeRepository {

    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<CustomerEntity> getCustomerDetailByFilters(String keyword, Integer pageNumber, Integer limit,
	    String customerType) {
	StringBuilder sb = new StringBuilder();
	sb.append("select c from CustomerEntity c  WHERE c.status.statusId not in (" + Constants.STATUS.DELETED.getId()
		+ ")");

	if (keyword != null && !keyword.isEmpty()) {
	    sb.append(
		    " AND c.customerName like :keyword  OR  c.customerId IN (select distinct ca.customer.customerId from CustomerAddressEntity ca where ca.addressLine1 like :keyword  OR ca.city like :keyword OR ca.country like :keyword OR  ca.state like :keyword OR ca.zipCode like :keyword)");

	}

	if (customerType != null) {
	    sb.append(" And c.customerType.customerType like :customerType");
	}

	sb.append(" order by c.customerId desc");

	System.out.println(sb.toString());

	Query query = em.createQuery(sb.toString());
	if (keyword != null && !keyword.isEmpty()) {
	    query.setParameter("keyword", "%" + keyword + "%");
	}

	if (customerType != null && !customerType.isEmpty()) {
	    query.setParameter("customerType", "%" + customerType + "%");
	}

	if (pageNumber == null && limit == null) {
	    pageNumber = 0;
	    limit = 10;
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	} else {
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	}
	return query.getResultList();
    }

    public Long getCustomerDetailCount(String keyword, String customerType) {
	StringBuilder sb = new StringBuilder();
	sb.append("select count(c) from CustomerEntity c  WHERE c.status.statusId not in ("
		+ Constants.STATUS.DELETED.getId() + ")");

	if (keyword != null && !keyword.isEmpty()) {
	    sb.append(
		    " And  c.customerName like :keyword  OR c.customerId IN (select distinct ca.customer.customerId from CustomerAddressEntity ca where  ca.addressLine1 like :keyword  OR ca.city like :keyword OR ca.country like :keyword OR  ca.state like :keyword OR ca.zipCode like :keyword)");

	}

	if (customerType != null) {
	    sb.append(" And c.customerType.customerType like :customerType");
	}

	sb.append(" order by c.customerId desc");

	System.out.println(sb.toString());

	Query query = em.createQuery(sb.toString());
	if (keyword != null && !keyword.isEmpty()) {
	    query.setParameter("keyword", "%" + keyword + "%");
	}

	if (customerType != null && !customerType.isEmpty()) {
	    query.setParameter("customerType", "%" + customerType + "%");
	}

	return (Long) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getUserDetailByFilters(Long customerId, String keyword, Integer pageNumber, Integer limit,
	    Long userId, String userType) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select u from UserEntity u left join CustomerEntity c on u.customer.customerId =c.customerId left join UserRoleEntity r on r.userEntity.userId = u.userId WHERE u.userId not in (:userId) and u.status.statusId not in ("
			+ Constants.STATUS.DELETED.getId() + ") and c.customerType.customerTypeId not in("
			+ Constants.SERVICE_PROVIDER_CUSTOMER_TYPE_ID + ")");

	if (customerId != null) {
	    sb.append("and u.customer.customerId IN (:customerId)");
	}

	if (keyword != null && !keyword.isEmpty()) {
	    sb.append(
		    " AND ( u.userFirstName like :keyword OR u.userLastName like :keyword  OR u.userContactNumber like :keyword OR  u.userId IN (select distinct ua.user.userId from UserAddressEntity ua where ua.addressLine1 like :keyword  OR ua.city like :keyword OR ua.country like :keyword OR  ua.state like :keyword OR ua.zipCode like :keyword))");

	}

	if (userType != null && !userType.isEmpty() && userType.equalsIgnoreCase("Operator")) {
	    sb.append(" and r.roleId = 12");
	}

	sb.append(" order by u.userId desc");

	System.out.println(sb.toString());

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	query.setParameter("userId", userId);

	if (keyword != null && !keyword.isEmpty()) {
	    query.setParameter("keyword", "%" + keyword + "%");
	}

	if (pageNumber == null && limit == null) {
	    pageNumber = 0;
	    limit = 10;
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	} else {
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	}
	return query.getResultList();
    }

    public Long getUserDetailCount(Long customerId, String keyword, String userType) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(u) from UserEntity u left join CustomerEntity c on u.customer.customerId =c.customerId left join UserRoleEntity r on r.userEntity.userId = u.userId WHERE u.status.statusId not in ("
			+ Constants.STATUS.DELETED.getId() + ") and c.customerType.customerTypeId not in("
			+ Constants.SERVICE_PROVIDER_CUSTOMER_TYPE_ID + ")");

	if (customerId != null) {
	    sb.append("and u.customer.customerId IN (:customerId)");
	}

	if (userType != null && !userType.isEmpty() && userType.equalsIgnoreCase("Operator")) {
	    sb.append(" and r.roleId = 12");
	}

	if (keyword != null && !keyword.isEmpty()) {
	    sb.append(
		    " AND u.userFirstName like :keyword OR u.userLastName like :keyword  OR u.userContactNumber like :keyword OR  u.userId IN (select distinct ua.user.userId from UserAddressEntity ua where ua.addressLine1 like :keyword  OR ua.city like :keyword OR ua.country like :keyword OR  ua.state like :keyword OR ua.zipCode like :keyword)");

	}
	sb.append(" order by u.userId desc");

	System.out.println(sb.toString());

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (keyword != null && !keyword.isEmpty()) {
	    query.setParameter("keyword", "%" + keyword + "%");
	}

	return (Long) query.getSingleResult();
    }

    public DcmDevice getDeviceBySerialNo(String deviceSerialNo, Long deviceTypeId) {
	StringBuilder sb = new StringBuilder();
	sb.append("select d from DcmDevice d  WHERE d.dcmStatus.id=" + Constants.STATUS.ACTIVE.getId());

	if (deviceSerialNo != null) {
	    sb.append(" AND d.serialNumber like :deviceSerialNo");
	}

	if (deviceTypeId != null) {
	    sb.append(" AND d.dcmDeviceType.id =:deviceTypeId");
	}

	sb.append(" order by d.id desc");

	Query query = em.createQuery(sb.toString());

	if (deviceSerialNo != null && !deviceSerialNo.isEmpty()) {
	    query.setParameter("deviceSerialNo", "%" + deviceSerialNo + "%");
	}

	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}

	return (DcmDevice) query.getSingleResult();
    }

	public List<UserEntity> getStateAdminDetailByFilters(Long customerId, String keyword, Integer pageNumber,
			Integer limit, Long userId) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(
			"select u from UserEntity u left join CustomerEntity c on u.customer.customerId =c.customerId left join UserRoleEntity r on r.userEntity.userId = u.userId WHERE u.userId not in (:userId) and u.status.statusId not in ("
				+ Constants.STATUS.DELETED.getId() + ") and c.customerType.customerTypeId not in("
				+ Constants.SERVICE_PROVIDER_CUSTOMER_TYPE_ID + ")");

		if (customerId != null) {
		    sb.append("and u.customer.customerId IN (:customerId)");
		}

		if (keyword != null && !keyword.isEmpty()) {
		    sb.append(
			    " AND ( u.userFirstName like :keyword OR u.userLastName like :keyword  OR u.userContactNumber like :keyword OR  u.userId IN (select distinct ua.user.userId from UserAddressEntity ua where ua.addressLine1 like :keyword  OR ua.city like :keyword OR ua.country like :keyword OR  ua.state like :keyword OR ua.zipCode like :keyword))");

		}

		 
		    sb.append(" and r.roleId = 14");
		

		sb.append(" order by u.userId desc");

		System.out.println(sb.toString());

		Query query = em.createQuery(sb.toString());

		if (customerId != null) {
		    query.setParameter("customerId", customerId);
		}
		query.setParameter("userId", userId);

		if (keyword != null && !keyword.isEmpty()) {
		    query.setParameter("keyword", "%" + keyword + "%");
		}

		if (pageNumber == null && limit == null) {
		    pageNumber = 0;
		    limit = 10;
		    query.setFirstResult(pageNumber * limit);
		    query.setMaxResults(limit);
		} else {
		    query.setFirstResult(pageNumber * limit);
		    query.setMaxResults(limit);
		}
		return query.getResultList();
		
	}

	public Long getStateAdminDetailCount(Long customerId, String keyword, String userType, Long userId) {
		StringBuilder sb = new StringBuilder();
		sb.append(
			"select count(u) from UserEntity u left join CustomerEntity c on u.customer.customerId =c.customerId left join UserRoleEntity r on r.userEntity.userId = u.userId WHERE u.status.statusId not in ("
				+ Constants.STATUS.DELETED.getId() + ") and c.customerType.customerTypeId not in("
				+ Constants.SERVICE_PROVIDER_CUSTOMER_TYPE_ID + ")");

		if (customerId != null) {
		    sb.append("and u.customer.customerId IN (:customerId)");
		}

		    sb.append(" and r.roleId = 14");
		

		if (keyword != null && !keyword.isEmpty()) {
		    sb.append(
			    " AND u.userFirstName like :keyword OR u.userLastName like :keyword  OR u.userContactNumber like :keyword OR  u.userId IN (select distinct ua.user.userId from UserAddressEntity ua where ua.addressLine1 like :keyword  OR ua.city like :keyword OR ua.country like :keyword OR  ua.state like :keyword OR ua.zipCode like :keyword)");

		}
		sb.append(" order by u.userId desc");

		System.out.println(sb.toString());

		Query query = em.createQuery(sb.toString());

		if (customerId != null) {
		    query.setParameter("customerId", customerId);
		}
		if (keyword != null && !keyword.isEmpty()) {
		    query.setParameter("keyword", "%" + keyword + "%");
		}

		return (Long) query.getSingleResult();
	}
}

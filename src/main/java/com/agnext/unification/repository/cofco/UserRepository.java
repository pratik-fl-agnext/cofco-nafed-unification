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

import com.agnext.unification.entity.cofco.UserEntity;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUserEmail(String userEmail);

	UserEntity findByUserIdAndStatusStatusId(Long userId, Long id);

	List<UserEntity> findByCustomerCustomerIdAndStatusStatusId(Long customerId, Long id);

	List<UserEntity> findByCustomerCustomerUuid(String customerUuid);


	List<UserEntity> findByUserEmailAndStatusStatusId(String userEmail, Long id);

	long countByCustomerCustomerIdAndStatusStatusIdNotIn(Long customerId, Long id);

	List<UserEntity> findByCustomerCustomerIdAndStatusStatusId(Long fetchApplicableCustomerId,
			Pageable pageable,Long id);

	UserEntity findByUserIdAndStatusStatusIdNotIn(Long userId, Long id);

	UserEntity findByUserEmailAndUserIdNotIn(String userEmail, Long userId);

	UserEntity findByStatusStatusIdAndCustomerCustomerId(Long id, Long customerId);
	
	Long countByStatusStatusIdNotIn(Long statusId);

	@Query("select count(*) from UserEntity u where u.customer.customerId in(:customers) and u.status.statusId !=:statusId")
	Long getServiceProviderUser(@Param("customers") List<Long> customers, @Param("statusId") Long statusId);
	
	 @Query("from UserEntity e where e.customer.id = :customerId "
	        	+ "and e.userId = :userId "
	        	+ "and e.status.statusId = 8 ")
	    	UserEntity findUsers(@Param("customerId") Long customerId, @Param("userId") Long userId);
	    	
	    	@Query("from UserEntity e "
	    	      + "left join UserRoleEntity r on r.userEntity.id = e.userId "
	    	      + "where e.customer.id = :customerId "
	    	      + "and r.roleId = 11")
	    	UserEntity findOperatorsAdmin(@Param("customerId") Long customerId);

	    	@Query("select u from UserEntity u where u.customer.customerId in(:customers) and u.status.statusId !=:statusId")
	    	List<UserEntity> getClientsUser(@Param("customers") List<Long> customers, @Param("statusId") Long statusId);

	    	@Query("select e.userId from UserEntity e "
		    	      + "left join UserRoleEntity r on r.userEntity.id = e.userId "
		    	      + "where e.status.statusId !=:statusId "
		    	      + "and r.roleId = 11")
			Long getAdminId(@Param("statusId") Long statusId);
	    	
	    	UserEntity findByCustomerCustomerId(Long customerId);
	    	
	@Query("select case when (count(u) > 0)  then true else false end from UserEntity u join UserRoleEntity r on u.userId=r.userEntity.id where u.userId = :userId and r.roleId = 14")
	Boolean isUserEntityExist(@Param("userId") Long userId);
	    	
}

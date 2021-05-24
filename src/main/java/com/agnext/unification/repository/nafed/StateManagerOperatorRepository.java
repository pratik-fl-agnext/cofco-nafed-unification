package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.StateManagerOperatorsEntity;

public interface StateManagerOperatorRepository extends JpaRepository<StateManagerOperatorsEntity, Long> {

	List<Long> findByStateManagerId(Long stateManagerId);
	@Query("select stateManagerId from StateManagerOperatorsEntity where operatorId =:operatorId")
	Long findStateManagerIdByOperatorId(@Param ("operatorId") Long operatorId);
	
	StateManagerOperatorsEntity findByOperatorId(Long operatorId);
	
	List<StateManagerOperatorsEntity> findBystateManagerIdAndStatusNotIn(Long stateManagerId, Integer status);
}

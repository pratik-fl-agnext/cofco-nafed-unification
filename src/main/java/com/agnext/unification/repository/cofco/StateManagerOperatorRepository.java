package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.StateManagerOperatorsEntity;

@Repository("cofcoStateManagerOperatorRepository")
public interface StateManagerOperatorRepository extends JpaRepository<StateManagerOperatorsEntity, Long> {

	List<Long> findByStateManagerId(Long stateManagerId);
	@Query("select stateManagerId from StateManagerOperatorsEntity where operatorId =:operatorId")
	Long findStateManagerIdByOperatorId(@Param ("operatorId") Long operatorId);
	
	StateManagerOperatorsEntity findByOperatorId(Long operatorId);
}

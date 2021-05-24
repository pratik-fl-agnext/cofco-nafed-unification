package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.StateAdminAndStateEntity;

public interface StateAdminAndStateRepository extends JpaRepository<StateAdminAndStateEntity, Long> {

    StateAdminAndStateEntity findByStateAdminUserId(Long stateAdminId);
    
}

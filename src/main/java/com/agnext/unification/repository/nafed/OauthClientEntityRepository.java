package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.nafed.OauthClientEntity;

@Repository
public interface OauthClientEntityRepository extends JpaRepository<OauthClientEntity, String> {

}

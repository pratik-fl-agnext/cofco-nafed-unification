package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.UserDetails;

@Repository("cofcoUserDetailsRepository")
public interface UserDetailsRepository extends JpaRepository< UserDetails,Long>{

}

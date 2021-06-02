package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.PackageSubscriptionType;

@Repository("cofcoPackageSubscriptionTypeRepository")
public interface PackageSubscriptionTypeRepository  extends JpaRepository<PackageSubscriptionType, Integer>{

}

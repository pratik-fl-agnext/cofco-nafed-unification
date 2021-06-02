package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.PlotEntity;

@Repository("cofcoPlotRepository")
public interface PlotRepository extends JpaRepository<PlotEntity, Long> {

	
    

}

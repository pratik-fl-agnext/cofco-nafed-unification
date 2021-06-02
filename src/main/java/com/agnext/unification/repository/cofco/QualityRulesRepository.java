package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.QualityGradeRules;

public interface QualityRulesRepository extends JpaRepository<QualityGradeRules, Long>{

	@Query("from QualityGradeRules q where q.commodityId=:commodity")
	List<QualityGradeRules> getRuleList(@Param("commodity") Long commodity);	
	
	@Query("select analysisCode from QualityGradeRules q where q.commodityId=:commodity")
	List<String> getName(Long commodity);	

}

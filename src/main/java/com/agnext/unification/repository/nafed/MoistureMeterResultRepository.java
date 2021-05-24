package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.MoistureMeterResult;

public interface MoistureMeterResultRepository extends JpaRepository<MoistureMeterResult, Long> {

	MoistureMeterResult findBySampleId(String sampleId);

	List<MoistureMeterResult> findByClientId(Long operatorId, Pageable pageable);

	@Query("select count(m) from MoistureMeterResult m where  m.clientId= :clientId")
	Long findCountByClientId(@Param("clientId") Long clientId);

	@Query("select m from MoistureMeterResult m where  m.sampleId=:sampleId and m.commodityName=:commodityName")
	List<MoistureMeterResult> findAllBySampleIdAndCommodityName(@Param("sampleId") String sampleId,@Param("commodityName")String commodityName);

	List<MoistureMeterResult> findBySampleIdAndCommodityName(String sampleId,String commodityName);

}

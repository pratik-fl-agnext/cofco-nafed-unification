package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.DcmCommodity;


public interface DcmCommodityRepository extends JpaRepository<DcmCommodity, Long> {

    @Query("from DcmCommodity c where c.commodityName=:commodityName")
    DcmCommodity getCommodityName(@Param("commodityName") String commodityName);

    // @Query("from DcmCommodity c where c.commodityName=:commodityName")
    List<DcmCommodity> findByStatusStatusId(Long id);

    List<DcmCommodity> findByDcmCommodityCategoryIdAndStatusStatusId(Long commodityCategoryId, Long statusId,
	    Pageable pageable);

    List<DcmCommodity> findByIdInAndStatusStatusId(List<Long> commoditiesIds, Long id);

    List<DcmCommodity> findByStatusStatusIdNotIn(Long id);

    @Query("select c.commodityName from DcmCommodity c where c.id=:commodityId")
    String getCommodityName(@Param("commodityId") Long commodityId);
    
    List<DcmCommodity> findByDcmCommodityCategoryIdAndStatusStatusIdAndIdIn(Long commodityCategoryId, Long statusId,List<Long> commodityIds,
    	    Pageable pageable);

	List<DcmCommodity> findByIdIn(List<Long> commodityIds);

	@Query("select c from DcmCommodity c where c.commodityName in (:commoditiesName)")
	List<DcmCommodity> findAllByCommodityName(@Param("commoditiesName") String[] commoditiesName);
}

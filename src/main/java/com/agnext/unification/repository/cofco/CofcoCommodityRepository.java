package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CofcoCommodityEntity;
import com.agnext.unification.repository.BaseRepository;

@Repository("cofcoCommodityRepository")
public interface CofcoCommodityRepository extends BaseRepository<CofcoCommodityEntity> {

    @Query("from CofcoCommodityEntity c where c.commodityName=:commodityName")
    CofcoCommodityEntity getCommodityName(@Param("commodityName") String commodityName);

    // @Query("from CofcoCommodityEntity c where c.commodityName=:commodityName")
    List<CofcoCommodityEntity> findByStatusStatusId(Long id);

    List<CofcoCommodityEntity> findByDcmCommodityCategoryIdAndStatusStatusId(Long commodityCategoryId, Long statusId,
	    Pageable pageable);

    List<CofcoCommodityEntity> findByIdInAndStatusStatusId(List<Long> commoditiesIds, Long id);

    List<CofcoCommodityEntity> findByStatusStatusIdNotIn(Long id);

    @Query("select c.commodityName from CofcoCommodityEntity c where c.id=:commodityId")
    String getCommodityName(@Param("commodityId") Long commodityId);
    
    List<CofcoCommodityEntity> findByDcmCommodityCategoryIdAndStatusStatusIdAndIdIn(Long commodityCategoryId, Long statusId,List<Long> commodityIds,
    	    Pageable pageable);

	List<CofcoCommodityEntity> findByIdIn(List<Long> commodityIds);

	@Query("select c from CofcoCommodityEntity c where c.commodityName in (:commoditiesName)")
	List<CofcoCommodityEntity> findAllByCommodityName(@Param("commoditiesName") String[] commoditiesName);
}

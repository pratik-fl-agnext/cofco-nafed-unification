package com.agnext.unification.repository.nafed;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.DcmCommodityCategory;

public interface DcmCommodityCategoryRepository extends JpaRepository<DcmCommodityCategory, Long> {

	Optional<DcmCommodityCategory> findByIdAndStatusStatusId(Long categoryId, Long id);

	@Query("Select cc from DcmCommodityCategory cc where cc.id IN(:categoryIds) ")
	List<DcmCommodityCategory> findAllCategoryById(@Param("categoryIds") List<Long> categoryIds);

}

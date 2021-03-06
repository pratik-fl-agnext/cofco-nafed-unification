package com.agnext.unification.repository.cofco;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CofcoCommodityCategoryEntity;

@Repository("cofcoCommodityCategoryRepository")
public interface CofcoCommodityCategoryRepository extends JpaRepository<CofcoCommodityCategoryEntity, Long> {

	Optional<CofcoCommodityCategoryEntity> findByIdAndStatusStatusId(Long categoryId, Long id);

	@Query("Select cc from CofcoCommodityCategoryEntity cc where cc.id IN(:categoryIds) ")
	List<CofcoCommodityCategoryEntity> findAllCategoryById(@Param("categoryIds") List<Long> categoryIds);

}

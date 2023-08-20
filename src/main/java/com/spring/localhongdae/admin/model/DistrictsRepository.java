package com.spring.localhongdae.admin.model;

import com.spring.localhongdae.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictsRepository extends JpaRepository<District, Integer> {
    @Query("select di from districts di where di.fk_city_id = :city_id")
    List<District> findAllByCity_id(@Param("city_id") int city_id);

    @Query("select di from districts  di where di.district_id = :district_id")
    District findByDistricId(@Param("district_id") int districtId);
}

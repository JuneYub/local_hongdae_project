package com.spring.localhongdae.admin.model;

import com.spring.localhongdae.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    @Query(value = "SELECT * FROM places p where p.fk_district_id = :fk_district_id and p.place_name like CONCAT('%', :name, '%')", nativeQuery = true)
    List<Place> findByDistrictIdAndPlaceName(@Param("fk_district_id") int fk_district_id, @Param("name") String place_name);
}

package com.spring.localhongdae.admin.model;

import com.spring.localhongdae.dto.PlaceDto;
import com.spring.localhongdae.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    @Query(value = "SELECT * FROM places p where p.fk_district_id = :fk_district_id and p.place_name like CONCAT('%', :name, '%')", nativeQuery = true)
    List<Place> findByDistrictIdAndPlaceName(@Param("fk_district_id") int fk_district_id, @Param("name") String place_name);

    @Query(value = "SELECT new com.spring.localhongdae.dto.PlaceDto(p.place_id, p.fk_district_id, p.place_name, p.latitude, p.longitude, p.image_id, COUNT(vh.fk_place_id)) " +
                   "FROM places p " +
                   "LEFT JOIN VisitHistory vh ON p.place_id = vh.fk_place_id " +
                   "WHERE p.fk_district_id =:districtId " +
                   "GROUP BY p.place_id, p.fk_district_id, p.place_name, p.latitude, p.longitude, p.image_id " +
                   "ORDER BY p.place_id DESC", nativeQuery = false)
    Page<PlaceDto> findByDistrictId(PageRequest pageRequest, @Param("districtId")int districtId);

    @Query(value = "SELECT * FROM places p where p.place_id =:placeId", nativeQuery = true)
    Place findByPlaceId(@Param("placeId") int placeId);


}

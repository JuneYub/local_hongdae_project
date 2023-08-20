package com.spring.localhongdae.restaurant.service;

import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.Place;
import com.spring.localhongdae.dto.DistrictDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterRestaurant {
    // 도시 목록을 조회
    List<City> getCities();

    // 지역 목록을 조회
    List<District> getDistricts(int cityId);

    // 음식점 페이지 조회
    Page<Place> getPageByDistrictId(HttpServletRequest request, Pageable pageable);
    
    // 지역 이름으로 지역 정보 조회
    DistrictDto getDistrictInfoByDistrictId(int districtId);
}

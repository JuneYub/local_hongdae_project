package com.spring.localhongdae.restaurant.service;

import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;

import java.util.List;

public interface InterRestaurant {
    // 도시 목록을 조회
    List<City> getCities();

    // 지역 목록을 조회
    List<District> getDistricts(int cityId);
}

package com.spring.localhongdae.restaurant.service;

import com.spring.localhongdae.admin.model.CityRepository;
import com.spring.localhongdae.admin.model.DistrictsRepository;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestaurantService implements InterRestaurant{

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictsRepository districtsRepository;

    @Override
    public List<City> getCities() {
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    @Override
    public List<District> getDistricts(int cityId) {
        List<District> districts = districtsRepository.findAllByCity_id(cityId);
        return districts;
    }
}

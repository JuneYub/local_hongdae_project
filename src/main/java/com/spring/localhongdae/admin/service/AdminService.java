package com.spring.localhongdae.admin.service;

import com.spring.localhongdae.admin.model.CityRepository;
import com.spring.localhongdae.admin.model.DistrictsRepository;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements InterAdminService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictsRepository districtsRepository;

    @Override
    public List<City> getCities() {
        List<City> cities = cityRepository.findAllBy();
        return cities;
    }

    @Override
    public List<District> getDistrictsById(int cityId) {
        List<District> districts = districtsRepository.findAllByCity_id(cityId);
        return districts;
    }
}

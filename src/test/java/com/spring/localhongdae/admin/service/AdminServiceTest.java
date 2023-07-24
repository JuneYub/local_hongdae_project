package com.spring.localhongdae.admin.service;

import com.spring.localhongdae.admin.model.CityRepository;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.awt.SystemColor.info;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CityRepository cityRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);

    @Test
    @DisplayName("Cities 조회 테스트")
    void getCitiesTest() throws Exception {
        List<City> list = adminService.getCities();
        for (City city: list) {
            logger.info(city.getCity_name());
        }
    }



}
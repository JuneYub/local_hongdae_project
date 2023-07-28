package com.spring.localhongdae.admin.service;

import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface InterAdminService {
    // 도시목록을 조회
    List<City> getCities();

    // 도시 id를 통해 구를 조회
    List<District> getDistrictsById(int cityId);
    
    // 처음 음식점을 등록
    int registerRestaurant(MultipartHttpServletRequest mrequest) throws IOException;
}

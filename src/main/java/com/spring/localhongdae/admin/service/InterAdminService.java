package com.spring.localhongdae.admin.service;

import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.Place;
import jakarta.servlet.http.HttpServletRequest;
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

    // 방문 정보를 등록
    void registerVisitHistory(HttpServletRequest request);

    // 음식점 이름 입력시 자동으로 검색해주는 기능
    List<Place> findPlaceList(HttpServletRequest request);
}

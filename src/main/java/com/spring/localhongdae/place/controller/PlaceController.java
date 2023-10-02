package com.spring.localhongdae.place.controller;

import com.spring.localhongdae.dto.DistrictDto;
import com.spring.localhongdae.dto.PlaceDto;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.Place;
import com.spring.localhongdae.place.service.PlaceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class PlaceController {

    @Autowired
    PlaceServiceImpl restaurantService;

    @GetMapping("/restaurant/list")
    public ModelAndView getResturantList(HttpServletRequest request, @PageableDefault(page = 1, size = 5)Pageable pageable) {
        ModelAndView model = new ModelAndView();
        List<City> cities = restaurantService.getCities();
        Page<PlaceDto> placePage = restaurantService.getPageByDistrictId(request, pageable);

        if(placePage != null && placePage.getTotalElements() != 0) {
            // 페이지 바 만들기
            int blockSize = 5;
            int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockSize))) - 1) * blockSize + 1;
            int endPage = Math.min(startPage + blockSize - 1, placePage.getTotalPages());
            model.addObject("startPage", startPage);
            model.addObject("endPage", endPage);
        }

        // 지역 정보 조회
        DistrictDto district = new DistrictDto();
        if(request.getParameter("selectedDistrict") != null && !"".equals(request.getParameter("selectedDistrict"))) {
             district = restaurantService.getDistrictInfoByDistrictId(Integer.parseInt(request.getParameter("selectedDistrict")));
        }
        else {
            // 서울시청 위도경도
            district.setLatitude(37.56618444304983);
            district.setLongitude(126.97836380791748);
        }

        model.addObject("district", district);
        model.addObject("selectedCity",request.getParameter("selectedCity"));
        model.addObject("selectedDistrict",request.getParameter("selectedDistrict"));
        model.addObject("cities", cities);
        model.addObject("placePage", placePage);

        model.setViewName("restaurant/restaurant-list");
        return model;
    }

    @GetMapping("/restaurant/detail")
    public ModelAndView getResturantDetail(@RequestParam(value = "placeId") int placeId) {
        ModelAndView model = new ModelAndView();
        PlaceDto placeDto = restaurantService.getPlaceById(placeId);
        model.setViewName("restaurant/restaurant-detail");
        model.addObject("placeDto", placeDto);
        return model;
    }

    @GetMapping("/restaurant/getDistrictList")
    @ResponseBody
    public List<District> getDistrictList(@RequestParam(value = "cityId") int cityId) {
        List<District> districts = restaurantService.getDistricts(cityId);
        return districts;
    }

}

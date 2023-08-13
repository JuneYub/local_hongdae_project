package com.spring.localhongdae.restaurant.controller;

import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.Place;
import com.spring.localhongdae.restaurant.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurant/list")
    public ModelAndView getResturantList(HttpServletRequest request, @PageableDefault(page = 1, size = 5)Pageable pageable) {
        ModelAndView model = new ModelAndView();
        List<City> cities = restaurantService.getCities();
        Page<Place> placePage = restaurantService.getPageByDistrictId(request, pageable);

        // 페이지 바 만들기
        int blockSize = 5;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockSize))) - 1) * blockSize + 1;
        int endPage = Math.min(startPage + blockSize - 1, placePage.getTotalPages());

        model.addObject("selectedCity",request.getParameter("selectedCity"));
        model.addObject("selectedDistrict",request.getParameter("selectedDistrict"));
        model.addObject("cities", cities);
        model.addObject("placePage", placePage);
        model.addObject("startPage", startPage);
        model.addObject("endPage", endPage);
        model.setViewName("restaurant/restaurant-list");
        return model;
    }

    @GetMapping("/restaurant/detail")
    public ModelAndView getResturantDetail() {
        ModelAndView model = new ModelAndView();
        model.setViewName("restaurant/restaurant-detail");
        return model;
    }

    @GetMapping("/restaurant/getDistrictList")
    @ResponseBody
    public List<District> getDistrictList(@RequestParam(value = "cityId") int cityId) {
        List<District> districts = restaurantService.getDistricts(cityId);
        return districts;
    }

}

package com.spring.localhongdae.restaurant.controller;

import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ModelAndView getResturantList() {
        ModelAndView model = new ModelAndView();
        List<City> cities = restaurantService.getCities();

        model.addObject("cities", cities);
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

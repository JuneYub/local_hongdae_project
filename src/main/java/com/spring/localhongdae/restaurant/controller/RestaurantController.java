package com.spring.localhongdae.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantController {

    @GetMapping("/restaurant/list")
    public ModelAndView getList() {
        ModelAndView model = new ModelAndView();
        model.setViewName("restaurant/restaurant-list");
        return model;
    }
}

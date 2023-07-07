package com.spring.localhongdae.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

    @GetMapping("/restaurant/list")
    public String getList() {
        return "restaurant-list";
    }
}

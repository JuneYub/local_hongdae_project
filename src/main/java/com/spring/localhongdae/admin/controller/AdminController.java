package com.spring.localhongdae.admin.controller;

import com.spring.localhongdae.admin.service.AdminService;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("admin/login")
    public ModelAndView getAdminLogin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/admin-login");
        return model;
    }

    @GetMapping("admin/dashboard")
    public ModelAndView getDashboard() {
        ModelAndView model = new ModelAndView();
        List<City> cities = adminService.getCities();
        model.addObject("cities", cities);
        model.setViewName("admin/dashboard");
        return model;
    }

    @PostMapping("admin/getDistricts")
    @ResponseBody
    public void getDistricts(@RequestBody HashMap<String, Object> map) {
        System.out.println(map.get("cityId"));
        int cityId = Integer.parseInt(map.get("cityId").toString());
        List<District> districts = adminService.getDistrictsById(cityId);

    }
}

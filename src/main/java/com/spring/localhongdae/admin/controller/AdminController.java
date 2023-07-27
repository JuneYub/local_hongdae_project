package com.spring.localhongdae.admin.controller;

import com.spring.localhongdae.admin.service.AdminService;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
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
    public List<District> getDistricts(@RequestBody HashMap<String, Object> map) {
        int cityId = Integer.parseInt(map.get("cityId").toString());
        List<District> districts = adminService.getDistrictsById(cityId);
        return districts;
    }

    @PostMapping("admin/registerRestaurant")
    @ResponseBody
    public String registerRestaurant(MultipartHttpServletRequest mrequest) throws IOException {
        HttpSession session = mrequest.getSession();
        ServletContext context = session.getServletContext();
        adminService.registerRestaurant(mrequest);
        return "테스트";
    }
}

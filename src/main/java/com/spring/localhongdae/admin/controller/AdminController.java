package com.spring.localhongdae.admin.controller;

import com.spring.localhongdae.admin.service.AdminService;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.Place;
import com.spring.localhongdae.entity.VisitHistory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    // 음식점 처음 방문 기록 등록하기
    @PostMapping("admin/registerRestaurant")
    @ResponseBody
    public String registerRestaurant(MultipartHttpServletRequest mrequest) throws IOException {
        HttpSession session = mrequest.getSession();
        ServletContext context = session.getServletContext();

        // 음시점이 이미 등록되어 있는지 확인
        boolean isVisitPlace = adminService.isVisitPlace(mrequest);
        int result = 0;

        if(isVisitPlace) {
            result = -1;
        }

        // 음식점이 등록되어 있지 않은 경우 정상적으로 등록 과정 진행
        if(!isVisitPlace) {
            result = adminService.registerRestaurant(mrequest);
        }
        log.info("출력 result" + String.valueOf(result));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", String.valueOf(result));
        return jsonObject.toString();
    }

    // 음식점 방문 기록 추가하기
    @PostMapping("admin/registerVisitHistory")
    @ResponseBody
    public String registerVisitHistory(HttpServletRequest request) {

        VisitHistory visitHistory =
                VisitHistory.builder()
                        .fk_place_id(Integer.parseInt(request.getParameter("vh_restaurantId")))
                        .visit_date(request.getParameter("vh_visitDay")) // yyyy-MM-dd
                        .visitors(Integer.parseInt(request.getParameter("vh_visitorsNum")))
                        .spent_money(Integer.parseInt(request.getParameter("vh_spendMoney").toString().replace(",","")))
                        .build();

        int result = adminService.registerVisitHistory(visitHistory);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", String.valueOf(result));
        return jsonObject.toString();
    }

    @GetMapping("admin/findPlace")
    @ResponseBody
    public List<Place> findPlaceList(HttpServletRequest request) {
        List<Place> placeList = adminService.findPlaceList(request);
        return placeList;
    }

}

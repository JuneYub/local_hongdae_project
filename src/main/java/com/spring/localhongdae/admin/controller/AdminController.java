package com.spring.localhongdae.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping("admin/login")
    public ModelAndView getAdminLogin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/admin-login");
        return model;
    }

    @GetMapping("admin/dashboard")
    public ModelAndView getDashboard() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/dashboard");
        return model;
    }
}

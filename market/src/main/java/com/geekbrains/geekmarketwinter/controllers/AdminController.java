package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showAdminDashboard(Model model) {
        model.addAttribute("users", registrationService.getAllUsers());
        return "admin-panel";
    }
}

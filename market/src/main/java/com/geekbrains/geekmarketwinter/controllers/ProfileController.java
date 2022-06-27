package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.services.RegistrationService;
import contract.entities.SystemUser;
import contract.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showAdminDashboard(Model model, Principal principal) {
        model.addAttribute("user", registrationService.getUser(principal.getName()));
        model.addAttribute("listRoles", registrationService.getListOfRoles());
        return "profile";
    }
}

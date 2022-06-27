package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.services.RegistrationService;
import contract.entities.Role;
import contract.entities.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("systemUser", registrationService.getSystemUser());
        theModel.addAttribute("listRoles", registrationService.getListOfRoles());
        return "registration-form";
    }

    // Binding Result после @ValidModel !!!
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser SystemUser, BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            model.addAttribute("listRoles", registrationService.getListOfRoles());
            return "registration-form";
        }
        String userName = registrationService.saveUser(SystemUser);
        if (userName == null) {
            // SystemUser.setUserName(null);
            model.addAttribute("systemUser", SystemUser);
            model.addAttribute("registrationError", "User with current username already exists");
            logger.debug("User name already exists.");
            return "registration-form";
        }
        logger.debug("Successfully created user: " + userName);
        return "registration-confirmation";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("systemUser", registrationService.getSystemUserById(id));
        model.addAttribute("listRoles", registrationService.getListOfRoles());
        return "registration-form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        registrationService.deleteUser(id);
        return "redirect:/admin";
    }
}

package com.example.userservice.controllers;

import com.example.userservice.services.RoleService;
import com.example.userservice.services.UserService;
import contract.entities.Role;
import contract.entities.SystemUser;
import contract.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationControllerImpl implements RegistrationController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public SystemUser getSystemUser() {
        return new SystemUser();
    }

    @Override
    public String saveUser(SystemUser systemUser) {

        String userName = systemUser.getUserName();

        if (systemUser.getId() != null) {
            userService.save(systemUser);
            return userName;
        }

        User existing = userService.findByUserName(userName);
        if (existing != null) {
            return null;
        }

        userService.save(systemUser);
        return userName;
    }

    @Override
    public User getUser(String userName) {
        return userService.findByUserName(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public SystemUser getSystemUserById(long id) {
        return userService.getSystemUserById(id);
    }

    @Override
    public void deleteUser(long id) {
        userService.deleteUser(id);
    }

    @Override
    public List<Role> getListOfRoles() {
        return roleService.getListOfRoles();
    }
}

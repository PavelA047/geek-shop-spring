package com.geekbrains.geekmarketwinter.services;

import contract.entities.Role;
import contract.entities.SystemUser;
import contract.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service-client", contextId = "user-service")
public interface RegistrationService {

    @GetMapping("/register/getSystemUser")
    SystemUser getSystemUser();

    @PostMapping("/register/saveUser")
    String saveUser(@RequestBody SystemUser systemUser);

    @GetMapping("register/getUserById")
    User getUser(@RequestParam String userName);

    @GetMapping("/register/get_all_users")
    List<User> getAllUsers();

    @GetMapping("/register/getSystemUserBuId")
    SystemUser getSystemUserById(@RequestParam long id);

    @DeleteMapping("/registration/deleteUser")
    void deleteUser(@RequestParam long id);

    @GetMapping("/register/getListOfRoles")
    List<Role> getListOfRoles();
}

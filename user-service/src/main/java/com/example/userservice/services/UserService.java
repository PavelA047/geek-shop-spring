package com.example.userservice.services;

import contract.entities.SystemUser;
import contract.entities.User;

import java.util.List;

public interface UserService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
    List<User> getAllUsers();
    SystemUser getSystemUserById(long id);
    void deleteUser(long id);
    User getUserById(long id);
}

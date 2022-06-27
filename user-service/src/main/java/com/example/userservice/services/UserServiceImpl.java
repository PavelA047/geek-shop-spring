package com.example.userservice.services;

import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import contract.entities.Role;
import contract.entities.SystemUser;
import contract.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByUserName(String username) {
        return userRepository.findOneByUserName(username);
    }

    @Override
    @Transactional
    public boolean save(SystemUser systemUser) {

        if (systemUser.getId() != null) {
            User oldUser = userRepository.findById(systemUser.getId()).orElse(null);
            changeUser(oldUser, systemUser);
            userRepository.save(oldUser);
            return true;
        }

        User user = new User();
        changeUser(user, systemUser);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public SystemUser getSystemUserById(long id) {
        User userEntity = userRepository.findById(id).orElse(null);
        SystemUser newSystemUser = new SystemUser();
        newSystemUser.setId(id);
        newSystemUser.setUserName(userEntity.getUserName());
        newSystemUser.setFirstName(userEntity.getFirstName());
        newSystemUser.setLastName(userEntity.getLastName());
        newSystemUser.setEmail(userEntity.getEmail());
        return newSystemUser;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    private void changeUser(User user, SystemUser systemUser) {
        user.setUserName(systemUser.getUserName());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());

        List<Role> roleListForUser = new ArrayList<>();

        for (Role role : roleRepository.findAll()) {
            for (Role userRole : systemUser.getRoles()) {
                if (role.getName().equals(userRole.getName())) roleListForUser.add(role);
            }
        }

        user.setRoles(roleListForUser);
    }
}

package com.geekbrains.geekmarketwinter.repositories;

import contract.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryForSecurity extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
}

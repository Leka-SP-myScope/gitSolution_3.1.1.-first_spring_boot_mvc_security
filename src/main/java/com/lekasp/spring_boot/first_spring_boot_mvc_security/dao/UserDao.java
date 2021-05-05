package com.lekasp.spring_boot.first_spring_boot_mvc_security.dao;

import com.lekasp.spring_boot.first_spring_boot_mvc_security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUser();

    Optional<User> getUserByName(String name);

    Optional<User> findById(Long id);

    void saveUser(User user);

    //void updateUser(User user);

    void deleteById(Long id);
}
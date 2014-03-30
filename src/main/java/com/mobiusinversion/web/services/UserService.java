package com.mobiusinversion.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobiusinversion.web.entities.User;
import com.mobiusinversion.web.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Integer createUser(User user) {
        return userRepository.createUser(user);
    }

}
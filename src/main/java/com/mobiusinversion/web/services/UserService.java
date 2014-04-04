package com.mobiusinversion.web.services;

import com.mobiusinversion.web.entities.User;
import com.mobiusinversion.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    public Integer createUser(User user) {
        return this.userRepository.createUser(user);
    }

}
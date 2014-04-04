package com.mobiusinversion.web.controllers;

import com.mobiusinversion.web.entities.User;
import com.mobiusinversion.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Welcome to Spring 4 MVC");
        return "welcome";
    }

    @ResponseBody
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public User createUser() {
        User user = new User(1,"David");
        userService.createUser(user);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

}
package com.dailycode.k8s.controller;

import com.dailycode.k8s.model.User;
import com.dailycode.k8s.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserControllerV2.class.getSimpleName());
    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user) {
        logger.info("adding a new user...");
        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody User user) {
        logger.info("updating user with new set of data...");
        user.setId(userId);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        logger.info("Deleting user with userId {}", userId);
        userService.deleteUser(userId);
        return "Deleted user successfully...";
    }

    @GetMapping
    public List<User> getUsers() {
        logger.info("Fetching all users from database...");
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Integer userId) {
        logger.info("get user details by the user Id {}", userId);
        return userService.getUser(userId);
    }

    @GetMapping("/by/{username}")
    public User getUserByName(@PathVariable String username) {
        logger.info("get user details by the user name {}", username);
        return userService.getUserByName(username);
    }
}

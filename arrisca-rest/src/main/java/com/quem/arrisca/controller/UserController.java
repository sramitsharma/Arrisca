package com.quem.arrisca.controller;

import com.quem.arrisca.model.User;
import com.quem.arrisca.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/createUser")
    public User createUser(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        User result;
        result = userService.saveUser(new User(null, username, password));
        return result;
    }
}

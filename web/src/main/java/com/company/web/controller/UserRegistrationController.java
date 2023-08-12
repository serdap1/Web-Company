package com.company.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.web.entity.User;
import com.company.web.service.UserRegistrationService;

@Controller
public class UserRegistrationController {
    @Autowired(required = true)
    private UserRegistrationService userRegistrationService;

    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/api/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userRegistrationService.isEmailAvailable(user.getEmail()) == true) {
            userRegistrationService.registerUser(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

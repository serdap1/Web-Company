package com.company.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.company.web.entity.User;
import com.company.web.service.UserManagementService;
import com.company.web.service.UserRegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserManagementController {
    private UserManagementService userManagementService;
    private UserRegistrationService userRegistrationService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userManagementService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

    public String saveUser(@ModelAttribute("user") User user) {
        if (userRegistrationService.isEmailAvailable(user.getEmail()) == true) {
            userRegistrationService.registerUser(user);
            return "redirect:/users";
        } else {
            return null;
        }
    }
}

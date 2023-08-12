package com.company.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.web.entity.User;
import com.company.web.repository.UserRepository;
import com.company.web.service.BlogService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserLoginController {

    @Autowired(required = true)
    private UserRepository userRepository;

    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("blog", blogService.getLatestBlog());
        return "index";
    }

    @PostMapping("/api/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User userData = userRepository.findByEmail(user.getEmail());
        if (userData != null && userData.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

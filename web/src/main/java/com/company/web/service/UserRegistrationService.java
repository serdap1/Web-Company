package com.company.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.entity.User;
import com.company.web.repository.UserRepository;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean isEmailAvailable(String email) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }
}

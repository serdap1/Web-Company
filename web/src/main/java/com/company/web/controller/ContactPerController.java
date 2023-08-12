package com.company.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.web.service.ContactPersonService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ContactPerController {
    private ContactPersonService contactPersonService;

    @GetMapping("/contacts")
    public String listUsers(Model model) {
        model.addAttribute("contact", contactPersonService.getAllContactPerson());
        return "contact";
    }
}

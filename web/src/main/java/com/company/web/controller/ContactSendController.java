package com.company.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.web.entity.ContactPerson;
import com.company.web.service.ContactSendService;

@Controller
public class ContactSendController {
    private final ContactSendService contactSendService;

    // Constructor Injection
    @Autowired
    public ContactSendController(ContactSendService contactSendService) {
        this.contactSendService = contactSendService;
    }

    @GetMapping("/contact-send")
    public String showContactForm(Model model) {
        ContactPerson contactPerson = new ContactPerson();
        model.addAttribute("contactPerson", contactPerson);
        return "index";
    }

    @PostMapping("/api/contacts")
    public ResponseEntity<ContactPerson> registerUser(@RequestBody ContactPerson contactPerson) {
        System.out.println(contactPerson.toString());
        contactSendService.registerContact(contactPerson);
        return ResponseEntity.ok(contactPerson);
    }
}

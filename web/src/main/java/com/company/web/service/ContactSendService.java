package com.company.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.entity.ContactPerson;
import com.company.web.repository.ContactPersonRepository;

@Service
public class ContactSendService {

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    public void registerContact(ContactPerson contactPerson) {
        contactPersonRepository.save(contactPerson);
    }
}

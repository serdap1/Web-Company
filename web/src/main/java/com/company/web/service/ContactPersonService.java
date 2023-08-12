package com.company.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.entity.ContactPerson;
import com.company.web.repository.ContactPersonRepository;

@Service
public class ContactPersonService {
    @Autowired
    private ContactPersonRepository contactPersonRep;

    public List<ContactPerson> getAllContactPerson() {
        return contactPersonRep.findAll();
    }
}

package com.company.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.entity.Faq;
import com.company.web.repository.FaqRepository;

@Service
public class FaqService {
	@Autowired
	FaqRepository faqRepository;

	public List<Faq> getAllFaq() {

		return faqRepository.findAll();
	}

}

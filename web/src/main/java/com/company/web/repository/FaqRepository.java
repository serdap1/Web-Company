package com.company.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.company.web.entity.Faq;


@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {

}

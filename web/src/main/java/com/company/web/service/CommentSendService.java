package com.company.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.entity.CommentBlog;
import com.company.web.repository.CommentRepository;

@Service
public class CommentSendService {

    @Autowired
    private CommentRepository commentRep;

    public void save(CommentBlog comment) {
        commentRep.save(comment);
    }
}

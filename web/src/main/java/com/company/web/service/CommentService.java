package com.company.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.repository.CommentRepository;
import com.company.web.entity.CommentBlog;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<CommentBlog> getAllCommentsByBlog(Integer blogId) {
        return commentRepository.findAllByBlogId(blogId);
    }
}

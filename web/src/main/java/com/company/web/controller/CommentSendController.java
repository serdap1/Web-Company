package com.company.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.web.entity.CommentBlog;
import com.company.web.service.CommentSendService;

@Controller
public class CommentSendController {
    private CommentSendService commentSendService;

    @Autowired
    public CommentSendController(CommentSendService commentSendService) {
        this.commentSendService = commentSendService;
    }

    @PostMapping("/blog/{id}/comments")
    public ResponseEntity<CommentBlog> registerUser(@RequestBody CommentBlog commentBlog) {
        if (commentBlog.getName() != null && !commentBlog.getName().trim().isEmpty() &&
                commentBlog.getEmail() != null && !commentBlog.getEmail().trim().isEmpty() &&
                commentBlog.getMessage() != null && !commentBlog.getMessage().trim().isEmpty()) {
            commentSendService.save(commentBlog);
            return ResponseEntity.ok(commentBlog);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

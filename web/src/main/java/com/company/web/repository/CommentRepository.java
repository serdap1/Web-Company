package com.company.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.web.entity.CommentBlog;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentBlog, Integer> {

    List<CommentBlog> findAllByBlogId(Integer blogId);
}
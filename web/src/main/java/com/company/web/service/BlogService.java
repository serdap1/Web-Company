package com.company.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.entity.Blog;
import com.company.web.repository.BlogRepository;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public List<Blog> getLatestBlog() {
        return blogRepository.findTop4ByOrderByPostDateDesc();
    }

    public Blog getBlog(Integer id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        return optionalBlog.orElse(null);
    }

	public void addBlog(Blog blog) {
		blogRepository.save(blog);
		
	}
}

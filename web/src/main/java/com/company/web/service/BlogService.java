package com.company.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.web.entity.Blog;
import com.company.web.repository.BlogRepository;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	private final String folder = "/C:/Users/vanan/Documents/GitHub/Web-Company/web/src/main/resources/static/img/";

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

	public void addBlog(Blog blog, MultipartFile file) throws IOException {
		String filePath = "img/" + file.getOriginalFilename();
		blogRepository.save(Blog.builder().author(blog.getAuthor()).detail(blog.getDetail())
				.short_detail(blog.getShort_detail()).title(blog.getTitle()).image(filePath).build());
		file.transferTo(new File(folder + file.getOriginalFilename()));
		System.out.println("post successfully");
	}

}
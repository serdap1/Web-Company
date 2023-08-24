package com.company.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.company.web.entity.Blog;
import com.company.web.entity.CommentBlog;
import com.company.web.entity.User;
import com.company.web.repository.UserRepository;
import com.company.web.service.BlogService;
import com.company.web.service.CommentService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class BlogController {
	@Autowired(required = true)
	private UserRepository userRepository;

	@Autowired
	private BlogService blogService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/blog-login")
	public String getAllBlogLogin(Model model) {
		model.addAttribute("blog", blogService.getAllBlog());
		return "blog-login";
	}

	@GetMapping("/blog")
	public String getAllBlog(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("blog", blogService.getAllBlog());
		return "blog";
	}

	@GetMapping("/blog-details/{id}")
	public String showBlog(@PathVariable Integer id, Model model) {
		Blog blog = blogService.getBlog(id);
		List<CommentBlog> commentBlog = commentService.getAllCommentsByBlog(id);
		if (blog == null) {
			// Xử lý nếu không tìm thấy bài đăng blog với ID tương ứng
			return "error";
		}
		model.addAttribute("blog", blog);
		model.addAttribute("comment", commentBlog);
		return "blog-details";
	}

	@GetMapping("/create-post")
	public String createPost(Model model) {
		Blog blog = new Blog();
		model.addAttribute("blog", blog);
		return "post";
	}

	

	@PostMapping("/post-blog-img")
	public ResponseEntity<String> postBlog(@RequestPart("data") Blog blog,

			@RequestPart("file") MultipartFile file, HttpSession session) throws IOException {
		if (blog.getTitle() != null && blog.getDetail() != null
				&& blog.getShort_detail() != null) {
			User user = (User) session.getAttribute("user");
			blog.setAuthor(user.getUsername());
			blogService.addBlog(blog, file);
			return ResponseEntity.ok().body("post successfully");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@RequestMapping(path = "/login-blog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String blogLogin(Model model, User user, HttpSession session) {

		User userData = userRepository.findByEmail(user.getEmail());
		if (userData != null && userData.getPassword().equals(user.getPassword())) {
			session.setAttribute("user", userData);
			model.addAttribute("user", user);
			model.addAttribute("blog", blogService.getAllBlog());
			return "blog-login";
		} else {
			model.addAttribute("error", "Invalid email or password");
			return "error";
		}

	}
}

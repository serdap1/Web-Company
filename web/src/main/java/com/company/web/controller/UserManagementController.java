package com.company.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.web.dto.UserDto;
import com.company.web.entity.User;
import com.company.web.repository.UserRepository;
import com.company.web.service.UserManagementService;
import com.company.web.service.UserRegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserManagementController {
	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private UserRegistrationService userRegistrationService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/admin/users")
	public String listUsers(Model model) {
		model.addAttribute("users", userManagementService.getAllUsers());
		return "users";
	}

	@PostMapping("/admin/users")
	public String createUsers(Model model) {
		model.addAttribute("users", userManagementService.getAllUsers());
		return "users";
	}

	@GetMapping("/admin/users/new")
	public String newUser(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "create_user";
	}

	@PostMapping("/admin/users/new")
	public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
		if (userRegistrationService.isEmailAvailable(userDto.getEmail())) {
			User user = User.builder().email(userDto.getEmail()).password(userDto.getPassword())
					.username(userDto.getUsername()).address(userDto.getAddress()).mobile(userDto.getMobile())
					.status(userDto.getStatus()).gr_id(userDto.getGr_id()).build();
			userRepository.save(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/users/{id}/delete")
	public String deleteUser(@PathVariable("id") Integer userId) {
		userManagementService.deleteUserById(userId);
		return "redirect:/admin/users"; // Hoặc điều hướng đến trang khác sau khi xóa
	}

}

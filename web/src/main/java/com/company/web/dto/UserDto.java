package com.company.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserDto {

	private String username;
	private String password;
	private String email;
	private String address;
	private String mobile;
	private Integer gr_id;
	private String status;
	public UserDto() {
		this.status = "activated";
	}
	
}

package com.pos.backend.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserDTO {
	
	public UserDTO() {
	}
	
	public UserDTO(String userName,List<String> roles) {
		this.userName = userName;
		this.roles = roles;
	}
	
	private Boolean active = true;

	private String userId;

	private String name;

	private String userName;

	private String password;

	private List<String> roles;
}

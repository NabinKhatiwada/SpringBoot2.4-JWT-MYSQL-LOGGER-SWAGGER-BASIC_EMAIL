package com.pos.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuperAdminDTO {

	private String superAdminId;

	private String name;

	private String userName;

	private String password;

	private Boolean firstTimeLogin;

	private Integer roleId;

	private String accessToken;
	
	private String refreshToken;
}

package com.pos.backend.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import com.pos.backend.utils.StringAttributeConverter;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity(name = "tbl_user")
public class UserDOB extends BaseDOB implements Serializable{

	private static final long serialVersionUID = -8848116804955751536L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "user_name")
	private String userName;
	
	@Convert(converter = StringAttributeConverter.class)
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_time_login")
	private Boolean firstTimeLogin;
	
	@Column(name = "role_id")
	private Integer roleId;
	
}

package com.pos.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseDOB {

	public BaseDOB() {
		this.createdDateTime = this.updatedDateTime = new Date();
		this.active = true;
	}

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 36,name = "id")
	protected String id;

	@Column(name = "created_datetime")
	protected Date createdDateTime;

	@Column(name = "updated_datetime")
	protected Date updatedDateTime;

	@Column(name = "active")
	protected Boolean active;
}

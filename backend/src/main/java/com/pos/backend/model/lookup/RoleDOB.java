package com.pos.backend.model.lookup;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbl_role")
public class RoleDOB extends LookupBaseDOB {
	private String name;
}

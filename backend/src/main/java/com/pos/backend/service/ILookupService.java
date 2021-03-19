package com.pos.backend.service;

import java.util.List;

import com.pos.backend.model.lookup.RoleDOB;

public interface ILookupService {

	List<RoleDOB> getRoles();
	
	RoleDOB getRole(Integer roleId);

}

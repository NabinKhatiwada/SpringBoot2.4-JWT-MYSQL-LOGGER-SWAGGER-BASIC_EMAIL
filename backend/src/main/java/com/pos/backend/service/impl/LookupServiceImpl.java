package com.pos.backend.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.backend.dao.IRoleDao;
import com.pos.backend.model.lookup.RoleDOB;
import com.pos.backend.service.ILookupService;

@Component
public class LookupServiceImpl implements ILookupService {

	@Autowired
	private IRoleDao roleDao;

	private List<RoleDOB> roles;

	@PostConstruct
	public void init() {
		this.initializeData();
	}

	private void initializeData() {
		this.roles = (List<RoleDOB>) roleDao.findAll();
	}

	@Override
	public List<RoleDOB> getRoles() {
		return roles;
	}

	@Override
	public RoleDOB getRole(Integer roleId) {
		RoleDOB roleDOB = null;
		for (RoleDOB role : roles) {
			if (role.getId().equals(roleId)) {
				roleDOB = role;
				break;
			}
		}
		return roleDOB;
	}

}

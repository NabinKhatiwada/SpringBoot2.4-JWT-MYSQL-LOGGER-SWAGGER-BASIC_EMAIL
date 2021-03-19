package com.pos.backend.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.backend.facade.ISuperAdminFacade;
import com.pos.backend.model.dto.SuperAdminDTO;
import com.pos.backend.service.ISuperAdminService;

@Service("superAdminFacade")
public class SuperAdminFacadeImpl implements ISuperAdminFacade{

//	@Autowired
//	private IEmailService emailService;
	
	@Autowired
	private ISuperAdminService superAdminService;
	
	@Override
	public SuperAdminDTO getSuperAdmin(String superAdminId) {
		return this.superAdminService.getSuperAdmin(superAdminId);
	}

	@Override
	public List<SuperAdminDTO> getAllSuperAdmins() {
		return this.superAdminService.getAllSuperAdmins();
	}

	@Override
	public void createSuperAdmin(SuperAdminDTO superAdmin) {
		this.superAdminService.createSuperAdmin(superAdmin);
		
//		this.emailService.sendEmail(superAdmin.getUserName(),
//				"POS - Account Created", 
//				"<h2>Congratulations,your account is successfully created in POS </h2> <br/><br/>User Name: <b>"
//				+ superAdmin.getUserName() + "</b><br/> Password:  <b> "+superAdmin.getPassword());
	}

	@Override
	public void updateSuperAdmin(SuperAdminDTO superAdmin) {
		this.superAdminService.updateSuperAdmin(superAdmin);
	}

	@Override
	public SuperAdminDTO login(SuperAdminDTO superAdminDTO) {
		return this.superAdminService.login(superAdminDTO);
	}

}

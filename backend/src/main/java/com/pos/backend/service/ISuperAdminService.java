package com.pos.backend.service;

import java.util.List;

import com.pos.backend.model.dto.SuperAdminDTO;

public interface ISuperAdminService {
	
	SuperAdminDTO getSuperAdmin(String superAdminId);
	
	List<SuperAdminDTO> getAllSuperAdmins();
	
	void createSuperAdmin(SuperAdminDTO superAdminDTO);
	
	void updateSuperAdmin(SuperAdminDTO superAdminDTO);

	SuperAdminDTO login(SuperAdminDTO superAdminDTO);
}

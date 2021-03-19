package com.pos.backend.facade;

import java.util.List;

import com.pos.backend.model.UserDOB;
import com.pos.backend.model.dto.SuperAdminDTO;

public interface ISuperAdminFacade {
	SuperAdminDTO getSuperAdmin(String superAdminId);

	List<SuperAdminDTO> getAllSuperAdmins();

	void createSuperAdmin(SuperAdminDTO superAdmin);

	void updateSuperAdmin(SuperAdminDTO superAdmin);

	SuperAdminDTO login(SuperAdminDTO superAdminDTO);
}

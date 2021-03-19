package com.pos.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.pos.backend.GlobalResponseHandler.CustomException;
import com.pos.backend.dao.ISuperAdminDao;
import com.pos.backend.model.UserDOB;
import com.pos.backend.model.dto.SuperAdminDTO;
import com.pos.backend.service.ISuperAdminService;

@Service("superAdminService")
@Transactional
public class SuperAdminServiceImpl implements ISuperAdminService {

	@Autowired
	private ISuperAdminDao superAdminDao;

	@Override
	public SuperAdminDTO getSuperAdmin(String superAdminId) {
		Optional<UserDOB> superAdminOptional = this.superAdminDao.findById(superAdminId);
		SuperAdminDTO superAdminDTO = null;
		if (superAdminOptional.isPresent()) {
			superAdminDTO = new SuperAdminDTO();
			superAdminDTO = toSuperAdminDTO(superAdminOptional.get());
		}
		return superAdminDTO;
	}

	@Override
	public List<SuperAdminDTO> getAllSuperAdmins() {
		List<UserDOB> superAdminDBList = (List<UserDOB>) this.superAdminDao.findAll();

		List<SuperAdminDTO> superAdminDTOList = new ArrayList<>();
		for (int i = 0; i < superAdminDBList.size(); i++) {
			UserDOB superAdminDB = superAdminDBList.get(i);
			superAdminDTOList.add(toSuperAdminDTO(superAdminDB));
		}

		return superAdminDTOList;
	}

	@Override
	public void createSuperAdmin(SuperAdminDTO superAdminDTO) {
		Optional<UserDOB> dbSuperAdminOPT = this.superAdminDao.findByUserName(superAdminDTO.getUserName());
		if (dbSuperAdminOPT.isPresent()) {
			throw new CustomException(HttpStatus.ALREADY_REPORTED, "Super Admin Already Exist");
		}
		UserDOB dbSuperAdmin = toSuperAdminDB(superAdminDTO);

		this.superAdminDao.save(dbSuperAdmin);
	}

	@Override
	public void updateSuperAdmin(SuperAdminDTO superAdminDTO) {
		Optional<UserDOB> superAdminDBOPT = this.superAdminDao.findById(superAdminDTO.getSuperAdminId());
		if (!superAdminDBOPT.isPresent()) {
			throw new CustomException(HttpStatus.NOT_FOUND, "Super Admin Not Found");
		}
		this.superAdminDao.save(superAdminDBOPT.get());
	}

	@Override
	public SuperAdminDTO login(SuperAdminDTO superAdminDTO) {
		Optional<UserDOB> superAdminDBOPT = this.superAdminDao.findByUserNameAndPassword(superAdminDTO.getUserName(),
				superAdminDTO.getPassword());

		superAdminDBOPT.orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Super Admin Not Found"));

		return toSuperAdminDTO(superAdminDBOPT.get());

	}

	private SuperAdminDTO toSuperAdminDTO(UserDOB superAdmin) {
		SuperAdminDTO superAdminDTO = new SuperAdminDTO();
		superAdminDTO.setSuperAdminId(superAdmin.getId());
		superAdminDTO.setName(superAdmin.getName());
		superAdminDTO.setPassword(superAdmin.getPassword());
		superAdminDTO.setUserName(superAdmin.getUserName());
		superAdminDTO.setFirstTimeLogin(superAdmin.getFirstTimeLogin());
		superAdminDTO.setRoleId(superAdmin.getRoleId());

		return superAdminDTO;
	}

	private UserDOB toSuperAdminDB(SuperAdminDTO superAdminDTO) {
		UserDOB dbSuperAdmin = new UserDOB();
		superAdminDTO.setSuperAdminId(superAdminDTO.getSuperAdminId());
		dbSuperAdmin.setName(superAdminDTO.getName());
		dbSuperAdmin.setPassword(superAdminDTO.getPassword());
		dbSuperAdmin.setUserName(superAdminDTO.getUserName());
		dbSuperAdmin.setFirstTimeLogin(superAdminDTO.getFirstTimeLogin());
		dbSuperAdmin.setRoleId(superAdminDTO.getRoleId());

		return dbSuperAdmin;
	}

}

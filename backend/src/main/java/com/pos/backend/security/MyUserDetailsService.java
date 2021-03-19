package com.pos.backend.security;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pos.backend.GlobalResponseHandler.CustomException;
import com.pos.backend.dao.IRoleDao;
import com.pos.backend.dao.ISuperAdminDao;
import com.pos.backend.model.UserDOB;
import com.pos.backend.model.dto.UserDTO;
import com.pos.backend.model.lookup.RoleDOB;
import com.pos.backend.service.ILookupService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private ISuperAdminDao superAdminDao;

	@Autowired
	private ILookupService lookupService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDOB> userDOBOPT = this.superAdminDao.findByUserName(username);

		userDOBOPT.orElseThrow(() -> new CustomException(HttpStatus.FORBIDDEN, "Invalid User"));

		UserDOB superAdminDB = userDOBOPT.get();

		UserDTO userDTO = new UserDTO();
		userDTO.setActive(superAdminDB.getActive());
		userDTO.setName(superAdminDB.getName());
		userDTO.setPassword(superAdminDB.getPassword());
		userDTO.setUserId(superAdminDB.getId());
		userDTO.setUserName(superAdminDB.getUserName());

		RoleDOB roleDOB = lookupService.getRole(superAdminDB.getRoleId());
		
		userDTO.setRoles(Arrays.asList(roleDOB.getName()));

		return new MyUserDetails(userDTO);

	}

}

package com.pos.backend.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pos.backend.GlobalResponseHandler.CustomException;
import com.pos.backend.GlobalResponseHandler.POSResponse;
import com.pos.backend.dao.IRoleDao;
import com.pos.backend.dao.ISuperAdminDao;
import com.pos.backend.model.UserDOB;
import com.pos.backend.model.dto.SuperAdminDTO;
import com.pos.backend.model.dto.UserDTO;
import com.pos.backend.model.lookup.RoleDOB;
import com.pos.backend.security.JWTUtils;
import com.pos.backend.security.MyUserDetails;
import com.pos.backend.service.ILookupService;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	private ISuperAdminDao superAdminDao;

	@Autowired
	private ILookupService lookupService;

	@Autowired
	private JWTUtils jwtUtils;

	@GetMapping("getUser")
	@ResponseBody
	public ResponseEntity<POSResponse> getUser() {
		SuperAdminDTO superAdminDTO = new SuperAdminDTO();
		superAdminDTO.setName("Test User");
		return new ResponseEntity<POSResponse>(new POSResponse(superAdminDTO), HttpStatus.OK);
	}

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<POSResponse> login(@RequestBody SuperAdminDTO superAdminDTO) {
		Optional<UserDOB> superAdminDBOPT = this.superAdminDao.findByUserNameAndPassword(superAdminDTO.getUserName(),
				superAdminDTO.getPassword());
		superAdminDBOPT.orElseThrow(() -> new CustomException(HttpStatus.FORBIDDEN, "Invalid User"));

		UserDOB superAdminDB = superAdminDBOPT.get();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(superAdminDB.getUserName());
		
		RoleDOB roleDOB = lookupService.getRole(superAdminDB.getRoleId());

		userDTO.setRoles(Arrays.asList(roleDOB.getName()));

		final UserDetails userDetails = new MyUserDetails(userDTO);

		final String accessToken = jwtUtils.generateToken(userDetails);
		final String refreshToken = jwtUtils.generateRefreshToken(userDetails);

		SuperAdminDTO adminResponse = new SuperAdminDTO();
		adminResponse.setAccessToken(accessToken);
		adminResponse.setRefreshToken(refreshToken);

		return new ResponseEntity<POSResponse>(new POSResponse(adminResponse), HttpStatus.OK);
	}

}

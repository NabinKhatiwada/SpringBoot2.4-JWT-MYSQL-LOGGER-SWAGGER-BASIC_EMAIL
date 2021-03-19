package com.pos.backend.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pos.backend.model.UserDOB;

@Repository("superAdminDao")
public interface ISuperAdminDao extends CrudRepository<UserDOB, String> {
	Optional<UserDOB> findByUserName(String userName);

	Optional<UserDOB> findByUserNameAndPassword(String userName, String password);
}

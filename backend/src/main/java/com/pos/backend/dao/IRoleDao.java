package com.pos.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pos.backend.model.lookup.RoleDOB;

@Repository("roleDao")
public interface IRoleDao extends CrudRepository<RoleDOB,Integer>{
	
}

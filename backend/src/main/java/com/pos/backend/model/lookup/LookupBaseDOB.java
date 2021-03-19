package com.pos.backend.model.lookup;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class LookupBaseDOB {

	@Id
	protected Integer id;

}

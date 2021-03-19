package com.pos.backend.dao;

import java.util.List;

public interface IGenericDao<T> {
	
	 	void save(T object);

		void update(T object);

		T getById(String id);

		List<T> getAll();
}

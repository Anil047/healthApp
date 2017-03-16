package com.drac.service;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<T, ID extends Serializable> {
	void save(T entity);
	T getById(ID id);
	List<T> getAll();
	void delete(ID id);
	
}

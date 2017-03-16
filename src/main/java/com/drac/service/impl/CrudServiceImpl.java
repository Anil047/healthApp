package com.drac.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.drac.service.ICrudService;

public class CrudServiceImpl <T,ID extends Serializable>implements ICrudService<T, ID> {
	
	private CrudRepository<T, ID> crudRepository;
	
	public CrudServiceImpl (CrudRepository<T, ID> crudRepository){
		this.crudRepository = crudRepository;
	}
	
	@Override
	public void save(T entity) {
		crudRepository.save(entity);
		
	}
	
	@Override
	public T getById(ID id) {
		
		return crudRepository.findOne(id);
	}

	@Override
	public List<T> getAll() {
		return (List<T>) crudRepository.findAll();
	}

	@Override
	public void delete(ID id) {
		crudRepository.delete(id);
		
	}

}

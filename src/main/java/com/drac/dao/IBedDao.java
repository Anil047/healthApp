package com.drac.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.drac.model.Bed;

@RepositoryRestResource
public interface IBedDao extends CrudRepository<Bed, Integer> {

}
